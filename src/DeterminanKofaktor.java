import javax.swing.*;
import java.awt.*;

public class DeterminanKofaktor {
    private JLabel kofaktorMethod;
    private JLabel nInputLabel;
    private JTextField inputN;
    private JLabel matInputLabel;
    private JTextArea inputMatrix;
    private JButton hitungButton;
    private JButton bukaFileButton;
    private JLabel resultLabel;
    private JLabel result;
    private JPanel DetKotPanel;
    private JFrame DetKofFrame = new JFrame("Kalkulator Matriks");
    public void run(){
        DetKofFrame.setContentPane(new DeterminanKofaktor().DetKotPanel);
        DetKofFrame.setMinimumSize(new Dimension(800,400));
        DetKofFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DetKofFrame.setVisible(true);
    }
}
