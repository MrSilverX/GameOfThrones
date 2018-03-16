
//package joeyVersion;

import java.util.Observable;
import java.util.Observer;
import java.awt.Point;

// A list (Point[]) of all PirateShips in the game.

public class PirateShips implements Observer, FactoryInterface {
	Point[] currentLocation;
	OceanMap oceanMap;
	// Point shipLocation;

	public PirateShips(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getPirates();
	}

	/*
	public PirateShips() {
		currentLocation = oceanMap.getPirates();
	}
	*/

	public Point[] getShipLocation() {
		return currentLocation;
	}

	public void goEast(Point p) {
		if (p.x < oceanMap.getDimensions() && oceanMap.isOcean(p.x + 1, p.y))
			p.x++;
	}

	public void goWest(Point p) {
		if (p.x > 0 && oceanMap.isOcean(p.x - 1, p.y))
			p.x--;
	}

	public void goNorth(Point p) {
		if (p.y > 0 && oceanMap.isOcean(p.x, p.y - 1))
			p.y--;
	}

	public void goSouth(Point p) {
		if (p.y < oceanMap.getDimensions() && oceanMap.isOcean(p.x, p.y + 1))
			p.y++;
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Ship) {
			Point shipLocation = ((Ship) o).getShipLocation();

			System.out.println(); // debug
			for (Point p : currentLocation) {
				boolean hasMoved = false;
				if (p.x < shipLocation.x && !hasMoved) {
					if (p.x < oceanMap.getDimensions() && oceanMap.isOcean(p.x + 1, p.y)) {
						goEast(p);
						hasMoved = true;
					}
				}
				if (p.x > shipLocation.x && !hasMoved) {
					if (p.x > 0 && oceanMap.isOcean(p.x - 1, p.y)) {
						goWest(p);
						hasMoved = true;
					}
				}
				if (p.y > shipLocation.y && !hasMoved) {
					if (p.y > 0 && oceanMap.isOcean(p.x, p.y - 1)) {
						goNorth(p);
						hasMoved = true;
					}
				}
				if (p.y < shipLocation.y && !hasMoved) {
					if (p.y < oceanMap.getDimensions() && oceanMap.isOcean(p.x, p.y + 1)) {
						goSouth(p);
						hasMoved = true;
					}
				}
				System.out.println(p.toString()); // debug
			}
			System.out.println(); // debug
		}
	}

	@Override
	public void create(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getPirates();
	}	
}
