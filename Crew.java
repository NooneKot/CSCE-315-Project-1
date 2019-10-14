import java.util.ArrayList;

public class Crew {
    private ArrayList<Boolean> crewDisplay;
    private ArrayList<Boolean> crewFilter;
    private ArrayList<String> crewFilterBy;
    private ArrayList<String> columnNames;
    private int columnOffset;

    public Crew() {

    }

    public Crew(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy, int indexIncrement) {
        Initialize();

        for(int index = 0; index < 2; index++) {
            crewDisplay.add(display.get(index + indexIncrement));
            crewFilter.add(filter.get(index + indexIncrement));
            crewFilterBy.add(filterBy.get(index + indexIncrement));
        }

        columnOffset = 1;
    }

    private void Initialize() {
        crewDisplay = new ArrayList<Boolean>();
        crewFilter = new ArrayList<Boolean>();
        crewFilterBy = new ArrayList<String>();
        columnNames = new ArrayList<String>();

        columnNames.add("tconst");
        columnNames.add("directors");
        columnNames.add("writers");
    }

    public ArrayList<Boolean> getCrewDisplay() {
        return crewDisplay;
    }

    public ArrayList<Boolean> getCrewFilter() {
        return crewFilter;
    }

    public ArrayList<String> getCrewFilterBy() {
        return crewFilterBy;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public int getColumnOffset() {
        return columnOffset;
    }
}
