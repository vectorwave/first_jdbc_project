package jdbc�M�D;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {

	public SqlConnection() {
		super();
	}

	public Connection conn;

	// �}��sql�s�u
	public void createConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB";
		try {
			conn = DriverManager.getConnection(url, "watcher", "P@ssw0rd");
			System.out.println("�s�u���A:" + (!conn.isClosed() ? "���`" : "���`"));
		} catch (SQLException e) {
			e.getMessage();
			System.out.println("�s�u�}�Үɿ��~");
		}
	}

	// ����sql�s�uconn
	public void closeConnection() {
		try {
			if (!conn.isClosed()) {
				conn.close();
				System.out.println("�s�u�w����");
			}
		} catch (SQLException e) {
			System.out.println("�s�u�����ɿ��~");
		}
	}
}
