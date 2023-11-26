package modelos;

import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class MandelBrot extends JPanel {

    private final int width;
    private final int height;
    private final BufferedImage image;

    public MandelBrot(int width, int height) {
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

    public void generateMandelbrotFractal(int maxIterations) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double zx, zy, cX, cY;
                zx = zy = 0;
                cX = (x - width / 2.0) * 4.0 / width;
                cY = (y - height / 2.0) * 4.0 / height;
                int iter = maxIterations;

                while (zx * zx + zy * zy < 4 && iter > 0) {
                    double tmp = zx * zx - zy * zy + cX;
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
            MandelBrot mandelbrot = new MandelBrot(800, 600);
            mandelbrot.generateMandelbrotFractal(1000);

            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(mandelbrot);
            frame.setTitle("MandelBrot");
            frame.setSize(800, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
