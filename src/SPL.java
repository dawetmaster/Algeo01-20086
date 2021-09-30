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

    public SPL() {
        eliminasiGaussButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GaussEliminationMethod gaussElim = new GaussEliminationMethod();
                gaussElim.run();
            }
        });
        matriksBalikanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MatriksBalikanMethod inversMethod = new MatriksBalikanMethod();
                inversMethod.run();
            }
        });
        kaidahCramerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KaidahCramerMethod cramer = new KaidahCramerMethod();
                cramer.run();
            }
        });
        eliminasiGaussJordanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GaussJordanEliminationMethod gaussJordan = new GaussJordanEliminationMethod();
                gaussJordan.run();
            }
        });
    }

    public void run() {
        frame.setContentPane(new SPL().panelMethod);
        frame.setMinimumSize(new Dimension(400, 400));
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

}
