package Classes_Interfaces;

import java.util.ArrayList;

import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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

public class CineReviewsClass implements CineReviews {

	private static final String ADMIN = "admin";
	private static final String CRITIC = "critic";
	private static final String AUDIENCE = "audience";

	private Map<String, User> users;
	private Map<String, Show> shows;
	private Map<String, Artist> artists;
	private int mostcommonsShows, largestGroup;

	public CineReviewsClass() {
		users = new TreeMap<>();
		shows = new TreeMap<>();
		artists = new TreeMap<>();
	}

	@Override
	public void addUser(String userType, String username, String password)
			throws UnknownUserTypeException, UserAlreadyExistsException {
		switch (userType) {
		case AUDIENCE:
			if (userExists(username)) {
				throw new UserAlreadyExistsException();
			}
			users.put(username, new AudienceClass(username));
			break;
		case CRITIC:
			if (userExists(username)) {
				throw new UserAlreadyExistsException();
			}
			users.put(username, new CriticClass(username));
			break;
		case ADMIN:
			if (userExists(username)) {
				throw new UserAlreadyExistsException();
			}
			users.put(username, new AdminClass(username, password));
			break;
		default:
			throw new UnknownUserTypeException();
		}

	}

	@Override
	public Iterator<User> listUsers() {
		return users.values().iterator();
	}

	@Override
	public int getAndcreateArtists(String personInCharge, String[] castMembers, String title) {
		int artistsAdded = 0;
		if (!artistExists(personInCharge)) {
			artists.put(personInCharge, new ArtistClass(personInCharge));
			artistsAdded++;
		}
		for (int i = 0; i < castMembers.length; i++) {
			if (!artistExists(castMembers[i])) {
				artists.put(castMembers[i], new ArtistClass(castMembers[i]));
				artistsAdded++;
			}
		}
		updateArtistsCredits(personInCharge, castMembers, shows.get(title));
		return artistsAdded;
	}

	@Override
	public void uploadMovie(String username, String password, String title, String directorName, int duration,
			String ageCertification, int releasedYear, String[] genres, String[] castMembers)
			throws AdminUsernameDontExistsException, PasswordIsWrongException, TitleAlreadyExistsException {
		User user = users.get(username);
		if (user == null || !(user instanceof AdminClass)) {
			throw new AdminUsernameDontExistsException();
		} else if (!((AdminClass) user).isPasswordCorrect(password)) {
			throw new PasswordIsWrongException();
		} else if (shows.containsKey(title)) {
			throw new TitleAlreadyExistsException();
		} else {
			Show show = new MovieShowClass(title, directorName, duration, ageCertification, releasedYear, genres,
					castMembers);
			shows.put(title, show);
			((AdminClass) user).addShow(show);
		}
	}

	@Override
	public void uploadSerie(String username, String password, String title, String creator, int nOfSeasons,
			String ageCertification, int releasedYear, String[] genres, String[] castMembers)
			throws AdminUsernameDontExistsException, PasswordIsWrongException, TitleAlreadyExistsException {
		User user = users.get(username);
		if (user == null || !(user instanceof AdminClass)) {
			throw new AdminUsernameDontExistsException();
		} else if (!((AdminClass) user).isPasswordCorrect(password)) {
			throw new PasswordIsWrongException();
		} else if (shows.containsKey(title)) {
			throw new TitleAlreadyExistsException();
		} else {
			Show show = new SerieShowClass(title, creator, nOfSeasons, ageCertification, releasedYear, genres,
					castMembers);
			shows.put(title, show);
			((AdminClass) user).addShow(show);
		}

	}

	@Override
	public Iterator<Show> listShows() {
		return shows.values().iterator();
	}

	@Override
	public boolean artistExists(String name) {
		return artists.containsKey(name);
	}

	@Override
	public void createArtist(String name, String date, String place) {
		artists.put(name, new ArtistClass(name, date, place));
	}

	@Override
	public void updateArtist(String name, String date, String place) throws NameAlreadyHasBioException {
		Artist artist = artists.get(name);
		if (artist.getDate() != null && artist.getPlace() != null) {
			throw new NameAlreadyHasBioException();
		}
		artist.addBio(date, place);
	}

	@Override
	public String[] getArtistBio(String name) throws ArtistNameDontExistsException {
		Artist artist = artists.get(name);
		if (artist == null) {
			throw new ArtistNameDontExistsException();
		}
		if (artist.getDate() == null) {
			return null;
		}
		String[] bio = { artist.getDate(), artist.getPlace() };
		return bio;
	}

	@Override
	public Iterator<Credit> listCredits(String name) {
		Artist artist = artists.get(name);
		return artist.listCredits();
	}

	@Override
	public void addReview(String username, String title, String comment, String classification)
			throws UsernameDoesntExistsException, UsernameIsAnAdminException, ShowDoesNotExistsException,
			UsernameAlreadyReviewThisShowException {
		User user = users.get(username);
		if (user == null) {
			throw new UsernameDoesntExistsException();
		}
		if (user instanceof AdminClass) {
			throw new UsernameIsAnAdminException();
		}
		Show show = shows.get(title);
		if (show == null) {
			throw new ShowDoesNotExistsException();
		}
		if (((AbstractReviewerClass) user).alreadyReviewThisTitle(title)) {
			throw new UsernameAlreadyReviewThisShowException();
		}
		Review review = new ReviewClass(user, title, comment, classification);
		show.addReview(review);
		((AbstractReviewerClass) user).addReview(review);
	}

