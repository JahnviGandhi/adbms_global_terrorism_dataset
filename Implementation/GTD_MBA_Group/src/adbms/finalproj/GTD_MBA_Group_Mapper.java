package adbms.finalproj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class GTD_MBA_Group_Mapper extends Mapper<Object, Text, Text, IntWritable> {

	private int numberOfPairs = 2;
	private static final Text reducerKey = new Text();

	private static final IntWritable ONE = new IntWritable(1);

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		String line = value.toString();

		if (line.contains("eventid")) {
			return;
		} else {
			List<String> items = convertItemsToList(line);

			if ((items == null) || (items.isEmpty())) {
				return;
			}
			generateMapperOutput(numberOfPairs, items, context);
		}
	}

	private static List<String> convertItemsToList(String line) {
		if ((line == null) || (line.length() == 0)) {
			// no mapper output will be generated
			return null;
		}

		String[] tokens = line.split(",");

		if ((tokens == null) || (tokens.length == 0)) {
			return null;
		}

		List<String> items = new ArrayList<String>();
		items.add(tokens[5]);
		items.add(tokens[3]);

		return items;
	}

	private void generateMapperOutput(int numberOfPairs, List<String> items, Context context)
			throws IOException, InterruptedException {
		List<List<String>> sortedCombinations = Combination.findSortedCombinations(items, numberOfPairs);
		for (List<String> itemList : sortedCombinations) {
			// System.out.println("itemlist=" + itemList.toString());
			reducerKey.set(itemList.toString());
			context.write(reducerKey, ONE);
		}
	}
}
