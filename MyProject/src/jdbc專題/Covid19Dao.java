package jdbc專題;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class Covid19Dao {

	private Connection conn;

	public Covid19Dao(SqlConnection sqlconn) {
		super();
		sqlconn.createConnection();
		this.conn = sqlconn.conn;
	}

	public void printResult(Covid19 c) {
		System.out.println("通報日 法定傳染病通報 居家檢疫送驗 擴大監測送驗 Total");
		System.out.print(c.get通報日() + " ");
		System.out.print(c.get法定傳染病通報() + " ");
		System.out.print(c.get居家檢疫送驗() + " ");
		System.out.print(c.get擴大監測送驗() + " ");
		System.out.println(c.getTotal());
	}

	public void printResult(ArrayListWithMeta arrayListWithMeta) throws SQLException {

		ArrayList<Covid19> arrayList = arrayListWithMeta.arrayList;
		ResultSetMetaData metaData = arrayListWithMeta.metaData;
		Covid19 c = new Covid19();

		for (int i = 1; i <= metaData.getColumnCount(); i++) {
			System.out.print(metaData.getColumnName(i) + " ");
		}
		System.out.println();

		for (int i = 0; i < arrayList.size(); i++) {

			c = arrayList.get(i);

			System.out.print(c.get通報日() + " ");
			System.out.print(c.get法定傳染病通報() + " ");
			System.out.print(c.get居家檢疫送驗() + " ");
			System.out.print(c.get擴大監測送驗() + " ");
			System.out.println(c.getTotal());
		}

	}

	public void addCovid19(Covid19 c) {

		String sql = "insert into Covid19(通報日,法定傳染病通報,居家檢疫送驗,擴大監測送驗,Total) values(?,?,?,?,?)";

		try (PreparedStatement preState = conn.prepareStatement(sql);) {

			preState.setDate(1, c.get通報日());
			preState.setFloat(2, c.get法定傳染病通報());
			preState.setFloat(3, c.get居家檢疫送驗());
			preState.setFloat(4, c.get擴大監測送驗());
			preState.setFloat(5, c.getTotal());

			preState.executeUpdate();

			System.out.println("成功加入新資料");

		} catch (SQLException e) {
			System.out.println("加入新資料時錯誤");
		}

	}

	public ArrayListWithMeta selectCovid19(String year, String month, String day, String type) {

		Covid19 covid = new Covid19(year, month, day);
		String sql = "select * from covid19 where 通報日 = ? or 1=?";
		ArrayListWithMeta arrayListWithMeta = new ArrayListWithMeta();
		arrayListWithMeta.arrayList = new ArrayList<Covid19>();

		try (PreparedStatement stm = conn.prepareStatement(sql)) {

			stm.setDate(1, covid.get通報日());

			if (type.equals("all")) {
				stm.setInt(2, 1);
			} else {
				stm.setInt(2, 2);
			}

			ResultSet rs = stm.executeQuery();
			arrayListWithMeta.metaData = rs.getMetaData();

			while (rs.next()) {
				Covid19 c = new Covid19();
				c.set通報日(rs.getDate("通報日"));
				c.set法定傳染病通報(rs.getFloat("法定傳染病通報"));
				c.set居家檢疫送驗(rs.getFloat("居家檢疫送驗"));
				c.set擴大監測送驗(rs.getFloat("擴大監測送驗"));
				c.setTotal(rs.getFloat("Total"));
				arrayListWithMeta.arrayList.add(c);

			}

			rs.close();
		} catch (SQLException e) {
			e.getMessage();
		}
		return arrayListWithMeta;
	}

	public void deleteByDate(String year, String month, String day) {
		String sql = "delete from covid19 where 通報日 = ?";
		Covid19 c = new Covid19(year, month, day);
		try (PreparedStatement stm = conn.prepareStatement(sql);) {
			stm.setDate(1, c.get通報日());
			int i = stm.executeUpdate();
			System.out.println("已刪除" + i + "筆資料");

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void updateCovid19(Covid19 c) {
		String sql = "update covid19 set 法定傳染病通報=?,居家檢疫送驗=?,擴大監測送驗=?,Total=? where 通報日 = ?";
		try (PreparedStatement stm = conn.prepareStatement(sql);) {

			stm.setDate(5, c.get通報日());
			stm.setFloat(1, c.get法定傳染病通報());
			stm.setFloat(2, c.get居家檢疫送驗());
			stm.setFloat(3, c.get擴大監測送驗());
			stm.setFloat(4, c.getTotal());

			System.out.println("已更新" + stm.executeUpdate() + "筆資料");

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void downloadByArrayList(ArrayList<Covid19> arrayList, ResultSetMetaData metaData) {
		// 檢查檔案是否存在，如果是，將檔案編號+1
		File file = new File("C:\\Users\\user\\Downloads\\Covid19.csv");
		int j = 1;
		while (file.exists()) {
			file = new File("C:\\Users\\user\\Downloads\\Covid19(" + j + ").csv");
			j++;
		}
		try (FileWriter fw = new FileWriter(file); BufferedWriter bfw = new BufferedWriter(fw);) {

			bfw.write(metaData.getColumnName(1));
			for (int i = 2; i <= metaData.getColumnCount(); i++) {
				bfw.write("," + metaData.getColumnName(i));
			}
			bfw.write("\n");

			Covid19 c = new Covid19();

			for (int i = 0; i < arrayList.size(); i++) {

				bfw.write(arrayList.get(i).get通報日().toString() + ",");
				bfw.write(arrayList.get(i).get法定傳染病通報() + ",");
				bfw.write(arrayList.get(i).get居家檢疫送驗() + ",");
				bfw.write(arrayList.get(i).get擴大監測送驗() + ",");
				bfw.write(arrayList.get(i).getTotal() + "\n");

			}
			System.out.println("下載完成");
		} catch (Exception e) {
			System.out.println("檔案下載失敗");
			e.getMessage();
		}

	}

}
