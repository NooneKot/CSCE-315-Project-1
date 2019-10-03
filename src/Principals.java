import java.util.ArrayList;

public class Principals {

   private ArrayList<Boolean> principalsDisplay;
   private ArrayList<Boolean> principalsFilter;
   private ArrayList<String> principalsFilterBy;
   private ArrayList<String> columnNames;

   public Principals() {

   }

   public Principals(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy, int indexIncrement) {
      Initialize();

      for(int index = 0; index < 4; index++) {
         principalsDisplay.add(display.get(index + indexIncrement));
         principalsFilter.add(filter.get(index + indexIncrement));
         principalsFilterBy.add(filterBy.get(index + indexIncrement));
      }
   }

   private void Initialize() {
      principalsDisplay = new ArrayList<Boolean>();
      principalsFilter = new ArrayList<Boolean>();
      principalsFilterBy = new ArrayList<String>();

      columnNames.add("tconst");
      columnNames.add("ordering");
      columnNames.add("nconst");
      columnNames.add("category");
      columnNames.add("job");
      columnNames.add("characters");
   }
}