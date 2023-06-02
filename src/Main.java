/**
*@author Miguel Cacais do Carmo
         *O principal objetivo desta classe é ser o ponto de partida do programa, para além disso, lê e escreve todos os dados do mesmo.  
*/

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Scanner;
import java.util.SortedSet;

import Classes_Interfaces.AbstractReviewerClass;
import Classes_Interfaces.AdminClass;
import Classes_Interfaces.CineReviews;
import Classes_Interfaces.CineReviewsClass;
import Classes_Interfaces.Credit;
import Classes_Interfaces.CriticClass;
import Classes_Interfaces.MovieShowClass;
import Classes_Interfaces.Pares;
import Classes_Interfaces.Review;
import Classes_Interfaces.Show;
import Classes_Interfaces.User;
import Exceptions.*;

public class Main {

	/**
	 * Feedback dado pelo programa.
	 */
	private static final String EXIT = "Bye!\n";
	private static final String UNKNOWN_COMMAND = "Unknown command. Type help to see available commands.\n";
	private static final String USER_REGISTERED = "User %s was registered as %s.\n";
	private static final String NO_USERS_REGISTERED = "No users registered.\n";
	private static final String USERS_HEADER = "All registered users:\n";
	private static final String MOVIE_ADDED = "Movie %s (%d) was uploaded [%d new artists were created].\n";
	private static final String SERIES_ADDED = "Series %s (%d) was uploaded [%d new artists were created].\n";
	private static final String NO_SHOWS_UPLOADED = "No shows have been uploaded.\n";
	private static final String ARTIST_CREATED = "%s bio was created.\n";
	private static final String ARTIST_UPDATED = "%s bio was updated.\n";
	private static final String REVIEW_ADDED = "Review for %s was registered [%d reviews].\n";
	private static final String NO_SHOWS = "No show was found within the criteria.\n";
	private static final String UNKNOWN_USER_TYPE = "Unknown user type!\n";
	private static final String USER_ALREADY_EXISTS = "User %s already exists!\n";
	private static final String USER_HAS_POSTED = "User %s has posted %d reviews\n";
	private static final String ADMIN_HAS_UPLOADED = "Admin %s has uploaded %d shows\n";
	private static final String ADMIN_DOES_NOT_EXISTS = "Admin %s does not exist!\n";
	private static final String INVALID_AUTHENTICATION = "Invalid authentication!\n";
	private static final String ALL_SHOWS = "All shows:";
	private static final String BIO_ALREADY_AVAILABLE = "Bio of %s is already available!\n";
	private static final String NO_INFORMATION = "No information about %s!\n";
	private static final String USER_DOES_NOT_EXISTS = "User %s does not exist!\n";
	private static final String ADMIN_CANNOT_REVIEW = "Admin %s cannot review shows!\n";
	private static final String ALREADY_REVIEWED = "%s has already reviewed %s!\n";
	private static final String REVIEWS_OF = "Reviews of %s [%.1f]:\n";
	private static final String REVIEWS_OF2 = "Review of %s (%s): %s [%s]\n";
	private static final String SEARCH_GENRE = "Search by genre:\n";
	private static final String RELEASED_ON = "%s %s by %s released on %d [%.1f]\n";
	private static final String SHOWS_RELEASED_ON = "Shows released on %d:\n";
	private static final String SMALL_WORLD = "It is a small world!\n";
	private static final String NO_ARTISTS = "No artists yet!\n";
	private static final String ARTISTS_TOGETHER = "These artists have worked on %d projects together:\n";
	private static final String ARTISTS_NEVER_TOGETHER = "These %d artists never worked together:\n";
	private static final String SHOW_ALREADY_EXISTS = "Show %s already exists!\n";
	private static final String SHOW_DOES_NOT_EXISTS = "Show %s does not exist!\n";
	private static final String SHOW_NO_REVIEWS = "Show %s has no reviews.\n";
	private static final String NO_COLLABS = "No collaborations yet!\n";

	private static final String MOVIE = "movie";
	private static final String SERIES = "series";
	private static final String CRITIC = "critic";
	private static final String AUDIENCE = "audience";

