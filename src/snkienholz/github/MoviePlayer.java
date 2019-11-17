package snkienholz.github;

/**
 * Captures and displays the details of movie players.
 */
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

  /**
   * Creates a movie player.
   * @param nameMP - movie player name
   * @param manufacturerMP - movie player manufacturer
   * @param screenMP - movie player screen
   * @param monitorTypeMP - movie player's monitor type
   */
  public MoviePlayer(String nameMP, String manufacturerMP, Screen screenMP,
      MonitorType monitorTypeMP) {
    super(nameMP, manufacturerMP, "VISUAL");
    this.screen = screenMP;
    this.monitorType = monitorTypeMP;
  }

  /**
   * CREATES A STRING.
   * @return - returns A STRING of the monitor's details
   */
  public String toString() {
    return super.toString() + "\n"
        + "Screen: " + screen + "\n"
        + "Monitor Type: " + monitorType;
  }
}
