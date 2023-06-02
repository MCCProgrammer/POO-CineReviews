package Classes_Interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.TreeSet;

public abstract class AbstractShowClass implements Show {
	private String title, ageCertification;
	private int realeasedYear;
	private float overrallScore;
	private List<String> genres, castMembers;
	private Set<Review> reviews;

	public AbstractShowClass(String title, String ageCertification, int realeasedYear, String[] genres,
			String[] castMembers) {
		this.title = title;
		this.ageCertification = ageCertification;
		this.realeasedYear = realeasedYear;
		this.genres = new ArrayList<>();
		this.castMembers = new ArrayList<>();
		this.reviews = new TreeSet<>();
		fillArrays(genres, castMembers);
		overrallScore = 0;
	}

	/**
	 * passa os arrays para a coleção arrayList para ser posteriormente mais fácil
	 * de obter e adicionar valores
	 * 
	 * @param genres
	 * @param castMembers
	 */
	private void fillArrays(String[] genres, String[] castMembers) {
		for (String genre : genres) {
			this.genres.add(genre);
		}
		for (String castMember : castMembers) {
			this.castMembers.add(castMember);
		}
	}

	@Override
	public String getTitle() {
		return title;
	}

	@Override
	public String getAgeCertification() {
		return ageCertification;
	}

	@Override
	public int getRealeasedYear() {
		return realeasedYear;
	}

	@Override
	public String getFirstGenre() {
		return genres.get(0);
	}

	@Override
	public ListIterator<String> getCastMembers() {
		return castMembers.listIterator();
	}

	@Override
	public int getNrReviews() {
		return reviews.size();
	}

	@Override
	public void addReview(Review review) {
		reviews.add(review);
		uptdateOverallScore();
	}

	@Override
	public Iterator<Review> listReviews() {
		return reviews.iterator();
	}

	@Override
	public float getOverallScore() {
		return overrallScore;
	}

	@Override
	public int compareTo(Show show) {
		if (overrallScore != show.getOverallScore()) {
			return Float.compare(show.getOverallScore(), overrallScore);
		}
		if (realeasedYear != show.getRealeasedYear()) {
			return show.getRealeasedYear() - realeasedYear;
		}
		return title.compareTo(show.getTitle());
	}

	@Override
	public boolean sameGenres(ArrayList<String> genres) {
		return this.genres.containsAll(genres);
	}

	@Override
	public boolean sameYear(int year) {
		return realeasedYear == year;
	}

	@Override
	public List<String> getAllArtists() {
		List<String> array = new ArrayList<>(castMembers);
		array.add(0, getPersonInCharge());
		return array;
	}

	/**
	 * faz update do overall score
	 */
	private void uptdateOverallScore() {
		float numerador = 0, divisor = 0;
		for (Review review : reviews) {
			int importance = ((AbstractReviewerClass) review.getWriter()).getImportance();
			numerador += importance * review.getClassificationValue();
			divisor += importance;
		}
		overrallScore = numerador / divisor;
	}

}
