import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Boolean> display = new ArrayList<Boolean>();
        ArrayList<Boolean> filter = new ArrayList<Boolean>();
        ArrayList<String> filterBy = new ArrayList<String>();
        Akas(display, filter, filterBy);
        Crew(display, filter, filterBy);
        Episode(display, filter, filterBy);
        Names(display, filter, filterBy);
        Principals(display, filter, filterBy);
        Ratings(display, filter, filterBy);
        TitleBasics(display, filter, filterBy);

        Parser parser = new Parser(display, filter, filterBy);
        parser.Select();
        parser.Join();
        System.out.println(parser.getQuery());
    }

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
        display.add(true);
        filter.add(false);
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
        display.add(true);
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
        display.add(true);
        filter.add(false);
        filterBy.add(null);
        // characters
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void Ratings(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // average ratings
        display.add(true);
        filter.add(false);
        filterBy.add(null);
        // num of votes
        display.add(false);
        filter.add(false);
        filterBy.add(null);
    }

    public static void TitleBasics(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        // title type
        display.add(false);
        filter.add(false);
        filterBy.add(null);
        // primary title
        display.add(true);
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
}
