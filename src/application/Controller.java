package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Full application controller.
 *
 * @author Sabrina
 */
public class Controller {

  private Connection conn;

  /**
   * Initializing connection to database.
   */
  public void initialize() {

    final String jdbcDriver = "org.h2.Driver";
    final String dbUrl = "jdbc:h2:./res/Product";

    //  Database credentials
    final String user = "";
    final String pass = "";

    try {
      Class.forName(jdbcDriver);

      conn = DriverManager.getConnection(dbUrl, user, pass);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    // ComboBox
    for (int i = 1; i <= 10; i++) {
      cboQuantity.getItems().add(i);
    }
    cboQuantity.setEditable(true);
    cboQuantity.getSelectionModel().selectFirst();

    // ChoiceBox
    for (ItemType item : ItemType.values()) {
      choiceItemType.getItems().add(String.valueOf(item));
    }
  }

  @FXML
  private GridPane productLineInfo;

  @FXML
  private AnchorPane tblExistingProducts;

  @FXML
  private Button btnAddProduct;

  @FXML
  private Button btnRecordProduction;

  @FXML
  private Tab productLine;

  @FXML
  private Tab produce;

  @FXML
  private Tab productionLog;

  // Product Line text fields
  @FXML
  private TextField txtProductName;

  @FXML
  private TextField txtManufacturer;

  @FXML
  private TextArea txtAreaProdLog;

  @FXML
  private ChoiceBox<String> choiceItemType;

  @FXML
  private ComboBox<Integer> cboQuantity;

  /**
   * Allows user to add products and stores them in the database.
   *
   * @param event Event handler from Scene Builder.
   */
  @FXML
  void addProduct(ActionEvent event) {
    // as an OOP approach, create a new Object that takes in each parameter and call the methods
    // instead of using a String
    String sql = "INSERT INTO Product(type, manufacturer, name) VALUES (?, ?, ?)";
    try {
      PreparedStatement preparedStatement = conn.prepareStatement(sql);

      preparedStatement.setString(1, choiceItemType.getValue());
      preparedStatement.setString(2, txtManufacturer.getText());
      preparedStatement.setString(3, txtProductName.getText());

      preparedStatement.executeUpdate();

      preparedStatement.close();
    } catch (SQLException e) {
      // handle errors for JDBC
      e.printStackTrace();
    }
  }

  @FXML
  void recordProduction(ActionEvent event) {
  }
}
