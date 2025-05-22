package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Direction;
import model.TrafficModel;
import model.Vehicle;
import view.TrafficView;

import java.util.List;

public class MainController {
    private final TrafficModel model;
    private final TrafficView view;

    private Timeline timeline;

    public MainController(TrafficModel model, TrafficView view) {
        this.model = model;
        this.view = view;

        // Araçları görünümde göster
        view.addVehicles(model.getNorthCars());
        view.addVehicles(model.getSouthCars());
        view.addVehicles(model.getEastCars());
        view.addVehicles(model.getWestCars());

        // Buton olayları
        view.getStartButton().setOnAction(e -> startSimulation());
        view.getPauseButton().setOnAction(e -> pauseSimulation());
        view.getResetButton().setOnAction(e -> resetSimulation());
    }

    private void startSimulation() {
        if (timeline != null) {
            timeline.stop();
        }

        timeline = new Timeline(new KeyFrame(Duration.millis(30), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void pauseSimulation() {
        if (timeline != null) {
            timeline.stop();
        }
    }

    private void resetSimulation() {
        pauseSimulation();

        // Araçları başlangıç pozisyonlarına getir
        for (Vehicle v : model.getNorthCars()) resetVehiclePosition(v);
        for (Vehicle v : model.getSouthCars()) resetVehiclePosition(v);
        for (Vehicle v : model.getEastCars()) resetVehiclePosition(v);
        for (Vehicle v : model.getWestCars()) resetVehiclePosition(v);

        // Işıkları kırmızı yap
        setLights("RED", "RED", "RED", "RED");

        // Araçları görünümde güncelle
        for (Vehicle v : model.getNorthCars()) view.updateVehiclePosition(v);
        for (Vehicle v : model.getSouthCars()) view.updateVehiclePosition(v);
        for (Vehicle v : model.getEastCars()) view.updateVehiclePosition(v);
        for (Vehicle v : model.getWestCars()) view.updateVehiclePosition(v);
    }

    private void update() {
        // 60 saniyelik döngü örneği
        double cycleTime = (System.currentTimeMillis() / 1000.0) % 60;

        if (cycleTime < 25) {
            // Kuzey-Güney Yeşil, Doğu-Batı Kırmızı
            setLights("GREEN", "GREEN", "RED", "RED");
            moveVehicles(true, true, false, false);
        } else if (cycleTime < 30) {
            // Sarı ışık (kuzey-güney)
            setLights("YELLOW", "YELLOW", "RED", "RED");
            moveVehicles(false, false, false, false);
        } else if (cycleTime < 55) {
            // Doğu-Batı Yeşil, Kuzey-Güney Kırmızı
            setLights("RED", "RED", "GREEN", "GREEN");
            moveVehicles(false, false, true, true);
        } else {
            // Sarı ışık (doğu-batı)
            setLights("RED", "RED", "YELLOW", "YELLOW");
            moveVehicles(false, false, false, false);
        }
    }

    private void setLights(String north, String south, String east, String west) {
        view.getNorthLight().setLight(north);
        view.getSouthLight().setLight(south);
        view.getEastLight().setLight(east);
        view.getWestLight().setLight(west);
    }

    private void moveVehicles(boolean northMoving, boolean southMoving, boolean eastMoving, boolean westMoving) {
        moveList(model.getNorthCars(), northMoving);
        moveList(model.getSouthCars(), southMoving);
        moveList(model.getEastCars(), eastMoving);
        moveList(model.getWestCars(), westMoving);
    }

    private void moveList(List<Vehicle> vehicles, boolean moving) {
        if (!moving) return;

        for (Vehicle v : vehicles) {
            moveVehicle(v);
            view.updateVehiclePosition(v);
        }
    }

    private void moveVehicle(Vehicle v) {
        double speed = 1.5;

        switch (v.getDirection()) {
            case NORTH -> v.setY(v.getY() - speed);
            case SOUTH -> v.setY(v.getY() + speed);
            case EAST -> v.setX(v.getX() + speed);
            case WEST -> v.setX(v.getX() - speed);
        }

        if (v.getX() < -30 || v.getX() > 830 || v.getY() < -30 || v.getY() > 630) {
            resetVehiclePosition(v);
        }
    }

    private void resetVehiclePosition(Vehicle v) {
        switch (v.getDirection()) {
            case NORTH -> { v.setX(390); v.setY(600); }
            case SOUTH -> { v.setX(410); v.setY(-50); }
            case EAST -> { v.setX(-50); v.setY(390); }
            case WEST -> { v.setX(830); v.setY(410); }
        }
    }
}
