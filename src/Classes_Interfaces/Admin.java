package Classes_Interfaces;

public interface Admin extends User {
	/**
	 * 
	 * @return o número de shows que este admin adicionou à aplicação
	 */
	int getNrUploadedShows();

	/**
	 * 
	 * @param Password
	 * @return se a passord que foi passada no argumento é igual à do admin
	 */
	boolean isPasswordCorrect(String Password);

	/**
	 * adiciona o show que o admin fez upload
	 * 
	 * @param show
	 */
	void addShow(Show show);
}
