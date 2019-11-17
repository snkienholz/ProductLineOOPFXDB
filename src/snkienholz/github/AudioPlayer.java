package snkienholz.github;

/**
 * Captures and displays the details of audio players.
 */
public class AudioPlayer extends Product implements MultimediaControl {

  String supportedAudioFormats;

  String supportedPlaylistFormats;

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
   * Creates an Audio Player.
   * @param nameAP - audio player name
   * @param manufacturerAP - audio player manufacturer
   * @param supportedAudioFormats - supported audio player format
   * @param supportedPlaylistFormats - supported playlist format
   */
  public AudioPlayer(String nameAP, String manufacturerAP, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(nameAP, manufacturerAP, "AUDIO");
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  /**
   * CREATES A STRING.
   * @return - returns A STRING of the supported audio and playlist formats
   */
  public String toString() {
    return super.toString() + "\n"
        + "Supported Audio Formats: " + supportedAudioFormats + "\n"
        + "Supported Playlist Formats: " + supportedPlaylistFormats;
  }
}
