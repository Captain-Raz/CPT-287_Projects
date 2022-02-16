import java.io.IOException;
import java.net.URISyntaxException;

/* *
 * * Author: Zach
 * * @throws IOException
 * * @throws URISyntaxException
 * */

enum Status {
	RELEASED, RECEIVED
}

public class Movie {

	private Date releaseDate;
	private String name;
	private String description;
	private Date receiveDate;
	private Status status;
	private String changes;

	public Movie() {
		this.name = null;
		this.releaseDate = null;
		this.description = null;
		this.receiveDate = null;
		this.status = null;
		this.changes = "";
	}

	public Movie(String[] movie) {
		this.name = movie[0];
		this.releaseDate = new Date(movie[1].split("/"));
		this.description = movie[2];
		this.receiveDate = new Date(movie[3].split("/"));
		if (movie[4].contains("RECEIVED")) {
			this.status = Status.RECEIVED;
		} else {
			this.status = Status.RELEASED;
		}
		this.changes = "";
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setEnumStatus(boolean showing) {
		if (showing) {
			this.status = Status.RELEASED;
		} else {
			this.status = Status.RECEIVED;
		}
	}

	public String getEnumStatus() {
		return this.status.toString();
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}

	public void setReleaseDate(Date released) {
		this.releaseDate = released;
	}

	public String formGetReleaseDate() {
		return this.releaseDate.getDate();
	}

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReceiveDate(Date date) {
		this.receiveDate = date;
	}

	public String formGetReceiveDate() {
		return this.receiveDate.getDate();
	}

	public Date getReceiveDate() {
		return this.receiveDate;
	}

	public void addChange(String message) {
		if (this.changes.length() == 0) {
			this.changes = message;
		} else {
			this.changes = String.join(", ", this.changes, message);
		}
	}

	public String getChanges() {
		return this.changes;
	}

	public String formatMovie() {

		if (this.getChanges().length() != 0) {
			return String.join(", ", this.name, this.releaseDate.getDate(), this.description,
					this.receiveDate.getDate(), this.getEnumStatus()) + " | " + this.getChanges();
		} else {
			return String.join(", ", this.name, this.releaseDate.getDate(), this.description,
					this.receiveDate.getDate(), this.getEnumStatus());
		}
	}
}
