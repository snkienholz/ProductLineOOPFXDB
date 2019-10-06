package application;

// Item pre-set types under Product Line tab
public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");

  public String code;

  ItemType(String code) {
    this.code = code;
  }
}