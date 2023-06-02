package Classes_Interfaces;

import java.util.SortedMap;
import java.util.TreeMap;

public class AdminClass extends AbstractUserClass implements Admin {

	private String password;
	private SortedMap<String, Show> shows;

	public AdminClass(String username, String password) {
		super(username);
		this.password = password;
		shows = new TreeMap<>();
	}

	@Override
	public int getNrUploadedShows() {
		return shows.size();
	}

	@Override
	public boolean isPasswordCorrect(String Password) {
		return this.password.equals(Password);
	}

	@Override
	public void addShow(Show show) {
		shows.put(show.getTitle(), show);

	}

}
