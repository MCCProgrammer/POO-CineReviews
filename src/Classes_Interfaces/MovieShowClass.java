package Classes_Interfaces;

public class MovieShowClass extends AbstractShowClass {

	private String director;
	private int duration;

	public MovieShowClass(String title, String directorName, int duration, String ageCertification, int realeasedYear,
			String[] genres, String[] castMembers) {
		super(title, ageCertification, realeasedYear, genres, castMembers);
		this.director = directorName;
		this.duration = duration;
	}

	@Override
	public String getPersonInCharge() {
		return director;
	}

	@Override
	public int getDuration() {
		return duration;
	}

}
