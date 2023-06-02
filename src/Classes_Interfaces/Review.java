package Classes_Interfaces;

public interface Review extends Comparable<Review> {

	/**
	 * 
	 * @return o usuário que escreveu esta review
	 */
	User getWriter();

	/**
	 * 
	 * @return o titulo do show
	 */
	String getTitle();

	/**
	 * 
	 * @return o comentário da review
	 */
	String getComment();

	/**
	 * 
	 * @return a classificação
	 */
	String getClassification();

	/**
	 * 
	 * @return a classificaçao em valor
	 */
	int getClassificationValue();
}
