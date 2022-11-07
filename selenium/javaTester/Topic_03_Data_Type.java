package javaTester;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_03_Data_Type {

	public static void main(String[] args) {
		// 2 loại kiểu dữ liệu
		
		// Cách khai báo 1 biến
		// 1 - Kiểu dữ liệu của biến
		// 2 - Tên biến
		// 3 - Giá trị của biến
		
		// 2 cách khai báo và gán giá trị 
		// 1 - vừa khai báo vừa gán trực tiếp luôn
		// String name = "Automation Testing";
		
		// 2 - Khai báo trước rồi gán sau
		// String name;
		// name = "Automation Testing";
		// name = "Automation FC";
		
		// I. Kiểu dữ liệu nguyên thủy (Primitive)
		// (hay dùng: int/ long/ double (float)/ boolean)
		
		// 8 loại
		
		// Số nguyên: byte/ short/ int/ long
		byte bNumber = 3;
		
		short sNumber = 500;
		
		int iNumber = 5000;
		
		long lNumber = 65484167;
		
		// Số thực: float/ double
		float salary = 5.5f;
		
		double point = 9.8d;
		
		// Kí tự: char
		// dấu nháy đơn ''  // chứa duy nhất 1 kí tự
		char a = 'a';
		
		// Logic: boolean
		boolean marriedStatus = true;
		marriedStatus = false;
		
		// II. Kiểu dữ liệu tham chiếu (Reference)
		
		// Chuổi : string (chữ/ số/ kí tự đặc biệt/ ...)  // dấu nháy đôi
		String emailInvalid = "thaongan@gmail.com";
		
		// Class/ Interface (DateTime)
		Date date = new Date();
				
		WebDriver driver = new FirefoxDriver();
		
		// Đối tượng: Object
		Object students;
		
		// Array: Mảng
		int numbers[] = {15,45,78};
		String addresses[] = {"Da Nang", "Ha Noi", "HCM"};
		
		// List/ Set/ Quêu (Collection)
		List<Integer> studentNumber = new ArrayList<Integer>();
		List<String> studentAddress = new ArrayList<String>();
		
		Set<String> studentCity = new LinkedHashSet<String>();
		
	}

}
