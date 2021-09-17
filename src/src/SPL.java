import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SPL {
    private JButton eliminasiGaussButton;
    private JButton eliminasiGaussJordanButton;
    private JButton matriksBalikanButton;
    private JButton kaidahCramerButton;
    private JLabel methodTitle;
    private JPanel panelMethod;
    private JFrame frame = new JFrame("Kalkulator Matriks");

    public void run(){
        frame.setContentPane(new SPL().panelMethod);
        frame.setMinimumSize(new Dimension(400,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
