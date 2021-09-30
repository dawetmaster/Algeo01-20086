import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private JButton bukaFileButton;
    private JLabel warningLabel;
    private JFrame polynomialInterpolationFrame = new JFrame("Interpolasi Polinom");

    public Matriks createAug(Matriks coordMatrix){
        Matriks m = new Matriks(coordMatrix.Nbaris, coordMatrix.Nbaris+1);
        for(int i = 0; i < coordMatrix.Nbaris; i++){
            for(int j = 0; j < coordMatrix.Nbaris; j++){
                m.matriks[i][j] = Math.pow(coordMatrix.matriks[i][0], j);
            }
            m.matriks[i][coordMatrix.Nbaris] = coordMatrix.matriks[i][1];
        }
        return m;
    }

    public void writeMatrix(Matriks m, JLabel y){
        // menulis matriks dalam bentuk tabel ke dalam satu JLabel
        String augMatrix = "<html>Matriks Augmented:<br/><table>";
        for(int i = 0; i < m.Nbaris; i++){
            augMatrix += "<tr>";
            for(int j = 0; j < m.Nkolom; j++){
                augMatrix += "<td>%.4f</td>".formatted(m.matriks[i][j]);
            }
            augMatrix += "</tr>";
        }
        augMatrix += "</table></html>";
        augMatrixLabel.setText(augMatrix);
    }

    public PolynomialInterpolation() {
        createEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* memghitung persamaan interpolasi polinom dan menuliskannya di layar */

                int i, j;
                int n = Integer.parseInt(nInput.getText());
                Matriks coordMatrix = Matriks.parseMatrix(coordList.getText(), n, 2);

                // inisialisasi matriks augmented untuk persiapan eliminasi Gauss
                Matriks m = createAug(coordMatrix);

                // menuliskan matriks augmented ke layar
              //  writeMatrix(m, augMatrixLabel);

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
                double x = Double.parseDouble(calcYField.getText());

                int n = Integer.parseInt(nInput.getText()); // TODO: somehow keluarin ini biar ga kerja 2 kali, buat variabel result di global?
                Matriks coordMatrix = Matriks.parseMatrix(coordList.getText(), n, 2);
                Matriks m = createAug(coordMatrix);
              //  writeMatrix(m, augMatrixLabel);
                double[] result = GaussMethod.gaussElim(m);

                double y = 0;
                for(int i = 0; i < n; i++){
                    y += result[i] * Math.pow(x, i);
                }

                calcY.setText("p(%.4f) = %.4f".formatted(x, y));
            }
        });
        bukaFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./test");
                int result = fileChooser.showOpenDialog(polynomialInterpolationFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    //parse input
                    String isi_file = "";
                    try {
                        String line = null;
                        BufferedReader reader;
                        reader = new BufferedReader(new FileReader(selectedFile));
                        while((line=reader.readLine())!=null){
                            isi_file+=line;
                            isi_file+="\n";
                        }
                        String[] augmented = isi_file.split("\n");
                        if(augmented.length>0){
                            String text_mat = "";
                            nInput.setText(Integer.toString(augmented.length));
                            for(int i=0;i< augmented.length;i++) {
                                String[] augmented_baris = augmented[i].split(" ");
                                for(int j=0;j<augmented_baris.length;j++){
                                    coordList.append(augmented_baris[j]+" ");
                                }
                                coordList.append("\n");
                            }
                        }
                    }
                    catch (FileNotFoundException fnf){
                        augMatrixLabel.setText("File tidak ditemukan");
                    }
                    catch (IOException io){
                        augMatrixLabel.setText("File kosong!");
                    }

                }
            }
        });
    }
    public void run(){
        polynomialInterpolationFrame.setContentPane(new PolynomialInterpolation().PolynomialInterpolationField);
        polynomialInterpolationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        polynomialInterpolationFrame.setMinimumSize(new Dimension(800,500));
        polynomialInterpolationFrame.setVisible(true);
    }
}
