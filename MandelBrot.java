import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MandelBrot extends JPanel {

    private final int ANCHO = 500;
    private final int ALTO = 500;

    public MandelBrot() {
        JFrame frame = new JFrame("Modelo Fractal de MandelBrot");
        frame.add(this);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(ANCHO, ALTO);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    private void calcular() {
        
    }


    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }
}
