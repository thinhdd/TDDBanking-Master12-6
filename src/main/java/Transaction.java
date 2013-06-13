import java.util.List;

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

    public static List<TransactionDTO> getListTransaction(String accountNumber) {
        return transactionDAO.getListTransaction(accountNumber);  //To change body of created methods use File | Settings | File Templates.
    }
    public static List<TransactionDTO> getListTransaction(String accountNumber, long start, long end) {
        return transactionDAO.getListTransaction(accountNumber, start, end);  //To change body of created methods use File | Settings | File Templates.
    }

    public static List<TransactionDTO> getListTransaction(String accountNumber, int count) {
        return transactionDAO.getListTransaction(accountNumber,count);  //To change body of created methods use File | Settings | File Templates.
    }
}
