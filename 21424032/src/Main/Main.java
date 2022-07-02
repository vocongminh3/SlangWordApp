package Main;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Main {
	static HashMap<String, ArrayList<String>> slangWords = new HashMap<String, ArrayList<String>>();
	static ArrayList<String> history = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);

	private static void LoadData() throws Exception
	{
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
	}
	
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
				System.out.println("Thêm thành công");
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

	private static void EditSlang() {
		String scan;
		String key;
		ArrayList<String> arrDefintion = new ArrayList<String>();
		while (true) {
			System.out.println("Nhập u để chỉnh sửa : ");
			System.out.println("Nhập t : để thoát");
			scan = sc.next();
			if (scan.equals("t"))
				return;
			else if (scan.equals("u")) {
				System.out.println("Nhập từ khóa tìm kiếm : ");
				scan = sc.next();	
					if (slangWords.get(scan) == null) {
						System.out.println("Không tìm thấy kết quả");
					} else {
					System.out.println("Nhập từ khóa mới : ");
					key = sc.next();
					
					arrDefintion = slangWords.get(scan);
					
					slangWords.remove(scan);
					slangWords.put(key, arrDefintion);
					System.out.println("Chỉnh sửa thành công");
				
				}
		}
		}
	}

	private static void DeleteSlang() {
		String scan;
		while (true) {
			System.out.println("Nhập d để xóa : ");
			System.out.println("Nhập t : để thoát : ");
			scan = sc.next();
			if (scan.equals("t"))
				return;
			else if (scan.equals("d")) {
				System.out.println("Nhập từ khóa tìm kiếm : ");
				scan = sc.next();	
					if (slangWords.get(scan) == null) {
						System.out.println("Không tìm thấy kết quả");
					} else {
					slangWords.remove(scan);
					System.out.println("Xóa thành công");
				
				}
		}
		}
	}

	private static void Reset() throws Exception {
		LoadData();
		System.out.println("Reset danh sách gốc thành công");
	}
	
	private static void Random() {
//		Random r = new Random();
//		int location = 0;
//		int i = r.nextInt(slangWords.size());
//		for (String key : slangWords.keySet()) {
//			if(location == i)
//			{
//				//System.out.println("Slang RanDom");
//				System.out.println(key);
//				for (String value : slangWords.get(key)) {
//					System.out.println( value);
//					
//				}
//				break;
//			}
//			location++;
//			
//		}
	}
	
	public static void main(String[] args) throws Exception {

		LoadData();
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
				EditSlang();
				break;
			case 6:
				DeleteSlang();
				break;
			case 7:
				Reset();
				break;
			case 8:
				Random();
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
