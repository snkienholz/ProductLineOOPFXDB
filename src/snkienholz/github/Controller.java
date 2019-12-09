package snkienholz.github;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
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
    loadProductionLog();

    // ComboBox - to select product quantity
    for (int i = 1; i <= 10; i++) {
      cboQuantity.getItems().add(String.valueOf(i));
    }
    cboQuantity.setEditable(true);
    cboQuantity.getSelectionModel().selectFirst();

    // ChoiceBox - to select product item type
    for (ItemType item : ItemType.values()) {
      choiceItemType.getItems().add(String.valueOf(item));
    }

    // updating the ListView with product info from the ObservableList productLine
    listViewProduct.setItems(productLine);
  }

  // creating an ObservableList of products for the Product Line tab
  ObservableList<Product> productLine = FXCollections.observableArrayList();

  ArrayList<ProductionRecord> productionRun = new ArrayList<>();
  ArrayList<ProductionRecord> productionLog = new ArrayList<>();

  @FXML
  private GridPane productLineInfo;

  @FXML
  private AnchorPane tblExistingProducts;

  @FXML
  private Button btnAddProduct;
  @FXML
  private Button btnRecordProduction;

  @FXML
  private Label lblInvalidPassword;

  @FXML
  private Tab produce;
  //@FXML
  //private Tab productionLog;

  @FXML
  private TextArea txtAreaProdLog;

  // Product Line text fields
  @FXML
  private TextField txtProductName;
  @FXML
  private TextField txtManufacturer;

  // Employee tab text fields
  @FXML
  private TextField txtEmployeeName;
  @FXML
  private TextField txtPassword;

  @FXML
  private Button btnConfirm;

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
  private ComboBox<String> cboQuantity;

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
        int productID = rs.getInt("ID");

        // Widget extends the abstract class Product, and used to add products to the table
        productLine.add(new Widget(productID, name, manufacturer, type));
      }

      stmt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Records a log for every product to be produced when the Record Production button gets pressed.
   *
   * @param event - record production button is pressed
   */
  @FXML
  void recordProduction(ActionEvent event) {

    // getting selected product and quantity
    Product selectedProduct = listViewProduct.getSelectionModel().getSelectedItem();
    int selectedQuantity = Integer.parseInt(cboQuantity.getValue());

    // clearing ArrayList before adding new products
    productionRun.clear();

    // creating a new ProductionRecord object with first ProductionRecord constructor
    for (int i = 0; i < selectedQuantity; i++) {
      ProductionRecord pr = new ProductionRecord(selectedProduct, selectedQuantity);

      // setting product ID (name), serial number, & date produced
      pr.setProductID(selectedProduct.getId());

      // populating ArrayList with object
      productionRun.add(pr);
    }

    addToProductionDB();
    loadProductionLog();
  }

  /**
   * Loops through productionRun, inserting productionRecord object information into the
   * ProductionRecord database table.
   */
  void addToProductionDB() {

    try {
      String sql = "INSERT INTO PRODUCTIONRECORD(PRODUCT_ID, SERIAL_NUM, DATE_PRODUCED) "
          + "VALUES (?, ?, ?)";
      PreparedStatement preparedStatement = conn.prepareStatement(sql);

      // inserting ArrayList productionRun information into PRODUCTIONRECORD table
      for (ProductionRecord item : productionRun) {

        // filling out Production Record table with set values
        preparedStatement.setInt(1, item.getProductID());
        preparedStatement.setString(2, item.getSerialNumber());
        preparedStatement.setTimestamp(3, new Timestamp(item.getDateProduced().getTime()));

        // update table with values
        preparedStatement.executeUpdate();
      }

      preparedStatement.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Populates the TextArea on the Production Log tab with the information from the productionLog,
   * replacing the productID with the product name, with one line for each product produced.
   */
  void showProduction() {

    txtAreaProdLog.setText("");

    for (ProductionRecord log : productionLog) {

      String productName = "";

      for (Product p : productLine) {

        if (p.getId() == log.getProductID()) {
          productName = p.getName();
        }
      }

      // using a simple date format to convert unix timestamp code to human date
      // M = month number w/o leading zeroes, d = day w/o leading zeroes, h = 12-hour time w/o
      // leading zeroes, a = AM/PM labels
      SimpleDateFormat sdf = new SimpleDateFormat("M/d/yyyy h:mm:ss a");
      String date = sdf.format(log.getDateProduced());

      String productInfo = "Production Number: " + log.getProductionNumber() + "  Product: "
          + productName + "  Serial #: " + log.getSerialNumber() + "  Date Produced: " + date;

      txtAreaProdLog.setText(txtAreaProdLog.getText() + productInfo + "\n");
    }
  }

  /**
   * Creates ProductionRecord objects from the records in the ProductionRecord database table,
   * populates productionRun ArrayList, and calls showProduction() to display the information.
   */
  void loadProductionLog() {

    try {
      Statement stmt = conn.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTIONRECORD");

      // clearing ArrayList before adding new products to log
      productionLog.clear();

      while (rs.next()) {

        // from ProductionRecord: int productionNumber, int productID, String serialNumber,
        //      Date dateProduced
        int productionNum = rs.getInt("PRODUCTION_NUM");
        int productID = rs.getInt("PRODUCT_ID");
        String serialNum = rs.getString("SERIAL_NUM");
        Date dateProduced = rs.getTimestamp("DATE_PRODUCED");

        // new ProductionRecord object using the above values from the corresponding row
        ProductionRecord newProduct = new ProductionRecord(productionNum, productID, serialNum,
            dateProduced);

        // adding new product to the productionLog ArrayList
        productionLog.add(newProduct);
      }

      stmt.close();

      showProduction();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  @FXML
  void confirmCredentials(ActionEvent event) {

    Alert confirmation = new Alert(AlertType.INFORMATION);

    Employee e = new Employee(txtEmployeeName.getText(), txtPassword.getText());

    if (txtEmployeeName.getText().isEmpty() || txtPassword.getText().isEmpty()){

      lblInvalidPassword.setText("Please fill out all fields.");
    } else if (!e.isValidPassword(txtPassword.getText())) {

      lblInvalidPassword.setText("Invalid password. \nMust contain at least one lowercase, "
          + "one uppercase, and one special character. \nPlease try again." );
    } else {
      lblInvalidPassword.setText("");

      confirmation.setContentText(e.toString() + "\n" + "Reversed Password: "
          + e.reverseString(txtPassword.getText()));

      confirmation.show();
    }

  }
}
