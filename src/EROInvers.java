import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class EROInvers {
    private JLabel eroInversLabel;
    private JLabel nInput;
    private JTextField nInputField;
    private JLabel matInputLabel;
    private JTextArea matInputField;
    private JButton bukaFileButton;
    private JButton hitungButton;
    private JLabel resultLabel;
    private JLabel resultField;
    private JPanel eroInversPanel;
    private JButton simpanHasilButton;
    private JFrame eroInversFrame = new JFrame("Kalkulator Matriks");
    private Matriks resultMatrix;//matriks yang berisi hasil operasi

    public EROInvers() {
        bukaFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./test");
                int result = fileChooser.showOpenDialog(eroInversFrame);
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
                            nInputField.setText(Integer.toString(augmented.length));
                            for (int i = 0; i < augmented.length; i++) {
                                String[] augmented_baris = augmented[i].split(" ");
                                for (int j = 0; j < augmented_baris.length; j++) {
                                    matInputField.append(augmented_baris[j] + " ");
                                }
                                matInputField.append("\n");
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
                int N = Integer.parseInt(nInputField.getText());
                Matriks matriks = Matriks.parseMatrix(matInputField.getText(), N, N);
                resultMatrix = InversMethod.invers(matriks);
                if (resultMatrix != null) {
                    resultField.setText("<html>" + resultMatrix.repr_forIO() + "</html>");
                } else {
                    resultField.setText("Tidak mempunyai invers");
                }
            }
        });
        simpanHasilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./result");
                int result = fileChooser.showSaveDialog(eroInversFrame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println(selectedFile.getName());
                    //menyimpan data
                    String resultString = resultMatrix.repr();
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
        eroInversFrame.setContentPane(new EROInvers().eroInversPanel);
        eroInversFrame.setMinimumSize(new Dimension(800, 400));
        eroInversFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        eroInversFrame.setVisible(true);
    }

}
