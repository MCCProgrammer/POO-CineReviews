package Classes_Interfaces;

public abstract class AbstractUserClass implements User {
	private String username;

	public AbstractUserClass(String username) {
		this.username = username;
	}

	@Override
	public String getUsername() {
		return username;
	}

}
