package sample;

import javafx.util.Duration;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class Notifications {
    public static void showUpdateNotification() {
        TrayNotification tray = new TrayNotification("Musician", "New version of musician is available", NotificationType.NOTICE);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(10));
    }

    public static void showCurrentlyPlayingFileNotification(String message) {
        TrayNotification tray = new TrayNotification("Musician", message, NotificationType.SUCCESS);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(5));
    }

    public static void showMissingFileNotification() {
        TrayNotification tray = new TrayNotification("Musician", "Select a .mp3 file first", NotificationType.ERROR);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(5));
    }

    public static void showUpdateCheckerErrorNotification() {
        TrayNotification tray = new TrayNotification("Musician (UpdateChecker)", "Error while looking for updates", NotificationType.ERROR);
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(5));
    }
}
