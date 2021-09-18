import javax.swing.*;
import java.awt.Dimension;

public class MatriksBalikanMethod {
    private JPanel inversMatriksMethodLabel;
    private JLabel labelN;
    private JTextField nInput;
    private JLabel matrixInputLabel;
    private JTextArea inputA;
    private JLabel labelB;
    private JTextArea inputB;
    private JLabel resultLabel;
    private JLabel resultField;
    private JButton hitungButton;
    private JFrame inversMethodFrame = new JFrame("Kalkulator Matriks");

    public void run(){
        inversMethodFrame.setContentPane(new MatriksBalikanMethod().inversMatriksMethodLabel);
        inversMethodFrame.setMinimumSize(new Dimension(600,400));
        inversMethodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inversMethodFrame.setVisible(true);
    }
}
