package snkienholz.github;

/**
 * Item pre-set types under Product Line tab.
 */

public enum ItemType {
  Audio("AU"),
  Visual("VI"),
  AudioMobile("AM"),
  VisualMobile("VM");

  public final String code;

  ItemType(String code) {
    this.code = code;
  }
}