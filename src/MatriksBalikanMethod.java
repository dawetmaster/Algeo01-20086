import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public MatriksBalikanMethod() {
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int N = Integer.parseInt(nInput.getText());//ukuran matriks
                Matriks matA = Matriks.parseMatrix(inputA.getText(),N,N);
                Matriks matB = Matriks.parseMatrix(inputB.getText(),N,1);
            }
        });
    }



    public void run(){
        inversMethodFrame.setContentPane(new MatriksBalikanMethod().inversMatriksMethodLabel);
        inversMethodFrame.setMinimumSize(new Dimension(600,400));
        inversMethodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inversMethodFrame.setVisible(true);
    }
}
