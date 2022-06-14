package jdbc�M�D;

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
		System.out.println("�q���� �k�w�ǬV�f�q�� �~�a�ˬ̰e�� �X�j�ʴ��e�� Total");
		System.out.print(c.get�q����() + " ");
		System.out.print(c.get�k�w�ǬV�f�q��() + " ");
		System.out.print(c.get�~�a�ˬ̰e��() + " ");
		System.out.print(c.get�X�j�ʴ��e��() + " ");
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

			System.out.print(c.get�q����() + " ");
			System.out.print(c.get�k�w�ǬV�f�q��() + " ");
			System.out.print(c.get�~�a�ˬ̰e��() + " ");
			System.out.print(c.get�X�j�ʴ��e��() + " ");
			System.out.println(c.getTotal());
		}

	}

	public void addCovid19(Covid19 c) {

		String sql = "insert into Covid19(�q����,�k�w�ǬV�f�q��,�~�a�ˬ̰e��,�X�j�ʴ��e��,Total) values(?,?,?,?,?)";

		try (PreparedStatement preState = conn.prepareStatement(sql);) {

			preState.setDate(1, c.get�q����());
			preState.setFloat(2, c.get�k�w�ǬV�f�q��());
			preState.setFloat(3, c.get�~�a�ˬ̰e��());
			preState.setFloat(4, c.get�X�j�ʴ��e��());
			preState.setFloat(5, c.getTotal());

			preState.executeUpdate();

			System.out.println("���\�[�J�s���");

		} catch (SQLException e) {
			System.out.println("�[�J�s��Ʈɿ��~");
		}

	}

	public ArrayListWithMeta selectCovid19(String year, String month, String day, String type) {

		Covid19 covid = new Covid19(year, month, day);
		String sql = "select * from covid19 where �q���� = ? or 1=?";
		ArrayListWithMeta arrayListWithMeta = new ArrayListWithMeta();
		arrayListWithMeta.arrayList = new ArrayList<Covid19>();

		try (PreparedStatement stm = conn.prepareStatement(sql)) {

			stm.setDate(1, covid.get�q����());

			if (type.equals("all")) {
				stm.setInt(2, 1);
			} else {
				stm.setInt(2, 2);
			}

			ResultSet rs = stm.executeQuery();
			arrayListWithMeta.metaData = rs.getMetaData();

			while (rs.next()) {
				Covid19 c = new Covid19();
				c.set�q����(rs.getDate("�q����"));
				c.set�k�w�ǬV�f�q��(rs.getFloat("�k�w�ǬV�f�q��"));
				c.set�~�a�ˬ̰e��(rs.getFloat("�~�a�ˬ̰e��"));
				c.set�X�j�ʴ��e��(rs.getFloat("�X�j�ʴ��e��"));
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
		String sql = "delete from covid19 where �q���� = ?";
		Covid19 c = new Covid19(year, month, day);
		try (PreparedStatement stm = conn.prepareStatement(sql);) {
			stm.setDate(1, c.get�q����());
			int i = stm.executeUpdate();
			System.out.println("�w�R��" + i + "�����");

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void updateCovid19(Covid19 c) {
		String sql = "update covid19 set �k�w�ǬV�f�q��=?,�~�a�ˬ̰e��=?,�X�j�ʴ��e��=?,Total=? where �q���� = ?";
		try (PreparedStatement stm = conn.prepareStatement(sql);) {

			stm.setDate(5, c.get�q����());
			stm.setFloat(1, c.get�k�w�ǬV�f�q��());
			stm.setFloat(2, c.get�~�a�ˬ̰e��());
			stm.setFloat(3, c.get�X�j�ʴ��e��());
			stm.setFloat(4, c.getTotal());

			System.out.println("�w��s" + stm.executeUpdate() + "�����");

		} catch (SQLException e) {
			e.getMessage();
		}
	}

	public void downloadByArrayList(ArrayList<Covid19> arrayList, ResultSetMetaData metaData) {
		// �ˬd�ɮ׬O�_�s�b�A�p�G�O�A�N�ɮ׽s��+1
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

				bfw.write(arrayList.get(i).get�q����().toString() + ",");
				bfw.write(arrayList.get(i).get�k�w�ǬV�f�q��() + ",");
				bfw.write(arrayList.get(i).get�~�a�ˬ̰e��() + ",");
				bfw.write(arrayList.get(i).get�X�j�ʴ��e��() + ",");
				bfw.write(arrayList.get(i).getTotal() + "\n");

			}
			System.out.println("�U������");
		} catch (Exception e) {
			System.out.println("�ɮפU������");
			e.getMessage();
		}

	}

}
