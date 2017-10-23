package adbms.finalproj;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Writable;

public class AvgByNationalityTuple implements Writable {
	private float avgPeopleKilled = 0;

	public float getAvgPeopleKilled() {
		return avgPeopleKilled;
	}

	public void setAvgPeopleKilled(float avgPeopleKilled) {
		this.avgPeopleKilled = avgPeopleKilled;
	}

	public void readFields(DataInput in) throws IOException {
		avgPeopleKilled = in.readFloat();
	}

	public void write(DataOutput out) throws IOException {
		out.writeFloat(avgPeopleKilled);
	}

	public String toString() {
		return String.valueOf(avgPeopleKilled);
	}
}
