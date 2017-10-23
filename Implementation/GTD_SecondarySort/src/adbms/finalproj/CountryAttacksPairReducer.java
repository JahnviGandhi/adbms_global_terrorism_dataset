package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class CountryAttacksPairReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

	public void reduce(Text key, IntWritable value, Context context) throws IOException, InterruptedException {

		context.write(key, value);
	}
}
