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


    public String [] getSearch(){
        String Text[] = new String[3];
        Text[0] = name.getString();
        Text[1] = movie.getString();
        Text[2] = TV.getString();
        return Text;
    }

    public Boolean [] getF(){
        Boolean Filter[] = new Boolean[3];
        Filter[0] = name.getFilter();
        Filter[1] = movie.getFilter();
        Filter[2] = TV.getFilter();
        return Filter;
    }

    public Boolean [] getD(){
        Boolean Display[] = new Boolean[3];
        Display[0] = name.getDisplay();
        Display[1] = movie.getDisplay();
        Display[2] = TV.getDisplay();
        return Display;
    }
}
