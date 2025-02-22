package adbms.finalproj;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class GTD_MBA_Driver {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Global Terrorism Database");

		Configuration conf = new Configuration();
		Job job = Job.getInstance(conf, "Attacks Per Country");

		job.setJarByClass(GTD_MBA_Driver.class);
		job.setMapperClass(GTD_MBA_Mapper.class);
		job.setReducerClass(GTD_MBA_Reducer.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(IntWritable.class);
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}

}
