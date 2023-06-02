package Classes_Interfaces;

public interface Reviewer extends User {

	/**
	 * 
	 * @return número de reviews feitas
	 */
	int getNrReviews();

	/**
	 * 
	 * @param title
	 * @return se este usuário já fez uma review ao show com o titulo <title> ou não
	 */
	boolean alreadyReviewThisTitle(String title);

	/**
	 * 
	 * @return a importancia da review
	 */
	int getImportance();

	/**
	 * adiciona a review feita
	 * 
	 * @param review
	 */
	void addReview(Review review);
}
