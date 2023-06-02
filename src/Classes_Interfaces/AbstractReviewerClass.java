package Classes_Interfaces;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractReviewerClass extends AbstractUserClass implements Reviewer {

	private List<Review> reviews;

	public AbstractReviewerClass(String username) {
		super(username);
		reviews = new ArrayList<>();
	}

	@Override
	public int getNrReviews() {
		return reviews.size();
	}

	@Override
	public void addReview(Review review) {
		reviews.add(review);
	}

	@Override
	public boolean alreadyReviewThisTitle(String title) {
		boolean found = false;
		for (Review review : reviews) {
			if (review.getTitle().equals(title)) {
				found = true;
				break;
			}
		}
		return found;
	}
}
