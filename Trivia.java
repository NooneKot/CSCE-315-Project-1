import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Trivia extends JFrame implements ActionListener{
    private JPanel panel1;
    private JButton Go;
    private JButton Exit;
    private JTextField field;

    public Trivia(){
        super("Trivia Question");
        Go = new JButton("Go");
        Go.addActionListener(this);
        field = new JTextField(25);
        field.addActionListener(this);

        //setLayout(new FlowLayout(FlowLayout.CENTER));
        panel1 = new JPanel();
        //panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextArea text = new JTextArea("What is the longest film ");
        JTextArea text2 = new JTextArea(" has been in?");
        panel1.add(text);
        panel1.add(field);
        panel1.add(text2);
        add(panel1);
        add(Go, BorderLayout.EAST);
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    //@Override
    public void actionPerformed (ActionEvent e){
        JButton clicked = (JButton) e.getSource();
        if(clicked == Go) {
            String inputName = field.getText();
            String sqlStatement = "SELECT primarytitle, runtimeminutes FROM titlebasics JOIN principals ON principals.tconst = titlebasics.tconst JOIN names ON principals.nconst = names.nconst WHERE primaryname='";
            sqlStatement += inputName;
            sqlStatement += "' AND titletype='movie';";
            dbSetup my = new dbSetup();
            //Building the connection
            Connection conn = null;
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/trkeller17_906",
                        my.user, my.pswd);
            } catch (Exception o) {
                o.printStackTrace();
                System.err.println(o.getClass().getName()+": "+o.getMessage());
                System.exit(0);
            }//end try catch
            String output = "";
            try{
                //create a statement object
                Statement stmt = conn.createStatement();
                System.out.print(sqlStatement + "\n");
                //send statement to DBMS
                ResultSet results = stmt.executeQuery(sqlStatement);
                //OUTPUT
                //System.out.println("______________________________________");
                ResultSetMetaData metaData = results.getMetaData();
                int colCount = metaData.getColumnCount();
                String[] names = new String[colCount];
                for (int i =1; i<= colCount; i++){
                    String colName = metaData.getColumnName(i);
                    output += colName;
                    names[i-1] = colName;
                    output += ",";
                }

                while (results.next()) {
                    for(int i = 0; i < colCount; i++) {
                        output += results.getString(names[i]);
                        output += ",";
                    }
                }

                ArrayList<String> tconstStmt = new ArrayList<String>(Arrays.asList(output.split(",")));
                System.out.println(tconstStmt);

                int longestFilm = 0;
                String filmName = "";

                for(int i=0; i<tconstStmt.size(); i++){
                    if(tconstStmt.get(i).equals("null") || tconstStmt.get(i) == null){
                        tconstStmt.set(i, "0");
                    }
                }
                //System.out.println(tconstStmt);

                for(int i=0; i<tconstStmt.size(); i++){
                    if(checkIfInteger(tconstStmt.get(i))){
                        if(Integer.parseInt(tconstStmt.get(i)) > longestFilm && !(tconstStmt.get(i-1).equals("0"))){
                            longestFilm = Integer.parseInt(tconstStmt.get(i));
                            filmName = tconstStmt.get(i-1);
                        }
                    }
                }

                String solution = "The longest film " + inputName + " has been in is " + filmName + ", with a runtime of " + longestFilm + " minutes.";
                JTextArea solArea = new JTextArea(solution);
                //System.out.println("The longest film " + inputName + " has been in is " + filmName + ", with a runtime of " + longestFilm + " minutes.");
                panel1.add(solArea);
                panel1.revalidate();
                panel1.repaint();

            } catch (Exception o){
                System.out.print("Error accessing Database.");
            }


            //closing the connection
            try {
                conn.close();
            } catch(Exception o) {
                System.out.print("connection closed\n");
            }//end try catch

        }
        if(clicked == Exit){
            System.exit(0);
        }
    }

    private Boolean checkIfInteger(String s) {
        try {
            // checking valid integer using parseInt() method
            Integer.parseInt(s);
            return true;
        }
        catch (NumberFormatException e) {
            return false;
        }
    }
}
