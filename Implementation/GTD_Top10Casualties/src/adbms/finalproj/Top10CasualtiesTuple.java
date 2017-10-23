package adbms.finalproj;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class Top10CasualtiesTuple implements Writable {
	private String year;
	private String country;
	private double casualities;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public double getCasualities() {
		return casualities;
	}

	public void setCasualities(double casualities) {
		this.casualities = casualities;
	}

	public void readFields(DataInput in) throws IOException {
		year = in.readUTF();
		country = in.readUTF();
		casualities = in.readDouble();
	}

	public void write(DataOutput out) throws IOException {
		out.writeUTF(year);
		out.writeUTF(country);
		out.writeDouble(casualities);
	}

	public String toString() {
		return year + "\t" + country + "\t" + casualities;
	}
}
