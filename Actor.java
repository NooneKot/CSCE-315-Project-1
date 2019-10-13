package Question1;

import java.util.ArrayList;
import java.util.List;

public class Actor {
    private String name;
    private String nconst;
    private List<String> moviesActedIn;
    private List<String> tconstOfMovies;

    public  Actor() {
        name = "";
        nconst = "";
        moviesActedIn = new ArrayList<String>();
        tconstOfMovies = new ArrayList<String>();
    }

    public Actor(String nconst) {
        name = "";
        this.nconst = nconst;
        moviesActedIn = new ArrayList<String>();
        tconstOfMovies = new ArrayList<String>();
    }

    public Actor(String name, String nconst, List<String> moviesActedIn, List<String> tconstOfMovies) {
        this.name = name;
        this.nconst = nconst;
        this.moviesActedIn = moviesActedIn;
        this.tconstOfMovies = tconstOfMovies;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNconst() {
        return nconst;
    }

    public void setNconst(String nconst) {
        this.nconst = nconst;
    }

    public List<String> getMoviesActedIn() {
        return moviesActedIn;
    }

    public void setMoviesActedIn(List<String> moviesActedIn) {
        this.moviesActedIn = moviesActedIn;
    }

    public List<String> getTconstOfMovies() {
        return tconstOfMovies;
    }

    public void setTconstOfMovies(List<String> tconstOfMovies) {
        this.tconstOfMovies = tconstOfMovies;
    }

    public void addTconst(String tconst) { tconstOfMovies.add(tconst); }

    public void addMovie(String movie) { moviesActedIn.add(movie); }


}
