import java.util.ArrayList;

public class TitleBasics {

   private ArrayList<Boolean> titleBasicsDisplay;
   private ArrayList<Boolean> titleBasicsFilter;
   private ArrayList<String> titleBasicsFilterBy;
   private ArrayList<String> columnNames;

   public TitleBasics() {

   }

   public TitleBasics(ArrayList<Boolean> display, ArrayList<Boolean> filter, ArrayList<String> filterBy, int indexIncrement) {
      Initialize();

      for(int index = 0; index < 8; index++) {
         titleBasicsDisplay.add(display.get(index + indexIncrement));
         titleBasicsFilter.add(filter.get(index + indexIncrement));
         titleBasicsFilterBy.add(filterBy.get(index + indexIncrement));
      }
   }

   private void Initialize() {
      titleBasicsDisplay = new ArrayList<Boolean>();
      titleBasicsFilter = new ArrayList<Boolean>();
      titleBasicsFilterBy = new ArrayList<String>();

      columnNames.add("tconst");
      columnNames.add("titletype");
      columnNames.add("primarytitle");
      columnNames.add("originaltitle");
      columnNames.add("isadult");
      columnNames.add("startyear");
      columnNames.add("endyear");
      columnNames.add("runtimeminutes");
      columnNames.add("genres");
   }
}