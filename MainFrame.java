import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
//import java.sql.DriverManager;

public class MainFrame extends JFrame{

    private ResultPanel ResultBox;
    private BottomToolbar BToolbar;
    //private SelectPanel SelectArea; // used for phase 3
    private SidePanel SidePanel;
    private Statement stmt;

    public MainFrame(){
        super("IMDB Database");

        setLayout(new BorderLayout());

        ResultBox = new ResultPanel();
        BToolbar = new BottomToolbar();
        //SelectArea = new SelectPanel();
        SidePanel = new SidePanel();


        BToolbar.setResultPanel(ResultBox, SidePanel);


        add(ResultBox, BorderLayout.CENTER);
        add(BToolbar, BorderLayout.SOUTH);
        //add(new JScrollPane(SelectArea), BorderLayout.WEST);
        add(new JScrollPane(SidePanel), BorderLayout.WEST);

        ResultBox.setBorder(new EmptyBorder(5, 5, 5, 5));
        //SelectArea.setBorder(new EmptyBorder(2, 2, 2, 2));
        SidePanel.setBorder(new EmptyBorder(2, 2, 2, 2));

        setSize(1100, 900);
        //SelectArea.setMinimumSize(new Dimension(250, 30));
        SidePanel.setMinimumSize(new Dimension(300, 30));
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
