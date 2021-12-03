package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		EUR10 = new Money(1000, EUR);
		SEK200 = new Money(20000, SEK);
		EUR20 = new Money(2000, EUR);
		SEK0 = new Money(0, SEK);
		EUR0 = new Money(0, EUR);
		SEKn100 = new Money(-10000, SEK);
	}

	@Test
	public void testGetAmount() {
//		Comparing value of amount field of one currency
//		Test with positive value
		assertTrue(Math.abs(SEK100.getAmount() - 10000) < .00001);
//		Test with negative value
		assertTrue(Math.abs(SEKn100.getAmount() - -10000) < .00001);
//		Test with zero
		assertEquals(SEK0.getAmount(), Double.valueOf(0));
	}

	@Test
	public void testGetCurrency() {
//		Comparing value of currency field of one currency
//		Testing with currency as separate object
		assertEquals(SEK, SEK100.getCurrency());
		assertEquals(SEK, SEKn100.getCurrency());
//		Testing with monies that shares same currency
		assertEquals(EUR10.getCurrency(), EUR0.getCurrency());
	}

	@Test
	public void testToString() {
//		Manually setting expected output
		var SEK100Output = String.format("(%b) (%b)", SEK100.getAmount(), SEK100.getCurrency().getName());
//		Comparing outputs
		assertEquals(SEK100Output, SEK100.toString());
	}

	@Test
//	public void testGlobalValue() {
//	Renaming to testUniversalValue since it meets convention rules
	public void testUniversalValue() {
//		Calculating universal value manually for positive, negative and zero values
		var posUniversalValue = Integer.valueOf((int)(SEK100.getAmount() * SEK100.getCurrency().getRate()));
		var negativeUniversalValue = Integer.valueOf((int)(SEKn100.getAmount() * SEKn100.getCurrency().getRate()));
		var zeroUniversalValue = Integer.valueOf(0);
//		Testing with positive
		assertEquals(posUniversalValue, SEK100.universalValue());
//		Testing with negative
		assertEquals(negativeUniversalValue, SEKn100.universalValue());
//		Testing with zero
		assertEquals(zeroUniversalValue, SEK0.universalValue());
	}

	@Test
	public void testEqualsMoney() {
//		Testing with same currencies
		assertFalse(SEK100.equals(SEK200));
		assertTrue(SEK100.equals(new Money(10000, SEK)));
//		Testing with different currencies
		assertTrue(EUR10.equals(SEK100));
	}

	@Test
	public void testAdd() {
//		Testing with same currencies
		var sameCurrenciesResult = SEK100.add(SEK200);
		assertEquals(sameCurrenciesResult.getCurrency().getName(), SEK.getName());
		assertEquals(Double.valueOf(10000 + 20000), sameCurrenciesResult.getAmount());
//		Testing with different currencies
		var differentCurrenciesResult = SEK100.add(EUR10);
		assertEquals(differentCurrenciesResult.getCurrency().getName(), SEK.getName());
		assertEquals(Double.valueOf((1000 * 1.5f + 10000 * .15f) / .15f), differentCurrenciesResult.getAmount());
	}

	@Test
	public void testSub() {
//		Testing with same currencies
		var sameCurrenciesResult = SEK100.sub(SEK200);
		assertEquals(sameCurrenciesResult.getCurrency().getName(), SEK.getName());
		assertEquals(Double.valueOf(10000 - 20000), sameCurrenciesResult.getAmount());
//		Testing with different currencies
		var differentCurrenciesResult = SEK100.sub(EUR10);
		assertEquals(differentCurrenciesResult.getCurrency().getName(), SEK.getName());
		assertEquals(Double.valueOf((1000 * 1.5f - 10000 * .15f) / .15f), differentCurrenciesResult.getAmount());
	}

	@Test
	public void testIsZero() {
//		Testing with positive
		assertFalse(SEK100.isZero());
//		Testing with negative
		assertFalse(SEKn100.isZero());
//		Testing with zero with different currencies
		assertTrue(SEK0.isZero());
		assertTrue(EUR0.isZero());
	}

	@Test
	public void testNegate() {
//		Testing with positive
		assertTrue(Math.abs(-SEK100.getAmount() - SEK100.negate().getAmount()) < .00001);
// 		Testing with negative
		assertTrue(Math.abs(-SEKn100.getAmount() - SEKn100.negate().getAmount()) < .00001);
	}

	@Test
	public void testCompareTo() {
//		Comparing with same currencies and same amounts
		assertEquals(0, SEK100.compareTo(new Money(10000, SEK)));
//		Testing with same currency but different amounts
		assertTrue(SEK100.compareTo(SEK200) < 0);
//		Testing with different currencies and same amounts
		assertEquals(0, SEK0.compareTo(EUR0));
//		Testing with different currencies and different amounts
		assertTrue(SEK100.compareTo(EUR20) < 0);
	}
}
