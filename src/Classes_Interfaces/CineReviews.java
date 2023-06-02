package Classes_Interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.SortedSet;

import Exceptions.AdminUsernameDontExistsException;
import Exceptions.ArtistNameDontExistsException;
import Exceptions.NameAlreadyHasBioException;
import Exceptions.PasswordIsWrongException;
import Exceptions.ShowDoesNotExistsException;
import Exceptions.TitleAlreadyExistsException;
import Exceptions.UnknownUserTypeException;
import Exceptions.UserAlreadyExistsException;
import Exceptions.UsernameAlreadyReviewThisShowException;
import Exceptions.UsernameDoesntExistsException;
import Exceptions.UsernameIsAnAdminException;

public interface CineReviews {
	/**
	 * adiciona um utilizador à aplicação
	 * 
	 * @param userType
	 * @param username
	 * @param password
	 * @throws UnknownUserTypeException
	 * @throws UserAlreadyExistsException
	 */
	void addUser(String userType, String username, String password)
			throws UnknownUserTypeException, UserAlreadyExistsException;

	/**
	 * 
	 * @return
	 */
	Iterator<User> listUsers();

	/**
	 * 
	 * @param personInCharge
	 * @param castMembers
	 * @param title
	 * @return o número de artistas que foram criados e também os adiciona
	 */
	int getAndcreateArtists(String personInCharge, String[] castMembers, String title);

	/**
	 * faz upload a um movie
	 * 
	 * @param username
	 * @param password
	 * @param title
	 * @param directorName
	 * @param duration
	 * @param ageCertification
	 * @param releasedYear
	 * @param genres
	 * @param castMembers
	 * @throws AdminUsernameDontExistsException
	 * @throws PasswordIsWrongException
	 * @throws TitleAlreadyExistsException
	 */
	void uploadMovie(String username, String password, String title, String directorName, int duration,
			String ageCertification, int releasedYear, String[] genres, String[] castMembers)
			throws AdminUsernameDontExistsException, PasswordIsWrongException, TitleAlreadyExistsException;

	/**
	 * faz upload a uma serie
	 * 
	 * @param username
	 * @param password
	 * @param title
	 * @param creator
	 * @param nOfSeasons
	 * @param ageCertification
	 * @param releasedYear
	 * @param genres
	 * @param castMembers
	 * @throws AdminUsernameDontExistsException
	 * @throws PasswordIsWrongException
	 * @throws TitleAlreadyExistsException
	 */
	void uploadSerie(String username, String password, String title, String creator, int nOfSeasons,
			String ageCertification, int releasedYear, String[] genres, String[] castMembers)
			throws AdminUsernameDontExistsException, PasswordIsWrongException, TitleAlreadyExistsException;

	/**
	 * 
	 * @return um iterador com todos os shows adicionados
	 */
	Iterator<Show> listShows();

	/**
	 * 
	 * @param name
	 * @return se o nome do artista existe ou não
	 */
	boolean artistExists(String name);

	/**
	 * cria um artista
	 * 
	 * @param name
	 * @param date
	 * @param place
	 */
	void createArtist(String name, String date, String place);

	/**
	 * faz update da bio do artista
	 * 
	 * @param name
	 * @param date
	 * @param place
	 * @throws NameAlreadyHasBioException
	 */
	void updateArtist(String name, String date, String place) throws NameAlreadyHasBioException;

	/**
	 * 
	 * @param name
	 * @return a bio do artista
	 * @throws ArtistNameDontExistsException
	 */
	String[] getArtistBio(String name) throws ArtistNameDontExistsException;

	/**
	 * 
	 * @param name
	 * @return um iterador com os creditos do artita com o nome <name>
	 */
	Iterator<Credit> listCredits(String name);

	/**
	 * adiciona uma review
	 * 
	 * @param username
	 * @param title
	 * @param comment
	 * @param classification
	 * @throws UsernameDoesntExistsException
	 * @throws UsernameIsAnAdminException
	 * @throws ShowDoesNotExistsException
	 * @throws UsernameAlreadyReviewThisShowException
	 */
	void addReview(String username, String title, String comment, String classification)
			throws UsernameDoesntExistsException, UsernameIsAnAdminException, ShowDoesNotExistsException,
			UsernameAlreadyReviewThisShowException;

	/**
	 * 
	 * @param title
	 * @return o numero de reviews que o show com o titulo <title> tem
	 */
	int getNrReviews(String title);

	/**
	 * 
	 * @param title
	 * @return um iterador com todas as reviews do show com o titulo <title>
	 * @throws ShowDoesNotExistsException
	 */
	Iterator<Review> listReviews(String title) throws ShowDoesNotExistsException;

	/**
	 * 
	 * @param genres
	 * @return um iterador com os shows filtrados com o determinaod genero
	 */
	Iterator<Show> listShowsByCriteria(ArrayList<String> genres);

	/**
	 * 
	 * @param releasedYear
	 * @return um iterador com os shows filtrado com o ano <releasedYear>
	 */
	Iterator<Show> listShowsByYear(int releasedYear);

	/**
	 * 
	 * @return se existe artistas na aplicação ou não
	 */
	boolean areThereAnyArtists();

	/**
	 * 
	 * @return um Iterador com os conjuntos que não têm shows em comum
	 */
	Iterator<SortedSet<String>> getArtistsAvoiders();

	/**
	 * 
	 * @return um iterador com os pares de artistas que têm mais shows em comum
	 */
	Iterator<Pares> getArtistsFriends();

	/**
	 * 
	 * @param title
	 * @return o overallScore de um show com o titulo <title>
	 */
	float getOverallScore(String title);

	/**
	 * 
	 * @return o numero que duas pessoas tiveram shows em comum
	 */
	int getmostCommonShows();

	/**
	 * 
	 * @return o maior numero de pessoas que não tem shows em comum
	 */
	int getlargestGroup();

}
