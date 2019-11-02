package application;

public class MoviePlayer extends Product implements MultimediaControl {

  Screen screen;
  MonitorType monitorType;

  @Override
  public void play() {
    System.out.println("Playing");
  }

  @Override
  public void stop() {
    System.out.println("Stopping");
  }

  @Override
  public void previous() {
    System.out.println("Previous");
  }

  @Override
  public void next() {
    System.out.println("Next");
  }

  public MoviePlayer(String nameMP, String manufacturerMP, Screen screenMP,
      MonitorType monitorTypeMP) {
    super(nameMP, manufacturerMP, "VISUAL");
    this.screen = screenMP;
    this.monitorType = monitorTypeMP;
  }

  public String toString() {
    return super.toString() + "\n"
        + "Screen: " + screen + "\n"
        + "Monitor Type: " + monitorType;
  }
}
