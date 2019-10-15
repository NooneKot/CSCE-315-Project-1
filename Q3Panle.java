import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Q3Panle extends JPanel {
    public JTextArea name;
    private JTextArea prompt;
    //setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));

    public Q3Panle() {
        name = new JTextArea(1,20);
        prompt = new JTextArea("Find the longest film an actor has been in:");

        setLayout(new GridLayout(6,1));
        setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));
        name.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));
        prompt.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));

        prompt.setEditable(false);
        prompt.setBackground(new Color(0,0,0,0));

        add(prompt);
        add(name);
    }
}
