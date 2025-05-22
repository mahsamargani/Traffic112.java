package view;

import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import model.Vehicle;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrafficView {
    private final Pane rootPane = new Pane();

    private final TrafficLight northLight = new TrafficLight();
    private final TrafficLight southLight = new TrafficLight();
    private final TrafficLight eastLight = new TrafficLight();
    private final TrafficLight westLight = new TrafficLight();

    private final Button startButton = new Button("Start");
    private final Button pauseButton = new Button("Pause");
    private final Button resetButton = new Button("Reset");

    private final Map<Vehicle, Rectangle> vehicleShapes = new HashMap<>();

    public TrafficView() {
        rootPane.setPrefSize(800, 600);

        // Yollar
        Rectangle verticalRoad = new Rectangle(380, 0, 80, 600);
        verticalRoad.setFill(Color.DARKGRAY);

        Rectangle horizontalRoad = new Rectangle(0, 280, 800, 80);
        horizontalRoad.setFill(Color.DARKGRAY);

        rootPane.getChildren().addAll(verticalRoad, horizontalRoad);

        // Işıklar
        northLight.setLayoutX(390);
        northLight.setLayoutY(250);

        southLight.setLayoutX(390);
        southLight.setLayoutY(350);

        eastLight.setLayoutX(480);
        eastLight.setLayoutY(290);

        westLight.setLayoutX(320);
        westLight.setLayoutY(290);

        rootPane.getChildren().addAll(northLight, southLight, eastLight, westLight);

        // Butonlar
        startButton.setLayoutX(50);
        startButton.setLayoutY(550);

        pauseButton.setLayoutX(150);
        pauseButton.setLayoutY(550);

        resetButton.setLayoutX(250);
        resetButton.setLayoutY(550);

        rootPane.getChildren().addAll(startButton, pauseButton, resetButton);
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public Button getStartButton() { return startButton; }
    public Button getPauseButton() { return pauseButton; }
    public Button getResetButton() { return resetButton; }

    public TrafficLight getNorthLight() { return northLight; }
    public TrafficLight getSouthLight() { return southLight; }
    public TrafficLight getEastLight() { return eastLight; }
    public TrafficLight getWestLight() { return westLight; }

    public void addVehicles(List<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            Rectangle rect = new Rectangle(20, 10);
            rect.setFill(Color.BLUE);
            rect.setX(v.getX());
            rect.setY(v.getY());
            vehicleShapes.put(v, rect);
            rootPane.getChildren().add(rect);
        }
    }

    public void updateVehiclePosition(Vehicle v) {
        Rectangle rect = vehicleShapes.get(v);
        if (rect != null) {
            rect.setX(v.getX());
            rect.setY(v.getY());
        }
    }
}
