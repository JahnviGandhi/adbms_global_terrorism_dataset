package adbms.finalproj;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class Top10CasualtiesReducer extends Reducer<NullWritable, Text, NullWritable, Text> {

	// private SortedMap<Long, Top10CasualtiesTuple> outTreeMap = new
	// TreeMap<Long, Top10CasualtiesTuple>();
	private TreeMap<Double, Top10CasualtiesTuple> outTreeMap = new TreeMap<Double, Top10CasualtiesTuple>();

	public void reduce(NullWritable key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {

		Configuration conf = context.getConfiguration();
		int N = Integer.parseInt(conf.get("topN"));

		for (Text value : values) {
			String[] data = value.toString().split("\t");

			double casualties = Double.parseDouble(data[2]);
			Top10CasualtiesTuple tuple = new Top10CasualtiesTuple();
			tuple.setYear(data[0]);
			tuple.setCountry(data[1]);
			tuple.setCasualities(casualties);
			outTreeMap.put(casualties, tuple);

			if (outTreeMap.size() > N) {
				outTreeMap.remove(outTreeMap.firstKey());
			}

		}

		for (Top10CasualtiesTuple value : outTreeMap.descendingMap().values()) {
			context.write(NullWritable.get(), new Text(value.toString()));
		}

	}
}
