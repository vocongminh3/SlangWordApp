/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg21424032;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;
import java.io.Console;

/**
 *
 * @author ThinkKING
 */
public class Main {
static HashMap<String, ArrayList<String>> slangWords = new HashMap<String, ArrayList<String>>();
	static ArrayList<String> history = new ArrayList<String>();
	static Scanner sc = new Scanner(System.in);
	
	private static void LoadData() throws Exception {
		BufferedReader br = new BufferedReader(new FileReader("data.txt"));
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

	private static void LoadDataOld() throws Exception {
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
			if (scan.equals("t"))
				return;
			history.add(scan);
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
			if (scan.equals("t"))
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
		slangWords.clear();
		LoadDataOld();
		System.out.println("Reset danh sách gốc thành công");
	}

	private static void WriteFile()
	{
		
		BufferedWriter  bf = null;
		
	        try {
	  
	            // create new BufferedWriter for the output file
	        	bf = new BufferedWriter(new FileWriter("data.txt"));
	  
	            // iterate map entries
	            for (Entry<String, ArrayList<String>> entry :
	            	slangWords.entrySet()) {
	  
	                // put key and value separated by a colon
	            	String line = entry.getKey()+ "`"+ String.join("|", entry.getValue());;
	                bf.write(line);
	                // new line
	                bf.newLine();
	            }
	  
	            bf.flush();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        finally {
	  
	            try {
	  	                bf.close();
	            }
	            catch (Exception e) {
	            }
	        }
	    
	}
	
	private static String Random() {
		Random r = new Random();
		int location = 0;
		String result = "";
		int i = r.nextInt(slangWords.size());
		for (String key : slangWords.keySet()) {
			if (location == i) {
				result = key;
				break;
			}
			location++;
		}
		return result;
	}

	private static void RandomSlang() {
		int scan;
		String c0 = Random(); // result
		String c1 = Random();
		String c2 = Random();
		String c3 = Random();
		System.out.println("Từ khóa là : " + c0);
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(c0);
		arr.add(c1);
		arr.add(c2);
		arr.add(c3);
		Collections.sort(arr);
		for (int i = 0; i < 4; i++) {
			System.out.println(i + 1 + " " + slangWords.get(arr.get(i)));
		}
		do {
			System.out.println("Chọn đáp án : ");
			scan = sc.nextInt();
		} while (scan < 1 || scan > 4);
		scan -=1;
			if (arr.get(scan).equals(c0)) {
				System.out.println("Đáp án chính xác ");
			} else {
				System.out.println("Đáp án sai. Đáp án đúng là " + slangWords.get(c0));
			}
		

	}

	private static void RandomDefinition() {
		int scan;
		String c0 = Random(); // result
		String c1 = Random();
		String c2 = Random();
		String c3 = Random();
		System.out.println("Định nghĩa là : " + slangWords.get(c0));
		ArrayList<String> arr = new ArrayList<String>();
		arr.add(c0);
		arr.add(c1);
		arr.add(c2);
		arr.add(c3);
		Collections.sort(arr);
		for (int i = 0; i < 4; i++) {
			System.out.println(i + 1 + " " + arr.get(i));
		}
		do {
			System.out.println("Chọn đáp án : ");
			scan = sc.nextInt() ;
		} while (scan < 1 || scan > 4);
		scan -=1;
			if (arr.get(scan).equals(c0)) {
				System.out.println("Đáp án chính xác ");
			} else {
				System.out.println("Đáp án sai. Đáp án đúng là " + c0);
			}
		
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
				WriteFile();
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
				String key = Random();
				System.out.println(key);
				for (String value : slangWords.get(key)) {
					System.out.println(value);
				}
				break;
			case 9:
				RandomSlang();
				break;
			case 10:
				RandomDefinition();
				break;
			default:
				System.out.println("Vui lòng chọn lại");
			}

		} while (choice != 0);

	}
    
}
