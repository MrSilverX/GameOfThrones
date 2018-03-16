import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.awt.Component;
import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.util.*;

public class OceanExplorer extends Application {
	
	boolean[][] islandMap;
	Pane root;
	final int dimensions = 20;
	final int islandCount = 20;
	final int scale = 50;
	boolean GameOver = false;
	Text WText, LText;
	Image shipImage;
	Image pirateImage;
	Image islandImage;
	Image pirateLand;
	Image sharkImage;
	Image pirate2Image;
	Image treasureImage;

	ImageView treasureImageView;
	ImageView pirate2ImageView;
	ImageView shipImageView;
	ImageView[] pirateImageView;
	ImageView[] islandImageView;
	ImageView sharkImageView;

	OceanMap oceanMap;
	Scene scene;
	Ship ship;
	PirateShips pirateShips;
	littleShark shark;
	DoubleSpeed doubleSpeed;
	Stage classStage;
	// ArrayList<Movement> moveables;

	void startGame(Stage mapStage) {
		oceanMap = OceanMap.createOceanMapInstance(dimensions, islandCount); // for Singleton
		islandMap = oceanMap.getMap(); // Note: We will revisit this in a future class and use an iterator instead of
										// exposing the underlying representation!!!
		PirateShipFactory pirateShipFactory = new PirateShipFactory(); //Factory pattern for creating new objects
		
		islandImageView = new ImageView[oceanMap.getIslands().length];
		pirateImageView = new ImageView[oceanMap.getPirates().length];
		root = new AnchorPane();
		drawMap();
		classStage = mapStage;

		ship = (Ship) pirateShipFactory.getPirateShip("PlayerShip");
		pirateShips = (PirateShips) pirateShipFactory.getPirateShip("PirateShip");
		doubleSpeed = (DoubleSpeed) pirateShipFactory.getPirateShip("FastPirateShip");
		
		ship.addObserver(pirateShips);
		ship.addObserver(doubleSpeed);
		//ship.addObserver(shark);
		
		
		//shark = new littleShark(oceanMap);
		loadShipImage();
		
		
		
		
		
		scene = new Scene(root, 1200, 1000);
		mapStage.setTitle("Christopher Columbus Sails the Ocean Blue");
		mapStage.setScene(scene);
		mapStage.show();
		startSailing();
		buttons();
	}
	@Override
	public void start(Stage mapStage) throws Exception {
		startGame(mapStage);

	}

	private void loadShipImage() {
		// Load the ship image for player
		Image shipImage = new Image("ship.png", 50, 50, true, true);
		shipImageView = new ImageView(shipImage);
		shipImageView.setX(oceanMap.getShipLocation().x * scale);
		shipImageView.setY(oceanMap.getShipLocation().y * scale);
		root.getChildren().add(shipImageView);
		// moveables.add(ship);

		// Load the treasure Image
		Image treasureImage = new Image("treasure.png", 50, 50, true, true);
		treasureImageView = new ImageView(treasureImage);
		treasureImageView.setX(oceanMap.getTreasureLocation().x * scale);
		treasureImageView.setY(oceanMap.getTreasureLocation().y * scale);
		root.getChildren().add(treasureImageView);

		// Load the shark image
		Image sharkImage = new Image("LittleShark.jpg", 50, 50, true, true);
		sharkImageView = new ImageView(sharkImage);
		sharkImageView.setX(oceanMap.getSharkLocation().x * scale);
		sharkImageView.setY(oceanMap.getSharkLocation().y * scale);
		root.getChildren().add(sharkImageView);

		// Load the double speed ship
		Image pirate2Image = new Image("doubleS.png", 50, 50, true, true);
		pirate2ImageView = new ImageView(pirate2Image);
		pirate2ImageView.setX(oceanMap.getDoubleSpeed().x * scale);
		pirate2ImageView.setY(oceanMap.getDoubleSpeed().y * scale);
		root.getChildren().add(pirate2ImageView);

		// Load the pirate ships
		Image pirateImage = new Image("pirateShip.png", 50, 50, true, true);
		for (int i = 0; i < oceanMap.getPirates().length; i++) {
			pirateImageView[i] = new ImageView(pirateImage);
			pirateImageView[i].setX(oceanMap.getPirates()[i].x * scale);
			pirateImageView[i].setY(oceanMap.getPirates()[i].y * scale);
			root.getChildren().add(pirateImageView[i]);
		}
		// Load the island images
		Image islandImage = new Image("island.jpg", 50, 50, true, true);
		for (int i = 0; i < 20; i++) {
			islandImageView[i] = new ImageView(islandImage);
			islandImageView[i].setX(oceanMap.getIslands()[i].x * scale);
			islandImageView[i].setY(oceanMap.getIslands()[i].y * scale);
			root.getChildren().add(islandImageView[i]);
		}
	}
	private void buttons() {
		Button reset = new Button("ReSet the game");
		reset.setLayoutY(0);
		reset.setLayoutX(1000);
		reset.setOnAction(new EventHandler<ActionEvent>() {
		    @Override 
		    public void handle(ActionEvent e) {
		        reset.setText("reset");
		        classStage.close();
		        restart();
		    }
		});
		root.getChildren().add(reset);
	}

	private void startSailing() {
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent ke) {
				switch (ke.getCode()) {
				case RIGHT:
					ship.goEast();
					break;
				case LEFT:
					ship.goWest();
					break;
				case UP:
					ship.goNorth();
					break;
				case DOWN:
					ship.goSouth();
					break;
				default:
					break;
				}
				shipImageView.setX(ship.getShipLocation().x * scale);
				shipImageView.setY(ship.getShipLocation().y * scale);
				for (int i = 0; i < oceanMap.getPirates().length; i++) {
					pirateImageView[i].setX(pirateShips.getShipLocation()[i].x * scale);
					pirateImageView[i].setY(pirateShips.getShipLocation()[i].y * scale);

				}
				sharkImageView.setX(shark.getLittleShark().x * scale);
				sharkImageView.setY(shark.getLittleShark().y * scale);

				pirate2ImageView.setX(doubleSpeed.getDoubleSS().x * scale);
				pirate2ImageView.setY(doubleSpeed.getDoubleSS().y * scale);
				if (GameOver == false) {
					gg();
				}
			}
		});
	}

	// Draw ocean and islands
	public void drawMap() {
		for (int x = 0; x < dimensions; x++) {
			for (int y = 0; y < dimensions; y++) {
				Rectangle rect = new Rectangle(x * scale, y * scale, scale, scale);
				rect.setStroke(Color.BLACK);
				rect.setFill(Color.GREEN);
				root.getChildren().add(rect);
			}
		}
	}

	void restart() {
		classStage.close();
		Stage mapStage = new Stage();
		startGame(mapStage);
	}
	
	public void gg() {
		if (oceanMap.checkW() == true) {
			Object[] options = {"Quit","Retry"};
			Component frame = null;
			int result = JOptionPane.showOptionDialog(frame,  "You Won!", "Winner Diaglog Message",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, frame);
			if (result == 0) System.exit(0);
			if (result == 1) restart(); 
	}
		if (oceanMap.checkL() == false) {
			Object[] options = {"Quit","Retry"};
			Component frame = null;
			int result = JOptionPane.showOptionDialog(frame,  "You lost!", "Loser Diaglog Message",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,options, frame);
			if (result == 0) System.exit(0); 
			if (result == 1) restart(); 
		} 
	}

	public static void main(String[] args) {
		launch(args);
	}

}