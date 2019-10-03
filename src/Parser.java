import java.util.ArrayList;

public class Parser {

    Akas akas;
    Crew crew;
    Episode episode;
    Names names;
    Principals principals;
    Ratings ratings;
    TitleBasics titleBasics;

    // Query to return
    String Query;

    public Parser() {

    }

    public Parser(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {

        for(int index = 0; index < display.size(); index++) {
            if(index <= 5) {
                akas = new Akas(display, filter, filterBy, 0);
            }
            else if (index <= 7) {
                crew = new Crew(display, filter, filterBy, 6);
            }
            else if (index <= 9) {
                episode = new Episode(display, filter, filterBy, 8);
            }
            else if (index <= 14) {
                names = new Names(display, filter, filterBy, 10);
            }
            else if (index <= 18) {
                principals = new Principals(display, filter, filterBy, 15);
            }
            else if (index <= 20) {
                ratings = new Ratings(display, filter, filterBy, 19);
            }
            else {
                titleBasics = new TitleBasics(display, filter, filterBy, 21);
            }
        }

        Query = "";
    }
}