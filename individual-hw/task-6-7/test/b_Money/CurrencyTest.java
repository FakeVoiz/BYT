package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CurrencyTest {
	Currency SEK, DKK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
	}

	@Test
	public void testGetName() {
//		Comparing value of name field of one currency
		assertEquals("SEK", SEK.getName());
	}
	
	@Test
	public void testGetRate() {
//		Comparing value of rate field of one currency
		assertTrue(Math.abs(0.15 - SEK.getRate()) < 0.00001);
	}

	@Test
	public void testSetRate() {
//		Setting positive, negative and zero values
		final double positive = 1.1d;
		final double negative = -1.1d;
		final double zero = 0;

//		Testing with positive
		SEK.setRate(positive);
		assertTrue(Math.abs(positive - SEK.getRate()) < .00001);
//		Testing with negative
		SEK.setRate(negative);
		assertTrue(Math.abs(negative - SEK.getRate()) < .00001);
//		Testing with zero
		SEK.setRate(zero);
		assertTrue(Math.abs(zero - SEK.getRate()) < .00001);
	}
	
	@Test
//	public void testGlobalValue() {
//	Renaming to testUniversalValue since it follows convention
	public void testUniversalValue() {
//		Setting positive,negative and zero amounts
		final int positive = 100;
		final int negative = -100;
		final int zero = 0;
//		Testing if manually calculated expected values are the same with method' outputs
		assertEquals(Integer.valueOf((int)(positive * SEK.getRate())), SEK.universalValue(positive));
		assertEquals(Integer.valueOf((int)(negative * SEK.getRate())), SEK.universalValue(negative));
		assertEquals(Integer.valueOf(zero), SEK.universalValue(zero));
	}
	
	@Test
	public void testValueInThisCurrency() {
//		Setting positive,negative and zero amounts
		final int positive = 100;
		final int negative = -100;
		final int zero = 0;
//		Manually calculating value in different currency using universal value
		var positiveFromDKKInSEK = new Integer((int)(DKK.universalValue(positive) / SEK.getRate()));
		var negativeFromDKKInSEK = new Integer((int)(DKK.universalValue(negative) / SEK.getRate()));
		var zeroFromDKKInSEK = new Integer(0);
//		Testing if manually calculated expected values are the same with method' outputs
		assertEquals(positiveFromDKKInSEK, SEK.valueInThisCurrency(positive, DKK));
		assertEquals(negativeFromDKKInSEK, SEK.valueInThisCurrency(negative, DKK));
		assertEquals(zeroFromDKKInSEK, SEK.valueInThisCurrency(zero, DKK));
	}

}
