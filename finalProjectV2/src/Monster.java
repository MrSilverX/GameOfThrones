import java.util.Observable;
import java.util.Observer;
import javafx.scene.image.ImageView;
import java.awt.Point;

public class Monster implements Observer {
	Point currentLocation;
	OceanMap oceanMap;
	OceanExplorer xs;
	ImageView s;

	public Monster(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
		currentLocation = oceanMap.getMonsterLocation();
		xs = new OceanExplorer();
	}

	public Point getMonster() {
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
			// xs.gg();
			while (true) {
				if (currentLocation.x < shipLocation.x) {
					if (currentLocation.x < oceanMap.getDimensions()
							&& oceanMap.isOcean(currentLocation.x + 1, currentLocation.y)) {
						goEast(currentLocation);
						// xs.gg();

						break;
					}
				}
				if (currentLocation.x > shipLocation.x) {
					if (currentLocation.x > 0 && oceanMap.isOcean(currentLocation.x - 1, currentLocation.y)) {
						goWest(currentLocation);
						// xs.gg();

						break;
					}
				}
				if (currentLocation.y > shipLocation.y) {
					if (currentLocation.y > 0 && oceanMap.isOcean(currentLocation.x, currentLocation.y - 1)) {
						goNorth(currentLocation);
						// xs.gg();
						break;
					}
				}
				if (currentLocation.y < shipLocation.y) {
					if (currentLocation.y < oceanMap.getDimensions()
							&& oceanMap.isOcean(currentLocation.x, currentLocation.y + 1)) {
						goSouth(currentLocation);
						// xs.gg();

						break;
					}
				}

			}

		}
	}
}
