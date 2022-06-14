package jdbc專題;

import java.sql.Date;

public class Covid19 {
	private Date 通報日;
	private float 法定傳染病通報;
	private float 居家檢疫送驗;
	private float 擴大監測送驗;
	private float Total;
	
	
	public Covid19() {
		super();
		
	}
	public Covid19(String year,String month,String day) {
		super();
		通報日 = Date.valueOf(year+"-"+month+"-"+day);
	}
	public Covid19(String year,String month,String day, float 法定傳染病通報, float 居家檢疫送驗, float 擴大監測送驗, float total) {
		super();
		this.通報日 = Date.valueOf(year+"-"+month+"-"+day);
		this.法定傳染病通報 = 法定傳染病通報;
		this.居家檢疫送驗 = 居家檢疫送驗;
		this.擴大監測送驗 = 擴大監測送驗;
		Total = total;
	}
	public Date get通報日() {
		return 通報日;
	}
	public void set通報日(Date 通報日) {
		this.通報日 = 通報日;
	}
	public float get法定傳染病通報() {
		return 法定傳染病通報;
	}
	public void set法定傳染病通報(float 法定傳染病通報) {
		this.法定傳染病通報 = 法定傳染病通報;
	}
	public float get居家檢疫送驗() {
		return 居家檢疫送驗;
	}
	public void set居家檢疫送驗(float 居家檢疫送驗) {
		this.居家檢疫送驗 = 居家檢疫送驗;
	}
	public float get擴大監測送驗() {
		return 擴大監測送驗;
	}
	public void set擴大監測送驗(float 擴大監測送驗) {
		this.擴大監測送驗 = 擴大監測送驗;
	}
	public float getTotal() {
		return Total;
	}
	public void setTotal(float total) {
		Total = total;
	}
	
	
	
	
	
}
