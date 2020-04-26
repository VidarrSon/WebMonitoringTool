package status;

import model.WebPage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WebPageStatus {
    private WebPage webPage;
    private String statusInfo = "";

    private ArrayList<String> critical = new ArrayList<>();
    private ArrayList<String> warning = new ArrayList<>();

    public WebPageStatus(WebPage webPage) {
        this.webPage = webPage;
    }

    public String determineStatus() {

        HttpURLConnection connection;

        try {
            URL url = new URL(webPage.getUrlAddress());
            connection = (HttpURLConnection) url.openConnection();

            // compare response time (and response substring search if it's defined)
            responseTime(connection);

            // compare response code
            responseCode(connection);

            // compare response code range
            responseRange(connection);

            connection.disconnect();

        } catch (MalformedURLException malformedEx) {
            return "Not valid URL!";
        } catch (IOException e) {
            return "Connection Error!";
        }

        if (critical.size() != 0) {
            statusInfo = statusInfo.concat("CRITICAL: ");
            for (String s : critical) {
                statusInfo = statusInfo.concat(s);
            }
        }

        if (warning.size() != 0) {
            if (!statusInfo.equals(""))
                statusInfo = statusInfo + "\n";
            statusInfo = statusInfo.concat("WARNING: ");
            for (String s : warning) {
                statusInfo = statusInfo.concat(s);
            }
        }

        if (statusInfo.equals("")) return "OK";
        else return statusInfo;
    }

    private void responseRange(HttpURLConnection connection) {
        if (connection.getContentLength() == -1)
            warning.add("content length is not known; ");
        else {
            if (connection.getContentLength() > webPage.getResponseRangeMax())
                critical.add("content length too long; ");
            if (connection.getContentLength() < webPage.getResponseRangeMin())
                critical.add("content length too short; ");
        }
    }

    private void responseCode(HttpURLConnection connection) throws IOException{
        if (connection.getResponseCode() != webPage.getResponseCode())
            critical.add("another response code (" + connection.getResponseCode() + "); ");
    }

    private void responseSubstring (HttpURLConnection connection) throws IOException{
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        boolean hasSubstring = false;

        if (!webPage.getResponseSubstring().equals("")) {
            while ((line = in.readLine()) != null) {
                if (line.contains(webPage.getResponseSubstring())) hasSubstring = true;
            }
        } else hasSubstring = true;

        if (!hasSubstring) critical.add("required substring is missing; ");
        in.close();
    }

    private void responseTime (HttpURLConnection connection) throws IOException{
        long startTimer = System.currentTimeMillis();
        // response substring searching
        responseSubstring(connection);
        long stopTimer = System.currentTimeMillis();

        // compare response time
        int responseTime = (int) ((int) stopTimer - startTimer);
        if (responseTime > webPage.getResponseTime() * 2)
            critical.add("very high response time; ");
        else if (responseTime > webPage.getResponseTime())
            warning.add("small response time delay; ");
    }
}
