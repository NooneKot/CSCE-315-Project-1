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
    private boolean filterMultipleTables;

    // table listed for the FROM query
    private String accessFrom;

    // list of tables we are accessing from
    private ArrayList<String> tablesToAccessFrom;
    // list of all tables in the database
    private ArrayList<String> tables;
    // contains the indices for all the columns to be filtered from their respective tables
    private ArrayList<Integer> filteredColumnsIndexes;

    private int totalColumnsToDisplay;

    // default constructor
    public Parser() {

    }

    // constructor
    public Parser(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy) {

        for(int index = 0; index < display.size(); index++) {
            if(index <= 7) {
                titleBasics = new TitleBasics(display, filter, filterBy, 0);
            }
            else if (index <= 12) {
                names = new Names(display, filter, filterBy, 8);
            }
            else if (index <= 15) {
                principals = new Principals(display, filter, filterBy, 13);
            }
            else if (index <= 21) {
                akas = new Akas(display, filter, filterBy, 16);
            }
            else if (index <= 23) {
                crew = new Crew(display, filter, filterBy, 22);
            }
            else if (index <= 25) {
                ratings = new Ratings(display, filter, filterBy, 24);
            }
            else {
                episode = new Episode(display, filter, filterBy, 26);
            }
        }

        Query = new StringBuffer(Character.MAX_VALUE);

        hasPreviousColumn = false;
        multipleTables = false;
        fromTableSelected = false;
        filterMultipleTables = false;

        accessFrom = "";

        tablesToAccessFrom = new ArrayList<String>();
        tables = new ArrayList<String>();
        filteredColumnsIndexes = new ArrayList<Integer>();

        totalColumnsToDisplay = 0;

        InitializeTableList();
    }

    private void InitializeTableList() {
        tables.add("titlebasics");
        tables.add("names");
        tables.add("principals");
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

        displayCountFromTable = AppendColumns(names.getNamesDisplay(), names.getColumnNames(), names.getColumnOffset());
        SelectChecks(displayCountFromTable, tableIndex);
        tableIndex++;

        displayCountFromTable = AppendColumns(principals.getPrincipalsDisplay(), principals.getColumnNames(), principals.getColumnOffset());
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

    // Performs all the checks for the Select Function
    private void SelectChecks(int columnCount, int tableIndex) {
        CheckToAddTable(columnCount, tables.get(tableIndex));
        totalColumnsToDisplay += columnCount;
        CheckForPreviousColumns(totalColumnsToDisplay);
        if (fromTableSelected == false && columnCount > 0) {
            accessFrom = tables.get(tableIndex);
            fromTableSelected = true;
        }
    }

    private void CheckForPreviousColumns(int columns) {
        if (columns > 0) {
            hasPreviousColumn = true;
        }
    }

    // Parses User Input for the JOIN queries
    public void Join() {
        boolean namesJoined = false;
        CheckForFilteringMultipleTables();
        CheckForMultipleTableAccess();
        if (multipleTables) {
            if (CheckForNamesTable()) {
                if (accessFrom == "principals") {
                    Query.append(" JOIN names ON principals.nconst = names.nconst");
                }
                else {
                    Query.append(" JOIN principals ON principals.tconst = " + accessFrom + ".tconst JOIN names ON principals.nconst = names.nconst");
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
                        Query.append(" JOIN principals ON principals.tconst = " + accessFrom + ".tconst");
                    }
                }
            }
        }
    }

    private void CheckForFilteringMultipleTables() {
        int filterCount;
        int tableIndex = 0;

        filterCount = CheckToFilterColumns(titleBasics.getTitleBasicsFilter(), titleBasics.getTitleBasicsFilterBy());
        CheckToAddTable(filterCount, tables.get(tableIndex));
        tableIndex++;

        filterCount = CheckToFilterColumns(names.getNamesFilter(), names.getNamesFilterBy());
        CheckToAddTable(filterCount, tables.get(tableIndex));
        tableIndex++;

        filterCount = CheckToFilterColumns(principals.getPrincipalsFilter(), principals.getPrincipalsFilterBy());
        CheckToAddTable(filterCount, tables.get(tableIndex));
        tableIndex++;

        filterCount = CheckToFilterColumns(akas.getAkasFilter(), akas.getAkasFilterBy());
        CheckToAddTable(filterCount, tables.get(tableIndex));
        tableIndex++;

        filterCount = CheckToFilterColumns(crew.getCrewFilter(), crew.getCrewFilterBy());
        CheckToAddTable(filterCount, tables.get(tableIndex));
        tableIndex++;

        filterCount = CheckToFilterColumns(ratings.getRatingsFilter(), ratings.getRatingsFilterBy());
        CheckToAddTable(filterCount, tables.get(tableIndex));
        tableIndex++;

        filterCount = CheckToFilterColumns(episode.getEpisodeFilter(), episode.getEpisodeFilterBy());
        CheckToAddTable(filterCount, tables.get(tableIndex));
        tableIndex++;
    }

    private int CheckToFilterColumns(ArrayList<Boolean> filter, ArrayList<String> filterBy) {
        int columnCount = 0;
        for (int index = 0; index < filter.size(); index++) {
            if (filter.get(index)) {
                columnCount++;
                filteredColumnsIndexes.add(index);
            }
        }
        return columnCount;
    }

    private boolean CheckForNamesTable() {
        for (int index = 0; index < tablesToAccessFrom.size(); index++) {
            if (tablesToAccessFrom.get(index) == "names") {
                return true;
            }
        }

        return false;
    }

    private void CheckToAddTable(int columnCount, String table) {
        if (columnCount != 0 && !tablesToAccessFrom.contains(table)) {
            tablesToAccessFrom.add(table);
        }
    }

    private void CheckForMultipleTableAccess() {
        if (tablesToAccessFrom.size() > 1 && multipleTables == false) {
            multipleTables = true;
        }
    }
}
