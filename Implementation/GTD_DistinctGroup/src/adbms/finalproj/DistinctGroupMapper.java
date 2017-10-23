package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DistinctGroupMapper extends Mapper<Object, Text, Text, NullWritable> {
	private Text outAttackType = new Text();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		if (!value.toString().contains("eventid")) {
			String type = value.toString().split(",")[5];

			if (!type.equals(".") && !type.equals("-")) {
				outAttackType.set(type);
				context.write(outAttackType, NullWritable.get());
			}
		}
	}
}
