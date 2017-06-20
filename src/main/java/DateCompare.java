import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by slan on 6/19/2017.
 */
public abstract class DateCompare implements Comparable{


    public  String getDate() {
        return Date;
    }

    public  void setDate(String date) {
        Date = date;
    }

    public  DateTime getRealDate() {
        return realDate;
    }

    public void setRealDate(DateTime realDate) {
        this.realDate = realDate;
    }

    private String Date;
    private  DateTime realDate;


    public static java.util.Date formatDate(String date) {
        Date response = new Date();
        try {

            String finaltext = date.substring(1);
            String last = finaltext.replace("T", " ");
            String really = last.replace("Z", " ");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            response = formatter.parse(really);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return response;
    }


  //  @Override
    /*
    public int compareTo(String one, String two) {

        Date first = formatDate(one);
        Date second = formatDate(two);

        return first.compareTo(second);
    }
    */
}
