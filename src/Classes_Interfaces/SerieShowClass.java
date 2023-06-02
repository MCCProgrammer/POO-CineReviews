package Classes_Interfaces;

public class SerieShowClass extends AbstractShowClass {

	private String creator;
	private int nrOfSeasons;

	public SerieShowClass(String title, String creator, int nrOfSeasons, String ageCertification, int realeasedYear,
			String[] genres, String[] castMembers) {
		super(title, ageCertification, realeasedYear, genres, castMembers);
		this.creator = creator;
		this.nrOfSeasons = nrOfSeasons;
	}

	@Override
	public String getPersonInCharge() {
		return creator;
	}

	@Override
	public int getDuration() {
		return nrOfSeasons;
	}

}
