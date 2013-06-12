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
    public BankAccountDAO bankAccountDAO = mock(BankAccountDAO.class);
    public String accountNumber;
    @Before
    public void setUp()
    {
        reset(bankAccountDAO);
        BankAccount.setBankAccountDAO(bankAccountDAO);
    }
    @Test
    public void depositCheckBalanceTest()
    {
        BankAccountDTO account = BankAccount.openAccount(accountNumber);
        ArgumentCaptor<BankAccountDTO> ac = ArgumentCaptor.forClass(BankAccountDTO.class);

        BankAccount.depositAccount(accountNumber, 100, "Gui vao 100k");
        List<BankAccountDTO> list = ac.getAllValues();
        verify(bankAccountDAO, times(2)).save(ac.capture());
        assertEquals(list.get(1).getBalace(), 100.0 , 0.01);

    }
}
