package Classes_Interfaces;

public class ParesClass implements Pares {

	private String[] par;

	public ParesClass(String A1, String A2) {
		par = new String[2];
		if (A1.compareTo(A2) < 0) {
			par[0] = A1;
			par[1] = A2;
		} else {
			par[0] = A2;
			par[1] = A1;
		}
	}

	@Override
	public int compareTo(Pares o) {
		if (!this.getFirst().equals(o.getFirst())) {
			return getFirst().compareTo(o.getFirst());
		}
		return getLast().compareTo(o.getLast());
	}

	@Override
	public String getFirst() {
		return par[0];
	}

	@Override
	public String getLast() {
		return par[1];
	}
}
