import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SidePanel extends JPanel {
    public Q1Panle Q1P;
    public Q2Panle Q2P;
    public Q3Panle Q3P;

    public JRadioButton Q1B;
    public JRadioButton Q2B;
    public JRadioButton Q3B;
    public SidePanel(){

        Q1P = new Q1Panle();
        Q2P = new Q2Panle();
        Q3P = new Q3Panle();

        Q1B = new JRadioButton("Shortest Path");
        Q2B = new JRadioButton("Actors with works between range");
        Q3B = new JRadioButton("Question 3");

        ButtonGroup bg = new ButtonGroup();
        bg.add(Q1B);
        bg.add(Q2B);
        bg.add(Q3B);

        Q1B.setBorder(new EmptyBorder(0,0,0,0));
        Q2B.setBorder(new EmptyBorder(0,0,0,0));
        Q3B.setBorder(new EmptyBorder(0,0,0,0));

        setLayout(new GridLayout(6, 1));
        Q1B.setAlignmentX(Component.LEFT_ALIGNMENT);
        Q1B.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        Q2B.setAlignmentX(Component.LEFT_ALIGNMENT);
        Q2B.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        Q3B.setAlignmentX(Component.LEFT_ALIGNMENT);
        Q3B.setAlignmentY(Component.BOTTOM_ALIGNMENT);

        Q1P.setAlignmentX(Component.LEFT_ALIGNMENT);
        Q1P.setAlignmentY(Component.TOP_ALIGNMENT);

        Q2P.setAlignmentX(Component.LEFT_ALIGNMENT);
        Q2P.setAlignmentY(Component.TOP_ALIGNMENT);

        Q3P.setAlignmentX(Component.LEFT_ALIGNMENT);
        Q3P.setAlignmentY(Component.TOP_ALIGNMENT);

        add(Q1B);
        add(Q1P);
        add(Q2B);
        add(Q2P);
        add(Q3B);
        add(Q3P);
    }
}
