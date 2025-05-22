package app;
import controller.MainController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.TrafficModel;
import view.TrafficView;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        TrafficModel model = new TrafficModel();
        TrafficView view = new TrafficView();
        new MainController(model, view);

        Scene scene = new Scene(view.getRootPane());

        stage.setTitle("Akıllı Trafik Işıkları Simülasyonu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
