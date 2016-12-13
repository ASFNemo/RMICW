package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.NotificationFramework.NSource;
import Com.coursework.NotificationFramework.NotificationSource;

/**
 * Created by asherfischbaum on 09/12/2016.
 */
public class CWSource {
    String moduleName;
    String URL;
    NSource notificationSource;

    public CWSource(String URL, NSource notificationSource) {
        this.URL = URL;
        this.notificationSource = notificationSource;

        moduleName = getURL().split("/")[getURL().split("/").length - 1];
    }

    public String getURL() {
        return URL;
    }

    public String toString() {
        return "Module name: " + this.moduleName;
    }

}
