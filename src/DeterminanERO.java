import javax.swing.*;
import java.awt.*;

public class DeterminanERO {
    private JLabel eroDetLabel;
    private JLabel nInputLabel;
    private JTextField inputN;
    private JLabel matrixInputLabel;
    private JTextArea inputMatrix;
    private JButton hitungButton;
    private JButton bukaFileButton;
    private JLabel resultLabel;
    private JLabel result;
    private JPanel eroDeterminan;
    private JFrame eroDeterminanFrame = new JFrame("Kalkulator Matriks");
    public void run(){
        eroDeterminanFrame.setContentPane(new DeterminanERO().eroDeterminan);
        eroDeterminanFrame.setMinimumSize(new Dimension(800,400));
        eroDeterminanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eroDeterminanFrame.setVisible(true);
    }
}
