package modelos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class JuliaFractal extends JPanel {

    private final int width;
    private final int height;
    private final BufferedImage image;

    public JuliaFractal(int width, int height) {
        this.width = width;
        this.height = height;
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    }

    private Color calculateColor(int iter, int maxIterations) {
        if (iter == 0) {
            return Color.BLACK;
        } else {
            float hue = (float) iter / maxIterations;
            return Color.getHSBColor(hue, 1, 1);
        }
    }

    public void generateJuliaFractal(int maxIterations, double cX, double cY) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double zx, zy, tmp;
                zx = x * 4.0 / width - 2;
                zy = y * 4.0 / height - 2;
                int iter = maxIterations;

                while (zx * zx + zy * zy < 4 && iter > 0) {
                    tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    iter--;
                }

                Color color = calculateColor(iter, maxIterations);
                image.setRGB(x, y, color.getRGB());
            }
        }
        repaint();
    }

    @Override
    protected void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JuliaFractal juliaFractal = new JuliaFractal(800, 600);
            juliaFractal.generateJuliaFractal(1000, -0.7, 0.27015);

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(juliaFractal);
            frame.setTitle("Julia Fractal");
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
