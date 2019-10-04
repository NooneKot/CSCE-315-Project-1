import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{

    private ResultPanel ResultBox;
    private BottomToolbar BToolbar;
    private SelectPanel SelectArea;

    public MainFrame(){
        super("IMDB Database");

        setLayout(new BorderLayout());

        ResultBox = new ResultPanel();
        BToolbar = new BottomToolbar();
        SelectArea = new SelectPanel();


        BToolbar.setResultPanel(ResultBox, SelectArea);


        add(ResultBox, BorderLayout.CENTER);
        add(BToolbar, BorderLayout.SOUTH);
        add(SelectArea, BorderLayout.WEST);

        ResultBox.setBorder(new EmptyBorder(5, 5, 5, 5));
        SelectArea.setBorder(new EmptyBorder(2, 2, 2, 2));

        setSize(800, 500);
        SelectArea.setMinimumSize(new Dimension(250, 30));
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
