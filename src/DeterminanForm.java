import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeterminanForm {
    private JLabel methodLabel;
    private JButton reduksiBarisButton;
    private JPanel determinanPanel;
    private JButton ekspansiKofaktorButton;
    private JFrame determinanFrame = new JFrame("Kalkulator Matriks");

    public DeterminanForm() {
        reduksiBarisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeterminanERO det = new DeterminanERO();
                det.run();
            }
        });
        ekspansiKofaktorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DeterminanKofaktor det = new DeterminanKofaktor();
                det.run();
            }
        });
    }

    public void run(){
        determinanFrame.setContentPane(new DeterminanForm().determinanPanel);
        determinanFrame.setMinimumSize(new Dimension(400,400));
        determinanFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        determinanFrame.setVisible(true);
    }
}
