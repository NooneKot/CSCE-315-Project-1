import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Q2Panle extends JPanel {
    public JTextArea year1;
    public JTextArea year2;
    public JTextArea exclude;
    private JTextArea heading_year1;
    private JTextArea heading_year2;
    private JTextArea heading_exclude;

    public Q2Panle(){
        year1 = new JTextArea(1,15);
        year2 = new JTextArea(1,15);
        exclude = new JTextArea(1,15);
        heading_year1 = new JTextArea("Year 1:");
        heading_year2 = new JTextArea("Year 2:");
        heading_exclude  = new JTextArea("Exclude Actor:");

        setLayout(new GridLayout(6,1));
        setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));

        heading_year1.setEditable(false);
        heading_year1.setBackground(new Color(0,0,0,0));

        heading_year2.setEditable(false);
        heading_year2.setBackground(new Color(0,0,0,0));

        heading_exclude.setEditable(false);
        heading_exclude.setBackground(new Color(0,0,0,0));

        year1.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));
        year2.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));
        exclude.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));

        add(heading_year1);
        add(year1);
        add(heading_year2);
        add(year2);
        add(heading_exclude);
        add(exclude);
    }
}
