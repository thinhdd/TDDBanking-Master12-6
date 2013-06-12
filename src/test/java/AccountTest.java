import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created with IntelliJ IDEA.
 * User: thinhdd
 * Date: 6/12/13
 * Time: 2:35 PM
 * To change this template use File | Settings | File Templates.
 */
public class AccountTest {
    BankAccountDAO mockBAD = mock(BankAccountDAO.class);
    public String accountNumber="123456";
    @Before
    public void setUp()
    {
        reset(mockBAD);
        BankAccount.setBankAccountDAO(mockBAD);
    }
    @Test
    public void testOpenAccount(){
        BankAccount.openAccount(accountNumber);
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);
        verify(mockBAD).save(ac.capture());
        assertEquals(ac.getValue().getBalace(),0.0, 0.1);
        assertEquals(ac.getValue().getAccountNumber(), accountNumber);
    }
    @Test
    public void testGetAccount()
    {
        BankAccountDTO accountOpen = BankAccount.openAccount(accountNumber);

        when(mockBAD.getAccount(accountNumber)).thenReturn(accountOpen);
        BankAccountDTO accountResult = BankAccount.getAccountNumber(accountNumber);
        verify(mockBAD).getAccount(accountNumber);
        assertEquals(accountResult.getAccountNumber(), accountNumber);
        assertEquals(accountResult.getBalace(), 0.0, 0.01);
    }

}
