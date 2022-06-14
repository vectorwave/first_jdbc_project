package jdbc±MÃD;

import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class ArrayListWithMeta {
	
	ArrayList<Covid19> arrayList;
	ResultSetMetaData metaData;
	
	
	public ArrayListWithMeta() {
		super();
	}

	public ArrayListWithMeta(ArrayList<Covid19> arrayList, ResultSetMetaData metaData) {
		super();
		this.arrayList = arrayList;
		this.metaData = metaData;
	}
	
}
