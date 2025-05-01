package GUI.ThongKe.Support;


import GUI.ThongKe.Support.BlankChart.BlankPlotChart;
import GUI.ThongKe.Support.BlankChart.BlankPlotChatRender;
import GUI.ThongKe.Support.BlankChart.SeriesSize;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;

public class Chart extends javax.swing.JPanel {

    private List<ModelLegend> legends = new ArrayList<>();
    private List<ModelChart> model = new ArrayList<>();
    private final int seriesSize = 12;
    private final int seriesSpace = 6;

    public Chart() {
        initComponents();
        blankPlotChart.setBlankPlotChatRender(new BlankPlotChatRender() {
            @Override
            public String getLabelText(int index) {
                return model.get(index).getLabel();
            }

            @Override
            public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index) {
                double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
                double x = (size.getWidth() - totalSeriesWidth) / 2;
                for (int i = 0; i < legends.size(); i++) {
                    ModelLegend legend = legends.get(i);
                    GradientPaint gra = new GradientPaint(0, 0, new Color(86, 195, 250), 0, (int) (size.getY() + size.getHeight()), legend.getColor());
                    g2.setPaint(gra);
                    double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight());
                    g2.fillRect((int) (size.getX() + x), (int) (size.getY() + size.getHeight() - seriesValues), seriesSize, (int) seriesValues);
                    x += seriesSpace + seriesSize;
                }
            }

            @Override
public void renderSeries(BlankPlotChart chart, Graphics2D g2, SeriesSize size, int index, List<Path2D.Double> gra) {
    double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
    double x = (size.getWidth() - totalSeriesWidth) / 2;
    for (int i = 0; i < legends.size(); i++) {
        ModelLegend legend = legends.get(i);
        double seriesValues = chart.getSeriesValuesOf(model.get(index).getValues()[i], size.getHeight());

        Path2D.Double path = new Path2D.Double();
        double px = size.getX() + x;
        double py = size.getY() + size.getHeight() - seriesValues;
        path.moveTo(px, py);
        path.lineTo(px + seriesSize, py);
        path.lineTo(px + seriesSize, size.getY() + size.getHeight());
        path.lineTo(px, size.getY() + size.getHeight());
        path.closePath();

        gra.add(path);

        GradientPaint graColor = new GradientPaint(0, 0, new Color(86, 195, 250), 0, (int) (size.getY() + size.getHeight()), legend.getColor());
        g2.setPaint(graColor);
        g2.fill(path);

        x += seriesSpace + seriesSize;
    }
}


            @Override
public boolean mouseMoving(BlankPlotChart chart, MouseEvent evt, Graphics2D g2, SeriesSize size, int index) {
    double totalSeriesWidth = (seriesSize * legends.size()) + (seriesSpace * (legends.size() - 1));
    double x = (size.getWidth() - totalSeriesWidth) / 2;
    boolean mouseOver = false;

    for (int i = 0; i < legends.size(); i++) {
        double px = size.getX() + x;
        double py = size.getY();
        double pw = seriesSize;
        double ph = size.getHeight();

        if (evt.getX() >= px && evt.getX() <= px + pw && evt.getY() >= py && evt.getY() <= py + ph) {
            mouseOver = true;
            g2.setColor(new Color(0, 0, 0, 50)); // Highlight màu đen trong suốt
            g2.fillRect((int) px, (int) py, (int) pw, (int) ph);
        }

        x += seriesSpace + seriesSize;
    }

    return mouseOver;
}


           @Override
public void renderGraphics(Graphics2D g2, List<Path2D.Double> gra) {
    g2.setColor(new Color(200, 200, 200, 80)); // Màu xám nhạt trong suốt
    for (Path2D.Double path : gra) {
        g2.fill(path);
    }
}

      @Override
public int getMaxLegend() {
    return legends.size();
}

    
        });
    }

    public void addLegend(String name, Color color) {
        ModelLegend data = new ModelLegend(name, color);
        legends.add(data);
        panelLegend.add(new LegendItem(data));
        panelLegend.repaint();
        panelLegend.revalidate();
    }

    public void addData(ModelChart data) {
        model.add(data);
        blankPlotChart.setLabelCount(model.size());
        double max = data.getMaxValues();
        if (max > blankPlotChart.getMaxValues()) {
            blankPlotChart.setMaxValues(max);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        blankPlotChart = new BlankPlotChart();
        panelLegend = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));

        panelLegend.setOpaque(false);
        panelLegend.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelLegend, javax.swing.GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE)
                    .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(blankPlotChart, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(panelLegend, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>                        

    // Variables declaration - do not modify                     
    private BlankPlotChart blankPlotChart;
    private javax.swing.JPanel panelLegend;
    // End of variables declaration                   
}
