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
    private String solution;
    public String inputName;

    public Trivia(String inputName) {
        this.inputName = inputName;
        data = new Connect();
        this.find_longest();
    }

    public String find_longest() {
        inputName = this.inputName;
        String sqlStatement = "SELECT primarytitle, runtimeminutes FROM titlebasics JOIN principals ON principals.tconst = titlebasics.tconst JOIN names ON principals.nconst = names.nconst WHERE primaryname='";
        sqlStatement += inputName;
        sqlStatement += "' AND titletype='movie';";
        System.out.println(sqlStatement);
        dbSetup my = new dbSetup();
        data.ConnectToDatabase();
        System.out.println("Connected");
        data.QueryDatabase(sqlStatement);
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        result = data.getQueryResult();
        data.CloseConnection();

        System.out.println(result.get(0));
        int longestFilm = 0;
        String filmName = "";

        for (int i = 0; i < result.get(0).size(); i++) {
            if (checkIfInteger(result.get(0).get(i))) {
                if (Integer.parseInt(result.get(0).get(i)) > longestFilm && !(result.get(0).get(i - 1).equals("0"))) {
                    longestFilm = Integer.parseInt(result.get(0).get(i));
                    filmName = result.get(0).get(i - 1);
                    System.out.println(longestFilm);
                }
            }
        }

        if(!(solution.equals(""))) {
            solution = "The longest film " + inputName + " has been in is " + filmName + ", with a runtime of " + longestFilm + " minutes.";
            return solution;
        }
        else
            return null;
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
