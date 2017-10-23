package adbms.finalproj;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraphHelper {

	public void createGraph(FileSystem fileSystem, Path outputPath, int N) {
		try {
			CategoryDataset dataset = createDataset(fileSystem, outputPath);
			JFreeChart chart = createChart(dataset);

			String name = "Top " + N + " Casualities";
			ChartFrame frame = new ChartFrame(name, chart);
			frame.setVisible(true);
			frame.setSize(650, 650);

			System.out.print("Graph Created.");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
			System.out.println(ex);
			System.out.println("Graph cannot be created at this time. Please run on stand alone mode.");
		}
	}

	public CategoryDataset createDataset(FileSystem fileSystem, Path outputPath) {
		DefaultCategoryDataset graphData = new DefaultCategoryDataset();
		String seriesName = "Casualities";

		try (BufferedReader br = new BufferedReader(new InputStreamReader(fileSystem.open(outputPath)))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {

				String[] data = sCurrentLine.split("\t");
				graphData.addValue(Double.parseDouble(data[2]), seriesName, data[1]);

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return graphData;
	}

	private JFreeChart createChart(CategoryDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart("Line Chart - Top 10 Casaulities", // chart
																							// title
				"Country", // domain axis label
				"Casuality", // range axis label
				dataset, // data
				PlotOrientation.VERTICAL, // orientation
				true, // include legend
				true, // tooltips
				false // urls
		);

		chart.setBackgroundPaint(Color.white);

		CategoryPlot plot = (CategoryPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.lightGray);
		plot.setRangeGridlinePaint(Color.white);

		return chart;
	}
}
