import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class AccountTest
{
	private Bank _bank;
	private Currency _usdCurrency, _eurCurrency;

	private Account _account0;
	private Account _account1;

	@Before
	public void setUp() throws Exception
	{
		_eurCurrency = new Currency("EUR", 0.11);
		_usdCurrency = new Currency("USD", 0.15);
		_bank = new Bank("Bank", _usdCurrency);
		_bank.openAccount("User0");
		_account0 = new Account("User1", _usdCurrency);
		_account0.deposit(new Money(10000000, _usdCurrency));
		_account1 = new Account("User0", _usdCurrency);
		_account1.deposit(new Money(1337, _usdCurrency));
		_bank.deposit("User0", new Money(1000000, _usdCurrency));
	}

	@Test
	public void testGetBalance()
	{
		assertEquals(Integer.valueOf(1337), _account1.getBalance().getAmount());

		assertEquals(_usdCurrency, _account1.getBalance().getCurrency());
	}

	@Test
	public void testTimedPayment()
	{
		_account1.addTimedPayment("trans1", 0, 1, new Money(100, _usdCurrency), _bank, _account0.getId());
		_account1.addTimedPayment("trans2", 0, 2, new Money(200, _usdCurrency), _bank, _account0.getId());

		assertTrue("Time Payment trans1", _account1.timedPaymentExists("trans1"));

		assertTrue("Time Payment trans2", _account1.timedPaymentExists("trans2"));

		assertFalse("Time Payment trans3", _account1.timedPaymentExists("trans3"));

		_account1.removeTimedPayment("trans1");

		assertFalse("Time Payment trans1-removed", _account1.timedPaymentExists("trans1"));

		assertTrue("Time Payment trans2", _account1.timedPaymentExists("trans2"));

		_account1.removeTimedPayment("trans2");

		assertFalse("Time Payment trans2", _account1.timedPaymentExists("trans2"));
	}

	@Test
	public void testAddWithdraw()
	{
		_account0.deposit(new Money(1000, _usdCurrency));

		assertEquals(Integer.valueOf(10001000), _account0.getBalance().getAmount());

		_account0.withdraw(new Money(1337, _eurCurrency));

		assertEquals(Integer.valueOf(10000853), _account0.getBalance().getAmount());

		_account0.deposit(new Money(200, _eurCurrency));

		assertEquals(Integer.valueOf(10000856), _account0.getBalance().getAmount());
	}

	@Test
	public void testAddRemoveTimedPayment()
	{
		_account0.addTimedPayment("trans1", 0, 1, new Money(100, _usdCurrency), _bank, _account1.getId());

		assertTrue("Append Timed Payment", _account0.timedPaymentExists("trans1"));

		_account0.removeTimedPayment("trans1");

		assertFalse("Remove Timed Payment", _account0.timedPaymentExists("trans1"));

		_account1.addTimedPayment("trans2", 0, 1, new Money(200, _eurCurrency), _bank, _account0.getId());

		assertFalse("Append Timed Payment that does not exist", _account1.timedPaymentExists("trans1"));

		assertTrue("Append Timed Payment that exists", _account1.timedPaymentExists("trans2"));

		_account1.removeTimedPayment("trans2");

		assertFalse("Remove payment", _account1.timedPaymentExists("trans2"));
	}
}
