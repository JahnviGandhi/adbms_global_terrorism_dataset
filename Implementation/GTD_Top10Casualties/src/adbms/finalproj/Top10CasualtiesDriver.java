package adbms.finalproj;

import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class Top10CasualtiesDriver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Global Terrorism Database");

		Scanner scan = new Scanner(System.in);
		System.out.println("Please Enter 'N' For Top N Records: ");
		int N = scan.nextInt();

		Configuration conf = new Configuration();
		conf.set("topN", String.valueOf(N));

		Job job = Job.getInstance(conf, "Top 10 Casualities");

		job.setJarByClass(Top10CasualtiesDriver.class);
		job.setMapperClass(Top10CasualtiesMapper.class);
		job.setReducerClass(Top10CasualtiesReducer.class);
		job.setOutputKeyClass(NullWritable.class);
		job.setOutputValueClass(Text.class);
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
		// graphHelper.createGraph(hdfs, outputPath, N);
	}
}
