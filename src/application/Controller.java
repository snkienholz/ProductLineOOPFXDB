package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Full application controller.
 *
 * @author Sabrina
 */
public class Controller {

  private Connection conn;
  final static String jdbcDriver = "org.h2.Driver";
  final static String dbUrl = "jdbc:h2:./res/Product";

  //  Database credentials
  final static String user = "";
  final static String pass = "";

  public void initialize() {

    try {
      Class.forName(jdbcDriver);

      conn = DriverManager.getConnection(dbUrl, user, pass);

    } catch (ClassNotFoundException | SQLException e) {
      e.printStackTrace();
    }

    setupProductLineTable();
    addToList();

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
  private Tab produce;
  @FXML
  private Tab productionLog;

  // Product Line text fields
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufacturer;

  // List View showing all existing products
  @FXML
  private ListView<String> listViewProduct;

  // Table View and columns of the existing products under Product Line tab
  @FXML
  private TableView<Product> tvProducts;

  @FXML
  private TableColumn<?, ?> colProductName;
  @FXML
  private TableColumn<?, ?> colManufacturer;
  @FXML
  private TableColumn<?, ?> colItemType;

  @FXML
  private ChoiceBox<String> choiceItemType;

  @FXML
  private ComboBox<Integer> cboQuantity;

  // creating an ObservableList of products for the Product Line tab
  ObservableList<Product> productLine = FXCollections.observableArrayList();

  /**
   * Allows user to add products and stores them in the database.
   *
   * @param event Event handler from Scene Builder.
   */
  @FXML
  void addProduct(ActionEvent event) throws SQLException {
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

    initialize();
  }

  public void setupProductLineTable() {

    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

      colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
      colManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
      colItemType.setCellValueFactory(new PropertyValueFactory<>("type"));

      tvProducts.setItems(productLine);

      while (rs.next()) {
        String name = rs.getString("Name");
        String manufacturer = rs.getString("Manufacturer");
        String type = rs.getString("Type");
        productLine.add(new Widget(name, manufacturer, type));
      }

    } catch (SQLException e){
      e.printStackTrace();
    }
  }

  /**
   *
   */
  public void addToList() {

    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT NAME FROM PRODUCT");

      /* while (rs.next()){

        String product = rs.getString("NAME");
        listViewProduct.getItems().addAll(product);
        initialize();
      } */

      while (rs.next()) {
        String productName = rs.getString("NAME");

        if (listViewProduct.getItems().contains(productName)) {
          System.out.println("");
        } else {
          listViewProduct.getItems().addAll(productName);
          initialize();
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void recordProduction(ActionEvent event) {
  }
}
