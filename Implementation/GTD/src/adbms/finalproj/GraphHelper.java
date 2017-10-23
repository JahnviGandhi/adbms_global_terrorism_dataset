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

	public void createGraph(FileSystem fileSystem, Path outputPath) {
		try {
			CategoryDataset dataset = createDataset(fileSystem, outputPath);
			JFreeChart chart = createChart(dataset);

			ChartFrame frame = new ChartFrame("Attacks Per Year", chart);
			frame.setVisible(true);
			frame.setSize(650, 650);

			System.out.print("Graph Created.");
		} catch (Exception ex) {
			// TODO: handle exception
			System.out.println("Graph cannot be created at this time. Please run on stand alone mode.");
		}
	}

	public CategoryDataset createDataset(FileSystem fileSystem, Path outputPath) {
		DefaultCategoryDataset graphData = new DefaultCategoryDataset();
		String seriesName = "Attacks";

		try (BufferedReader br = new BufferedReader(new InputStreamReader(fileSystem.open(outputPath)))) {

			String sCurrentLine;
			int i = 1;
			while ((sCurrentLine = br.readLine()) != null) {

				if (i == 1 || i % 5 == 0) {
					String[] data = sCurrentLine.split("\t");
					graphData.addValue(Integer.parseInt(data[1]), seriesName, data[0]);
				}
				i++;
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return graphData;
	}

	private JFreeChart createChart(CategoryDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart("Line Chart - Attacks Per Year", // chart
																							// title
				"Year", // domain axis label
				"Attacks Count", // range axis label
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