	/**
	 * Enumerado que define os comandos do utilizador
	 */
	private enum Command {
		REGISTER("registers a user in the system"), USERS("lists all registered users"), MOVIE(
				"uploads a new movie"), SERIES("uploads a new series"), SHOWS("lists all shows"), ARTIST(
						"adds bio information about an artist"), CREDITS(
								"lists the bio and credits of an artist"), REVIEW("adds a review to a show"), REVIEWS(
										"lists the reviews of a show"), GENRE("lists shows of given genres"), RELEASED(
												"lists shows released in a given year"), AVOIDERS(
														"lists artists that have no common projects"), FRIENDS(
																"lists artists that have more projects together"), HELP(
																		"shows the available commands"), EXIT(
																				"terminates the execution of the program"), UNKNOWN(
																						"Unknown command");

		private final String description;

		private Command(String description) {
			this.description = description;
		}

		public String getDescription() {
			return this.description;
		}
	};

	private static Command getCommand(Scanner in) {
		try {
			return Command.valueOf(in.next().toUpperCase());
		} catch (IllegalArgumentException e) {
			return Command.UNKNOWN;
		}
	}

	/**
	 * Programa principal. Invoca interpretador de comandos.
	 * 
	 * @param args
	 *            argumentos para execucao da aplicacao. Nao sao usados, neste
	 *            programa.
	 */
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Main.execute_commands();
	}

	/**
	 * Interpretador de comandos.
	 */
	private static void execute_commands() {
		Scanner in = new Scanner(System.in);
		CineReviews cineRev = new CineReviewsClass();
		Command cmd = getCommand(in);
		while (!cmd.equals(Command.EXIT)) {
			switch (cmd) {
			case REGISTER:
				register(in, cineRev);
				break;
			case USERS:
				listUsers(in, cineRev);
				break;
			case MOVIE:
				uploadMovie(in, cineRev);
				break;
			case SERIES:
				uploadSerie(in, cineRev);
				break;
			case SHOWS:
				listShows(in, cineRev);
				break;
			case ARTIST:
				artist(in, cineRev);
				break;
			case CREDITS:
				listCredits(in, cineRev);
				break;
			case REVIEW:
				addReview(in, cineRev);
				break;
			case REVIEWS:
				listReviews(in, cineRev);
				break;
			case GENRE:
				genre(in, cineRev);
				break;
			case RELEASED:
				released(in, cineRev);
				break;
			case AVOIDERS:
				avoiders(in, cineRev);
				break;
			case FRIENDS:
				friends(in, cineRev);
				break;
			case HELP:
				printHelp();
				break;
			default:
				System.out.printf(UNKNOWN_COMMAND);
				break;
			}
			cmd = getCommand(in);
		}
		System.out.printf(EXIT);
		in.close();
	}

	/**
	 * comando que regista um usuário na aplicação
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void register(Scanner in, CineReviews cineRev) {
		String userType, username, password = null;
		userType = in.next();
		username = in.next();
		if (userType.equals("admin")) {
			password = inputString(in);
		}
		try {
			cineRev.addUser(userType, username, password);
			System.out.printf(USER_REGISTERED, username, userType);
		} catch (UnknownUserTypeException e) {
			System.out.printf(UNKNOWN_USER_TYPE);
		} catch (UserAlreadyExistsException e) {
			System.out.printf(USER_ALREADY_EXISTS, username);
		}
	}

	/**
	 * comando que lista todos os usuários cridados
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void listUsers(Scanner in, CineReviews cineRev) {
		Iterator<User> it = cineRev.listUsers();
		if (!it.hasNext()) {
			System.out.printf(NO_USERS_REGISTERED);
		} else {
			System.out.printf(USERS_HEADER);
			while (it.hasNext()) {
				User user = it.next();
				if (user instanceof AbstractReviewerClass) {
					System.out.printf(USER_HAS_POSTED, user.getUsername(),
							((AbstractReviewerClass) user).getNrReviews());
				} else {
					System.out.printf(ADMIN_HAS_UPLOADED, user.getUsername(), ((AdminClass) user).getNrUploadedShows());
				}
			}
		}
	}

	/**
	 * faz upload de um show do tipo filme na aplicação
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void uploadMovie(Scanner in, CineReviews cineRev) {
		String username, password, title, directorName, ageCertification;
		int duration, releasedYear, nGenres, nCastMembers;
		String[] genres, castMembers;
		username = in.next();
		password = inputString(in);
		title = inputString(in);
		directorName = inputString(in);
		duration = inputInt(in);
		ageCertification = inputString(in);
		releasedYear = inputInt(in);
		nGenres = inputInt(in);
		genres = new String[nGenres];
		for (int i = 0; i < genres.length; i++) {
			genres[i] = inputString(in);
		}
		nCastMembers = inputInt(in);
		castMembers = new String[nCastMembers];
		for (int i = 0; i < castMembers.length; i++) {
			castMembers[i] = inputString(in);
		}
		try {
			cineRev.uploadMovie(username, password, title, directorName, duration, ageCertification, releasedYear,
					genres, castMembers);
			int artistsCreated = cineRev.getAndcreateArtists(directorName, castMembers, title);
			System.out.printf(MOVIE_ADDED, title, releasedYear, artistsCreated);
		} catch (AdminUsernameDontExistsException e) {
			System.out.printf(ADMIN_DOES_NOT_EXISTS, username);
		} catch (PasswordIsWrongException e) {
			System.out.printf(INVALID_AUTHENTICATION);
		} catch (TitleAlreadyExistsException e) {
			System.out.printf(SHOW_ALREADY_EXISTS, title);
		}
	}

	/**
	 * faz upload de um show do tipo serie na aplicação
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void uploadSerie(Scanner in, CineReviews cineRev) {
		String username, password, title, creator, ageCertification;
		int nOfSeasons, releasedYear, nGenres, nCastMembers;
		String[] genres, castMembers;
		username = in.next();
		password = inputString(in);
		title = inputString(in);
		creator = inputString(in);
		nOfSeasons = inputInt(in);
		ageCertification = inputString(in);
		releasedYear = inputInt(in);
		nGenres = inputInt(in);
		genres = new String[nGenres];
		for (int i = 0; i < genres.length; i++) {
			genres[i] = inputString(in);
		}
		nCastMembers = inputInt(in);
		castMembers = new String[nCastMembers];
		for (int i = 0; i < castMembers.length; i++) {
			castMembers[i] = inputString(in);
		}
		try {
			cineRev.uploadSerie(username, password, title, creator, nOfSeasons, ageCertification, releasedYear, genres,
					castMembers);
			int artistsCreated = cineRev.getAndcreateArtists(creator, castMembers, title);
			System.out.printf(SERIES_ADDED, title, releasedYear, artistsCreated);
		} catch (AdminUsernameDontExistsException e) {
			System.out.printf(ADMIN_DOES_NOT_EXISTS, username);
		} catch (PasswordIsWrongException e) {
			System.out.println(INVALID_AUTHENTICATION);
		} catch (TitleAlreadyExistsException e) {
			System.out.printf(SHOW_ALREADY_EXISTS, title);
		}
	}

	/**
	 * lista todos os shows que foram criados
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void listShows(Scanner in, CineReviews cineRev) {
		Iterator<Show> it = cineRev.listShows();
		if (!it.hasNext()) {
			System.out.printf(NO_SHOWS_UPLOADED);
		} else {
			System.out.println(ALL_SHOWS);
			while (it.hasNext()) {
				Show show = it.next();
				System.out.printf("%s; %s; %d; %s; %d; %s; ", show.getTitle(), show.getPersonInCharge(),
						show.getDuration(), show.getAgeCertification(), show.getRealeasedYear(), show.getFirstGenre());
				Iterator<String> castMembers = show.getCastMembers();
				int counter = 0;
				while (castMembers.hasNext() && counter != 3) {
					String castMember = castMembers.next();
					System.out.printf(castMember);
					if (castMembers.hasNext() && counter != 2) {
						System.out.printf("; ");
					} else {
						System.out.println();
					}
					counter++;
				}
			}
		}
	}

	/**
	 * cria um artista na aplicação
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void artist(Scanner in, CineReviews cineRev) {
		String name, date, place;
		name = inputString(in);
		date = inputString(in);
		place = inputString(in);
		try {
			if (!cineRev.artistExists(name)) {
				cineRev.createArtist(name, date, place);
				System.out.printf(ARTIST_CREATED, name);
			} else {
				cineRev.updateArtist(name, date, place);
				System.out.printf(ARTIST_UPDATED, name);
			}
		} catch (NameAlreadyHasBioException e) {
			System.out.printf(BIO_ALREADY_AVAILABLE, name);
		}
	}

	/**
	 * lista todos os creditos de um determinado artista
	 * @param in
	 * @param cineRev
	 */
	private static void listCredits(Scanner in, CineReviews cineRev) {
		String name = inputString(in);
		try {
			String[] bio = cineRev.getArtistBio(name);
			if (bio != null) {
				System.out.println(bio[0]);
				System.out.println(bio[1]);
			}
			Iterator<Credit> it = cineRev.listCredits(name);
			while (it.hasNext()) {
				Credit credit = it.next();
				System.out.printf("%s; %d; %s ", credit.getShow().getTitle(), credit.getShow().getRealeasedYear(),
						credit.getTypeCredit());
				if (credit.getShow() instanceof MovieShowClass) {
					System.out.printf("[%s]\n", MOVIE);
				} else {
					System.out.printf("[%s]\n", SERIES);
				}
			}
		} catch (ArtistNameDontExistsException e) {
			System.out.printf(NO_INFORMATION, name);
		}
	}

	/**
	 * adiciona uma review a um determinado show
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void addReview(Scanner in, CineReviews cineRev) {
		String username, title, comment, classification;
		username = in.next();
		title = inputString(in);
		comment = inputString(in);
		classification = inputString(in);
		try {
			cineRev.addReview(username, title, comment, classification);
			System.out.printf(REVIEW_ADDED, title, cineRev.getNrReviews(title));
		} catch (UsernameDoesntExistsException e) {
			System.out.printf(USER_DOES_NOT_EXISTS, username);
		} catch (UsernameIsAnAdminException e) {
			System.out.printf(ADMIN_CANNOT_REVIEW, username);
		} catch (ShowDoesNotExistsException e) {
			System.out.printf(SHOW_DOES_NOT_EXISTS, title);
		} catch (UsernameAlreadyReviewThisShowException e) {
			System.out.printf(ALREADY_REVIEWED, username, title);
		}
	}
	
	/**
	 * lista as reviews de um determinado show
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void listReviews(Scanner in, CineReviews cineRev) {
		String title = inputString(in);
		try {
			Iterator<Review> it = cineRev.listReviews(title);
			if (!it.hasNext()) {
				System.out.printf(SHOW_NO_REVIEWS, title);
			} else {
				System.out.printf(REVIEWS_OF, title, cineRev.getOverallScore(title));
				while (it.hasNext()) {
					Review review = it.next();
					String type;
					if (review.getWriter() instanceof CriticClass) {
						type = CRITIC;
					} else {
						type = AUDIENCE;
					}
					System.out.printf(REVIEWS_OF2, review.getWriter().getUsername(), type, review.getComment(),
							review.getClassification());
				}
			}
		} catch (ShowDoesNotExistsException e) {
			System.out.printf(SHOW_DOES_NOT_EXISTS, title);
		}
	}

	/**
	 * devolve os shows que contém os determinados generos dados
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void genre(Scanner in, CineReviews cineRev) {
		int nGenres = inputInt(in);
		ArrayList<String> genres = new ArrayList<>();
		for (int i = 0; i < nGenres; i++) {
			genres.add(inputString(in));
		}
		Iterator<Show> it = cineRev.listShowsByCriteria(genres);
		if (!it.hasNext()) {
			System.out.printf(NO_SHOWS);
		} else {
			System.out.printf(SEARCH_GENRE);
			while (it.hasNext()) {
				Show show = it.next();
				String typeShow;
				if (show instanceof MovieShowClass) {
					typeShow = MOVIE.substring(0, 1).toUpperCase() + MOVIE.substring(1);
				} else {
					typeShow = SERIES.substring(0, 1).toUpperCase() + SERIES.substring(1);
				}
				System.out.printf(RELEASED_ON, typeShow, show.getTitle(),
						show.getPersonInCharge(), show.getRealeasedYear(), show.getOverallScore());
			}
		}
	}

	/**
	 * devolve os shows que o releadYear é igual ao fornecido
	 * 
	 * @param in
	 * @param cineRev
	 */
	private static void released(Scanner in, CineReviews cineRev) {
		int releasedYear = inputInt(in);
		Iterator<Show> it = cineRev.listShowsByYear(releasedYear);
		if (!it.hasNext()) {
			System.out.printf(NO_SHOWS);
		} else {
			System.out.printf(SHOWS_RELEASED_ON, releasedYear);
			while (it.hasNext()) {
				Show show = it.next();
				String typeShow;
				if (show instanceof MovieShowClass) {
					typeShow = MOVIE.substring(0, 1).toUpperCase() + MOVIE.substring(1);
				} else {
					typeShow = SERIES.substring(0, 1).toUpperCase() + SERIES.substring(1);
				}
				System.out.printf(RELEASED_ON, typeShow, show.getTitle(), show.getPersonInCharge(),
						show.getRealeasedYear(), show.getOverallScore());
			}
		}
	}

	/**
	 * lista o/os maiores grupos de pessoas que não têm shows em comum
	 * @param in
	 * @param cineRev
	 */
	private static void avoiders(Scanner in, CineReviews cineRev) {
		if (cineRev.areThereAnyArtists()) {
			Iterator<SortedSet<String>> it = cineRev.getArtistsAvoiders();
			if (it == null) {
				System.out.printf(SMALL_WORLD);
			} else {
				System.out.printf(ARTISTS_NEVER_TOGETHER, cineRev.getlargestGroup());
				while (it.hasNext()) {
					SortedSet<String> set = it.next();
					String output = "";
					for (String string : set) {
						output += string + ", ";
					}
					System.out.println(output.substring(0, output.length() - 2));
				}
			}
		} else {
			System.out.printf(NO_ARTISTS);
		}
	}

	/**
	 * lista os pares de pessoas que têm um maior número de shows partilhados
	 * @param in
	 * @param cineRev
	 */
	private static void friends(Scanner in, CineReviews cineRev) {
		if (cineRev.areThereAnyArtists()) {
			Iterator<Pares> it = cineRev.getArtistsFriends();
			if (it == null) {
				System.out.printf(NO_COLLABS);
			} else {
				System.out.printf(ARTISTS_TOGETHER, cineRev.getmostCommonShows());
				while (it.hasNext()) {
					Pares par = it.next();
					System.out.printf("%s and %s\n", par.getFirst(), par.getLast());
				}
			}
		} else {
			System.out.printf(NO_ARTISTS);
		}
	}

	/**
	 * imprime todos os comandos da aplicação e respetiva descrição
	 */
	private static void printHelp() {
		StringBuilder str = new StringBuilder();
		for (Command comm : Command.values()) {
			if (!comm.toString().equals("UNKNOWN"))
				str.append(comm.toString().toLowerCase() + " - " + comm.getDescription() + "\n");
		}
		System.out.print(str);
	}
	
	/**
	 * lê e devolve uma string com o espaço em branco removido
	 * 
	 * @param in
	 * @return
	 */
	private static String inputString(Scanner in) {
		String s = in.nextLine().trim();
		return s;
	}

	/**
	 * lê e devolve um inteiro com mudança de linha
	 * 
	 * @param in
	 * @return
	 */
	private static int inputInt(Scanner in) {
		int s = in.nextInt();
		in.nextLine();
		return s;
	}

}