package blackjack;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {
	Player amelie,benoit,humbert;
	@Before
	public void setUp(){
		amelie = new Player(150, "Amélie");
		benoit = new Player(0, "Benoit");
		humbert = new Player(120, "HHumbert");
	}
	@Test
	public void test() {
				amelie.mise(5);
		assertEquals(145, amelie.getMoney(),0);
		assertEquals("Amélie", amelie.getNom());
		
		benoit.mise(5);
		assertEquals(0, benoit.getMoney(),0);
		assertEquals("Benoit", benoit.getNom());
		
		humbert.mise(120);
		assertEquals(0, humbert.getMoney(),0);
		assertEquals("Humbert", humbert.getNom());
	}

}
