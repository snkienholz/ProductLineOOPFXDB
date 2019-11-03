package application;

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

  public AudioPlayer(String nameAP, String manufacturerAP, String supportedAudioFormats,
      String supportedPlaylistFormats) {
    super(nameAP, manufacturerAP, "AUDIO");
    this.supportedAudioFormats = supportedAudioFormats;
    this.supportedPlaylistFormats = supportedPlaylistFormats;
  }

  public String toString() {
    return super.toString() + "\n"
        + "Supported Audio Formats: " + supportedAudioFormats + "\n"
        + "Supported Playlist Formats: " + supportedPlaylistFormats;
  }
}
