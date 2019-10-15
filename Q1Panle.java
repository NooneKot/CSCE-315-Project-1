import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Q1Panle extends JPanel {
    //setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));
    public JTextArea sourceActor;
    public JTextArea destinationActor;
    public JTextArea actorSelection;
    public JTextArea excludedActor;
    private JTextArea heading_sourceActor;
    private JTextArea heading_destinationActor;
    private JTextArea heading_excludedActor;
    private JTextArea heading_actorSelection;
    
    public Q1Panle() {
        sourceActor = new JTextArea(1,15);
        destinationActor = new JTextArea(1,15);
        excludedActor = new JTextArea(1,15);
        heading_sourceActor = new JTextArea("Source Actor:");
        heading_destinationActor = new JTextArea("Destination Actor:");
        heading_excludedActor = new JTextArea("Excluded Actor:");
        
        setLayout(new GridLayout(6,1));
        setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));

        heading_sourceActor.setEditable(false);
        heading_sourceActor.setBackground(new Color(0,0,0,0));
        
        heading_destinationActor.setEditable(false);
        heading_destinationActor.setBackground(new Color(0,0,0,0));
        
        heading_excludedActor.setEditable(false);
        heading_excludedActor.setBackground(new Color(0,0,0,0));
        
        sourceActor.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));
        destinationActor.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));
        excludedActor.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), BorderFactory.createLineBorder(Color.GRAY)));
        
        add(heading_sourceActor);
        add(sourceActor);
        add(heading_destinationActor);
        add(destinationActor);
        add(heading_excludedActor);
        add(excludedActor);
}
