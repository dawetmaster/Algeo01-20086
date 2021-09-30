import javax.swing.*;
import java.awt.*;
<<<<<<< Updated upstream
=======
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
>>>>>>> Stashed changes

public class RegresiForm {
    private JFrame regresiFrame = new JFrame("Regresi Linear Berganda");
    private JPanel regresiPanel;
    private JTextField varField;
    private JTextField countDataField;
    private JButton readfileButton;
    private JTextArea inputArea;
    private JTextArea splTextArea;
<<<<<<< Updated upstream
    private JTextArea resultTextArea;
=======
>>>>>>> Stashed changes
    private JButton saveButton;
    private JLabel titleLabel;
    private JLabel guideLabel;
    private JLabel varLabel;
    private JLabel countDataLabel;
    private JLabel inputDataLabel;
    private JLabel resultsplLabel;
    private JLabel resultLabel;
<<<<<<< Updated upstream

=======
    private JButton hitungRegresiButton;
    private JLabel formatLabel;
    private JLabel testLabelInput;
    private JTextArea testCaseInput;
    private JLabel resultField;

    public RegresiForm() {
        hitungRegresiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jumlahVariabel = Integer.parseInt(varField.getText());
                int jumlahData = Integer.parseInt(countDataField.getText());
                Matriks hasilBaca = Matriks.parseMatrix(inputArea.getText(),jumlahData,jumlahVariabel+1);
                double[] sigmaTiapVar = new double[jumlahVariabel];
                for(int i=0;i<jumlahVariabel;i++){
                    sigmaTiapVar[i] = 0;
                }
                //menghitung jumlah tiap data
                for(int i=0;i<jumlahVariabel;i++){
                    for(int j=0;j<jumlahData;j++){
                        sigmaTiapVar[i] += hasilBaca.matriks[j][i];
                    }
                }
                //membuat matriks regresi untuk x
                //membuat kolom pertama
                Matriks x_regresi = new Matriks(jumlahVariabel+1,jumlahVariabel+1);
                //elemen pertama diset sebagai jumlah data
                x_regresi.matriks[0][0] = jumlahData;
                //isi kolom 0 dan baris 0 selain indeks 0,0 dengan sigma nilai var ke-i atau ke-j
                for(int i=1;i< x_regresi.Nbaris;i++){
                    x_regresi.matriks[i][0] = sigmaTiapVar[i-1];
                    x_regresi.matriks[0][i] = sigmaTiapVar[i-1];
                }
                //mengisi kolom diantaranya
                Matriks temp_mat = hasilBaca.transpose();
                for(int i=1;i< x_regresi.Nbaris;i++){
                    for(int j=1;j< x_regresi.Nkolom;j++){
                        x_regresi.matriks[i][j] = sigma2Var(temp_mat.matriks[i-1],temp_mat.matriks[j-1]);
                    }
                }
                //mengisi matriks y
                Matriks y_regresi = new Matriks(jumlahVariabel+1,1);
                //mencari sum y
                double sum_y = 0;
                for(int i=0;i< temp_mat.Nkolom;i++){
                    sum_y += temp_mat.matriks[jumlahVariabel][i];
                }
                //isi baris pertama
                y_regresi.matriks[0][0] = sum_y;
                //isi baris selanjutnya
                for(int i=1;i<y_regresi.Nbaris;i++){
                    y_regresi.matriks[i][0] = sigma2Var(temp_mat.matriks[i-1],temp_mat.matriks[jumlahVariabel]);
                }
                //mencari b
                Matriks b = Matriks.kali(InversMethod.invers(x_regresi),y_regresi);
                //splTextArea.setText("<html>"+x_regresi.repr_forIO()+"</html>");
                //splTextArea.setText("<html>"+y_regresi.repr_forIO()+"</html>");
                //splTextArea.setText("<html>"+InversMethod.invers(x_regresi).repr_forIO()+"</html>");
                //splTextArea.setText("<html>"+b.repr_forIO()+"</html>");
                //mencetak hasil
                String equation = "y=";
                //isi konstanta dulu
                equation += (b.matriks[0][0]+"+");
                for(int i=1;i<jumlahVariabel;i++){
                    equation += (b.matriks[i][0]+"x"+i+"+");
                }
                //mencetak variabel terakhir
                equation += (b.matriks[jumlahVariabel][0]+"x"+jumlahVariabel);
                //mencetak ke layar
                splTextArea.setText(equation);
                //menghitung solusi
                Matriks test_case = Matriks.parseMatrix(testCaseInput.getText(),jumlahVariabel,1);
                //diisi konstanta fungsi hasil regresi
                double result = b.matriks[0][0];
                for(int i=1;i< test_case.Nbaris+1;i++){
                    result += (test_case.matriks[i-1][0]*b.matriks[i][0]);
                }
                //menampilkan hasil
                resultField.setText(Double.toString(result));
            }
        });
    }
    private double sigma2Var(double[]var1,double[]var2){
        //prekondisi:panjang var 1 dan var 2 sama
        double sigma = 0;
        for(int i=0;i<var2.length;i++){
            sigma += (var1[i]*var2[i]);
        }
        return sigma;
    }
>>>>>>> Stashed changes
    public void run() {
        regresiFrame.setContentPane(new RegresiForm().regresiPanel);
        regresiFrame.setMinimumSize(new Dimension(800, 400));
        regresiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regresiFrame.setVisible(true);
    }

}
