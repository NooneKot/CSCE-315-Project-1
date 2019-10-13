import java.util.ArrayList;
import java.util.Scanner;

import Question1.Actor;
import Question1.Q1;
import org.jgrapht.*;
import org.jgrapht.alg.interfaces.ShortestPathAlgorithm.*;
import org.jgrapht.alg.interfaces.*;
import org.jgrapht.alg.shortestpath.*;
import org.jgrapht.graph.*;


import Question1.jdbcpostgreSQL;

public class Main {
    public static void main(String[] args) {
        /*
        ArrayList<Boolean> display = new ArrayList<Boolean>();
        ArrayList<Boolean> filter = new ArrayList<Boolean>();
        ArrayList<String> filterBy = new ArrayList<String>();
        TitleBasics(display, filter, filterBy);
        Names(display, filter, filterBy);
        Principals(display, filter, filterBy);
        Akas(display, filter, filterBy);
        Crew(display, filter, filterBy);
        Ratings(display, filter, filterBy);
        Episode(display, filter, filterBy);


        Parser parser = new Parser(display, filter, filterBy);
        parser.Select();
        parser.Join();
        System.out.println(parser.getQuery());
        */

        Q1 question = new Q1();
        String actorA = "";
        String actorB = "";
        Scanner scan = new Scanner(System.in);
        boolean actorSelected = false;

        do {
            System.out.print("Please enter a name for Actor A: ");
            if (scan.hasNextLine()) {
                actorA = scan.nextLine();
            }
            actorSelected = question.FindInitialActor(actorA, scan, true);
        }while (!actorSelected);
        System.out.println();

        actorSelected = false;
        do {
            System.out.print("Please enter a name for Actor B: ");
            if (scan.hasNextLine()) {
                actorB = scan.nextLine();
            }
            actorSelected = question.FindInitialActor(actorB, scan, false);
        }while (!actorSelected);
        System.out.println();

        question.CreateGraph("", false);

        System.out.println();
        question.getShortestPath();

        String excludeActor = "";
        String excludedActor = "";
        System.out.print("Would you like to exclude an Actor? (Y or N): ");
        if (scan.hasNextLine()) {
            excludeActor = scan.nextLine();
        }
        if (excludeActor.equals("Y")) {
            System.out.print("Which actor?: ");
            if (scan.hasNextLine()) {
                excludedActor = scan.nextLine();
            }
            question.getGraph().removeVertex(excludedActor);
            question.CreateGraph(excludedActor, true);
            question.getShortestPath();
        }



    }

    /*
    public static void Akas(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // title
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // region
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // language
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // types
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // attributes
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // isOriginalTitle
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void Crew(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // directors
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // writers
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void Episode(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // Season Number
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // Episode Number
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void Names(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // primary name
        display.add(false);
        filter.add(true);
        filterBy.add(null);
        // birth year
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // death year
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // primary profession
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // known for titles
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void Principals(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // category
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // job
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // characters
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void Ratings(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // average ratings
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // num of votes
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void TitleBasics(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // title type
        display.add(true);
        filter.add(false);
        filterBy.add(null);
        // primary title
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // original title
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // is adult
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // start year
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // end year
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // runtime in minutes
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // genres
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }
     */
}
