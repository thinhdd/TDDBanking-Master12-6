/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/12/13
 * Time: 3:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class BankAccount {
    static BankAccountDAO bankAccountDAO;
    public static void setBankAccountDAO(BankAccountDAO mockBAD) {
        BankAccount.bankAccountDAO = mockBAD;
        //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccountDTO openAccount(String accountNumber) {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDAO.save(account);
        return account;
        //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccountDTO getAccountNumber(String accountNumber) {

        return bankAccountDAO.getAccount(accountNumber);  //To change body of created methods use File | Settings | File Templates.
    }

    public static BankAccountDTO depositAccount(String accountNumber, double amount, String des) {
        BankAccountDTO account = BankAccount.getAccountNumber(accountNumber);
        account.setBalance(amount);
        Transaction.createTransactionDTO(accountNumber,amount,des);
        bankAccountDAO.save(account);
        return account;
        //To change body of created methods use File | Settings | File Templates.
    }
}
