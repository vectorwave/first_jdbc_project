package jdbc�M�D;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
	//�κ�����ƫإ�table
	public static void dropTable(Connection conn) {
		String sql ="drop table covid19";
		try(Statement st=conn.createStatement()){
			st.execute(sql);
		} catch (SQLException e) {
			e.getMessage();
		}
	}
	public static void createTable(URL url,Connection conn) throws SQLException, IOException {
		
		String sql = "insert into covid19(�q����,�k�w�ǬV�f�q��,�~�a�ˬ̰e��,�X�j�ʴ��e��,Total) values(?,?,?,?,?)";
		

			URLConnection urlconn = url.openConnection();

			try (
					PreparedStatement preState = conn.prepareStatement(sql);
					InputStream ins = urlconn.getInputStream();
					InputStreamReader isr = new InputStreamReader(ins);
					BufferedReader br=new BufferedReader(isr);)
			{
				
				String line = null;
				line=br.readLine();
				
				while((line=br.readLine())!=null) {
					
					String[] token =line.split(",");
					String[] datepart=token[0].split("/");
					java.sql.Date date= Date.valueOf(datepart[0]+"-"+datepart[1]+"-"+datepart[2]);
					
					preState.setDate(1, date);
					preState.setFloat(2, Float.parseFloat(token[1]));
					preState.setFloat(3, Float.parseFloat(token[2]));
					preState.setFloat(4, Float.parseFloat(token[3]));
					preState.setFloat(5, Float.parseFloat(token[4]));
					
					preState.execute();
					
				}
				System.out.println("�W�ǧ���");
			}
		
		
	}
}
