import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CurrencyTest
{
	private Currency _sekCurrency;
	private Currency _dkkCurrency;
	private Currency _eurCurrency;

	@Before
	public void setUp()
	{
		_eurCurrency = new Currency("EUR", 1.5);
		_sekCurrency = new Currency("SEK", 0.15);
		_dkkCurrency = new Currency("DKK", 0.20);
	}

	@Test
	public void testGlobalValue()
	{
		assertEquals(15,(int) _eurCurrency.universalValue(10));
	}

	@Test
	public void testGetRate()
	{
		assertEquals(.2, _dkkCurrency.get_rate(), .001);
	}

	@Test
	public void testSetRate()
	{
		assertEquals(1.5d, _eurCurrency.get_rate(), .001);

		_eurCurrency.set_rate(0.35d);

		assertEquals(0.35d, _eurCurrency.get_rate(), .001);
	}

	@Test
	public void testValueInThisCurrency()
	{
		assertEquals(225_000, (int)_sekCurrency.valueInThisCurrency(1_000_000, _eurCurrency));
	}

	@Test
	public void testGetName()
	{
		assertEquals("EUR", _eurCurrency.get_name());
	}
}
