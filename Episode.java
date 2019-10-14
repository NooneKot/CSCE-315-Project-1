import java.util.ArrayList;

public class Episode {
    private ArrayList<Boolean> episodeDisplay;
    private ArrayList<Boolean> episodeFilter;
    private ArrayList<String> episodeFilterBy;
    private ArrayList<String> columnNames;
    private int columnOffset;

    public Episode() {

    }

    public Episode(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy, int indexIncrement) {
        Initialize();

        for(int index = 0; index < 2; index++) {
            episodeDisplay.add(display.get(index + indexIncrement));
            episodeFilter.add(filter.get(index + indexIncrement));
            episodeFilterBy.add(filterBy.get(index + indexIncrement));
        }

        columnOffset = 2;
    }

    private void Initialize() {
        episodeDisplay = new ArrayList<Boolean>();
        episodeFilter = new ArrayList<Boolean>();
        episodeFilterBy = new ArrayList<String>();
        columnNames = new ArrayList<String>();

        columnNames.add("tconst");
        columnNames.add("parentconst");
        columnNames.add("seasonnumber");
        columnNames.add("episodenumber");
    }

    public ArrayList<Boolean> getEpisodeDisplay() {
        return episodeDisplay;
    }

    public ArrayList<Boolean> getEpisodeFilter() {
        return episodeFilter;
    }

    public ArrayList<String> getEpisodeFilterBy() {
        return episodeFilterBy;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public int getColumnOffset() {
        return columnOffset;
    }
}
