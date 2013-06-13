import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/12/13
 * Time: 4:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionTest {
    public BankAccountDAO mockBAD = mock(BankAccountDAO.class);
    public TransactionDAO mockTD = mock(TransactionDAO.class);
    public String accountNumber;
    @Before
    public void setUp()
    {
        reset(mockBAD);
        reset(mockTD);
        BankAccount.setBankAccountDAO(mockBAD);
        Transaction.setTransactionDAO(mockTD);
    }
    @Test
    public void depositCheckBalanceTest()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        //------------------------------------------------------
        when(mockBAD.getAccount(accountNumber)).thenReturn(account);
        BankAccount.depositAccount(accountNumber, 100, "Gui vao 100k");

        verify(mockBAD, times(2)).save(ac.capture());
        List<BankAccountDTO> list = ac.getAllValues();
        assertEquals(list.get(1).getBalace(), 100.0 , 0.01);
        assertEquals(list.get(1).getAccountNumber(), accountNumber);
    }
    @Test
    public void depositSaveDBCheckTimeStampTest()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);

        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        Calendar calendar = mock(Calendar.class);
        TransactionDTO.setCalendar(calendar);
        when(calendar.getTimeInMillis()).thenReturn(100000l);
        when(mockBAD.getAccount(accountNumber)).thenReturn(account);
        BankAccount.depositAccount(accountNumber, 100, "Them 100k");
        verify(mockTD,times(1)).save(act.capture());
        List<TransactionDTO> list = act.getAllValues();
        assertEquals(act.getValue().getAccountNumber(), accountNumber);
        assertEquals(act.getValue().getBalance(),100.0);
        assertEquals(act.getValue().getDescriber(),"Them 100k");
        assertEquals(act.getValue().getTimeStamp(), 100000l);
    }
}
