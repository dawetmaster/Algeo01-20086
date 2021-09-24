import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class MainForm {
    private JButton buttonSPL;
    private JPanel panelSPL;
    private JLabel TitleLabel;
    private JButton buttonDeterminan;
    private JButton buttonInvers;
    private JButton buttonInterpolation;
    private JButton buttonRegretion;
    private JFrame frame = new JFrame("Kalkulator Matriks");

    public MainForm() {
        buttonSPL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //[Class buat solve SPL].setVisible(true)
                SPL spl = new SPL();
                spl.run();
            }
        });
        buttonInterpolation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PolynomialInterpolation polin = new PolynomialInterpolation();
                polin.run();
            }
        });
    }
        public void run(){
            frame.setContentPane(new MainForm().panelSPL);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
           //frame.pack();
            frame.setMinimumSize(new Dimension(400,400));
            frame.setVisible(true);
    }
}
