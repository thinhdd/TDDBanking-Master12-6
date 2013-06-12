import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

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
    public String accountNumber;
    @Before
    public void setUp()
    {
        reset(mockBAD);
        BankAccount.setBankAccountDAO(mockBAD);
    }
    @Test
    public void depositCheckBalanceTest()
    {
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        when(mockBAD.getAccount(accountNumber)).thenReturn(account);
        BankAccount.depositAccount(accountNumber, 100, "Gui vao 100k");

        verify(mockBAD, times(2)).save(ac.capture());
        List<BankAccountDTO> list = ac.getAllValues();
        assertEquals(list.get(1).getBalace(), 100.0 , 0.01);

    }
}
