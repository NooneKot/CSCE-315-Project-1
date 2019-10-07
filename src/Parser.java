import java.util.ArrayList;

public class Parser {
    private Akas akas;
    private Crew crew;
    private Episode episode;
    private Names names;
    private Principals principals;
    private Ratings ratings;
    private TitleBasics titleBasics;

    // Query to return
    private StringBuffer Query;

    private boolean hasPreviousColumn;
    private boolean multipleTables;
    private boolean fromTableSelected;

    // table listed for the FROM query
    private String accessFrom;

    // list of tables we are accessing from
    private ArrayList<String> tablesToAccessFrom;
    // list of all tables in the database
    private ArrayList<String> tables;

    private int totalColumnsToDisplay;
    private int tableCount;

    // default constructor
    public Parser() {

    }

    // constructor
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
            else if (index <= 17) {
                principals = new Principals(display, filter, filterBy, 15);
            }
            else if (index <= 19) {
                ratings = new Ratings(display, filter, filterBy, 18);
            }
            else {
                titleBasics = new TitleBasics(display, filter, filterBy, 20);
            }
        }

        Query = new StringBuffer(Character.MAX_VALUE);

        hasPreviousColumn = false;
        multipleTables = false;
        fromTableSelected = false;

        accessFrom = "";

        tablesToAccessFrom = new ArrayList<String>();
        tables = new ArrayList<String>();

        totalColumnsToDisplay = 0;
        tableCount = 0;

        InitializeTableList();


        // System.out.println("Finished Running Through Constructor");
    }

    private void InitializeTableList() {
        tables.add("titlebasics");
        tables.add("principals");
        tables.add("names");
        tables.add("akas");
        tables.add("crew");
        tables.add("ratings");
        tables.add("episode");
    }

    public StringBuffer getQuery() {
        return Query;
    }

    // Parses User input to a SELECT and FROM Query
    public void Select() {
        int displayCountFromTable = 0;
        int tableIndex = 0;
        Query.append("SELECT ");

        displayCountFromTable = AppendColumns(titleBasics.getTitleBasicsDisplay(), titleBasics.getColumnNames(), titleBasics.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        displayCountFromTable = AppendColumns(principals.getPrincipalsDisplay(), principals.getColumnNames(), principals.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        displayCountFromTable = AppendColumns(names.getNamesDisplay(), names.getColumnNames(), names.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        displayCountFromTable = AppendColumns(akas.getAkasDisplay(), akas.getColumnNames(), akas.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        displayCountFromTable = AppendColumns(crew.getCrewDisplay(), crew.getColumnNames(), crew.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        displayCountFromTable = AppendColumns(ratings.getRatingsDisplay(), ratings.getColumnNames(), ratings.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        displayCountFromTable = AppendColumns(episode.getEpisodeDisplay(), episode.getColumnNames(), episode.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        Query.append(" FROM " + accessFrom);
    }

    // Performs all the checks for the Select Function
    private void SelectChecks(int columnCount, int tableIndex) {
        CheckToAddTable(columnCount, tables.get(tableIndex));
        if (columnCount > 0) {
            tableCount++;
        }
        totalColumnsToDisplay += columnCount;
        CheckForPreviousColumns(totalColumnsToDisplay);
        if (fromTableSelected == false && columnCount > 0) {
            accessFrom = tables.get(tableIndex);
            fromTableSelected = true;
        }
        if (tableIndex != 0) {
            CheckForMultipleTableAccess();
        }
    }

    // appends the column name to our query
    private int AppendColumns(ArrayList<Boolean> display, ArrayList<String> columns, int indexOffset) {
        int count = 0;
        for (int index = 0; index < display.size(); index++) {
            if (display.get(index) == true) {
                if (hasPreviousColumn) {
                    Query.append(", " + columns.get(index + indexOffset));
                    count++;
                }
                else {
                    Query.append(columns.get(index + indexOffset));
                    count++;
                    hasPreviousColumn = true;
                }
            }
        }

        return count;
    }

    private void CheckToAddTable(int columnCount, String table) {
        if (columnCount != 0) {
            tablesToAccessFrom.add(table);
        }
    }

    private void CheckForPreviousColumns(int columns) {
        if (columns > 0) {
            hasPreviousColumn = true;
        }
    }

    private void CheckForMultipleTableAccess() {
        if (tableCount > 1 && multipleTables == false) {
            multipleTables = true;
        }
    }

    // Parses User Input for the JOIN queries
    public void Join() {
        boolean namesJoined = false;
        if (multipleTables) {
            if (CheckForNamesTable()) {
                if (accessFrom == "principals") {
                    Query.append(" JOIN names ON principal.nconst = names.nconst");
                }
                else {
                    Query.append(" JOIN principal ON principal.tconst = " + accessFrom + ".tconst JOIN names ON principal.nconst = names.nconst");
                }
                namesJoined = true;
            }

            for(int index = 0; index < tablesToAccessFrom.size(); index++) {
                if (tablesToAccessFrom.get(index) != accessFrom) {
                    if (tablesToAccessFrom.get(index) != "names" && tablesToAccessFrom.get(index) != "principals") {
                        Query.append(" JOIN " + tablesToAccessFrom.get(index) + " ON " + tablesToAccessFrom.get(index) + ".tconst = " +
                                accessFrom + ".tconst");
                    }
                    else if (tablesToAccessFrom.get(index) == "principals" && namesJoined == false) {
                        Query.append(" JOIN principal ON principal.tconst = " + accessFrom + ".tconst");
                    }
                }
            }
        }
    }

    private boolean CheckForNamesTable() {
        for (int index = 0; index < tablesToAccessFrom.size(); index++) {
            if (tablesToAccessFrom.get(index) == "names") {
                return true;
            }
        }

        return false;
    }
}
