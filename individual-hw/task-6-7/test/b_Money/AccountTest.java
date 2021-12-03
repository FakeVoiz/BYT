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
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");

		testAccountA = new Account("Hans", SEK);
		testAccountA.deposit(new Money(10000000, SEK));
		testAccountB = new Account("Alice", SEK);
		testAccountB.deposit(new Money(10000000, SEK));

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
	public void testTimedPayment() throws AccountDoesNotExistException {
		fail("Write test case here");
	}

	@Test
	public void testAddWithdraw() {
		fail("Write test case here");
	}
	
	@Test
	public void testGetBalance() {
		fail("Write test case here");
	}
}
