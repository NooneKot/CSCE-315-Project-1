package Question1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class jdbcpostgreSQL {
    private Connection conn;
    private ArrayList<ArrayList<String>> queryResult;

    public jdbcpostgreSQL() {

        conn = null;
        queryResult = new ArrayList<ArrayList<String>>();
    }

    public ArrayList<ArrayList<String>> getQueryResult() {
        return queryResult;
    }

    public void ConnectToDatabase() {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://db-315.cse.tamu.edu/trkeller17_906",
                    dbSetup.user, dbSetup.pswd);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
            System.exit(0);
        }//end try catch
        //System.out.println("Opened database successfully");
    }

    public void QueryDatabase(String query) {
        String output = "";
        queryResult.clear();
        try{
            //create a statement object
            Statement stmt = conn.createStatement();
            //create an SQL statement
            String sqlStatement = query;
            //send statement to DBMS
            ResultSet result = stmt.executeQuery(sqlStatement);

            //OUTPUT
            ResultSetMetaData metaData = result.getMetaData();
            int colCount = metaData.getColumnCount();
            String[] names = new String[colCount];

            for (int i =1; i<= colCount; i++){
                String colName = metaData.getColumnName(i);
                //output += colName;
                names[i-1] = colName;
                if(i != colCount){
                   // output += ", ";
                }
                else{
                    //output += '\n';
                }
            }

            int index = 0;
            while (result.next()) {
                ArrayList<String> tempArray = new ArrayList<String>();
                for(int i = 0; i < colCount; i++) {
                    //output += result.getString(names[i]);
                    //System.out.println(result.getString(names[i]));
                    tempArray.add(result.getString(names[i]));
                    if(i != colCount-1){
                        //output += ", ";
                    }
                    else{
                       //output += '\n';
                    }

                }
                queryResult.add(tempArray);
                //output += "\n";
                index++;
            }
            //System.out.println(output);
        } catch (Exception e){
            System.out.println("Error accessing Database.");
        }

        //return output;
    }

    public void CloseConnection() {
        try {
            conn.close();
            //System.out.println("Connection Closed.");
        } catch(Exception e) {
            System.out.println("Connection NOT Closed.");
        }
    }
}
