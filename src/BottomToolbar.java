import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;

public class BottomToolbar extends JPanel implements ActionListener {
    private JButton Search;
    private JButton Exit;
    private ResultPanel resultPanel;
    private SelectPanel selectPanel;


    private String[] Text;
    private Boolean[] Filter;
    private Boolean[] Display;

    public BottomToolbar(){
        Search = new JButton("Search");
        Exit = new JButton("Exit");

        Search.addActionListener(this);
        Exit.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(Search);
        add(Exit);
    }

    public void setResultPanel(ResultPanel resultPanel, SelectPanel selectPanel){
        this.resultPanel = resultPanel;
        this.selectPanel = selectPanel;
    }

    @Override
    public void actionPerformed (ActionEvent e){
        JButton clicked = (JButton) e.getSource();
        if(clicked == Search) {

            // get Query
            Parser p = new Parser(selectPanel.getD(), selectPanel.getF(), selectPanel.getSearch());
            p.Select();

            p.Join();
            StringBuffer q = p.getQuery();
            String sqlStatement = q.toString() + " LIMIT 30";

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
            resultPanel.setResult(output);
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
}
