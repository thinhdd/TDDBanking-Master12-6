import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.util.Calendar;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
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
    public String accountNumber="123456";
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
        assertEquals(list.get(1).getBalace(), 200.0 , 0.01);
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
        assertEquals(list.get(0).getAccountNumber(), accountNumber);
        assertEquals(list.get(0).getBalance(),100.0);
        assertEquals(list.get(0).getDescriber(),"Them 100k");
        assertEquals(list.get(0).getTimeStamp(), 100000l);
    }
    @Test
    public void withDrawBalanceTest()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        when(mockBAD.getAccount(accountNumber)).thenReturn(account);
        BankAccount.withDrawAccount(accountNumber, 50, "Rut 50k");
        verify(mockBAD, times(2)).save(ac.capture());
        assertEquals(ac.getValue().getAccountNumber(), accountNumber);
        assertEquals(ac.getValue().getBalace(), 50.0 ,0.01);
    }
    @Test
    public void withcDrawTransactionTest()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);


        Calendar calendar = mock(Calendar.class);
        TransactionDTO.setCalendar(calendar);
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        when(calendar.getTimeInMillis()).thenReturn(100000l);
        when(mockBAD.getAccount(accountNumber)).thenReturn(account);
        BankAccount.withDrawAccount(accountNumber, 100, "Rut 100k");
        verify(mockTD,times(1)).save(act.capture());
        List<TransactionDTO> list = act.getAllValues();

        //----------------------------------------------------------
        assertEquals(list.get(0).getAccountNumber(), accountNumber);
        assertEquals(list.get(0).getBalance(), 100.0);
        assertEquals(list.get(0).getDescriber(),"Rut 100k");
        assertEquals(list.get(0).getTimeStamp(), 100000l);
    }
    public void transactionCreate()
    {
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        Calendar calendar = mock(Calendar.class);
        TransactionDTO.setCalendar(calendar);
        when(calendar.getTimeInMillis()).thenReturn(1000l).thenReturn(1009l).thenReturn(1100l);
        when(mockBAD.getAccount(accountNumber)).thenReturn(account);
        BankAccount.withDrawAccount(accountNumber, 100, "Rut 100k");
        BankAccount.depositAccount(accountNumber, 100, "Them 100k");
        BankAccount.withDrawAccount(accountNumber, 100, "Rut 100k");
    }
    @Test
    public void getListTransactionOneAccountTest()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);
        transactionCreate();
        verify(mockTD,times(3)).save(act.capture());
        List<TransactionDTO> list = act.getAllValues();
        when(mockTD.getListTransaction(accountNumber)).thenReturn(list);
        List<TransactionDTO> listDB= BankAccount.getListTransaction(accountNumber);
        //-----------Check Transaction 1-------------
        assertTrue(list.get(0).equals(listDB.get(0)));
        //-----------Check Transaction 1-------------
        assertTrue(list.get(1).equals(listDB.get(1)));
        //-----------Check Transaction 1-------------
        assertTrue(list.get(2).equals(listDB.get(2)));
    }
    @Test
    public void getListTransactionOnTime()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);
        transactionCreate();
        verify(mockTD,times(3)).save(act.capture());
        List<TransactionDTO> list = act.getAllValues();
        list=list.subList(0,2);
        when(mockTD.getListTransaction(accountNumber, 1000l, 1009l)).thenReturn(list);
        List<TransactionDTO> listDB= BankAccount.getListTransaction(accountNumber,1000l, 1009l);
        assertTrue(listDB.equals(list));
    }
    @Test
    public void getListTransactionOnLimit()
    {
        ArgumentCaptor<TransactionDTO> act = ArgumentCaptor.forClass(TransactionDTO.class);
        transactionCreate();
        verify(mockTD,times(3)).save(act.capture());
        List<TransactionDTO> list = act.getAllValues();
        list=list.subList(0,2);
        when(mockTD.getListTransaction(accountNumber, 2)).thenReturn(list);
        List<TransactionDTO> listDB= BankAccount.getListTransaction(accountNumber,2);
        assertTrue(listDB.equals(list));
    }
}
