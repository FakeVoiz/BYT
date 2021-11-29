import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest
{

	private Money _sek0Money;
	private Money _eur0Money;
	private Money _sek1Money;
	private Money _eur1Money;
	private Money _negatedSek1Money;
	private Money _sek2Money;
	private Money _eur2Money;
	private Currency _dkkCurrency;
	private Currency _sekCurrency;

	private final int TEN_S = 10000;
	private final int TWO_S = 2000;
	private final int NONE_S = 0;

	@Before
	public void setUp()
	{
		int ONE_S = 1000;
		int TWENTY_S = 20000;
		var _eurCurrency = new Currency("EUR", 1.5);

		_dkkCurrency = new Currency("DKK", 0.20);
		_sekCurrency = new Currency("SEK", 0.15);
		_eur2Money = new Money(ONE_S, _eurCurrency);
		_sek1Money = new Money(NONE_S, _sekCurrency);
		_sek0Money = new Money(TEN_S, _sekCurrency);
		_negatedSek1Money = new Money(-TEN_S, _sekCurrency);
		_eur1Money = new Money(TWO_S, _eurCurrency);
		_eur0Money = new Money(NONE_S, _eurCurrency);
		_sek2Money = new Money(TWENTY_S, _sekCurrency);
	}

	@Test
	public void testToString()
	{
		assertEquals("0 SEK", _sek1Money.toString());
	}

	@Test
	public void testUniversalValue()
	{
		assertEquals(TEN_S * 0.15, _sek0Money.universalValue(), 0.0);

		assertEquals(NONE_S, (int)_sek1Money.universalValue());

		assertEquals(TWO_S * 1.5, _eur1Money.universalValue(), 0.0);
	}

	@Test
	public void testGetCurrency()
	{
		assertEquals(0.2d, _dkkCurrency.get_rate(), 0.001);
	}

	@Test
	public void testGetAmount()
	{
		assertEquals(NONE_S, (int)_eur0Money.getAmount());
	}

	@Test
	public void testIsZero()
	{
		assertTrue(_eur0Money.isZero());

		assertTrue(_eur1Money.sub(_eur1Money).isZero());

		assertFalse(_eur1Money.sub(_eur2Money).isZero());
	}

	@Test
	public void testEqualsMoney()
	{
		assertNotEquals(_sek0Money, _sek1Money);

		assertEquals(_sek0Money, _sek0Money);
	}

	@Test
	public void testAdd()
	{
		assertEquals(_sek2Money.getAmount(), _sek0Money.add(_sek0Money).getAmount());

		assertEquals(_sek0Money.getAmount(), _sek0Money.add(_sek0Money).add(_negatedSek1Money).getAmount());
	}

	@Test
	public void testNegate()
	{
		assertTrue(_sek1Money.negate().isZero());

		assertEquals(-1000, _eur2Money.negate().getAmount().intValue());

		assertEquals(_negatedSek1Money.getAmount().intValue(), _sek0Money.negate().getAmount().intValue());
	}

	@Test
	public void testSub()
	{
		assertEquals(_sek0Money.getAmount(), _sek2Money.sub(_sek0Money).getAmount());

		assertEquals(_sek2Money.getAmount(), _sek0Money.sub(_negatedSek1Money).getAmount());
	}

	@Test
	public void testCompareTo()
	{
		assertEquals(1, _sek0Money.compareTo(_negatedSek1Money));

		assertEquals(-1, _sek0Money.compareTo(_sek2Money));

		assertEquals(0, _sek0Money.compareTo(_sek0Money));
	}
}
