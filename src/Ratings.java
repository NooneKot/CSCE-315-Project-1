import java.util.ArrayList;

public class Ratings {
    private ArrayList<Boolean> ratingsDisplay;
    private ArrayList<Boolean> ratingsFilter;
    private ArrayList<String> ratingsFilterBy;
    private ArrayList<String> columnNames;
    private int columnOffset;

    public Ratings() {

    }

    public Ratings(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy, int indexIncrement) {
        Initialize();

        for(int index = 0; index < 2; index++) {
            ratingsDisplay.add(display.get(index + indexIncrement));
            ratingsFilter.add(filter.get(index + indexIncrement));
            ratingsFilterBy.add(filterBy.get(index + indexIncrement));
        }

        columnOffset = 1;
    }

    private void Initialize() {
        ratingsDisplay = new ArrayList<Boolean>();
        ratingsFilter = new ArrayList<Boolean>();
        ratingsFilterBy = new ArrayList<String>();
        columnNames = new ArrayList<String>();

        columnNames.add("tconst");
        columnNames.add("averagerating");
        columnNames.add("numvotes");
    }

    public ArrayList<Boolean> getRatingsDisplay() {
        return ratingsDisplay;
    }

    public ArrayList<Boolean> getRatingsFilter() {
        return ratingsFilter;
    }

    public ArrayList<String> getRatingsFilterBy() {
        return ratingsFilterBy;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public int getColumnOffset() {
        return columnOffset;
    }
}
