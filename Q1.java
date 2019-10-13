package Question1;
import org.jgrapht.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;

import Question1.jdbcpostgreSQL;

import java.util.*;

public class Q1 {
    private Graph<String, RelationshipEdge> graph;
    private Graph<String, DefaultEdge> movieGraph;
    private jdbcpostgreSQL data;
    private ArrayList<ArrayList<String>> result;
    private ArrayList<Actor> Actors;
    private Actor sourceActor;
    private Actor destinationActor;
    private List<Actor> coActors;
    private Queue<Actor> unsettledActors;

    public Q1() {

        graph = new SimpleDirectedGraph<String, RelationshipEdge>(RelationshipEdge.class);
        movieGraph = new SimpleDirectedGraph<String, DefaultEdge>(DefaultEdge.class);
        data = new jdbcpostgreSQL();
        Actors = new ArrayList<Actor>();
        coActors = new ArrayList<Actor>();
        unsettledActors = new LinkedList<>();
        sourceActor = new Actor();
        destinationActor = new Actor();
    }

    public Graph<String, RelationshipEdge> getGraph() {
        return graph;
    }

    public boolean FindInitialActor(String actorName, Scanner scan, boolean source) {
        // Query Database for all actors matching actorName
        String query = "select nconst, knownfortitles from names where (primaryname = '" + actorName +"' and " +
                "primaryprofession like '%actor%') or (primaryname = '" + actorName +"' and " + "primaryprofession like '%actress%')";
        data.ConnectToDatabase();
        data.QueryDatabase(query);
        result = data.getQueryResult();

        if (result.size() == 0) {
            System.out.println("There were no actors matching that name.");
            data.CloseConnection();
            return false;
        }

        // Formatting the KnownForTitles element
        FormatTitlesList();

        // clear the results from the query
        result.clear();

        // Query Database for the names of the movies from the KnownForTitles element since it only lists their tconst
        int movieIndex = 0;
        for (Actor a:Actors) {
            for (String tconst:a.getTconstOfMovies()) {
                query = "select primarytitle from titlebasics where tconst = '" + tconst + "'";
                data.QueryDatabase(query);
                result = data.getQueryResult();
                a.addMovie(result.get(0).get(0));
            }
        }

        data.CloseConnection();

        // If there are mulitple actors with the same name
        if (Actors.size() > 1) {
            int count = 1;
            for (Actor a : Actors) {
                System.out.print(count + ". " + actorName + ", ");
                System.out.print("Movies Known For: ");
                for (String movie : a.getMoviesActedIn()) {
                    System.out.print("{" + movie + "} ");
                }
                count++;
                System.out.println();
            }

            // Select the Actor the user wants
            System.out.print("There were multiple actors/actresses with that name. Please select which one you meant (enter in the number): ");
            int user_input = scan.nextInt();

            if (source) {
                sourceActor = Actors.get(user_input - 1);
                sourceActor.setName(actorName);
                scan.nextLine();
            }
            else {
                destinationActor = Actors.get(user_input - 1);
                destinationActor.setName(actorName);
            }
        }
        else {
            if (source) {
                sourceActor = Actors.get(0);
                sourceActor.setName(actorName);
            }
            else {
                destinationActor = Actors.get(0);
                destinationActor.setName(actorName);
            }
        }

        Actors.clear();
        return true;
    }

    // formatting the knownForTitles by removing the curly braces and adding these tconst to the actors movie titles
    private void FormatTitlesList() {
        int startIndex;
        int endIndex;
        String str = "";
        String temp = "";
        for (ArrayList<String> actor: result) {
            Actor a = new Actor(actor.get(0));
            Actors.add(a);
            // if the actor is known for any movies
            if (actor.get(1) != null) {
                temp = actor.get(1);
                temp = temp.replace("{", "");
                temp = temp.replace("}", "");
                actor.set(1, temp);
                startIndex = 0;
                endIndex = 0;
                for (int index = 0; index < actor.get(1).length(); index++) {
                    if ((actor.get(1).charAt(index) == ',')){
                        endIndex = index;
                        str = actor.get(1).substring(startIndex, endIndex);
                        a.addTconst(str);
                        startIndex = index + 1;
                    }
                    if (index == actor.get(1).length() - 1) {
                        endIndex = index + 1;
                        str = actor.get(1).substring(startIndex, endIndex);
                        a.addTconst(str);
                        startIndex = index + 1;
                    }
                }
            }
        }
    }

