import javax.swing.*;
import java.awt.*;

public class InversForm {
    private JLabel inversLabel;
    private JButton operasiBarisElementerButton;
    private JButton adjoinButton;
    private JPanel inversPanel;
    private JFrame invFrame = new JFrame("Kalkulator Matriks");
    public void run(){
        invFrame.setContentPane(new InversForm().inversPanel);
        invFrame.setMinimumSize(new Dimension(400,400));
        invFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        invFrame.setVisible(true);
    }
}
