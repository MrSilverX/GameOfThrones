//package joeyVersion;

import java.awt.Point;
import java.util.Observable;

public class Ship extends Observable implements Movement, FactoryInterface {
    Point currentLocation;
    OceanMap oceanMap;
    
    public Ship(OceanMap oceanMap){    	
    	this.oceanMap = oceanMap;
    	currentLocation = oceanMap.getShipLocation();
    }
    
    public Point getShipLocation(){
    	return currentLocation;
    }
    
    public void goEast() {
    	if(currentLocation.x<oceanMap.getDimensions()-1 && oceanMap.isOcean(currentLocation.x+1, currentLocation.y)){
    		currentLocation.x++;
    		setChanged();
    		notifyObservers(currentLocation);
    	}
    }
    
    public void goWest() {
    	if(currentLocation.x >0 && oceanMap.isOcean(currentLocation.x-1, currentLocation.y)){
    		currentLocation.x--;
    		setChanged();
    		notifyObservers(currentLocation);
    	}
    }
    
    public void goNorth() {
    	if(currentLocation.y>0 && oceanMap.isOcean(currentLocation.x, currentLocation.y-1)){
    		currentLocation.y--;
    		setChanged();
    		notifyObservers(currentLocation);
    	}
    }
    
    public void goSouth() {
    	if(currentLocation.y<oceanMap.getDimensions()-1 && oceanMap.isOcean(currentLocation.x, currentLocation.y+1)){
    		currentLocation.y++;
    		setChanged();
    		notifyObservers(currentLocation);
    	}
    }

	

	@Override
	public void create(OceanMap oceanMap) {
		this.oceanMap = oceanMap;
    	currentLocation = oceanMap.getShipLocation();
	}

	
}
