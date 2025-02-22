package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AttacksCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
	private IntWritable attackCount = new IntWritable();

	public void reduce(Text key, Iterable<IntWritable> values, Context context)
			throws IOException, InterruptedException {

		int count = 0;

		for (IntWritable value : values) {
			count += value.get();
		}

		attackCount.set(count);
		context.write(key, attackCount);
	}
}
