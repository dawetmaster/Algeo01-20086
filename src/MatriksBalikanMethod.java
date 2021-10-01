import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.io.*;
import java.util.Locale;

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
    private JButton openFile;
    private JButton simpanHasilButton;
    private JFrame inversMethodFrame = new JFrame("Kalkulator Matriks");
    private Matriks resultMatrix;//matriks yang berisi hasil operasi

    public MatriksBalikanMethod() {
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int N = Integer.parseInt(nInput.getText());//ukuran matriks
                Matriks matA = Matriks.parseMatrix(inputA.getText(), N, N);
                Matriks matB = Matriks.parseMatrix(inputB.getText(), N, 1);
                resultMatrix = InversMethod.solve(matA, matB);
                String hasil = "<html>" + CetakHasil(resultMatrix) + "</html>";
                resultField.setText(hasil);
            }
        });
        openFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nInput.setText("");
                inputA.setText("");
                inputB.setText("");
                resultField.setText("");
                JFileChooser fileChooser = new JFileChooser("./test");
                int result = fileChooser.showOpenDialog(inversMethodFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
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
                            String text_matA = "";
                            String text_matB = "";
                            nInput.setText(Integer.toString(augmented.length));
                            inputA.setText(""); inputB.setText(""); resultField.setText("");
                            for (int i = 0; i < augmented.length; i++) {
                                String[] augmented_baris = augmented[i].split(" ");
                                for (int j = 0; j < augmented_baris.length - 1; j++) {
                                    inputA.append(augmented_baris[j] + " ");
                                }
                                inputA.append("\n");
                                inputB.append(augmented_baris[augmented_baris.length - 1] + "\n");
                            }
                        }
                    } catch (FileNotFoundException fnfe) {
                        resultField.setText("File tidak ditemukan");
                    } catch (IOException io) {
                        resultField.setText("File kosong!");
                    }

                }
            }
        });
        simpanHasilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./result");
                int result = fileChooser.showSaveDialog(inversMethodFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String fileName = selectedFile.getName(); // menambah ekstensi .txt
                    if (!fileName.toLowerCase().endsWith(".txt")){
                        selectedFile = new File(selectedFile + ".txt");
                    }
                    System.out.println(selectedFile.getName());
                    //menyimpan data
                    String resultString = resultMatrix.SimpanHasil();
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

    public static String CetakHasil(Matriks result) {
        String hasil = "";
        if (result == null) {
            hasil = "Tidak ada solusi atau solusi tak berhingga karena determinan matriks A adalah 0";
        } else {
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
        for (int i = 0; i < result.Nbaris; i++) {
            for (int j = 0; j < result.Nkolom; j++) {
                if (Double.isNaN(result.matriks[i][j])) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String MatrikstoString(Matriks result) {
        String hasil = "";
        for (int i = 0; i < result.Nbaris; i++) {
            for (int j = 0; j < result.Nkolom; j++) {
                hasil += result.matriks[i][j];
                hasil += " ";
            }
            hasil += "<br/>";
        }
        return hasil;
    }


    public void run() {
        inversMethodFrame.setContentPane(new MatriksBalikanMethod().inversMatriksMethodLabel);
        inversMethodFrame.setMinimumSize(new Dimension(800, 400));
        inversMethodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        inversMethodFrame.setVisible(true);
    }

}
