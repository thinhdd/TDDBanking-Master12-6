import java.util.Calendar;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/13/13
 * Time: 1:21 AM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionDTO {
    String accountNumber;
    double amount;
    String des;
    long  timeStamp;
    boolean state=true;
    static Calendar calendar= Calendar.getInstance();
    TransactionDTO(String accountNumber, double amount, String des) {
        this.accountNumber=accountNumber;
        if(amount<0){
            this.state=false;
            this.amount=-amount;
        }
        else
            this.amount=amount;
        this.des=des;
        timeStamp= calendar.getTimeInMillis();
        //To change body of created methods use File | Settings | File Templates.
    }

    public String getAccountNumber() {
        return accountNumber;  //To change body of created methods use File | Settings | File Templates.
    }

    public double getBalance() {
        return amount;  //To change body of created methods use File | Settings | File Templates.
    }

    public String getDescriber() {
        return des;  //To change body of created methods use File | Settings | File Templates.
    }

    public long getTimeStamp() {
        return timeStamp;  //To change body of created methods use File | Settings | File Templates.
    }

    public static void setCalendar(Calendar calendars) {
        calendar=calendars;
        //To change body of created methods use File | Settings | File Templates.
    }
}
