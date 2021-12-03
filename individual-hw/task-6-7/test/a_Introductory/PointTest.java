package a_Introductory;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PointTest {
	Point p1, p2, p3;
	
	@Before
	public void setUp() throws Exception {
		p1 = new Point(7, 9);
		p2 = new Point(-3, -30);
		p3 = new Point(-10, 3);
	}

	@Test
	public void testAdd() {
		Point res1 = p1.add(p2);
		Point res2 = p1.add(p3);
		
		assertEquals(4, res1.x.intValue());
		assertEquals(-21, res1.y.intValue());
		assertEquals(-3, res2.x.intValue());
//		assertEquals(12, res2.x.intValue());
//		this test case should be tested for y coordinate
		assertEquals(12, res2.y.intValue());
	}

	@Test
	public void testSub() {
		Point res1 = p1.sub(p2);
		Point res2 = p1.sub(p3);
		
//		assertEquals(4, res1.x.intValue());
//		p1.x(7) - p2.x(-3) is 10 not 4
		assertEquals(10, res1.x.intValue());
//		assertEquals(-21, res1.y.intValue());
//		p1.y(9) - p2.y(-30) is 39 not -21
		assertEquals(39, res1.y.intValue());
//		assertEquals(-3, res2.x.intValue());
//		p1.x(7) - p3.x(-10) is 17 not -3
		assertEquals(17, res2.x.intValue());
//		assertEquals(12, res2.y.intValue());
// 		p1.y(9) - p3.y(3) is 6 not 12
		assertEquals(6, res2.y.intValue());
	}

}
