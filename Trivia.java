import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Trivia {
    private Connect data;
    private Connect data2;
    public String inputName;

    public Trivia(String inputName) {
        this.inputName = inputName;
        //data = new Connect();
        //data2 = new Connect();
        this.find_longest();
    }

    public String find_longest() {
        inputName = this.inputName;
        data = new Connect();
        data2 = new Connect();
        String sqlStatement = "SELECT runtimeminutes FROM titlebasics JOIN principals ON principals.tconst = titlebasics.tconst JOIN names ON principals.nconst = names.nconst WHERE primaryname='";
        String sqlStatement2 = "SELECT primarytitle FROM titlebasics JOIN principals ON principals.tconst = titlebasics.tconst JOIN names ON principals.nconst = names.nconst WHERE primaryname='";
        sqlStatement += inputName;
        sqlStatement += "' AND titletype='movie';";

        sqlStatement2 += inputName;
        sqlStatement2 += "' AND titletype='movie';";

        data.ConnectToDatabase();
        data.QueryDatabase(sqlStatement);
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result = data.getQueryResult();
        data.CloseConnection();

        data2.ConnectToDatabase();
        data2.QueryDatabase(sqlStatement2);
        ArrayList<ArrayList<String>> result2 = new ArrayList<ArrayList<String>>();
        result2 = data2.getQueryResult();
        data2.CloseConnection();

        System.out.println(result.get(0));
        System.out.println(result2.get(0));

        int longestFilm = 0;
        String filmName = "";

        for (int i = 0; i < result.get(0).size(); i++) {
            if (checkIfInteger(result.get(0).get(i))) {
                if (Integer.parseInt(result.get(0).get(i)) > longestFilm) {
                    longestFilm = Integer.parseInt(result.get(0).get(i));
                    filmName = result2.get(0).get(i);

                }
            }
        }

        String solution = "The longest film " + inputName + " has been in is " + filmName + ", with a runtime of " + longestFilm + " minutes.";
        return solution;
    }

    private Boolean checkIfInteger(String s){
        try {
            // checking valid integer using parseInt() method
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
