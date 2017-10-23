package adbms.finalproj;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
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
		CategoryDataset dataset = createDataset(fileSystem, outputPath);
		JFreeChart chart = createChart(dataset);

		ChartFrame frame = new ChartFrame("Attacks Per Country", chart);
		frame.setVisible(true);
		frame.setSize(650, 650);
	}

	public CategoryDataset createDataset(FileSystem fileSystem, Path outputPath) {
		System.out.println("Create DataSet Called");
		DefaultCategoryDataset graphData = new DefaultCategoryDataset();
		String seriesName = "Attacks";

		try (BufferedReader br = new BufferedReader(new InputStreamReader(fileSystem.open(outputPath)))) {

			String sCurrentLine;
			int i = 1;
			while ((sCurrentLine = br.readLine()) != null) {

				String[] data = sCurrentLine.split("\t");
				if (Integer.parseInt(data[1]) > 4000) {
					graphData.addValue(Integer.parseInt(data[1]), seriesName, data[0]);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		return graphData;
	}

	private JFreeChart createChart(CategoryDataset dataset) {
		// create the chart...
		JFreeChart chart = ChartFactory.createLineChart("Line Chart - Attacks Per Country", // chart
				// title
				"Country", // domain axis label
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
