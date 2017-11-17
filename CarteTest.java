package blackjack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
/**
 * Groupe 16 - Blackjack
 * @author Humbert Meyers
 * Cette classe représente le test JUnit pour la classe Carte.
 */
public class CarteTest {
	Carte premiereCarte, deuxiemeCarte, troisiemeCarte;
	@Before
	public void setUp() {
		premiereCarte = new Carte(12, 1);
		deuxiemeCarte = new Carte(2, 2);
		troisiemeCarte = new Carte(0, 9);
	}
	
	@Test
	public void testEquals() {
		assertFalse(premiereCarte.equals(null));
		assertFalse(premiereCarte.equals(deuxiemeCarte));
		assertFalse(deuxiemeCarte.equals(troisiemeCarte));
	}
	
	@Test
	public void testGetFamilleString() {
		assertEquals("Coeurs", premiereCarte.getFamilleString());
		assertEquals("Carreaux", deuxiemeCarte.getFamilleString());
		assertEquals("??", troisiemeCarte.getFamilleString());
	}
	
	@Test
	public void testGetValeurString() {
		assertEquals("Dame", premiereCarte.getValeurString());
		assertEquals("2", deuxiemeCarte.getValeurString());
		assertEquals("??", troisiemeCarte.getValeurString());
	}
}


