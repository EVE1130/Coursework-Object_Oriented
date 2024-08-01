import java.util.*;

public class Artist {
	private String artistName, style, liveStatus;
	private int maxPrice, minPrice;
	private int [] artId;        //Artwork of the artist. 
	
	// accessor and mutator
	public String getName() { return artistName; }
	public void setName(String name) throws UserDefinedException { 
		if (name.matches("^[a-zA-Z\\s]*$"))  {
			artistName = name; 
		}
		else
			throw new UserDefinedException("Name must consist only alphabets. Please re-enter the Name");
	}
	
	public String getStyle() { return style; }
	public void setStyle (String theStyle) { style = theStyle; }
	
	public String getStatus() { return liveStatus; }
	public void setStatus(String status) { liveStatus = status; }
	
	public int getMaxP() { return maxPrice; }
	public void setMaxP(int maxP) { maxPrice = maxP; }
	
	public int getMinP() { return minPrice; }
	public void setMinP(int minP) { minPrice = minP; }
	
	public int [] getArtId() { return artId; }
	public void setArtId(int [] artworkId) { artId= artworkId; }
	
	//Constructor 1
	public Artist(String name, String style, String status) throws UserDefinedException {
		this.setName(name);
		this.style= style;
		liveStatus = status;
	}
	
	//Constructor 2
	
	public Artist(String name, String style, String status, int minP, int maxP ) {
		artistName=name;
		this.style= style;
		liveStatus = status;
		maxPrice=maxP;
		minPrice= minP;
	}
	public Artist() {
	}
	
}
