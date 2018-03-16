import java.awt.Point;
import java.util.Random;
// import java.util.ArrayList;

// Implements the Singleton design pattern.

public class OceanMap {
	final static int pirates = 2;
	private static OceanMap oceanMap_instance = null;
	
	boolean[][] grid;
	int dimensions;
	int islandCount;
	Random rand = new Random();
	Point shipLocation;
	Point sharkLocation;
	Point DoubleLocation;
	Point treasureLocation;
	Point[] islands = new Point[20];
	Point[] pirate = new Point[pirates];

	// ArrayList<Movement> moveables = new ArrayList<Movement>();
	// Constructor
	// Not adding validation code so make sure islandCount is much less than
	// dimension^2
	private OceanMap(int dimensions, int islandCount) {
		this.dimensions = dimensions;
		this.islandCount = islandCount;
		createGrid();
		placeIslands();
		placeShip();
		placePirate();
		placeShark();
		placeDoubleS();
		placeTreasure();
	}
	
	// Instantiates OceanMap in accordance with Singleton pattern
	public static OceanMap createOceanMapInstance(int dimensions, int islandCount) {
		oceanMap_instance = new OceanMap(dimensions, islandCount);
		return oceanMap_instance;
	}
	
	// May be unneeded, but returns the OceanMap if needs to be accessed again.
	public static OceanMap getOceanMapInstance() {
		return oceanMap_instance;
	} // Will return NULL if createOceanMapInstance isn't called first!!

	// Create an empty map
	private void createGrid() {
		grid = new boolean[dimensions][dimensions];
		for (int x = 0; x < dimensions; x++)
			for (int y = 0; y < dimensions; y++)
				grid[x][y] = false;
	}
	public void retry() {
		//placeIslands();
		placePirate();
		placeShark();
		placeDoubleS();
		placeTreasure();
		placeShip();
	}

	// Place islands onto map
	private void placeIslands() {
		int islandsToPlace = islandCount;
		int count = 0;
		while (islandsToPlace > 0) {
			int x = rand.nextInt(dimensions);
			int y = rand.nextInt(dimensions);
			if (!grid[x][y]) {
				grid[x][y] = true;
				islandsToPlace--;
				islands[count] = new Point(x, y);
				count++;
			}
		}
	}

	private void placeShip() {
		boolean placedShip = false;
		int x = 0, y = 0;
		while (!placedShip) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (!grid[x][y]) {
				placedShip = true;
				shipLocation = new Point(x,y);
				grid[x][y] =true;
			}
		}
	}

	private void placeShark() {
		boolean placedShark = false;
		int x = 0;
		int y = 0;
		while (!placedShark) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (!grid[x][y]) {
				grid[x][y] = true;
				sharkLocation = new Point(x, y);
				placedShark = true;
			}
		}
	}

	private void placeTreasure() {
		boolean placedTreasure = false;
		int x = 0;
		int y = 0;
		while (!placedTreasure) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (!grid[x][y]) {
				//grid[x][y] = true;
				treasureLocation = new Point(x, y);
				placedTreasure = true;
			}
		}
	}

	private void placeDoubleS() {
		boolean placedDouble = false;
		int x = 0;
		int y = 0;
		while (!placedDouble) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (!grid[x][y]) {
				grid[x][y] = true;

				DoubleLocation = new Point(x, y);
				placedDouble = true;
			}
		}
	}

	private void placePirate() {
		int x = 0, y = 0;
		int count = 0;
		while (count < pirates) {
			x = rand.nextInt(dimensions);
			y = rand.nextInt(dimensions);
			if (!grid[x][y]) {
				grid[x][y] = true;

				pirate[count] = new Point(x, y);
				count++;
			}
		}
	}

	public Point getShipLocation() {
		return shipLocation;
	}

	public Point[] getPirates() {
		return pirate;
	}

	public Point getSharkLocation() {
		return sharkLocation;
	}

	public Point getDoubleSpeed() {
		return DoubleLocation;
	}

	public Point[] getIslands() {
		return islands;
	}

	public Point getTreasureLocation() {
		return treasureLocation;
	}

	// Return generated map
	public boolean[][] getMap() {
		return grid;
	}

	public int getDimensions() {
		return dimensions;
	}

	public boolean isOcean(int x, int y) {
		if (x >= 0 && x < dimensions && y >= 0 && y < dimensions) {
			if (!grid[x][y])
				return true;
			return false;
		}
		return false;
	}

	public boolean checkL() {
		if (getSharkLocation().equals(getShipLocation())) {
			return false;
		}
		for (int i = 0; i < 2; i++) {
			if (getPirates()[i].equals(getShipLocation())) {
				return false;
			}
		}
		return true;
	}

	public boolean checkW() {
		if (getShipLocation().equals(getTreasureLocation())) {
			return true;
		}else {
			return false;
	}
		}
}