import javax.swing.*;
import java.awt.*;

public class WorkFrameController extends JPanel {
    int[] data = {50, 70, 30, 90, 60};  // Dữ liệu biểu đồ
    String[] labels = {"A", "B", "C", "D", "E"};

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        int width = getWidth();
        int height = getHeight();
        int barWidth = width / data.length;

        int max = 100; // giả sử max 100

        for (int i = 0; i < data.length; i++) {
            int barHeight = (int) ((data[i] / (float) max) * (height - 50));
            int x = i * barWidth + 10;
            int y = height - barHeight - 30;

            // Vẽ cột
            g2.setColor(Color.BLUE);
            g2.fillRect(x, y, barWidth - 20, barHeight);

            // Vẽ nhãn
            g2.setColor(Color.BLACK);
            g2.drawString(labels[i], x + (barWidth - 20) / 2 - 5, height - 10);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Biểu đồ cột đơn giản");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.add(new WorkFrameController());
        frame.setVisible(true);
    }
}
