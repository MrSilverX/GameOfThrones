import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

public class DoubleSpeed implements Observer, FactoryInterface {
	Point currentLocation;
	OceanMap oceanMap;
	Point shipLocation;

	public DoubleSpeed(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getDoubleSpeed();
	}
	
	/*
	public void PirateShips() {
		currentLocation = oceanMap.getDoubleSpeed();
	}
	*/

	public Point getDoubleSS() {
		return currentLocation;
	}

	public void goNorth(Point p) {
		if (p.y > 0 && oceanMap.isOcean(p.x, p.y - 1)) {
			p.y -= 2;
		}
	}

	public void goEast(Point p) {
		if (p.x < 9 && oceanMap.isOcean(p.x + 1, p.y)) {
			p.x += 2;
		}
	}

	public void goWest(Point p) {
		if (p.x > 0 && oceanMap.isOcean(p.x - 1, p.y)) {
			p.x -= 2;
		}
	}

	public void goSouth(Point p) {
		if (p.y < 9 && oceanMap.isOcean(p.x, p.y + 1)) {
			p.y += 2;
		}
	}

	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof Ship) {
			shipLocation = ((Ship) arg0).getShipLocation();
			if (shipLocation.x < currentLocation.x) {
				if (currentLocation.x > 0 && oceanMap.isOcean(currentLocation.x - 2, currentLocation.y)) {
					goWest(currentLocation);
				}
			}
			if (shipLocation.y > currentLocation.y) {
				if (currentLocation.y < oceanMap.getDimensions()
						&& oceanMap.isOcean(currentLocation.x, currentLocation.y + 2)) {
					goSouth(currentLocation);
				}

			}
			if (shipLocation.y < currentLocation.y) {
				if (currentLocation.y > 0 && oceanMap.isOcean(currentLocation.x, currentLocation.y - 2)) {
					goNorth(currentLocation);
				}
			}
			if (shipLocation.x > currentLocation.x) {
				if (currentLocation.x < oceanMap.getDimensions()
						&& oceanMap.isOcean(currentLocation.x + 2, currentLocation.y)) {
					goEast(currentLocation);
				}
			}
		}
	}
	@Override
	public void create(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getDoubleSpeed();
	}
}
