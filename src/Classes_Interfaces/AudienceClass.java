package Classes_Interfaces;

public class AudienceClass extends AbstractReviewerClass {
	private static final int IMPORTANCE = 1;

	public AudienceClass(String username) {
		super(username);
	}

	@Override
	public int getImportance() {
		return IMPORTANCE;
	}
}
