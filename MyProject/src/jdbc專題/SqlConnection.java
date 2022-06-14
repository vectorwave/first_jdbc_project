package jdbc專題;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnection {

	public SqlConnection() {
		super();
	}

	public Connection conn;

	// 開啟sql連線
	public void createConnection() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=JDBCDemoDB";
		try {
			conn = DriverManager.getConnection(url, "watcher", "P@ssw0rd");
			System.out.println("連線狀態:" + (!conn.isClosed() ? "正常" : "異常"));
		} catch (SQLException e) {
			e.getMessage();
			System.out.println("連線開啟時錯誤");
		}
	}

	// 關閉sql連線conn
	public void closeConnection() {
		try {
			if (!conn.isClosed()) {
				conn.close();
				System.out.println("連線已關閉");
			}
		} catch (SQLException e) {
			System.out.println("連線關閉時錯誤");
		}
	}
}
