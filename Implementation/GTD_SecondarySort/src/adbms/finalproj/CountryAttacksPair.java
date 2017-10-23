package adbms.finalproj;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

public class CountryAttacksPair implements Writable, WritableComparable<CountryAttacksPair> {

	private Text country = new Text();
	private IntWritable count = new IntWritable();

	public Text getCountry() {
		return country;
	}

	public void setCountry(Text country) {
		this.country = country;
	}

	public IntWritable getCount() {
		return count;
	}

	public void setCount(IntWritable count) {
		this.count = count;
	}

	public void write(DataOutput out) throws IOException {
		country.write(out);
		count.write(out);
	}

	public void readFields(DataInput in) throws IOException {
		country.readFields(in);
		count.readFields(in);
	}

	public int compareTo(CountryAttacksPair countryPair) {
		int compareValue = this.country.compareTo(countryPair.getCountry());
		if (compareValue == 0) {
			return count.compareTo(countryPair.getCount());
		}
		return compareValue;
	}

	public String toString() {
		return country.toString() + "\t" + count.get();
	}
}
