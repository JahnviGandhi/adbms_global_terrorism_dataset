package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AttacksCountMapper extends Mapper<Object, Text, Text, IntWritable> {
	private Text outType = new Text();
	private IntWritable outCount = new IntWritable();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String[] data = value.toString().split(",");

		if (data.length > 1) {

			String type = data[3];

			if (!type.equals("attacktype") && !type.equals(".") && !type.equals("-")) {

				outType.set(type);
				outCount.set(1);

				context.write(outType, outCount);
			}

		}
	}
}
