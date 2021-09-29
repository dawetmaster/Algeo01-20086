import javax.swing.*;
import java.awt.*;

public class RegresiForm {
    private JFrame regresiFrame = new JFrame("Regresi Linear Berganda");
    private JPanel regresiPanel;
    private JTextField varField;
    private JTextField countDataField;
    private JButton readfileButton;
    private JTextArea inputArea;
    private JTextArea splTextArea;
    private JTextArea resultTextArea;
    private JButton saveButton;
    private JLabel titleLabel;
    private JLabel guideLabel;
    private JLabel varLabel;
    private JLabel countDataLabel;
    private JLabel inputDataLabel;
    private JLabel resultsplLabel;
    private JLabel resultLabel;

    public void run() {
        regresiFrame.setContentPane(new RegresiForm().regresiPanel);
        regresiFrame.setMinimumSize(new Dimension(800, 400));
        regresiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regresiFrame.setVisible(true);
    }
}
