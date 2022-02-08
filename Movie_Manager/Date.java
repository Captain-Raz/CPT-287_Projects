
public class Date { // Still needs major formatting from Class movieManager
	
	private int month;
	private int day;
	private int year;
	
	public Date() { // I'm assuming people will enter correct dates, but checks can be coded to make sure
		
		this.month = -1;
		this.day = -1;
		this.year = -1;
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

	public String formatDate() {
		
		return String.join("/", String.valueOf(this.month), String.valueOf(this.day), String.valueOf(this.year));

	}

}