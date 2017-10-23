package adbms.finalproj;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class AvgByNationalityMapper extends Mapper<Object, Text, Text, AvgByNationalityTuple> {

	private Text outNationality = new Text();
	private AvgByNationalityTuple outAvgTuple = new AvgByNationalityTuple();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		if (value.toString().contains("eventid")) {
			return;
		} else {
			String[] data = value.toString().split(",");

			if (data[2].matches("\\.") || data[2].matches("\\-")) {
				outAvgTuple.setAvgPeopleKilled(0);
			} else {
				outNationality.set(data[3]);
				outAvgTuple.setAvgPeopleKilled(Float.parseFloat(data[2]));
				context.write(outNationality, outAvgTuple);
			}

		}
	}
}
