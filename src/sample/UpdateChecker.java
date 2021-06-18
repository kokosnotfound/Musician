package sample;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UpdateChecker {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RED = "\033[0;31m";

    public static void checkForUpdates(double appVersion) throws Exception {
        try {
            String url = "https://raw.githubusercontent.com/Maksiooo/Musician/main/app.json";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            int responseCode = con.getResponseCode();
            System.out.println(ANSI_GREEN + "[UpdateChecker] Sending 'GET' request to URL : " + url);
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            JSONObject myResponse = new JSONObject(response.toString());
            System.out.println("[UpdateChecker] AppName: " + myResponse.getString("name"));
            System.out.println("[UpdateChecker] LatestVersion: " + myResponse.getString("version"));
            System.out.println("[UpdateChecker] AppVersion: " + appVersion);
            double latestVersion = myResponse.getDouble("version");
            if (latestVersion > appVersion) {
                System.out.println(ANSI_RED + "[UpdateChecker] Please update your app");
                Notifications.showUpdateNotification();
            }
        }
        catch (Exception e) {
            System.out.println(ANSI_RED + "[UpdateChecker] Error while looking for updates");
            Notifications.showUpdateCheckerErrorNotification();
        }
    }
}
