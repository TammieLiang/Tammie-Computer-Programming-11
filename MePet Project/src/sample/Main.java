package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        //This is the music of the application. It is not created by me and is from the game
        // Cubic Castles. The music belongs to Cosmic Cow Games.
        Media musicFile = new Media("file:///Users/tamtamliang/Downloads/VLN/MePet%20Project/src/sample/resources/Cubic%20Castles%20-%20Cubic%20Field%20Tune.mp3");
        MediaPlayer music = new MediaPlayer(musicFile);
        music.setAutoPlay(true);
        music.setVolume(1);

        //this loops the application music
        music.setOnEndOfMedia(new Runnable() {
            public void run() {
                music.seek(Duration.ZERO);
            }
        });

        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("MePet ");
        primaryStage.setScene(new Scene(root, 773, 612));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);

    }
}
