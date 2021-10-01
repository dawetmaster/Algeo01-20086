import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class RegresiForm {
    private JFrame regresiFrame = new JFrame("Regresi Linear Berganda");
    private JPanel regresiPanel;
    private JTextField varField;
    private JTextField countDataField;
    private JButton readfileButton;
    private JTextArea inputArea;
    private JTextArea splTextArea;
    private JButton saveButton;
    private JLabel titleLabel;
    private JLabel guideLabel;
    private JLabel varLabel;
    private JLabel countDataLabel;
    private JLabel inputDataLabel;
    private JLabel resultsplLabel;
    private JLabel resultLabel;
    private JTextArea textArea1;
    private JButton hitungRegresiButton;
    private JLabel formatLabel;
    private JLabel testLabelInput;
    private JTextArea testCaseInput;
    private JLabel resultField;
    private String equation;//persamaan akhir
    private Matriks test_case;//test_case untuk dicari nilainya
    private double result_score;//hasil nilai suatu titik menggunakan regresi

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
                equation = "y=";
                //isi konstanta dulu
                equation += (b.matriks[0][0]);
                for(int i=1;i<jumlahVariabel;i++){
                    equation += ((b.matriks[i][0]<0?"":"+")+b.matriks[i][0]+"x"+i);
                }
                //mencetak variabel terakhir
                equation += ((b.matriks[jumlahVariabel][0]<0?"":"+")+b.matriks[jumlahVariabel][0]+"x"+jumlahVariabel);
                //mencetak ke layar
                splTextArea.setText(equation);
                //menghitung solusi
                test_case = Matriks.parseMatrix(testCaseInput.getText(),1,jumlahVariabel);
                //diisi konstanta fungsi hasil regresi
                result_score = b.matriks[0][0];
                for(int i=1;i< test_case.Nbaris+1;i++){
                    result_score += (test_case.matriks[0][i-1]*b.matriks[i][0]);
                }
                //menampilkan hasil
                resultField.setText(Double.toString(result_score));
            }
        });
        readfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./test");
                int result = fileChooser.showOpenDialog(regresiFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    //mereset tulisan di GUI
                    varField.setText("");
                    countDataField.setText("");
                    inputArea.setText("");
                    testCaseInput.setText("");
                    splTextArea.setText("");
                    resultField.setText("");
                    //parse input
                    String isi_file = "";
                    try {
                        String line = null;
                        BufferedReader reader;
                        reader = new BufferedReader(new FileReader(selectedFile));
                        while ((line = reader.readLine()) != null) {
                            isi_file += line;
                            isi_file += "\n";
                        }
                        String[] augmented = isi_file.split("\n");
                        if (augmented.length > 0) {
                            String text_mat = "";
                            countDataField.setText(Integer.toString(augmented.length));
                            for (int i = 0; i < augmented.length; i++) {
                                String[] augmented_baris = augmented[i].split(" ");
                                varField.setText(Integer.toString(augmented_baris.length-1));;
                                for (int j = 0; j < augmented_baris.length; j++) {
                                    inputArea.append(augmented_baris[j] + " ");
                                }
                                inputArea.append("\n");
                            }
                        }
                    } catch (FileNotFoundException fnf) {
                        splTextArea.setText("File tidak ditemukan");
                    } catch (IOException io) {
                        splTextArea.setText("File kosong!");
                    }

                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./result");
                int result = fileChooser.showSaveDialog(regresiFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName(); // menambah ekstensi .txt
                    if (!fileName.toLowerCase().endsWith(".txt")){
                        selectedFile = new File(selectedFile + ".txt");
                    }
                    System.out.println(selectedFile.getName());
                    //menyimpan data
                    //menyimpan persamaan di baris 1
                    String resultString = equation;
                    //menyimpan hasil nilai di baris 2
                    resultString += "\ny(";
                    for(int i=0;i<test_case.Nkolom-1;i++){
                        resultString+= (test_case.matriks[0][i]+",");
                    }
                    resultString+= (test_case.matriks[0][test_case.Nkolom-1]+")=");
                    resultString+= result_score;
                    try {
                        FileWriter fw = new FileWriter(selectedFile);
                        fw.write(resultString);
                        fw.close();
                    } catch (FileNotFoundException fnfe) {
                        resultField.setText("File tidak ditemukan");
                    } catch (IOException io) {
                        resultField.setText("File kosong!");
                    }

                }
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
    public void run() {
        regresiFrame.setContentPane(new RegresiForm().regresiPanel);
        regresiFrame.setMinimumSize(new Dimension(800, 400));
        regresiFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        regresiFrame.setVisible(true);
    }

}
