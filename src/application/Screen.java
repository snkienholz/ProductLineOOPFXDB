package application;

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

  public Screen (String resolution, int refreshrate, int responsetime) {
    this.resolution = resolution;
    this.refreshrate = refreshrate;
    this.responsetime = responsetime;
  }

  public String toString() {
    return "Screen:" + "\n"
        + "Resolution: " + resolution + "\n"
        + "Refresh rate: " + refreshrate + "\n"
        + "Response time: " + responsetime;
  }
}
