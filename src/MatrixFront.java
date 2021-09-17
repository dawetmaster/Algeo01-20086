import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MatrixFront {
    private JButton buttonSPL;
    private JPanel panelSPL;
    private JLabel TitleLabel;
    private JButton buttonDeterminan;
    private JButton buttonInvers;
    private JButton buttonInterpolation;
    private JButton buttonRegretion;

    public MatrixFront() {
        buttonSPL.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //[Class buat solve SPL].setVisible(true)
            }
        });
    }
        public void run(){
            JFrame frame = new JFrame("Kalkulator Matriks");
            frame.setContentPane(new MatrixFront().panelSPL);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);
    }
}
