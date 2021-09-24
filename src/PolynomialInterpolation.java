import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PolynomialInterpolation {
    Matriks m;

    private JTextField nInput;
    private JTextArea coordList;
    private JButton createEqButton;
    private JButton calcYButton;
    private JTextField calcYField;
    private JLabel equationLabel;
    private JPanel PolynomialInterpolationField;
    private JLabel calcY;
    private JLabel augMatrixLabel;
    private JLabel calcYLabel;
    private JFrame polynomialInterpolationFrame = new JFrame("Interpolasi Polinom");

    public PolynomialInterpolation() {
        createEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* memghitung persamaan interpolasi polinom dan menuliskannya di layar */

                int i, j;
                int n = Integer.parseInt(nInput.getText());
                Matriks coordMatrix = Matriks.parseMatrix(coordList.getText(), n, 2);

                // inisialisasi matriks augmented untuk persiapan eliminasi Gauss
                // berukuran n x n+1
                Matriks m = new Matriks(n, n+1);
                for(i = 0; i < n; i++){
                    for(j = 0; j < n; j++){
                        m.matriks[i][j] = Math.pow(coordMatrix.matriks[i][0], j);
                    }
                    m.matriks[i][n] = coordMatrix.matriks[i][1];
                }

                // mengeluarkan matriks augmented
                String augMatrix = "<html>Matriks Augmented:<br/><table>";
                for(i = 0; i < n; i++){
                    augMatrix += "<tr>";
                    for(j = 0; j < n+1; j++){
                        augMatrix += "<td>%.4f</td>".formatted(m.matriks[i][j]);
                    }
                    augMatrix += "</tr>";
                }
                augMatrix += "</table></html>";
                augMatrixLabel.setText(augMatrix);

                // eliminasi gauss dari matriks augmented
                double[] result = GaussMethod.gaussElim(m);

                // cetak hasil, membuat persamaan dalam bentuk string
                String equation = "<html>Persamaan:<br/>p(x) = ";
                if (result[0] != 0) equation += "%.4f ".formatted(result[0]);
                if (result[1] != 0) {
                    if (result[1] > 0) {
                        equation += "+ ";
                        equation += "%.4fx ".formatted(result[1]);
                    } else {
                        equation += "- ";
                        equation += "%.4fx ".formatted(result[1] * -1);
                    }
                }
                for(i = 2; i < result.length; i++){
                    if (result[i] > 0) {
                        equation += "+ ";
                        equation += "%.4fx^%d".formatted(result[i], i);
                    } else {
                        equation += "- ";
                        equation += "%.4fx^%d".formatted(result[i] * -1, i);
                    }
                    if (i <= result.length-1){
                        equation += " ";
                    }
                }
                equation += "</html>";
                equationLabel.setText(equation);
            }
        });
        calcYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
    public void run(){
        polynomialInterpolationFrame.setContentPane(new PolynomialInterpolation().PolynomialInterpolationField);
        polynomialInterpolationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        polynomialInterpolationFrame.setMinimumSize(new Dimension(500,500));
        polynomialInterpolationFrame.setVisible(true);
    }
}
