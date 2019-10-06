import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SelectPanel extends JPanel {
    private ColumnBox name;
    private ColumnBox movie;
    private ColumnBox TV;

    public SelectPanel(){
        name = new ColumnBox("Actor");
        movie = new ColumnBox("Movie");
        TV = new ColumnBox("Series");

        setLayout(new GridLayout(3, 1));
        name.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));
        movie.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));
        TV.setBorder(new CompoundBorder(new EmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.GRAY)));

        add(name);
        add(movie);
        add(TV);
    }


    public ArrayList<String> getSearch(){
        ArrayList<String> Text = new ArrayList<String>(3);
        Text.add(0, name.getString());
        Text.add(1, movie.getString());
        Text.add(2, TV.getString());
        return Text;
    }

    public ArrayList<Boolean> getF(){
        ArrayList<Boolean> Filter = new ArrayList<Boolean>(3);
        Filter.add(0, name.getFilter());
        Filter.add(1, movie.getFilter());
        Filter.add(2, TV.getFilter());
        return Filter;
    }

    public ArrayList<Boolean> getD(){
        ArrayList<Boolean> Display = new ArrayList<Boolean>(3);
        Display.add(0, name.getDisplay());
        Display.add(1, movie.getDisplay());
        Display.add(2, TV.getDisplay());
        return Display;
    }
}
