package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class YearAttacksMapper extends Mapper<Object, Text, Text, IntWritable> {
	private Text outYear = new Text();
	private IntWritable outCount = new IntWritable();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] data = value.toString().split(",");

		if (data.length > 1) {

			String year = data[1];

			if (!year.equals("year") && year.length() == 4) {

				outYear.set(year);
				outCount.set(1);

				context.write(outYear, outCount);
			}

		}
	}

}
