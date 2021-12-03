package a_Introductory;

public class Fibonacci {
	public int fib(int n) {
		switch (n) {
			case 0: return 0;
			case 1: return 1;
//			default: return (fib(n - 1) + fib(n - 2)) + 1;
//			"+1" is redundant in the fibonacci formula
			default: return (fib(n - 1) + fib(n - 2));
		}
	}
}
