package b_Money;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccountA, testAccountB;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", .2);
		SEK = new Currency("SEK", .15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");

		testAccountA = new Account("Hans", SEK);
		testAccountA.deposit(new Money(1000000, SEK));
		testAccountB = new Account("Alice", SEK);
		testAccountB.deposit(new Money(1000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
//		Creating time payment
		testAccountA.addTimedPayment("0", 0, 1, new Money(666, SEK), SweBank, testAccountB.getName());
//		Testing if time payment was created
		assertTrue(testAccountA.timedPaymentExists("0"));
//		Removing time payment
		testAccountA.removeTimedPayment("0");
//		Testing if time payment was removed
		assertFalse(testAccountA.timedPaymentExists("0"));
	}
	
	@Test
	public void testTimedPayment() {
//		Creating payments for accountB with transition to accountA
		testAccountB.addTimedPayment("0",0,1,new Money(1389,SEK),Nordea,testAccountA.getName());
		testAccountB.addTimedPayment("1",0,2,new Money(2378,SEK),SweBank,testAccountA.getName());

//		Checking if payments were created
		assertTrue(testAccountB.timedPaymentExists("0"));
		assertTrue(testAccountB.timedPaymentExists("1"));

//		Removing first Payment
		testAccountB.removeTimedPayment("0");
//		Checking if first payment was removed
		assertFalse(testAccountB.timedPaymentExists("0"));
//		Checking if second payment exist
		assertTrue(testAccountB.timedPaymentExists("1"));
	}

	@Test
	public void testAddWithdraw() {
//		Creating money that will be used for account manipulation
		var dkk100Money = new Money(100,DKK);
		var dkk200Money = new Money(200,DKK);
		var sek1000Money = new Money(1000,SEK);

//		Checking if account contains money that were deposited in setUp function
		assertEquals(Double.valueOf(1000000),testAccountA.getBalance().getAmount());
//		Withdrawing money with different currency
		testAccountA.withdraw(dkk100Money);
		assertEquals(Double.valueOf(999867),testAccountA.getBalance().getAmount());
//		Depositing money with different currency
		testAccountA.deposit(dkk100Money);
//		The amount should be the same as before withdrawing since we subtract and add same amount of money
		assertEquals(Double.valueOf(1000000),testAccountA.getBalance().getAmount());
	}
	
	@Test
	public void testGetBalance() {
//		Creating test account
		var testAccountC = new Account("Test", SEK);
//		Creating money to deposit
		var moneyToDeposit = new Money(100, SEK);
//		Depositing some money to the account with the same currency
		testAccountC.deposit(moneyToDeposit);
//		Checking if currency of the balance is the same as initial currency with which account was created
		assertEquals(testAccountC.getBalance().getCurrency().getName(), SEK.getName());
//		Checking if amount of money is the same as was deposited initially
		assertEquals(testAccountC.getBalance().getAmount(), moneyToDeposit.getAmount());

	}
}
