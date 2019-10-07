import javax.swing.*;
import java.awt.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class ColumnBox extends JPanel {
    private JCheckBox Filter;
    private JTextArea text;
    private JTextArea heading;
    private JRadioButton Display;

    public ColumnBox(String name){
        Filter = new JCheckBox("Filter");
        Display = new JRadioButton("Display");
        text = new JTextArea(1, 15);
        heading = new JTextArea(name, 1, 15);

        setLayout(new GridLayout(4,1));
        setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));
        heading.setEditable(false);
        heading.setBackground(new Color(0,0,0,0));

        add(heading);
        add(Filter);
        add(Display);
        add(text);


    }



    public String getString (){ return text.getText(); }
    public Boolean getFilter(){
        return Filter.isSelected();
    }
    public Boolean getDisplay(){
        return Display.isSelected();
    }
}
