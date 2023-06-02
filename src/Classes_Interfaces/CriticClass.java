package Classes_Interfaces;

public class CriticClass extends AbstractReviewerClass {
	private static final int IMPORTANCE = 5;

	public CriticClass(String username) {
		super(username);
	}

	@Override
	public int getImportance() {
		return IMPORTANCE;
	}
}
