import java.util.ArrayList;

public class Akas {
    private ArrayList<Boolean> akasDisplay;
    private ArrayList<Boolean> akasFilter;
    private ArrayList<String> akasFilterBy;
    private ArrayList<String> columnNames;
    private int columnOffset;

    public Akas() {

    }

    public Akas(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy, int indexIncrement) {
        Initialize();

        for(int index = 0; index < 6; index++) {
            akasDisplay.add(display.get(index + indexIncrement));
            akasFilter.add(filter.get(index + indexIncrement));
            akasFilterBy.add(filterBy.get(index + indexIncrement));
        }

        columnOffset = 2;
    }

    private void Initialize() {
        akasDisplay = new ArrayList<Boolean>();
        akasFilter = new ArrayList<Boolean>();
        akasFilterBy = new ArrayList<String>();
        columnNames = new ArrayList<String>();

        columnNames.add("titleid");
        columnNames.add("ordering");
        columnNames.add("title");
        columnNames.add("region");
        columnNames.add("language");
        columnNames.add("types");
        columnNames.add("attributes");
        columnNames.add("isorginaltitle");
    }

    public ArrayList<Boolean> getAkasDisplay() {
        return akasDisplay;
    }

    public ArrayList<Boolean> getAkasFilter() {
        return akasFilter;
    }

    public ArrayList<String> getAkasFilterBy() {
        return akasFilterBy;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public int getColumnOffset() {
        return columnOffset;
    }
}
