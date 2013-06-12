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

    public static void openAccount(String accountNumber) {
        BankAccountDTO account = new BankAccountDTO(accountNumber);
        bankAccountDAO.save(account);
        //To change body of created methods use File | Settings | File Templates.
    }
}
