/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/12/13
 * Time: 3:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccountDTO {
    private String accountNumber;
    private double balance;
    BankAccountDTO(String accountNumber){
        this.accountNumber=accountNumber;
    };
    public double getBalace() {
        return this.balance;  //To change body of created methods use File | Settings | File Templates.
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getAccountNumber() {
        return this.accountNumber;  //To change body of created methods use File | Settings | File Templates.
    }
}
