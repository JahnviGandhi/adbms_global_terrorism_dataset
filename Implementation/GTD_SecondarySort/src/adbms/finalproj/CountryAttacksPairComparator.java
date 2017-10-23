package adbms.finalproj;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class CountryAttacksPairComparator extends WritableComparator {
	protected CountryAttacksPairComparator() {
		super(CountryAttacksPair.class, true);
	}

	@Override
	public int compare(WritableComparable a, WritableComparable b) {
		CountryAttacksPair key1 = (CountryAttacksPair) a;
		CountryAttacksPair key2 = (CountryAttacksPair) b;

		int result = key1.getCount().get() < key2.getCount().get() ? 1
				: key1.getCount().get() == key2.getCount().get() ? 0 : -1;
		return result;
	}
}
