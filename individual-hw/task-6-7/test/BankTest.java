import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest
{
	private Currency _usdCurrency, _euroCurrency;
	private Bank _bank0;
	private Bank _bank1;
	private Bank _bank2;

	@Before
	public void setUp() throws Exception
	{
		_euroCurrency = new Currency("EUR", 0.20);
		_usdCurrency = new Currency("USD", 0.15);
		_bank0 = new Bank("Bank0", _usdCurrency);
		_bank1 = new Bank("Bank1", _usdCurrency);
		_bank2 = new Bank("Bank2", _euroCurrency);
		_bank0.openAccount("User0");
		_bank0.openAccount("User1");
		_bank1.openAccount("User0");
		_bank1.openAccount("User1");
		_bank2.openAccount("User3");
		_bank0.deposit("User1", new Money(100, _usdCurrency));
	}

	@Test
	public void testGetCurrency()
	{
		assertNotEquals(_euroCurrency, _bank1.getCurrency());

		assertEquals(_usdCurrency, _bank1.getCurrency());
	}

	@Test
	public void testGetName()
	{
		assertNotEquals("Bank0", _bank1.getName());
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException
	{
		assertEquals(100, (int)_bank0.getBalance("User1"));

		_bank0.deposit("User1", new Money(10, _usdCurrency));

		assertEquals(110, (int)_bank0.getBalance("User1"));

		_bank1.deposit("User1", new Money(15, _usdCurrency));

		assertEquals(110, (int)_bank0.getBalance("User1"));

		assertEquals(15, (int)_bank1.getBalance("User1"));
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException
	{
		assertEquals(100, (int)_bank0.getBalance("User1"));

		_bank0.openAccount("User3");

		assertEquals(0, (int)_bank0.getBalance("User3"));
	}

	@Test
	public void testGetBalance() throws AccountDoesNotExistException
	{
		assertEquals(100, (int) _bank0.getBalance("User1"));

		_bank0.deposit("User1", new Money(100, _usdCurrency));

		assertEquals(200, (int) _bank0.getBalance("User1"));

		_bank0.withdraw("User1", new Money(80, _usdCurrency));

		assertEquals(120, (int) _bank0.getBalance("User1"));
	}

	@Test
	public void testTransfer() throws AccountDoesNotExistException
	{
		_bank0.deposit("User1", new Money(100, _usdCurrency));

		assertEquals(200, (int)_bank0.getBalance("User1"));

		_bank0.transfer("User1", _bank1, "User0", new Money(10, _usdCurrency));

		assertEquals(190, (int)_bank0.getBalance("User1"));

		assertEquals(10, (int)_bank1.getBalance("User0"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException
	{
		_bank0.deposit("User1", new Money(50, _usdCurrency));

		assertEquals(150, (int)_bank0.getBalance("User1"));

		_bank0.withdraw("User1", new Money(30, _usdCurrency));

		assertEquals(120, (int)_bank0.getBalance("User1"));

		_bank0.withdraw("User1", new Money(15, _usdCurrency));

		assertEquals(105, (int)_bank0.getBalance("User1"));
	}
}
