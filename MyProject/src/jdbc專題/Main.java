package jdbc�M�D;

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
				System.out.println("��ܭn�ϥΪ��\��");
				System.out.println("1:�W�Ǹ�� 2:�d�߸�� 3:�d�ߥ��� 4:�s�W��� 5:�R����� 6:��s��� 7:�h�X�{��");
				key = scanner.next();

				if (key.equals("1")) {
					Table.createTable(url, sqlconn.conn);

				} else if (key.equals("2") || key.equals("3")) {
					ArrayListWithMeta arrayListWithMeta = null;

					if (key.equals("2")) {
						System.out.println("��J�q�� �~ �� ��");
						arrayListWithMeta = dao.selectCovid19(scanner.next(), scanner.next(), scanner.next(),
								"not all");
					} else if (key.equals("3")) {
						arrayListWithMeta = dao.selectCovid19("2000", "8", "15", "all");
					}
					dao.printResult(arrayListWithMeta);

					System.out.println("�O�_�n�U���d�ߵ��G (�O/�_)");

					key = scanner.next();
					if (key.equals("�O")) {
						dao.downloadByArrayList(arrayListWithMeta.arrayList, arrayListWithMeta.metaData);
					} else if (!key.equals("�_")) {
						System.out.println("��J���~");
					}

				} else if (key.equals("4")) {
					System.out.println("��J�q�� �~ �� �� �k�w�ǬV�f�q�� �~�a�ˬ̰e�� �X�j�ʴ��e�� Total");
					Covid19 c = new Covid19(scanner.next(), scanner.next(), scanner.next(), scanner.nextFloat(),
							scanner.nextFloat(), scanner.nextFloat(), scanner.nextFloat());
					dao.addCovid19(c);
				} else if (key.equals("5")) {
					Covid19 c = new Covid19();
					System.out.println("��J�n�R���� �~ �� ��");
					dao.deleteByDate(scanner.next(), scanner.next(), scanner.next());

				} else if (key.equals("6")) {

					System.out.println("��J�n��s�� �~ �� ��");
					String year = scanner.next();
					String month = scanner.next();
					String day = scanner.next();

					System.out.println("��J �k�w�ǬV�f�q�� �~�a�ˬ̰e�� �X�j�ʴ��e�� Total");
					Covid19 c = new Covid19(year, month, day, scanner.nextFloat(), scanner.nextFloat(),
							scanner.nextFloat(), scanner.nextFloat());
					dao.updateCovid19(c);

				} else if (key.equals("7")) {
					break;
				} else {
					System.out.println("��J���~");
					continue;
				}

			}

		} catch (Exception e) {
			e.getMessage();
		} finally {
			System.out.println("�{������");
			scanner.close();
			sqlconn.closeConnection();
		}
	}
}