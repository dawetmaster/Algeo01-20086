import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public KaidahCramerMethod() {
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            int N = Integer.parseInt(nInput.getText());
            Matriks matA = Matriks.parseMatrix(inputA.getText(),N,N);
            Matriks matB = Matriks.parseMatrix(inputB.getText(),N,1);
            Matriks result = CramerMethod.solve(matA,matB);
            String hasil = "<html>" + MatriksBalikanMethod.CetakHasil(result)+"</html>";
            resultField.setText(hasil);
            }
        });
    }

    public void run(){
        kaiahCramerFrame.setContentPane(new KaidahCramerMethod().cramerMethodLabel);
        kaiahCramerFrame.setMinimumSize(new Dimension(800,400));
        kaiahCramerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        kaiahCramerFrame.setVisible(true);
    }
}
