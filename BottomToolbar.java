import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.io.*;

public class BottomToolbar extends JPanel implements ActionListener {
    private JButton Search;
    private JButton Exit;
    private ResultPanel resultPanel;
    private SidePanel sidePanel;
    //private SelectPanel selectPanel;
    public JCheckBox file;
    public JTextArea filename;


    private String[] Text;
    private Boolean[] Filter;
    private Boolean[] Display;

    public BottomToolbar(){
        file = new JCheckBox("Output to file: ");
        filename = new JTextArea("output.txt", 1, 10);
        Search = new JButton("Search");
        Exit = new JButton("Exit");

        Search.addActionListener(this);
        Exit.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(file);
        add(filename);
        add(Search);
        add(Exit);
    }

    public void setResultPanel(ResultPanel resultPanel, SidePanel sidePanel){
        this.resultPanel = resultPanel;
        //this.selectPanel = selectPanel;
        this.sidePanel = sidePanel;
    }

    @Override
    public void actionPerformed (ActionEvent e){
        JButton clicked = (JButton) e.getSource();
        String output = "";
        if(clicked == Search) {
            if(sidePanel.Q1B.isSelected()){
                Q1 question1 = new Q1();
                String sourceActor = sidePanel.Q1B.sourceActor.getText();
                question1.FindInitialActor(sourceActor, true); // need to modify this method
                String destinationActor = sidePanel.Q1b.destinationActor.getText();
                question1.FindInitialActor(destinationActor, true);
                String excludedActor = sidePanel.Q1b.excludedActor.getText();
                
                question1.CreateGraph(excludedActor);
                output = question1.getShortestPath(); // need to modify return type and have it return string
            }
            else if(sidePanel.Q2B.isSelected()){
                Question2 Q2 = new Question2(sidePanel.Q2P.year1.getText(), sidePanel.Q2P.year2.getText(), sidePanel.Q2P.exclude.getText());
                output = Q2.get_answer();

            }
            else if(sidePanel.Q3B.isSelected()){
                //call question 3 here
            }

            if(!file.isSelected()){
                resultPanel.setResult(output);
            }
            else{
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(filename.getText());
                    byte b[] = output.getBytes();
                    out.write(b);
                    out.close();
                    resultPanel.setResult("Results in file: " + filename.getText() + "\n");
                }
                catch (Exception o){
                    System.out.print("Error opening new file.");
                }
            }

            /*
            // get Query
            Parser p = new Parser(selectPanel.getD(), selectPanel.getF(), selectPanel.getSearch());
            p.Select();

            p.Join();
            p.Where();
            StringBuffer q = p.getQuery();
            String sqlStatement = q.toString();

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
                    if(i != colCount){
                        output += ", ";
                    }
                    else{
                        output += '\n';
                    }
                }
                while (results.next()) {
                    //System.out.println(results.getString("primaryname"));
                    for(int i = 0; i < colCount; i++) {
                        output += results.getString(names[i]);
                        if(i != colCount-1){
                            output += ", ";
                        }
                        else{
                            output += '\n';
                        }
                    }
                    output += "\n";
                }
            } catch (Exception o){
                System.out.print("Error accessing Database.");
            }
            if(!file.isSelected()){
                resultPanel.setResult(output);
            }
            else{
                FileOutputStream out = null;
                try {
                    out = new FileOutputStream(filename.getText());
                    byte b[] = output.getBytes();
                    out.write(b);
                    out.close();
                    resultPanel.setResult("Results in file: " + filename.getText() + "\n");
                }
                catch (Exception o){
                    System.out.print("Error opening new file.");
                }
            }

            //closing the connection
            try {
                conn.close();
            } catch(Exception o) {
                System.out.print("connection closed\n");
            }//end try catch
            */
        }
        if(clicked == Exit){
            System.exit(0);
        }
    }
}
