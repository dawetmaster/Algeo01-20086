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
                Matriks result = InversMethod.solve(matA,matB);
                String hasil = "<html>"+CetakHasil(result)+"</html>";
                resultField.setText(hasil);
            }
        });
    }

    public static String CetakHasil(Matriks result) {
        String hasil = "";
        if(result==null){
            hasil = "Tidak ada solusi atau solusi tak berhingga karena determinan matriks A adalah 0";
        }
        else {
            for (int i = 0; i < result.Nbaris; i++) {
               // System.out.println("Hasil:"+result.matriks[i][0]);
                hasil = hasil + "x" + (i + 1) + ":";
                hasil += result.matriks[i][0];
                hasil += "<br/>";
            }
        }
        return hasil;
    }

    private boolean isNaNExist(Matriks result) {
        //menghasilkan true jika ada elemen matriks yang berupa NaN
        for(int i=0;i<result.Nbaris;i++){
            for(int j=0;j<result.Nkolom;j++){
                if(Double.isNaN(result.matriks[i][j])){
                    return true;
                }
            }
        }
        return false;
    }

    public static String MatrikstoString(Matriks result) {
        String hasil = "";
        for(int i=0;i<result.Nbaris;i++){
            for(int j=0;j<result.Nkolom;j++){
                hasil += result.matriks[i][j];
                hasil+= " ";
            }
            hasil +="<br/>";
        }
        return hasil;
    }


    public void run(){
        inversMethodFrame.setContentPane(new MatriksBalikanMethod().inversMatriksMethodLabel);
        inversMethodFrame.setMinimumSize(new Dimension(800,400));
        inversMethodFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inversMethodFrame.setVisible(true);
    }
}
