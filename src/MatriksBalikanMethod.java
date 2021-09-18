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
                Matriks matA = parseMatrix(inputA.getText(),N,N);
                Matriks matB = parseMatrix(inputB.getText(),N,1);
            }
        });
    }

    private Matriks parseMatrix(String text,int NBaris,int NKolom) {
        Matriks m = new Matriks(NBaris,NKolom);
        String[] test_line = text.split("\n");
        for(int i=0;i<NBaris;i++){
            String[] line_text = test_line[i].split(" ");
            for(int j=0;j<NKolom;j++){
                System.out.println(line_text[j]);
                m.matriks[i][j] = Double.parseDouble(line_text[j]);
                System.out.println(m.matriks[i][j]);
            }
        }
        return m;
    }

    public void run(){
        inversMethodFrame.setContentPane(new MatriksBalikanMethod().inversMatriksMethodLabel);
        inversMethodFrame.setMinimumSize(new Dimension(600,400));
        inversMethodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inversMethodFrame.setVisible(true);
    }
}