    public void CreateGraph(String excludedActor, boolean exclusion) {
        if (!exclusion) {
            unsettledActors.add(sourceActor);
            graph.addVertex(sourceActor.getName());
        }

        while(!unsettledActors.isEmpty()) {
            Actor currentActor = unsettledActors.poll();
            // access database to get list of movies that the current actor has acted in
            QueryForMovies(currentActor.getNconst(), currentActor);
            int movieIndex = 0;
            for (String movie : currentActor.getTconstOfMovies()) {
                // checks to make sure we aren't accessing the database for a movie that we have already accessed before
                if (!movieGraph.containsVertex(movie)) {
                    movieGraph.addVertex(movie);
                    //  access database for coActors and add them to coActors List
                    QueryForCoActors(movie, currentActor);
                    // iterate through coActors and add them to the graph
                    for (Actor actor : coActors) {
                        // check if actor is the actor we are looking for. If true exit out of method
                        if (actor.getNconst().equals(destinationActor.getNconst())) {
                            graph.addVertex(actor.getName());
                            graph.addEdge(currentActor.getName(), actor.getName(), new RelationshipEdge(currentActor.getMoviesActedIn().get(movieIndex)));
                            return;
                        }
                        // checks to make sure the actor isn't the one we excluded
                        if (!actor.getName().equals(excludedActor)) {
                            if (!graph.containsVertex(actor.getName())) {
                                unsettledActors.add(actor);
                                graph.addVertex(actor.getName());
                                graph.addEdge(currentActor.getName(), actor.getName(), new RelationshipEdge(currentActor.getMoviesActedIn().get(movieIndex)));
                            }
                            else {
                                graph.addEdge(currentActor.getName(), actor.getName(), new RelationshipEdge(currentActor.getMoviesActedIn().get(movieIndex)));
                            }
                        }
                    }
                }
                movieIndex++;
            }
            coActors.clear();
        }
    }

    private void QueryForMovies(String nconst, Actor actor) {
        String query = "select titlebasics.tconst, primarytitle from titlebasics join principals on principals.tconst = titlebasics.tconst" +
                " where titletype = 'movie' and nconst = '" + nconst + "' and (category = 'actor' or category = 'actress')";
        data.ConnectToDatabase();
        data.QueryDatabase(query);
        result = data.getQueryResult();
        data.CloseConnection();

        int tconstColumn = 0;
        int titleColumn = 1;
        boolean inActorMovieList = false;
        if (result.size() != 0) {
            // iterates through query result and adds tconst and movie titles to actor's list
            for (ArrayList<String> movieList : result) {
                // checks to make sure movie is not already added to actors movie list
                for (String tconst : actor.getTconstOfMovies()) {
                    if (movieList.get(tconstColumn).equals(tconst)) {
                        inActorMovieList = true;
                        break;
                    }
                }
                if (!inActorMovieList) {
                    actor.addTconst(movieList.get(tconstColumn));
                    actor.addMovie(movieList.get(titleColumn));
                }
                inActorMovieList = false;
            }
        }

        result.clear();
    }

    private void QueryForCoActors(String tconst, Actor currentActor) {
        String query = "select primaryname, names.nconst from names join principals on principals.nconst = names.nconst" +
                " where (category = 'actor' or category = 'actress') and tconst = '" + tconst +"'";
        data.ConnectToDatabase();
        data.QueryDatabase(query);
        result = data.getQueryResult();
        data.CloseConnection();

        int nameColumn = 0;
        int nconstColumn = 1;
        // adds the coActors to our coActors List
        for (ArrayList<String> actor: result) {
            Actor tempActor = new Actor();
            tempActor.setName(actor.get(nameColumn));
            tempActor.setNconst(actor.get(nconstColumn));
            if (!currentActor.getNconst().equals(tempActor.getNconst())) {
                coActors.add(tempActor);
            }
        }

        result.clear();
    }

    public void getShortestPath() {
        System.out.println("Shortest path from " + sourceActor.getName() + " to " + destinationActor.getName());
        DijkstraShortestPath<String, RelationshipEdge> dijkstraAlg = new DijkstraShortestPath<>(graph);
        SingleSourcePaths<String, RelationshipEdge> sourceActorPaths = dijkstraAlg.getPaths(sourceActor.getName());
        System.out.println(sourceActorPaths.getPath(destinationActor.getName()) + "\n");
    }
}
