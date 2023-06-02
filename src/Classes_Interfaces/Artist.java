package Classes_Interfaces;

import java.util.Iterator;

public interface Artist {
	/**
	 * 
	 * @return o nome do artista
	 */
	String getName();

	/**
	 * 
	 * @return o sitio onde nasceu
	 */
	String getPlace();

	/**
	 * 
	 * @return a data de nascimento
	 */
	String getDate();

	/**
	 * 
	 * @return um iterador com todos os creditos do artista
	 */
	Iterator<Credit> listCredits();

	/**
	 * adiciona atualiza a data o local de nascimento
	 * 
	 * @param date
	 * @param place
	 */
	void addBio(String date, String place);

	/**
	 * adiciona aos creditos
	 * 
	 * @param roll
	 * @param show
	 */
	void addShow(String roll, Show show);
}
