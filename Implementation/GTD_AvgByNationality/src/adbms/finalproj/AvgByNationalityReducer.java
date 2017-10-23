package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class AvgByNationalityReducer extends Reducer<Text, AvgByNationalityTuple, Text, AvgByNationalityTuple> {

	private AvgByNationalityTuple result = new AvgByNationalityTuple();

	public void reduce(Text key, Iterable<AvgByNationalityTuple> values, Context context)
			throws IOException, InterruptedException {

		result.setAvgPeopleKilled(0);

		float sum = 0;
		int count = 0;

		for (AvgByNationalityTuple value : values) {
			sum += value.getAvgPeopleKilled();
			count += 1;
		}

		float avg = sum / count;
		result.setAvgPeopleKilled(avg);

		context.write(key, result);
	}
}
