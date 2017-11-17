package blackjack;

/**
 * Groupe 16 - Blackjack
 * @author Humbert Meyers
 * Cette classe repr�sente une carte � jouer avec une valeur et une famille.
 */
public class Carte {
	
	private final static int PIQUES = 0,       // Codes pour les quatres familles
            				 COEURS = 1,
            				 CARREAUX = 2,
            				 TREFLES = 3;
	
	private final static int AS = 1,        //   Codes pour les cartes non num�rique.
            				 VALET = 11,	//   Les cartes de 2 � 10 ont d�ja un
            				 DAME = 12,		//   code num�rique.
            			 	 ROI = 13;		 
	
	private final int famille;
	private final int valeur;
	
	/**
	 * constructeur utilis� pour creer une carte
	 * @param valeur  	La valeur de la carte
	 * @param famille	La famille de la carte
	 */
	public Carte(int valeur, int famille) {
		this.valeur = valeur;
		this.famille = famille;
	}

	/**
	 * Re�oit la famille de la carte
	 * @return la famille de la carte
	 */
	public int getFamille() {
		return famille;
	}

	/**
	 * Re�oit la valeur de la carte
	 * @return la valeur de la carte
	 */
	public int getValeur() {
		return valeur;
	}
	
	/**
	 * 
	 * @return la famille sous forme de String
	 */
	public String getFamilleString() {
	    switch (famille) {
	       case PIQUES:   return "Piques";
	       case COEURS:   return "Coeurs";
	       case CARREAUX: return "Carreaux";
	       case TREFLES:  return "Trefles";
	       default:       return "??";
	    }
	}
	
	/**
	 * 
	 * @return la valeur sous forme de String
	 */
	public String getValeurString() {
		switch (valeur) {
	        case 1:   	return "As";
	        case 2:   	return "2";
	        case 3:   	return "3";
	        case 4:   	return "4";
	        case 5:   	return "5";
	        case 6:   	return "6";
	        case 7:   	return "7";
	        case 8:   	return "8";
	        case 9:   	return "9";
	        case 10:  	return "10";
	        case 11: 	return "Valet";
	        case 12:  	return "Dame";
	        case 13:  	return "Roi";
	        default:  	return "??";
		}
	}
	
	/**
	 * Concatenation de la valeur et de la 
	 */
	public String toString() {
		return getValeurString() + " de " + getFamilleString();
	}
}
