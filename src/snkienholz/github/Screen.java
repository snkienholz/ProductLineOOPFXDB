package snkienholz.github;

/**
 * Captures details of a monitor screen.
 */
public class Screen implements ScreenSpec {

  String resolution;
  int refreshrate;
  int responsetime;

  @Override
  public String getResolution() {
    return resolution;
  }

  @Override
  public int getRefreshRate() {
    return refreshrate;
  }

  @Override
  public int getResponseTime() {
    return responsetime;
  }

  /**
   * Creates a screen.
   * @param resolution - screen resolution
   * @param refreshrate - screen refresh rate
   * @param responsetime - screen response time
   */
  public Screen(String resolution, int refreshrate, int responsetime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }

  /**
   * CREATES A STRING.
   * @return - returns A STRING of a screen details
   */
  public String toString() {
    return "Screen:" + "\n"
        + "Resolution: " + resolution + "\n"
        + "Refresh rate: " + refreshrate + "\n"
        + "Response time: " + responsetime;
  }
}
