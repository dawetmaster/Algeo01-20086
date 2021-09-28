import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AdjoinMethod {
    private JLabel adjoinLabel;
    private JLabel nInputLabel;
    private JTextField nInputField;
    private JLabel matInputLabel;
    private JTextArea matField;
    private JButton bukaFileButton;
    private JButton hitungButton;
    private JLabel resultLabel;
    private JLabel resultField;
    private JPanel adjoinPanel;
    private JFrame adjInversFrame = new JFrame("Kalkulator Matriks");

    public AdjoinMethod() {
        bukaFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser("./test");
                int result = fileChooser.showOpenDialog(adjInversFrame);
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
                            nInputField.setText(Integer.toString(augmented.length));
                            for(int i=0;i<augmented.length;i++) {
                                String[] augmented_baris = augmented[i].split(" ");
                                for(int j=0;j<augmented_baris.length;j++){
                                    matField.append(augmented_baris[j]+" ");
                                }
                                matField.append("\n");
                            }
                        }
                    }
                    catch (FileNotFoundException fnf){
                        resultField.setText("File tidak ditemukan");
                    }
                    catch (IOException io){
                        resultField.setText("File kosong!");
                    }

                }
            }
        });
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int N = Integer.parseInt(nInputField.getText());
                Matriks matriks = Matriks.parseMatrix(matField.getText(),N,N);
                double determinan = matriks.determinantCofactor();
                matriks = matriks.cofactor();
                System.out.println("Yo da yo");
                System.out.println(matriks.repr());
                matriks = matriks.transpose();
                System.out.println(matriks.repr());
                //System.out.println(determinan);
                if(Double.compare(determinan,0.0)!=0) {
                    System.out.println(1.0/determinan);
                    matriks = matriks.scalarMult(1.0/determinan);
                    resultField.setText("<html>"+matriks.repr_forIO()+"</html>");
                }
                else{
                    resultField.setText("Tidak mempunyai invers");
                }
            }
        });
    }

    public void run() {
        adjInversFrame.setContentPane(new AdjoinMethod().adjoinPanel);
        adjInversFrame.setMinimumSize(new Dimension(800, 400));
        adjInversFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adjInversFrame.setVisible(true);
    }
}

