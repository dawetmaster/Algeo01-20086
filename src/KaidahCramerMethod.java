import javax.swing.*;
import java.awt.*;

public class KaidahCramerMethod {
    private JLabel labelN;
    private JTextField nInput;
    private JLabel matrixInputLabel;
    private JTextArea inputA;
    private JLabel labelB;
    private JTextArea inputB;
    private JButton hitungButton;
    private JLabel resultLabel;
    private JLabel resultField;
    private JPanel cramerMethodLabel;
    private JFrame kaiahCramerFrame = new JFrame("Kalkulator Matriks");

    public void run(){
        kaiahCramerFrame.setContentPane(new KaidahCramerMethod().cramerMethodLabel);
        kaiahCramerFrame.setMinimumSize(new Dimension(800,400));
        kaiahCramerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kaiahCramerFrame.setVisible(true);
    }
}
