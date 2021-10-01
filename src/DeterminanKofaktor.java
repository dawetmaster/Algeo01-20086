import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class DeterminanKofaktor {
    private JLabel kofaktorMethod;
    private JLabel nInputLabel;
    private JTextField inputN;
    private JLabel matInputLabel;
    private JTextArea inputMatrix;
    private JButton hitungButton;
    private JButton bukaFileButton;
    private JLabel resultLabel;
    private JLabel resultField;
    private JPanel DetKotPanel;
    private JButton simpanHasilButton;
    private JFrame DetKofFrame = new JFrame("Kalkulator Matriks");
    private double final_determinan;//determinan matriks

    public DeterminanKofaktor() {
        bukaFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./test");
                int result = fileChooser.showOpenDialog(DetKofFrame);
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
                            inputN.setText(Integer.toString(augmented.length));
                            inputMatrix.setText(""); resultField.setText("");
                            for (int i = 0; i < augmented.length; i++) {
                                String[] augmented_baris = augmented[i].split(" ");
                                for (int j = 0; j < augmented_baris.length; j++) {
                                    inputMatrix.append(augmented_baris[j] + " ");
                                }
                                inputMatrix.append("\n");
                            }
                        }
                    } catch (FileNotFoundException fnf) {
                        resultField.setText("File tidak ditemukan");
                    } catch (IOException io) {
                        resultField.setText("File kosong!");
                    }

                }
            }
        });
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int N = Integer.parseInt(inputN.getText());
                Matriks mat = Matriks.parseMatrix(inputMatrix.getText(), N, N);
                final_determinan = mat.determinantCofactor();
                if(Double.compare(final_determinan,0)==0){
                    System.out.println("Naon ieu?");
                }
                else {
                    resultField.setText(Double.toString(final_determinan));
                }
            }

        });
        simpanHasilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./result");
                int result = fileChooser.showSaveDialog(DetKofFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    //menyimpan data
                    String resultString = Double.toString(final_determinan);
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

    public void run() {
        DetKofFrame.setContentPane(new DeterminanKofaktor().DetKotPanel);
        DetKofFrame.setMinimumSize(new Dimension(800, 400));
        DetKofFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        DetKofFrame.setVisible(true);
    }

}
