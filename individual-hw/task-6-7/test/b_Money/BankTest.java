package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Account0");
		SweBank.openAccount("Account1");
		Nordea.openAccount("Account0");
		DanskeBank.openAccount("Account0");
	}

	@Test
	public void testGetName() {
//		Checking if bank name is the same as it was in setUp function
		assertEquals("SweBank",SweBank.getName());
		assertEquals("Nordea",Nordea.getName());
	}

	@Test
	public void testGetCurrency() {
//		Checking if bank currency is the same as it was in setUp function
		assertEquals(SEK, SweBank.getCurrency());
//		Checking with different currency
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
//		Checking if new account can be created
		var accountId = "TestAccount";
		try {
			SweBank.openAccount(accountId);
		} catch(AccountExistsException e) {
			fail("Account exist with such id");
		}

//		Checking if account with existed id can be created
		try
		{
			SweBank.openAccount(accountId);
			fail("Account was added");
		}
		catch (AccountExistsException e)
		{
			assertTrue("Account was no added successfully", true);
		}
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
//		Adding money to account that was added in setUp function
		SweBank.deposit("Account0", new Money(100, SEK));
//		Checking if money were added
		assertEquals(Double.valueOf(100), SweBank.getBalance("Account0"));
//		Adding more money but with other currency
		SweBank.deposit("Account0", new Money(100, DKK));
//		Checking if money were added
		assertEquals(Double.valueOf(233), SweBank.getBalance("Account0"));
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
//		Adding money to account that was added in setUp function
		SweBank.deposit("Account0", new Money(100, SEK));
//		Checking if money were added
		assertEquals(Double.valueOf(100), SweBank.getBalance("Account0"));
//		Removing some money of the same currency
		SweBank.withdraw("Account0", new Money(50, SEK));
//		Checking if money were added
		assertEquals(Double.valueOf(53), SweBank.getBalance("Account0"));
//		Removing some money of the different currency
		SweBank.withdraw("Account0", new Money(10, DKK));
//		Checking if money with other currency was subtracted
		assertEquals(Double.valueOf(40), SweBank.getBalance("Account0"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
//		Adding money to account that was added in setUp function
		SweBank.deposit("Account0", new Money(100, SEK));
//		Checking if money were added
		assertEquals(Double.valueOf(100), SweBank.getBalance("Account0"));
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
//		Adding money to accounts that were added in setUp function
		SweBank.deposit("Account0", new Money(100, SEK));
		SweBank.deposit("Account1", new Money(100, SEK));
//		Transferring money
		SweBank.transfer("Account0", "Account1", new Money(50, SEK));
//		Checking if money were transferred
		assertEquals(Double.valueOf(53), SweBank.getBalance("Account0"));
		assertEquals(Double.valueOf(147), SweBank.getBalance("Account1"));

//		Adding transferred previously money
		SweBank.deposit("Account0", new Money(50, SEK));
//		Transferring money to account from different bank
		SweBank.transfer("Account0", Nordea, "Account0", new Money(50, SEK));
//		Checking if money were transferred
		assertEquals(Double.valueOf(53), SweBank.getBalance("Account0"));
		assertEquals(Double.valueOf(47), Nordea.getBalance("Account0"));
	}
}
