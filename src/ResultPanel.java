import javax.swing.*;
import java.awt.*;

public class ResultPanel extends JPanel {

    private JTextArea textArea;
    public ResultPanel(){
        textArea = new JTextArea();
        textArea.setEditable(false);

        setLayout(new BorderLayout());

        add(new JScrollPane(textArea), BorderLayout.CENTER);
    }
    public void setResult(String text){
        textArea.setText(text);
    }


}
