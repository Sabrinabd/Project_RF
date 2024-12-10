import javax.swing.*;
import java.awt.Color;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Hist {

    // Histogram of distance
    public static void Histo_dist(String methode, String y, double[] dist_mi, double[] dist_euc, double[] dist_ma) {
        validateEqualLengths(dist_mi, dist_euc, dist_ma);

        JFrame frame = new JFrame("Methode " + methode);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocation(400, 400);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < dist_ma.length; i++) {
            dataset.addValue(dist_mi[i], "Minkowski", "i° " + (i + 1));
            dataset.addValue(dist_euc[i], "Euclidienne", "i° " + (i + 1));
            dataset.addValue(dist_ma[i], "Manhattan", "i° " + (i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Histogram " + methode,
                "Iterations",
                "" + y,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        applyBarChartStyle(chart);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);
    }

    // Histogram of features
    public static void Histo_car(String dist, String y, double[] e34, double[] gfd, double[] art, double[] yang, double[] zernike7) {
        validateEqualLengths(e34, gfd, art, yang, zernike7);

        JFrame frame = new JFrame("Distance " + dist);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocation(0, 0);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < e34.length; i++) {
            dataset.addValue(e34[i], "E34", "i° " + (i + 1));
            dataset.addValue(gfd[i], "GFD", "i° " + (i + 1));
            dataset.addValue(art[i], "ART", "i° " + (i + 1));
            dataset.addValue(yang[i], "Yang", "i° " + (i + 1));
            dataset.addValue(zernike7[i], "Zernike7", "i° " + (i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Histogram of the 5 features",
                "Iterations",
                "" + y,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        applyBarChartStyle(chart);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);
    }

    // Histogram of feature's precision
    public static void Histo_prec(String dist, String y, double[] p_e34, double[] p_gfd, double[] p_art, double[] p_yang, double[] p_zernike7) {
        validateEqualLengths(p_e34, p_gfd, p_art, p_yang, p_zernike7);

        JFrame frame = new JFrame("Distance " + dist);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocation(0, 420);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < p_e34.length; i++) {
            dataset.addValue(p_e34[i], "E34", "i° " + (i + 1));
            dataset.addValue(p_gfd[i], "GFD", "i° " + (i + 1));
            dataset.addValue(p_art[i], "ART", "i° " + (i + 1));
            dataset.addValue(p_yang[i], "Yang", "i° " + (i + 1));
            dataset.addValue(p_zernike7[i], "Zernike7", "i° " + (i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Histogram of the 5 features",
                "Iterations",
                "" + y,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        applyBarChartStyle(chart);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);
    }

    // Histogram of feature's recall
    public static void Histo_rapp(String dist, String y, double[] p_e34, double[] p_gfd, double[] p_art, double[] p_yang, double[] p_zernike7) {
        validateEqualLengths(p_e34, p_gfd, p_art, p_yang, p_zernike7);

        JFrame frame = new JFrame("Distance " + dist);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocation(930, 420);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < p_e34.length; i++) {
            dataset.addValue(p_e34[i], "E34", "i° " + (i + 1));
            dataset.addValue(p_gfd[i], "GFD", "i° " + (i + 1));
            dataset.addValue(p_art[i], "ART", "i° " + (i + 1));
            dataset.addValue(p_yang[i], "Yang", "i° " + (i + 1));
            dataset.addValue(p_zernike7[i], "Zernike7", "i° " + (i + 1));
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Histogram of the 5 features",
                "Iterations",
                "" + y,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        applyBarChartStyle(chart);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);
    }

    // Curve of true positives
    public static void courb_pos(String dist, String y, double[] po_e34, double[] po_gfd, double[] po_art, double[] po_yang, double[] po_zernike7) {
        validateEqualLengths(po_e34, po_gfd, po_art, po_yang, po_zernike7);

        JFrame frame = new JFrame("Distance " + dist);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocation(930, 0);

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(createSeries(po_e34, "E34"));
        dataset.addSeries(createSeries(po_gfd, "GFD"));
        dataset.addSeries(createSeries(po_art, "ART"));
        dataset.addSeries(createSeries(po_yang, "Yang"));
        dataset.addSeries(createSeries(po_zernike7, "Zernike7"));

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Curve of the True Positives",
                "Iterations",
                "" + y,
                dataset,
                PlotOrientation.VERTICAL,
                true,
                true,
                false
        );

        applyLineChartStyle(chart);

        ChartPanel chartPanel = new ChartPanel(chart);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);
    }

    // Helper method to create a series for a line chart
    private static XYSeries createSeries(double[] data, String seriesName) {
        XYSeries series = new XYSeries(seriesName);
        for (int i = 0; i < data.length; i++) {
            series.add(i + 1, data[i]);
        }
        return series;
    }

    // Helper method to validate equal lengths of arrays
    private static void validateEqualLengths(double[]... arrays) {
        int length = arrays[0].length;
        for (double[] array : arrays) {
            if (array.length != length) {
                throw new IllegalArgumentException("All input arrays must have the same length.");
            }
        }
    }

    // Apply a common style to bar charts
    private static void applyBarChartStyle(JFreeChart chart) {
        CategoryPlot plot = chart.getCategoryPlot();
        plot.getRenderer().setSeriesPaint(0, new Color(0xffa556));
        plot.getRenderer().setSeriesPaint(1, new Color(0x6bbd6b));
        plot.getRenderer().setSeriesPaint(2, new Color(0xe36968));
        plot.getRenderer().setSeriesPaint(3, new Color(0x61B0FF));
        plot.getRenderer().setSeriesPaint(4, new Color(0xFF8080));

        plot.setBackgroundPaint(new Color(0xADADAD));

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setItemMargin(0.1);
        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(false);
    }

    // Apply a common style to line charts
    private static void applyLineChartStyle(JFreeChart chart) {
        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();

        renderer.setSeriesPaint(0, new Color(0xffa556));
        renderer.setSeriesPaint(1, new Color(0x6bbd6b));
        renderer.setSeriesPaint(2, new Color(0xe36968));
        renderer.setSeriesPaint(3, new Color(0x61B0FF));
        renderer.setSeriesPaint(4, new Color(0xFF8080));

        plot.setRenderer(renderer);
    }
}
