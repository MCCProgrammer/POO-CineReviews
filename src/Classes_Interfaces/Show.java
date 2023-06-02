package Classes_Interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public interface Show extends Comparable<Show> {
	/**
	 * 
	 * @return o titulo do show
	 */
	String getTitle();

	/**
	 * 
	 * @return AgeCertification do show
	 */
	String getAgeCertification();

	/**
	 * 
	 * @return RealeasedYear do show
	 */
	int getRealeasedYear();

	/**
	 * 
	 * @return PersonInCharge do show
	 */
	String getPersonInCharge();

	/**
	 * 
	 * @return duração do show
	 */
	int getDuration();

	/**
	 * 
	 * @return o genéro mais importante
	 */
	String getFirstGenre();

	/**
	 * 
	 * @return um iterador com os castMembers
	 */
	Iterator<String> getCastMembers();

	/**
	 * 
	 * @return o numero de reviews que o show tem
	 */
	int getNrReviews();

	/**
	 * 
	 * @param review
	 */
	void addReview(Review review);

	/**
	 * 
	 * @return um iterador com as várias reviews feitas
	 */
	Iterator<Review> listReviews();

	/**
	 * 
	 * @return o overallScore do show
	 */
	float getOverallScore();

	/**
	 * 
	 * @param genres
	 * @return
	 */
	boolean sameGenres(ArrayList<String> genres);

	/**
	 * 
	 * @param year
	 * @return
	 */
	boolean sameYear(int year);

	/**
	 * 
	 * @return uma lista com todos os artistas incluindo a personInCharge
	 */
	List<String> getAllArtists();

}
