import java.util.Arrays;
import java.util.Scanner;

public class bestbefore {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String input_date_str = scan.next();
		String[] input = input_date_str.split("/");
		int[] input_date = new int[input.length];
		for (int i = 0; i < input_date.length; i++)
			input_date[i] = Integer.parseInt(input[i]);
		Arrays.sort(input_date);
		int max = input_date[2];
		int mid = input_date[1];
		int min = input_date[0];
		best_date[] dates = new best_date[6];
		dates[0] = new best_date(min, mid, max);
		dates[1] = new best_date(min, max, mid);
		dates[2] = new best_date(mid, min, max);
		dates[3] = new best_date(mid, max, min);
		dates[4] = new best_date(max, min, mid);
		dates[5] = new best_date(max, mid, min);
		boolean isValid = false;
		for (best_date d : dates) {
			if (d.isValid()) {
				System.out.println(d);
				isValid = true;
				break;
			}
		}
		if (!isValid)
			System.out.printf("%s is illegal", input_date_str);
		scan.close();
	}
}

class best_date {
	int day, month, year;

	public best_date(int year, int month, int day) {
		this.day = day;
		this.month = month;
		this.year = (year % 2000) + 2000;
	}

	private boolean isLeapYear() {
		return year % 100 == 0 ? year % 400 == 0 : year % 4 == 0;
	}

	private String padZero(int d) {
		return ("00" + d).substring(String.valueOf(d).length());
	}

	public boolean isValid() {
		if (month > 12 || day > 31 || month < 1 || day < 1)
			return false;
		int[] days = new int[] { 31, isLeapYear() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		return day <= days[month - 1];
	}

	public String toString() {
		return year + "-" + padZero(month) + "-" + padZero(day);
	}
}
