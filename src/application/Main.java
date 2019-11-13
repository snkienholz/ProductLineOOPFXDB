package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.Date;

/**
 * Main application file. Sets up scene and launches FX application.
 *
 * @author Sabrina
 */
public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getResource("productLine.fxml"));
    primaryStage.setTitle("Production Line Tracker");
    Scene scene = new Scene(root, 750, 600);
    primaryStage.setScene(scene);
    scene.getStylesheets().add(Main.class.getResource("WindowStyle.css").toExternalForm());
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
