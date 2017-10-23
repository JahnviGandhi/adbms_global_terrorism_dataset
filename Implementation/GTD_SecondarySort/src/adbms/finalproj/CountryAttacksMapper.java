package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class CountryAttacksMapper extends Mapper<Object, Text, Text, IntWritable> {
	private Text outCountry = new Text();
	private IntWritable outCount = new IntWritable();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] data = value.toString().split(",");

		if (data.length > 1) {

			String country = data[4];

			if (!country.equals("country") && !country.matches("^[0-9]")) {

				outCountry.set(country);
				outCount.set(1);

				context.write(outCountry, outCount);
			}

		}
	}
}
