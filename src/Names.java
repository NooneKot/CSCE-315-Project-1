import java.util.ArrayList;

public class Names {

   private ArrayList<Boolean> namesDisplay;
   private ArrayList<Boolean> namesFilter;
   private ArrayList<String> namesFilterBy;
   private ArrayList<String> columnNames;

   public Names() {

   }

   public Names(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy, int indexIncrement) {
      Initialize();

      for(int index = 0; index < 5; index++) {
         namesDisplay.add(display.get(index + indexIncrement));
         namesFilter.add(filter.get(index + indexIncrement));
         namesFilterBy.add(filterBy.get(index + indexIncrement));
      }
   }

   private void Initialize() {
      namesDisplay = new ArrayList<Boolean>();
      namesFilter = new ArrayList<Boolean>();
      namesFilterBy = new ArrayList<String>();

      columnNames.add("nconst");
      columnNames.add("primaryname");
      columnNames.add("birthyear");
      columnNames.add("deathyear");
      columnNames.add("primaryprofessions");
      columnNames.add("knownfortitles");
   }
}