	@Override
	public int getNrReviews(String title) {
		Show show = shows.get(title);
		return show.getNrReviews();
	}

	@Override
	public Iterator<Review> listReviews(String title) throws ShowDoesNotExistsException {
		Show show = shows.get(title);
		if (show == null) {
			throw new ShowDoesNotExistsException();
		}
		return show.listReviews();
	}

	@Override
	public Iterator<Show> listShowsByCriteria(ArrayList<String> genres) {
		Set<Show> sortedset = new TreeSet<>();
		for (Show show : shows.values()) {
			if (show.sameGenres(genres)) {
				sortedset.add(show);
			}
		}
		return sortedset.iterator();
	}

	@Override
	public Iterator<Show> listShowsByYear(int releasedYear) {
		Set<Show> sortedset = new TreeSet<>();
		for (Show show : shows.values()) {
			if (show.sameYear(releasedYear)) {
				sortedset.add(show);
			}
		}
		return sortedset.iterator();
	}

	@Override
	public boolean areThereAnyArtists() {
		return artists.size() > 0;
	}

	@Override
	public Iterator<SortedSet<String>> getArtistsAvoiders() {
		SortedSet<SortedSet<String>> powerSet = powerSet();
		largestGroup = 1;
		List<SortedSet<String>> set = new ArrayList<>();
		for (SortedSet<String> sortedSet : powerSet) {
			if (!(sortedSet.isEmpty() || sortedSet.size() == 1)) {
				if (!hasCooperations(sortedSet)) {
					set.add(sortedSet);
					if (sortedSet.size() > largestGroup) {
						largestGroup = sortedSet.size();
					}
				}
			}
		}
		if (largestGroup == 1) {
			return null;
		}
		List<SortedSet<String>> setFiltered = new ArrayList<>();
		for (SortedSet<String> sortedSet : set) {
			if (sortedSet.size() == largestGroup) {
				setFiltered.add(sortedSet);
			}
		}
		return setFiltered.iterator();
	}

	private boolean hasCooperations(SortedSet<String> sortedSet) {
		SortedMap<String, SortedMap<String, Integer>> cooperations = fillArtists();
		boolean cooperation = false;
		for (String artist1 : sortedSet) {
			for (String artist2 : sortedSet) {
				if (artist1 != artist2 && cooperations.get(artist1).containsKey(artist2)) {
					cooperation = true;
				}
			}
		}
		return cooperation;
	}

	private SortedSet<SortedSet<String>> powerSet() {
		SortedSet<SortedSet<String>> subsets = new TreeSet<>(new SetComparator());
		subsets.add(new TreeSet<>());
		for (String artist : artists.keySet()) {
			SortedSet<SortedSet<String>> current = new TreeSet<>(new SetComparator());
			for (SortedSet<String> set : subsets) {
				SortedSet<String> newSet = new TreeSet<>(set);
				newSet.add(artist);
				current.add(newSet);
			}
			subsets.addAll(current);
		}
		return subsets;
	}

	private SortedMap<String, SortedMap<String, Integer>> fillArtists() {
		mostcommonsShows = 0;
		SortedMap<String, SortedMap<String, Integer>> cooperations = new TreeMap<>();
		for (String artist : artists.keySet()) {
			cooperations.put(artist, new TreeMap<>());
		}
		for (Show show : shows.values()) {
			List<String> array = show.getAllArtists();
			for (String artist1 : array) {
				for (String artist2 : array) {
					if (!artist1.equals(artist2)) {
						int n;
						if (cooperations.get(artist1).containsKey(artist2)) {
							n = cooperations.get(artist1).get(artist2) + 1;
							cooperations.get(artist1).put(artist2, n);
						} else {
							n = 1;
							cooperations.get(artist1).put(artist2, 1);
						}
						if (n > mostcommonsShows) {
							mostcommonsShows = n;
						}
					}
				}
			}
		}
		return cooperations;
	}

	@Override
	public Iterator<Pares> getArtistsFriends() {
		SortedMap<String, SortedMap<String, Integer>> cooperations = fillArtists();
		if (mostcommonsShows == 0) {
			return null;
		}
		SortedSet<Pares> sortedSet = new TreeSet<>();
		for (String key : cooperations.keySet()) {
			for (String key2 : cooperations.get(key).keySet()) {
				if (cooperations.get(key).get(key2) == mostcommonsShows) {
					sortedSet.add(new ParesClass(key, key2));
				}
			}
		}
		return sortedSet.iterator();
	}

	/**
	 * 
	 * @param username
	 * @return se o usuário existe ou não
	 */
	private boolean userExists(String username) {
		return users.containsKey(username);
	}

	@Override
	public float getOverallScore(String title) {
		Show show = shows.get(title);
		return show.getOverallScore();
	}

	@Override
	public int getmostCommonShows() {
		return mostcommonsShows;
	}

	/**
	 * faz update dos creditos do artista
	 * 
	 * @param personInCharge
	 * @param castMembers
	 * @param show
	 */
	private void updateArtistsCredits(String personInCharge, String[] castMembers, Show show) {
		if (show instanceof MovieShowClass) {
			artists.get(personInCharge).addShow("director", show);
		} else {
			artists.get(personInCharge).addShow("creator", show);
		}
		for (String castMember : castMembers) {
			artists.get(castMember).addShow("actor", show);
		}
	}

	@Override
	public int getlargestGroup() {
		return largestGroup;
	}

}
