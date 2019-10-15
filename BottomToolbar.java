import javafx.scene.control.cell.TreeItemPropertyValueFactory;

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
                Question1.Q1 question1 = new Question1.Q1();
                String sourceActor = sidePanel.Q1P.sourceActor.getText();
                //question1.FindInitialActor(sourceActor, true); // need to modify this method
                String destinationActor = sidePanel.Q1P.destinationActor.getText();
                //question1.FindInitialActor(destinationActor, true);
                String excludedActor = sidePanel.Q1P.excludedActor.getText();
                
                question1.CreateGraph(excludedActor, true);
                /*
                if(question1.checkForMultipleActors()){
                    JDialog options = new JDialog();
                    for(int i=0; i<question1.returnActors().size(); i++){
                        options.
                    }
                }
                 */
                output = question1.getShortestPath(); // need to modify return type and have it return string
            }
            else if(sidePanel.Q2B.isSelected()){
                Question2 Q2 = new Question2(sidePanel.Q2P.year1.getText(), sidePanel.Q2P.year2.getText(), sidePanel.Q2P.exclude.getText());
                output = Q2.get_answer();

            }
            else if(sidePanel.Q3B.isSelected()){
                Trivia Q3 = new Trivia(sidePanel.Q3P.name.getText());
                output = Q3.find_longest();
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
        }
        if(clicked == Exit){
            System.exit(0);
        }
    }
}
