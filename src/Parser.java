import java.util.ArrayList;

public class Parser {

    // lists for whether to display the column
    private ArrayList<Boolean> akasDisplay;
    private ArrayList<Boolean> crewDisplay;
    private ArrayList<Boolean> episodeDisplay;
    private ArrayList<Boolean> namesDisplay;
    private ArrayList<Boolean> principalsDisplay;
    private ArrayList<Boolean> ratingsDisplay;
    private ArrayList<Boolean> titleBasicsDisplay;

    // lists for whether to filter by that column
    private ArrayList<Boolean> akasFilter;
    private ArrayList<Boolean> crewFilter;
    private ArrayList<Boolean> episodeFilter;
    private ArrayList<Boolean> namesFilter;
    private ArrayList<Boolean> principalsFilter;
    private ArrayList<Boolean> ratingsFilter;
    private ArrayList<Boolean> titleBasicsFilter;

    // lists containing what to filter by for a column
    private ArrayList<String> akasFilterBy;
    private ArrayList<String> crewFilterBy;
    private ArrayList<String> episodeFilterBy;
    private ArrayList<String> namesFilterBy;
    private ArrayList<String> principalsFilterBy;
    private ArrayList<String> ratingsFilterBy;
    private ArrayList<String> titleBasicsFilterBy;

    // list containing the names of the columns
    private ArrayList<String> columnNames;
    // list containing the names of the tables
    private ArrayList<String> tableNames;
    // Query to return
    String Query;

    public Parser() {

    }

    public Parser(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        Initialize();

        for(int index = 0; index < display.size(); index++) {
            if(index <= 5) {
                akasDisplay.add(display.get(index));
                akasFilter.add(filter.get(index));
                akasFilterBy.add(filterBy.get(index));
            }
            else if (index <= 13) {
                titleBasicsDisplay.add(display.get(index));
                titleBasicsFilter.add(filter.get(index));
                titleBasicsFilterBy.add(filterBy.get(index));
            }
            else if (index <= 15) {
                crewDisplay.add(display.get(index));
                crewFilter.add(filter.get(index));
                crewFilterBy.add(filterBy.get(index));
            }
            else if (index <= 17) {
                episodeDisplay.add(display.get(index));
                episodeFilter.add(filter.get(index));
                episodeFilterBy.add(filterBy.get(index));
            }
            else if (index <= 20) {
                principalsDisplay.add(display.get(index));
                principalsFilter.add(filter.get(index));
                principalsFilterBy.add(filterBy.get(index));
            }
            else if (index <= 22) {
                ratingsDisplay.add(display.get(index));
                ratingsFilter.add(filter.get(index));
                ratingsFilterBy.add(filterBy.get(index));
            }
            else {
                namesDisplay.add(display.get(index));
                namesFilter.add(filter.get(index));
                namesFilterBy.add(filterBy.get(index));
            }
        }

        Query = "";
        tableNames = new ArrayList<String>();
        columnNames = new ArrayList<String>();
    }

    private void Initialize() {
        akasDisplay = new ArrayList<Boolean>();
        crewDisplay = new ArrayList<Boolean>();
        episodeDisplay = new ArrayList<Boolean>();
        namesDisplay = new ArrayList<Boolean>();
        principalsDisplay = new ArrayList<Boolean>();
        ratingsDisplay = new ArrayList<Boolean>();
        titleBasicsDisplay = new ArrayList<Boolean>();

        akasFilter = new ArrayList<Boolean>();
        crewFilter = new ArrayList<Boolean>();
        episodeFilter = new ArrayList<Boolean>();
        namesFilter = new ArrayList<Boolean>();
        principalsFilter = new ArrayList<Boolean>();
        ratingsFilter = new ArrayList<Boolean>();
        titleBasicsFilter = new ArrayList<Boolean>();

        akasFilterBy = new ArrayList<String>();
        crewFilterBy = new ArrayList<String>();
        episodeFilterBy = new ArrayList<String>();
        namesFilterBy = new ArrayList<String>();
        principalsFilterBy = new ArrayList<String>();
        ratingsFilterBy = new ArrayList<String>();
        titleBasicsFilterBy = new ArrayList<String>();
    }
}