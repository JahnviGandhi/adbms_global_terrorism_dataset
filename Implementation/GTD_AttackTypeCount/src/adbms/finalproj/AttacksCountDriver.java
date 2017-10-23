package adbms.finalproj;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AttacksCountDriver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Global Terrorism Database");

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Attacks Per Attack Type");

		job.setJarByClass(AttacksCountDriver.class);
		job.setMapperClass(AttacksCountMapper.class);
		job.setReducerClass(AttacksCountReducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);

		// job.waitForCompletion(true);
		//
		// System.out.println("Graph Generation.");
		//
		// Path outputPath = new Path(args[1] + "/part-r-00000");
		// FileSystem hdfs = FileSystem.get(conf);
		//
		// GraphHelper graphHelper = new GraphHelper();
		// graphHelper.createGraph(hdfs, outputPath);
	}

}
