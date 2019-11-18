package snkienholz.github;

import java.util.Date;

/**
 * Creates a record of every product, creating an ID, unique serial number, and production date for
 * each product.
 */
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
    return (Date)dateProduced.clone();
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
    this.dateProduced = (Date)dateProduced.clone();
  }

  /**
   * Creates production record of a specific product ID.
   * @param productID - product ID containing production number, serial number, and date
   */
  public ProductionRecord(int productID) {
    this.productionNumber = 0;
    this.serialNumber = "0";
    this.dateProduced = new Date();
  }

  /**
   * Creates a detailed production record.
   * @param productionNumber - ID of the production
   * @param productID - ID of the product
   * @param serialNumber - product serial number
   * @param dateProduced - date the product is produced
   */
  public ProductionRecord(int productionNumber, int productID, String serialNumber,
      Date dateProduced) {
    this.productionNumber = productionNumber;
    this.productID = productID;
    this.serialNumber = serialNumber;
    this.dateProduced = (Date)dateProduced.clone();
  }

  /**
   * Overloaded constructor to accept a Product and an int for the number of items of
   * the Product type.
   * @param product - product being produced
   * @param itemCount - quantity of the product being produced
   */
  public ProductionRecord(Product product, int itemCount) {
    this(product.getId());
    setSerialNumber(product.getManufacturer().substring(0, 3)
        + product.getType()
        + "00000".substring(0, 5 - (Integer.toString(itemCount).length()))
        + itemCount);

  }

  /**
   * CREATES A STRING.
   * @return - returns A STRING for production record of a product
   */
  public String toString() {
    return "Prod. Num: " + this.productionNumber + " "
        + "Product ID: " + this.productID + " "
        + "Serial Number: " + this.serialNumber + " "
        + "Date: " + this.dateProduced;
  }
}
