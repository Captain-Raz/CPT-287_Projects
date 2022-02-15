
/**
	 * Author: Zach
	 * @throws IOException
	 * @throws URISyntaxException
	 */

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Date { // Still needs major formatting from Class movieManager

	private int month;
	private int day;
	private int year;
	private boolean valid;

	public Date() { // I'm assuming people will enter correct dates, but checks can be coded to make
					// sure

		this.month = -1;
		this.day = -1;
		this.year = -1;
		this.valid = isValid(this);
	}

	public Date(String[] date) { // I'm assuming people will enter correct dates, but checks can be coded to make
									// sure

		this.month = Integer.parseInt(date[0]);
		this.day = Integer.parseInt(date[1]);
		this.year = Integer.parseInt(date[2]);
		this.valid = isValid(this);
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getMonth() {
		return this.month;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDay() {
		return this.day;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return this.year;
	}

	public String getDate() {

		return String.join("/", String.valueOf(this.month), String.valueOf(this.day), String.valueOf(this.year));

	}

	public boolean isValid(Date inQuestion) {

		ArrayList<Integer> days31 = new ArrayList<>(Arrays.asList(1, 3, 0, 7, 8, 10, 12));
		ArrayList<Integer> days30 = new ArrayList<>(Arrays.asList(1, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));

		if (this.year > 0 && this.year < 3000) {
			return true;
		} else if (this.day < 32 && days31.contains(this.month)) {
			return true;
		} else if (this.day < 31 && days30.contains(this.month)) {
			return true;
		} else if (this.day < 29 && this.month == 2) {
			return true;
		}

		return false;

	}

	public static boolean compareDates(Date entered, Date current) {

		//System.out.println(entered.year > current.year);
		//System.out.println(entered.month > current.month);
		//System.out.println(entered.day > current.day);

		if (entered.year > current.year) {
			return true;
		} else if ((entered.year == current.year) && (entered.month > current.month)) {
			return true;
		} else if ((entered.month == current.month) && (entered.day >= current.day)) {
			return true;
		}

		return false;

	}

}
