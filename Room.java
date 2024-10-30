package gameInterface;

import java.util.HashMap;

public class Room {
	private String name;
	private String description;
	private HashMap <String, Room> neighbors;
	private String mapLocation;
	
	
	Room(String n, String descript){
		name=n;
		description=descript;
		neighbors=new HashMap<String,Room>();
	}
	Room(){
		name="";
		description="";
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public void addNeighbor(String dir,Room r) {
		neighbors.put(dir, r);
	}
	public Boolean checkIfToken(String dir) {
		if(neighbors.containsKey(dir.toLowerCase())) {
			return true;
		}
		return false;
	}
	public Room getRoom(String dir) {
		return neighbors.get(dir);
	}
	public void setMapLocation(String path) {
		mapLocation=path;
	}
	public String getMapPath() {
		return mapLocation;
	}
}
