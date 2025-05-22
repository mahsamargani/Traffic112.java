package view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class TrafficLight extends VBox {
    private final Circle redLight = createLight(Color.DARKRED);
    private final Circle yellowLight = createLight(Color.DARKGOLDENROD);
    private final Circle greenLight = createLight(Color.DARKGREEN);

    public TrafficLight() {
        Rectangle box = new Rectangle(40, 120);
        box.setArcWidth(20);
        box.setArcHeight(20);
        box.setFill(Color.DARKGRAY);

        setSpacing(10);
        setAlignment(Pos.CENTER);
        getChildren().addAll(box, redLight, yellowLight, greenLight);

        setLight("RED");
    }

    private Circle createLight(Color color) {
        Circle circle = new Circle(12);
        circle.setFill(color);
        circle.setStroke(Color.BLACK);
        return circle;
    }

    public void setLight(String color) {
        redLight.setFill(Color.DARKRED);
        yellowLight.setFill(Color.DARKGOLDENROD);
        greenLight.setFill(Color.DARKGREEN);

        switch (color.toUpperCase()) {
            case "RED": redLight.setFill(Color.RED); break;
            case "YELLOW": yellowLight.setFill(Color.YELLOW); break;
            case "GREEN": greenLight.setFill(Color.LIMEGREEN); break;
        }
    }
}
