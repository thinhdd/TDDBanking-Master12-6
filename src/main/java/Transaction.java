/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/13/13
 * Time: 1:24 AM
 * To change this template use File | Settings | File Templates.
 */
public class Transaction {
    static TransactionDAO transactionDAO;
    public static void createTransactionDTO(String accountNumber, double amount, String des)
    {
        TransactionDTO transactionDTO = new TransactionDTO(accountNumber,amount ,des);
        transactionDAO.save(transactionDTO);
    }
    public static void setTransactionDAO(TransactionDAO mockTD) {
        transactionDAO=mockTD;
        //To change body of created methods use File | Settings | File Templates.
    }
}