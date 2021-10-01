import javax.swing.*;
import java.awt.*;
import java.io.*;

public class GaussJordanEliminationMethod {
    private JButton hitungButton;
    private JTextField RowSize;
    private JTextField ColumnSize;
    private JLabel matrixInputLabel;
    private JTextArea inputA;
    private JLabel labelB;
    private JTextArea inputB;
    private JLabel resultLabel;
    private JLabel resultField;
    private JPanel gaussJordanElimPanel;
    private JButton openFile;
    private JButton simpanHasilButton;
    private JTextField textField1;
    private JFrame GaussJordanFrame = new JFrame("Eliminasi Gauss-Jordan");
    private Matriks m;
    private double[] solution;

    public GaussJordanEliminationMethod() {
        hitungButton.addActionListener(e -> {
            int M = Integer.parseInt(RowSize.getText());
            int N = Integer.parseInt(ColumnSize.getText());
            Matriks a = Matriks.parseMatrix(inputA.getText(), M, N);
            Matriks b = Matriks.parseMatrix(inputB.getText(), M, 1);

            // buat matriks augmented
            m = GaussMethod.augment(a, b);
            EchelonRedux.selfReduce(m, true);

            String solutionString = "";
            if (GaussMethod.isNoSol(m)){
                solutionString = "SPL tidak memiliki solusi.";
            } else if (GaussMethod.isUniqueSol(m)) {
                solution = GaussMethod.gaussJordanElim(m);
                solutionString = GaussMethod.printSol(solution, false);
            } else if (GaussMethod.isManySol(m)){
                solutionString = "SPL memiliki persamaan parametrik. ";
                solutionString += GaussMethod.toParamEq(m);
            }
            // cetak solusi ke layar

            resultField.setText(solutionString);
        });
        /* */
        openFile.addActionListener(e -> {
            RowSize.setText("");
            ColumnSize.setText("");
            inputA.setText("");
            inputB.setText("");
            resultField.setText("");
            JFileChooser fileChooser = new JFileChooser("./test");
            int result = fileChooser.showOpenDialog(GaussJordanFrame);
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
                        RowSize.setText(Integer.toString(augmented.length));
                        for (int i = 0; i < augmented.length; i++) {
                            String[] augmented_baris = augmented[i].split(" ");
                            ColumnSize.setText(Integer.toString(augmented_baris.length-1));
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
        });
        simpanHasilButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser("./result");
            int result = fileChooser.showSaveDialog(GaussJordanFrame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                String fileName = selectedFile.getName(); // menambah ekstensi .txt
                if (!fileName.toLowerCase().endsWith(".txt")){
                    selectedFile = new File(selectedFile + ".txt");
                }
                System.out.println(selectedFile.getName());
                //menyimpan data
                String resultString;
                if (solution == null){
                    resultString = resultField.getText();
                } else {
                    resultString = GaussMethod.printSol(solution, true);
                }
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
        });
    }

    public void run() {
        GaussJordanFrame.setContentPane(new GaussJordanEliminationMethod().gaussJordanElimPanel);
        GaussJordanFrame.setMinimumSize(new Dimension(800, 400));
        GaussJordanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        GaussJordanFrame.setVisible(true);
    }

}
