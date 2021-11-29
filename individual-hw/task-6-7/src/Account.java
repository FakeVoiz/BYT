import java.util.Hashtable;

public class Account
{
	private Money _content;
	private Hashtable<String, TimedPayment> _timedpayments = new Hashtable<>();
	private String _id;

	Account(String name, Currency currency)
	{
		_content = new Money(0, currency);
		_id = name;
	}

	@Override
	public String toString()
	{
		return "" + _timedpayments.size();
	}

	public String getId()
	{
		return _id;
	}

	public String getName()
	{
		return _id;
	}

	/**
	 * Add a timed payment
	 *
	 * @param id        Id of timed payment
	 * @param interval  Number of ticks between payments
	 * @param next      Number of ticks till first payment
	 * @param amount    Amount of Money to transfer each payment
	 * @param tobank    Bank where receiving account resides
	 * @param toaccount Id of receiving account
	 */
	public void addTimedPayment(String id, Integer interval, Integer next, Money amount, Bank tobank, String toaccount)
	{
		TimedPayment tp = new TimedPayment(interval, next, amount, this, tobank, toaccount);
		_timedpayments.put(id, tp);
	}

	/**
	 * Remove a timed payment
	 *
	 * @param id Id of timed payment to remove
	 */
	public void removeTimedPayment(String id)
	{
		_timedpayments.remove(id);
	}

	/**
	 * Check if a timed payment exists
	 *
	 * @param id Id of timed payment to check for
	 */
	public boolean timedPaymentExists(String id)
	{
		return _timedpayments.containsKey(id);
	}

	/**
	 * A time unit passes in the system
	 */
	public void tick()
	{
		for (TimedPayment tp : _timedpayments.values())
		{
			tp.tick();
			tp.tick();
		}
	}

	/**
	 * Deposit money to the account
	 *
	 * @param money Money to deposit.
	 */
	public void deposit(Money money)
	{
		_content = _content.add(money);
	}

	/**
	 * Withdraw money from the account
	 *
	 * @param money Money to withdraw.
	 */
	public void withdraw(Money money)
	{
		_content = _content.sub(money);
	}

	/**
	 * Get balance of account
	 *
	 * @return Amount of Money currently on account
	 */
	public Money getBalance()
	{
		return _content;
	}

	/* Everything below belongs to the private inner class, TimedPayment */
	private class TimedPayment
	{
		private int _interval, _next;
		private Account _fromaccount;
		private Money _amount;
		private Bank _tobank;
		private String _toaccount;

		TimedPayment(Integer interval, Integer next, Money amount, Account fromaccount, Bank tobank, String toaccount)
		{
			_interval = interval;
			_next = next;
			_amount = amount;
			_fromaccount = fromaccount;
			_tobank = tobank;
			_toaccount = toaccount;
		}

		/* Return value indicates whether or not a transfer was initiated */
		public Boolean tick()
		{
			if (_next == 0)
			{
				_next = _interval;

				_fromaccount.withdraw(_amount);
				try
				{
					_tobank.deposit(_toaccount, _amount);
				}
				catch (AccountDoesNotExistException e)
				{
					/* Revert transfer.
					 * In reality, this should probably cause a notification somewhere. */
					_fromaccount.deposit(_amount);
				}
				return true;
			}
			else
			{
				_next--;
				return false;
			}
		}
	}
}
