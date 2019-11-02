package application;

import java.util.Date;

public class ProductionRecord {
  // unique for every item produced and get auto incremented by the database
  private int productionNumber;
  // corresponding product ID from Product table/class
  private int productID;
  private String serialNumber;
  private Date dateProduced;

  /* ACCESSOR METHODS */
  public int getProductionNumber() {
    return productionNumber;
  }
  public int getProductID() {
    return productID;
  }
  public String getSerialNumber() {
    return serialNumber;
  }
  public Date getDateProduced() {
    return dateProduced;
  }

  /* SETTER METHODS */
  public void setProductionNumber(int productionNumber) {
    this.productionNumber = productionNumber;
  }
  public void setProductID(int productID) {
    this.productID = productID;
  }
  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }
  public void setDateProduced(Date dateProduced) {
    this.dateProduced = dateProduced;
  }

  public ProductionRecord (int productID) {
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  public ProductionRecord (int productionNumber, int productID, String serialNumber, Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = dateProduced;
  }

  public String toString() {
    return "Prod. Num: " + this.productionNumber +
        "Product ID: " + this.productID +
        "Serial Number: " + this.serialNumber +
        "Date: " + this.dateProduced;
  }
}
