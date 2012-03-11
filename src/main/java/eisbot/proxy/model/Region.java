package eisbot.proxy.model;

import java.util.ArrayList;
/**
 * Represents a region in a StarCraft map.
 * 
 * For a description of fields see: http://code.google.com/p/bwta/wiki/Region
 */
public class Region {

	public static final int numAttributes = 3;

	private final int ID;
	private final int centerX;
	private final int centerY;
	private int[] coordinates;
	private final ArrayList<Region> connectedRegions = new ArrayList<Region>();
	private final ArrayList<ChokePoint> chokePoints = new ArrayList<ChokePoint>();
	
	public Region(int[] data, int index) {
		ID = data[index++];	
		centerX = data[index++];	
		centerY = data[index++];	
	}

	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

	public int getID() {
		return ID;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public ArrayList<Region> getConnectedRegions() {
		return connectedRegions;
	}

	public ArrayList<ChokePoint> getChokePoints() {
		return chokePoints;
	}
}
