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
    /*// test constructor used when creating production records from user interface
    Integer numProduced = 3;  // this will come from the combobox in the UI

    for (int productionRunProduct = 0; productionRunProduct < numProduced; productionRunProduct++) {
      ProductionRecord pr = new ProductionRecord(0);
      System.out.println(pr.toString());
    }

    // test constructor used when creating production records from reading database
    ProductionRecord pr = new ProductionRecord(0, 3, "1", new Date());
    System.out.println(pr.toString());

    // testing accessors and mutators
    pr.setProductionNumber(1);
    System.out.println(pr.getProductionNumber());

    pr.setProductID(4);
    System.out.println(pr.getProductID());

    pr.setSerialNumber("2");
    System.out.println(pr.getSerialNumber());

    pr.setDateProduced(new Date());
    System.out.println(pr.getDateProduced());
*/
  }
}
