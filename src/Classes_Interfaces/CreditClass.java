package Classes_Interfaces;

public class CreditClass implements Credit {
	private String typeCredit;
	private Show show;

	public CreditClass(Show show, String typeCredit) {
		this.show = show;
		this.typeCredit = typeCredit;
	}

	@Override
	public Show getShow() {
		return show;
	}

	@Override
	public String getTypeCredit() {
		return typeCredit;
	}

	@Override
	public int compareTo(Credit o) {
		int releasedYear = this.show.getRealeasedYear();
		int otherreleasedYear = o.getShow().getRealeasedYear();
		if (releasedYear != otherreleasedYear) {
			return otherreleasedYear - releasedYear;
		}
		return this.show.getTitle().compareTo(o.getShow().getTitle());
	}

}
