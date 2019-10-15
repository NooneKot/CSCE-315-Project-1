
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Question2 { //create this class in gui
    private Connect data;
    private String year1;
    private String year2;
    private ArrayList<String> exclude;
    private Boolean exFlag; //flag to set if excludes were used
    private int int_year1;
    private int int_year2;
    public Question2(String year1, String year2, String exclude){
       this.year1 = year1;
       this.year2 = year2;
       int_year1 = Integer.parseInt(year1);
       int_year2 = Integer.parseInt(year2);
       if(exclude != null && exclude != "") {
           this.exclude = format_to_list(exclude);
           exFlag = true;
       }
       else {
           this.exclude = new ArrayList<String>();
           exFlag = false;
       }
       data = new Connect();
    }
    //main method
    public String get_answer (){ //call this function in gui
        ArrayList<String> names1 = get_year_names(year1);
        ArrayList<String> names2 = get_year_names(year2);
        String result = null;

        for(int i = 0; i < names1.size(); i++){
            if(names2.contains(names1.get(i)) && !exclude.contains(names1.get(i))){ //check if person has movies in both start and end years
                result = check_years(names1.get(i));
            }
            if(result != null){
                return names1.get(i) + "\n" + "Movies:\n" + result;
            }
        }

        return "No Result";
    }

    //query the database for people who have work in a given year
    private ArrayList<String> get_year_names(String year){
        String query  = " SELECT DISTINCT primaryname FROM actor_titles WHERE startyear = " + year + " or endyear = " + year + " ORDER BY primaryname ASC";
        data.ConnectToDatabase();
        data.QueryDatabase(query);
        ArrayList result = data.getQueryResult().get(0); //get only 1 arraylist
        data.CloseConnection();
        return result;
    }

    private String check_years(String name){
        String query =  "SELECT primarytitle, startyear FROM actor_titles WHERE primaryname = '" + name + "' ORDER BY startyear ASC";
        data.ConnectToDatabase();
        data.QueryDatabase(query);
        ArrayList<ArrayList<String>> result = data.getQueryResult();
        ArrayList<Integer> indexes = new ArrayList<Integer>(); //stores indexes of relevant results
        boolean[] check = new boolean[(int_year2 - int_year1)+1]; //holds a true value if a year was found
        Arrays.fill(check, false);

        int curnum = 0;
        for(int i = 0; i < result.get(1).size(); i++){
            if(result.get(1).get(i) != null) {
                curnum = Integer.parseInt(result.get(1).get(i));
                if (curnum >= int_year1 && curnum <= int_year2) {
                    check[curnum - int_year1] = true;
                    indexes.add(i);
                }
            }
        }
        if(areAllTrue(check)){
            String output = "";
            for(int i = 0; i < indexes.size(); i++){
                output += result.get(0).get(indexes.get(i)) + ", " + result.get(1).get(indexes.get(i)) + "\n";
            }
            return output;
        }
        else{
            return null; //result is null if the person dose not have a movie in all years
        }


    }

    private ArrayList<String> format_to_list(String string){ //changes string input into arraylist
        List<String> list = Arrays.asList(string.split("\\s*,\\s*"));
        ArrayList<String> newArrayList = new ArrayList<String>(list);

        return newArrayList;
    }

    public static boolean areAllTrue(boolean[] array) //checks if all bools in an array are true
    {
        for(boolean b : array) if(!b) return false;
        return true;
    }
}
