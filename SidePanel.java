import javax.swing.*;
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
        Q2B = new JRadioButton("Actors with works between rang");
        Q3B = new JRadioButton("Question 3");

        ButtonGroup bg = new ButtonGroup();
        bg.add(Q1B);
        bg.add(Q2B);
        bg.add(Q3B);

        setLayout(new GridLayout(6, 1));
        add(Q1B);
        add(Q1P);
        add(Q2B);
        add(Q2P);
        add(Q3B);
        add(Q3P);
    }
}
