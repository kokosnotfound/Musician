package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Musician");
        primaryStage.setScene(new Scene(root, 673, 200));
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("https://github.com/Maksiooo/Musician/blob/main/logo.jpg?raw=true"));
        primaryStage.show();

        DiscordRPC.startRPC("Listening to music", "logo");
        UpdateChecker.checkForUpdates(1.0);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void stop() {
        System.out.println("Good bye");
        System.exit(0);
    }
}
