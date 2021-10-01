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
    private JTextArea equationLabel;
    private JPanel PolynomialInterpolationField;
    private JLabel calcY;
    private JLabel augMatrixLabel;
    private JLabel calcYLabel;
    private JButton bukaFileButton;
    private JLabel warningLabel;
    private JButton simpanHasilButton;
    private JFrame polynomialInterpolationFrame = new JFrame("Interpolasi Polinom");
    private String equation;//persamaan interpolasi polinom yang diperoleh
    private double x;//nilai x yang ingin dicari nilai y nya
    private double y;//nilai y dengan masukan x menggunakan persamaan hasil interpolasi

    public PolynomialInterpolation() {
        createEqButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /* memghitung persamaan interpolasi polinom dan menuliskannya di layar */

                int i, j;
                int n = Integer.parseInt(nInput.getText());
                Matriks coordMatrix = Matriks.parseMatrix(coordList.getText(), n, 2);

                // inisialisasi matriks augmented untuk persiapan eliminasi Gauss
                Matriks m = PolynomialInterpretationLib.createAugmented(coordMatrix);

                // eliminasi gauss dari matriks augmented
                double[] result = GaussMethod.gaussElim(m);

                // cetak hasil, membuat persamaan dalam bentuk string
                String persamaan_label =  "";
                double epsilon = 0.0001d;
                equation = "p(x) = ";
                if (result[0] >= epsilon) equation += "%.4f ".formatted(result[0]);
                if (result[1] >= epsilon) {
                    equation += "+ ";
                    equation += "%.4fx ".formatted(result[1]);
                } else if (result[1] <= -epsilon) {
                    equation += "";
                    equation += "- %.4fx ".formatted(result[1] * -1);
                }
                for (i = 2; i < result.length; i++) {
                    if (result[i] >= epsilon){
                        if (result[i] > 0) {
                            equation += "+ ";
                            equation += "%.4fx^%d".formatted(result[i], i);
                        } else {
                            equation += " ";
                            equation += "%.4fx^%d".formatted(result[i] * -1, i);
                        }
                        if (i <= result.length - 1) {
                            equation += " ";
                        }
                    }
                }
                equationLabel.setText(persamaan_label+equation);
            }
        });
        calcYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                x = Double.parseDouble(calcYField.getText());

                int n = Integer.parseInt(nInput.getText());
                Matriks coordMatrix = Matriks.parseMatrix(coordList.getText(), n, 2);
                Matriks m = PolynomialInterpretationLib.createAugmented(coordMatrix);

                double[] result = GaussMethod.gaussElim(m);

                y = 0;
                for (int i = 0; i < n; i++) {
                    y += result[i] * Math.pow(x, i);
                }

                calcY.setText("p(%.4f) = %.4f".formatted(x, y));
            }
        });
        bukaFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nInput.setText("");
                coordList.setText("");
                equationLabel.setText("");
                calcYField.setText("");
                calcY.setText("");
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
                        while ((line = reader.readLine()) != null) {
                            isi_file += line;
                            isi_file += "\n";
                        }
                        String[] augmented = isi_file.split("\n");
                        if (augmented.length > 0) {
                            String text_mat = "";
                            nInput.setText(Integer.toString(augmented.length));
                            coordList.setText("");
                            for (int i = 0; i < augmented.length; i++) {
                                String[] augmented_baris = augmented[i].split(" ");
                                for (int j = 0; j < augmented_baris.length; j++) {
                                    coordList.append(augmented_baris[j] + " ");
                                }
                                coordList.append("\n");
                            }
                        }
                    } catch (FileNotFoundException fnf) {
                        augMatrixLabel.setText("File tidak ditemukan");
                    } catch (IOException io) {
                        augMatrixLabel.setText("File kosong!");
                    }

                }
            }
        });
        simpanHasilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./result");
                int result = fileChooser.showSaveDialog(polynomialInterpolationFrame);
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
                    resultString += ("\np("+x+")=");
                    resultString+= y;
                    try {
                        FileWriter fw = new FileWriter(selectedFile);
                        fw.write(resultString);
                        fw.close();
                    } catch (FileNotFoundException fnfe) {
                        augMatrixLabel.setText("File tidak ditemukan");
                    } catch (IOException io) {
                        augMatrixLabel.setText("File kosong!");
                    }

                }
            }
        });
    }

    public void run() {
        polynomialInterpolationFrame.setContentPane(new PolynomialInterpolation().PolynomialInterpolationField);
        polynomialInterpolationFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        polynomialInterpolationFrame.pack();
        polynomialInterpolationFrame.setMinimumSize(new Dimension(400, 600));
        polynomialInterpolationFrame.setVisible(true);
    }

}
