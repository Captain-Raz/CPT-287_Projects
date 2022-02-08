
public class Movie {
        
    private String releaseDate;
    private String name;
    private String description;
    private String receiveDate;
    //private enum status;
    	
    	
	public Movie() {
		this.releaseDate = null;
        this.name = null;
        this.description = null;
        this.receiveDate = null;
         //this.status = null;
    }
        
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
        
    public void setEnemStatus() {
        // I'm confused here....
    }
        
    public void setDescription(String desc) {
        this.description = desc;
    }
    public String getDescription() {
        return this.description;
    }
    
    public String formatMovie() {
    	return "";
    }
        
}