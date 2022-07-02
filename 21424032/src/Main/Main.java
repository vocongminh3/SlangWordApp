package Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
	static HashMap<String, ArrayList<String>> slangWords = new HashMap<String, ArrayList<String>>();
	static ArrayList<String> history = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);

	private static void SearchKey() {
		String scan;
		while (true) {
			System.out.println("Nhập từ khóa tìm kiếm : ");
			System.out.println("Nhập t : để thoát");
			scan = sc.next();
			history.add(scan);
			if (scan.equals("t"))
				return;
			if (slangWords.get(scan) == null) {
				System.out.println("Không tìm thấy kết quả");
			} else {
				for (String s : slangWords.get(scan)) {
					System.out.println(s);
				}
			}
		}

	}

	private static void SearchDefinition() {
		String scan;
		while (true) {
			System.out.println("Nhập từ khóa tìm kiếm : ");
			System.out.println("Nhập t : để thoát");
			scan = sc.next();
			if (scan.equals("T"))
				return;
			boolean check = false;
			for (String key : slangWords.keySet()) {
				for (String value : slangWords.get(key)) {
					if (value.contains(scan)) {
						check = true;
						System.out.println(key);
					}
				}
			}

			if (!check)
				System.out.println("Không tìm thấy kết quả");
		}

	}

	private static void AddSlang() {
		String scan;
		String key;
		String definition;
		ArrayList<String> arrDefintion = new ArrayList<String>();
		while (true) {

			System.out.println("Nhập t : để thoát");
			System.out.println("Nhập a : để thêm slang word");
			scan = sc.next();
			if (scan.equals("t"))
				return;
			else if (scan.equals("a")) {
				System.out.println("Nhập từ khóa : ");
				key = sc.next();
				System.out.println("Nhập định nghĩa : ");
				definition = sc.next();
				if (definition.split("[|]").length > 1) {
					for (String s : definition.split("[|]")) {
						arrDefintion.add(s);
					}
				} else {
					arrDefintion.add(definition);
				}
				slangWords.put(key, arrDefintion);
			}

		}

	}

	private static void History() {
		String scan;
		if (history.isEmpty()) {
			System.out.println("Không có dữ liệu");
		} else {
			for (String i : history) {
				System.out.println(i);
			}

		}
		while (true) {
			System.out.println("Nhập t : để thoát");
			scan = sc.next();
			if (scan.equals("t"))
				return;
		}
	}

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new FileReader("slang.txt"));
		String st;
		while ((st = br.readLine()) != null) {
			if (st.indexOf("`") != -1) // have character in string
			{
				String[] arrLine = st.split("`");
				ArrayList<String> defintion = new ArrayList<String>();
				if (arrLine[1].split("[|]").length > 1) {
					for (String s : arrLine[1].split("[|]")) {
						defintion.add(s);
					}

				} else {
					defintion.add(arrLine[1]);
				}
				slangWords.put(arrLine[0], defintion);
			}

		}
		int choice;
		do {
			System.out.println("1. Tìm kiếm theo slang word ");
			System.out.println("2. Tìm kiếm theo defintion ");
			System.out.println("3. Lịch sử tìm kiếm ");
			System.out.println("4. Thêm mới Slang Word ");
			System.out.println("5. Chỉnh sửa Slang Word ");
			System.out.println("6. Xóa Slang Word ");
			System.out.println("7. Làm lại Slang word ");
			System.out.println("8. Tự động Slang Word ");
			System.out.println("9. Đố vui Slang Word ");
			System.out.println("10. Đố vui Definition ");
			System.out.println("0. Thoát ");
			choice = sc.nextInt();
			switch (choice) {
			case 0:
				break;
			case 1:
				SearchKey();
				break;
			case 2:
				SearchDefinition();
				break;
			case 3:
				History();
				break;
			case 4:
				AddSlang();
				break;
			case 5:
				System.out.println("Friday");
				break;
			case 6:
				System.out.println("Saturday");
				break;
			case 7:
				System.out.println("Sunday");
				break;
			case 8:
				System.out.println("Sunday");
				break;
			case 9:
				System.out.println("Sunday");
				break;
			case 10:
				System.out.println("Sunday");
				break;
			default:
				System.out.println("Vui lòng chọn lại");
			}
		} while (choice != 0);

	}

}
