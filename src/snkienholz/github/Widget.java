package snkienholz.github;

/**
 * Subclass used to allow use of the Product constructor.
 */
class Widget extends Product {

  public Widget(int id, String name, String manufacturer, String type) {
    super(id, name, manufacturer, type);
  }
}