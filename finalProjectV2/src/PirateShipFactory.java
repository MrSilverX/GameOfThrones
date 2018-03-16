

public class PirateShipFactory {
	public FactoryInterface getPirateShip(String pirateShipType) {
		OceanMap oceanMap = OceanMap.getOceanMapInstance();
		
		if (pirateShipType == null) {
			return null;
		}
		if (pirateShipType.equals("PirateShip")) {
			return new PirateShips(oceanMap);
		}
		if (pirateShipType.equals("FastPirateShip")){
			return new DoubleSpeed(oceanMap);
		}
		if (pirateShipType.equals("PlayerShip")) {
			return new Ship(oceanMap);
		}
		return null;
	}
}
