package snkienholz.github;

/**
 * Defines a product with a name, manufacturer, product type, and ID.
 */

public abstract class Product implements Item {

  private int id;
  private String type;
  private String manufacturer;
  private String name;

  public int getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  Product(String name, String manufacturer, String type) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.type = type;
  }

  /**
   * Displays product information for testing.
   *
   * @return A string.
   */
  public String toString() {
    return "Name: " + name + "\n"
        + "Manufacturer: " + manufacturer + "\n"
        + "Type: " + type;
  }
}

/**
 * Subclass used to allow use of the Product constructor.
 */
class Widget extends Product {

  public Widget(String name, String manufacturer, String type) {
    super(name, manufacturer, type);
  }
}



