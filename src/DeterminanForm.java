import javax.swing.*;
import java.awt.*;

public class DeterminanForm {
    private JLabel methodLabel;
    private JButton reduksiBarisButton;
    private JPanel determinanPanel;
    private JButton ekspansiKofaktorButton;
    private JFrame determinanFrame = new JFrame("Kalkulator Matriks");

    public void run(){
        determinanFrame.setContentPane(new DeterminanForm().determinanPanel);
        determinanFrame.setMinimumSize(new Dimension(400,400));
        determinanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        determinanFrame.setVisible(true);
    }
}
