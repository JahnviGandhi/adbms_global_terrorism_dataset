package adbms.finalproj;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class SecondarySortDriver {

	private static final String INT_OUTPUT_PATH = "int_output";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		System.out.println("Global Terrorism Database");

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Attacks Per Country");

		job.setJarByClass(SecondarySortDriver.class);
		job.setMapperClass(CountryAttacksMapper.class);
		job.setReducerClass(CountryAttacksReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(INT_OUTPUT_PATH));

		job.waitForCompletion(true);

		Job jobSort = new Job(conf, "Ascending Attacks Per Country");

		jobSort.setMapperClass(CountryAttacksPairMapper.class);
		// jobSort.setReducerClass(CountryAttacksPairReducer.class);

		jobSort.setSortComparatorClass(CountryAttacksPairComparator.class);

		jobSort.setNumReduceTasks(1);
		jobSort.setOutputKeyClass(CountryAttacksPair.class);
		jobSort.setOutputValueClass(NullWritable.class);
		FileInputFormat.addInputPath(jobSort, new Path(INT_OUTPUT_PATH));
		FileOutputFormat.setOutputPath(jobSort, new Path(args[1]));

		System.exit(jobSort.waitForCompletion(true) ? 0 : 1);

	}

}
