package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountryAttacksPairMapper extends Mapper<Object, Text, CountryAttacksPair, NullWritable> {

	private CountryAttacksPair outData = new CountryAttacksPair();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] data = value.toString().split("\t");

		outData.setCountry(new Text(data[0]));
		outData.setCount(new IntWritable(Integer.parseInt(data[1])));

		context.write(outData, NullWritable.get());
	}
}
