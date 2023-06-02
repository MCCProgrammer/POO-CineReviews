package Classes_Interfaces;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class ArtistClass implements Artist {

	private String name, date, place;
	private Set<Credit> credits;

	public ArtistClass(String name, String date, String place) {
		this.name = name;
		this.date = date;
		this.place = place;
		credits = new TreeSet<>();
	}

	public ArtistClass(String name) {
		this.name = name;
		this.date = null;
		this.place = null;
		credits = new TreeSet<>();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getPlace() {
		return place;
	}

	@Override
	public String getDate() {
		return date;
	}

	@Override
	public void addShow(String roll, Show show) {
		credits.add(new CreditClass(show, roll));
	}

	@Override
	public void addBio(String date, String place) {
		this.date = date;
		this.place = place;
	}

	@Override
	public Iterator<Credit> listCredits() {
		return credits.iterator();
	}

}
