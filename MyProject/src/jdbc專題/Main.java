package jdbc專題;

import java.net.URL;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		SqlConnection sqlconn = new SqlConnection();
		Scanner scanner = new Scanner(System.in);
		try {

			URL url = new URL("https://od.cdc.gov.tw/eic/covid19/covid19_tw_specimen.csv");
			Covid19Dao dao = new Covid19Dao(sqlconn);

			String key;

			while (true) {

				System.out.println(" #####  ####### #     # ### ######          #    #####  \r\n"
						+ "#     # #     # #     #  #  #     #        ##   #     # \r\n"
						+ "#       #     # #     #  #  #     #       # #   #     # \r\n"
						+ "#       #     # #     #  #  #     # #####   #    ###### \r\n"
						+ "#       #     #  #   #   #  #     #         #         # \r\n"
						+ "#     # #     #   # #    #  #     #         #   #     # \r\n"
						+ " #####  #######    #    ### ######        #####  ##### ");
				System.out.println("選擇要使用的功能");
				System.out.println("1:上傳資料 2:查詢資料 3:查詢全部 4:新增資料 5:刪除資料 6:更新資料 7:退出程序");
				key = scanner.next();

				if (key.equals("1")) {
					Table.createTable(url, sqlconn.conn);

				} else if (key.equals("2") || key.equals("3")) {
					ArrayListWithMeta arrayListWithMeta = null;

					if (key.equals("2")) {
						System.out.println("輸入通報 年 月 日");
						arrayListWithMeta = dao.selectCovid19(scanner.next(), scanner.next(), scanner.next(),
								"not all");
					} else if (key.equals("3")) {
						arrayListWithMeta = dao.selectCovid19("2000", "8", "15", "all");
					}
					dao.printResult(arrayListWithMeta);

					System.out.println("是否要下載查詢結果 (是/否)");

					key = scanner.next();
					if (key.equals("是")) {
						dao.downloadByArrayList(arrayListWithMeta.arrayList, arrayListWithMeta.metaData);
					} else if (!key.equals("否")) {
						System.out.println("輸入錯誤");
					}

				} else if (key.equals("4")) {
					System.out.println("輸入通報 年 月 日 法定傳染病通報 居家檢疫送驗 擴大監測送驗 Total");
					Covid19 c = new Covid19(scanner.next(), scanner.next(), scanner.next(), scanner.nextFloat(),
							scanner.nextFloat(), scanner.nextFloat(), scanner.nextFloat());
					dao.addCovid19(c);
				} else if (key.equals("5")) {
					Covid19 c = new Covid19();
					System.out.println("輸入要刪除的 年 月 日");
					dao.deleteByDate(scanner.next(), scanner.next(), scanner.next());

				} else if (key.equals("6")) {

					System.out.println("輸入要更新的 年 月 日");
					String year = scanner.next();
					String month = scanner.next();
					String day = scanner.next();

					System.out.println("輸入 法定傳染病通報 居家檢疫送驗 擴大監測送驗 Total");
					Covid19 c = new Covid19(year, month, day, scanner.nextFloat(), scanner.nextFloat(),
							scanner.nextFloat(), scanner.nextFloat());
					dao.updateCovid19(c);

				} else if (key.equals("7")) {
					break;
				} else {
					System.out.println("輸入錯誤");
					continue;
				}

			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			System.out.println("程序關閉");
			scanner.close();
			sqlconn.closeConnection();
		}
	}
}