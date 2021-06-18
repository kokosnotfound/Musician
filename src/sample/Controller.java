package sample;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements Initializable {
    @FXML
    private Pane main;

    @FXML
    private Label songNameLabel, timeLabel;

    @FXML
    private Button playBtn, pauseBtn, selectFileBtn, resetBtn;

    @FXML
    private Slider timeSlider;

    private Media media;
    private MediaPlayer mediaPlayer;

    String filePath;

    private Timer timer;
    private TimerTask task;
    private boolean isMusicRunning;

    public void selectFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Select a file (*.mp3)", "*.mp3");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(null);
        filePath = file.toURI().toString();

        if (filePath != null) {
            media = new Media(filePath);
            mediaPlayer = new MediaPlayer(media);
            songNameLabel.setText(file.getName());
            mediaPlayer.play();
            startMusicTimer();
            Notifications.showCurrentlyPlayingFileNotification("We are playing " + file.getName() + " now");
        }
    }

    public void playMusic(ActionEvent event) {
        if (filePath == null) {
            System.out.println("Select a mp3 file first!");
            Notifications.showMissingFileNotification();
        } else {
            mediaPlayer.play();
            startMusicTimer();
        }
    }

    public void pauseMusic(ActionEvent event) {
        if (filePath == null) {
            System.out.println("Select a mp3 file first!");
            Notifications.showMissingFileNotification();
        } else {
            mediaPlayer.pause();
        }
    }

    public void resetVideo(ActionEvent event) {
        if (filePath == null) {
            System.out.println("Select a mp3 file first!");
            Notifications.showMissingFileNotification();
        } else {
            timeSlider.setValue(0);
            mediaPlayer.seek(Duration.seconds(0));
        }
    }

    public void startMusicTimer() {
        timeSlider.valueChangingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean wasChanged, Boolean isChanging) {
                if (!isChanging) {
                    mediaPlayer.seek(Duration.seconds(timeSlider.getValue() * media.getDuration().toSeconds()));
                }
            }
        });

        timer = new Timer();

        task = new TimerTask() {
            public void run() {
                isMusicRunning = true;
                double currentTime = mediaPlayer.getCurrentTime().toSeconds();
                double endTime = media.getDuration().toSeconds();
                timeSlider.setValue(currentTime/endTime);
                if(currentTime/endTime == 1) {
                    cancelMusicTimer();
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 1000);
    }

    public void cancelMusicTimer() {
        isMusicRunning = false;
        timer.cancel();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
