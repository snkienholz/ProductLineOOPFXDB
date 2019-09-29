package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

public class Controller {

  @FXML
  private Tab productLine;

  @FXML
  private GridPane productLineInfo;

  @FXML
  private Button btnAddProduct;

  @FXML
  private AnchorPane tblExistingProducts;

  @FXML
  private Button btnRecordProduction;

  @FXML
  private Tab produce;

  @FXML
  private Tab productionLog;

  @FXML
  void addProduct(ActionEvent event) {
    System.out.println("hi");
  }

  @FXML
  void recordProduction(ActionEvent event) {
    System.out.println("bon giorno");
  }
}
