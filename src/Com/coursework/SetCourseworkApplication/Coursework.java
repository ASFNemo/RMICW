package Com.coursework.SetCourseworkApplication;
import Com.coursework.NotificationFramework.Notification;

/**
 * Created by asherfischbaum on 05/12/2016.
 */
public class Coursework implements Notification {

    String name;
    String LecName;
    String Description;
    int day;
    int month;
    int year;

    /*
        jsut a class representation of the coursework. since this is the notification for our application, it implements
        the notificaiton interface forom the framework.
     */

    public Coursework(String name, String lecName, String description, int day, int month, int year) {
        this.name = name;
        LecName = lecName;
        Description = description;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public String getLecName() {
        return LecName;
    }

    public String getDescription() {
        return Description;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public String toString() {
        return "Name: " + getName() + " \t Due: " + getDay() +"/" + getMonth() + "/" + getYear();
    }


}
