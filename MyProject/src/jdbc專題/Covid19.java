package jdbc�M�D;

import java.sql.Date;

public class Covid19 {
	private Date �q����;
	private float �k�w�ǬV�f�q��;
	private float �~�a�ˬ̰e��;
	private float �X�j�ʴ��e��;
	private float Total;
	
	
	public Covid19() {
		super();
		
	}
	public Covid19(String year,String month,String day) {
		super();
		�q���� = Date.valueOf(year+"-"+month+"-"+day);
	}
	public Covid19(String year,String month,String day, float �k�w�ǬV�f�q��, float �~�a�ˬ̰e��, float �X�j�ʴ��e��, float total) {
		super();
		this.�q���� = Date.valueOf(year+"-"+month+"-"+day);
		this.�k�w�ǬV�f�q�� = �k�w�ǬV�f�q��;
		this.�~�a�ˬ̰e�� = �~�a�ˬ̰e��;
		this.�X�j�ʴ��e�� = �X�j�ʴ��e��;
		Total = total;
	}
	public Date get�q����() {
		return �q����;
	}
	public void set�q����(Date �q����) {
		this.�q���� = �q����;
	}
	public float get�k�w�ǬV�f�q��() {
		return �k�w�ǬV�f�q��;
	}
	public void set�k�w�ǬV�f�q��(float �k�w�ǬV�f�q��) {
		this.�k�w�ǬV�f�q�� = �k�w�ǬV�f�q��;
	}
	public float get�~�a�ˬ̰e��() {
		return �~�a�ˬ̰e��;
	}
	public void set�~�a�ˬ̰e��(float �~�a�ˬ̰e��) {
		this.�~�a�ˬ̰e�� = �~�a�ˬ̰e��;
	}
	public float get�X�j�ʴ��e��() {
		return �X�j�ʴ��e��;
	}
	public void set�X�j�ʴ��e��(float �X�j�ʴ��e��) {
		this.�X�j�ʴ��e�� = �X�j�ʴ��e��;
	}
	public float getTotal() {
		return Total;
	}
	public void setTotal(float total) {
		Total = total;
	}
	
	
	
	
	
}
