package Classes_Interfaces;

public interface Credit extends Comparable<Credit> {
	/**
	 * 
	 * @return o show em que participou
	 */
	Show getShow();

	/**
	 * 
	 * @return o papel que estava a desempenhar
	 */
	String getTypeCredit();
}
