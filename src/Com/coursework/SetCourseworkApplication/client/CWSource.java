package Com.coursework.SetCourseworkApplication.client;

import Com.coursework.NotificationFramework.NotificationSource;

/**
 * Created by asherfischbaum on 09/12/2016.
 */
public class CWSource {
    String moduleName;
    String URL;
    NotificationSource notificationSource;

    public CWSource(String URL, NotificationSource notificationSource) {
        this.URL = URL;
        this.notificationSource = notificationSource;
    }

    public String getURL() {
        return URL;
    }

    public NotificationSource getNotificationSource() {
        return notificationSource;
    }
}
