import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomToolbar extends JPanel implements ActionListener {
    private JButton Search;
    private JButton Exit;
    private ResultPanel resultPanel;
    private SelectPanel selectPanel;

    private String[] Text;
    private Boolean[] Filter;
    private Boolean[] Display;

    public BottomToolbar(){
        Search = new JButton("Search");
        Exit = new JButton("Exit");

        Search.addActionListener(this);
        Exit.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.RIGHT));

        add(Search);
        add(Exit);
    }

    public void setResultPanel(ResultPanel resultPanel, SelectPanel selectPanel){
        this.resultPanel = resultPanel;
        this.selectPanel = selectPanel;
    }

    @Override
    public void actionPerformed (ActionEvent e){
        JButton clicked = (JButton) e.getSource();
        if(clicked == Search) {
            Text = selectPanel.getSearch();
            Filter = selectPanel.getF();
            Display = selectPanel.getD();
            resultPanel.setResult( "Strings: " + Text[0] + ", " + Text[1] + ", " + Text[2] + "\n"
            + "Filter: " + Filter[0] + ", " + Filter[1] + ", " + Filter[2] + "\n"
                            + "Display: " + Display[0] + ", " + Display[1] + ", " + Display[2] + "\n");
        }
        if(clicked == Exit){
            System.exit(0);
        }
    }
}
