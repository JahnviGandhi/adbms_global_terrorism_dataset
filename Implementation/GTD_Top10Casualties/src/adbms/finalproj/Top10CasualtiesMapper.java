package adbms.finalproj;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class Top10CasualtiesMapper extends Mapper<Object, Text, NullWritable, Text> {
	// private SortedMap<Long, Top10CasualtiesTuple> outTreeMap = new
	// TreeMap<Long, Top10CasualtiesTuple>();
	private TreeMap<Double, Top10CasualtiesTuple> outTreeMap = new TreeMap<Double, Top10CasualtiesTuple>();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		Configuration conf = context.getConfiguration();
		int N = Integer.parseInt(conf.get("topN"));

		String[] data = value.toString().split(",");
		// System.out.println(data.length);
		if (data.length > 1 && !data[0].equals("eventid")) {

			Top10CasualtiesTuple tupleData = new Top10CasualtiesTuple();
			tupleData.setYear(data[1]);
			tupleData.setCountry(data[3]);

			if (data[2].matches("\\.") || data[2].matches("\\-")) {
				tupleData.setCasualities(0);
			} else {
				tupleData.setCasualities(Double.parseDouble(data[2]));
			}

			outTreeMap.put(tupleData.getCasualities(), tupleData);

			if (outTreeMap.size() > N) {
				outTreeMap.remove(outTreeMap.firstKey());
			}

		}
	}

	protected void cleanup(Context context) throws IOException, InterruptedException {
		for (Top10CasualtiesTuple value : outTreeMap.values()) {
			context.write(NullWritable.get(), new Text(value.toString()));
		}
	}

}
