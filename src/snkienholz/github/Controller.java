package snkienholz.github;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.io.FileInputStream;
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
 * Controller for the application that enables UI interaction and uses data from the PRODUCT
 * database.
 *
 * @author Sabrina
 */
public class Controller {

  private Connection conn;

  /**
   * Initializes the application with UI interaction.
   */
  public void initialize() {

    try {
      final String jdbcDriver = "org.h2.Driver";
      final String dbUrl = "jdbc:h2:./res/Product";

      //  Database credentials
      final String user = "";
      Properties prop = new Properties();
      prop.load(new FileInputStream("res/properties"));
      String pass = prop.getProperty("password");

      Class.forName(jdbcDriver);

      conn = DriverManager.getConnection(dbUrl, user, pass);

    } catch (ClassNotFoundException | SQLException | IOException e) {
      e.printStackTrace();
    }

    // set up the product line table and add its values to the list in the Produce tab
    setupProductLineTable();

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

    listViewProduct.setItems(productLine);

  }

  // creating an ObservableList of products for the Product Line tab
  ObservableList<Product> productLine = FXCollections.observableArrayList();

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
  //private ListView<Product> listViewProduct = new ListView<>(productLine);
  private ListView<Product> listViewProduct;

  // Table View and columns of the existing products under Product Line tab
  @FXML
  private TableView<Product> tvProducts;

  @FXML
  private TableColumn<?, ?> colProductName;
  @FXML
  private TableColumn<?, ?> colManufacturer;
  @FXML
  private TableColumn<?, ?> colItemType;

  // drop down list to select the product type
  @FXML
  private ChoiceBox<String> choiceItemType;

  // drop down list to select the quantity of products from the List View to be produced
  @FXML
  private ComboBox<Integer> cboQuantity;


  /**
   * Allows user to add products and stores them in the database.
   *
   * @param event Event handler from Scene Builder.
   */
  @FXML
  void addProduct(ActionEvent event) throws SQLException {

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

    /* the table is set up with the new product as a new one is added, and the List View in the
    Produce tab gets updated with the name of the added product */
    setupProductLineTable();
  }

  /**
   * Sets up and populates the Table View in the Product Line tab of the application.
   */
  public void setupProductLineTable() {

    colProductName.setCellValueFactory(new PropertyValueFactory<>("name"));
    colManufacturer.setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    colItemType.setCellValueFactory(new PropertyValueFactory<>("type"));

    tvProducts.setItems(productLine);

    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT");

      while (rs.next()) {
        String name = rs.getString("Name");
        String manufacturer = rs.getString("Manufacturer");
        String type = rs.getString("Type");

        // Widget extends the abstract class Product, and used to add products to the table
        productLine.add(new Widget(name, manufacturer, type));
      }

      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Records a log for every product to be produced when the Record Production button gets pressed.
   *
   * @param event - button is pressed
   */
  @FXML
  void recordProduction(ActionEvent event) {

    // creating a log out of a selected product and selected quantity
    //ProductionRecord log = new ProductionRecord(, cboQuantity.getValue());
  }
}
