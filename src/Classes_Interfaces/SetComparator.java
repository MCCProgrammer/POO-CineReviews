package Classes_Interfaces;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class SetComparator implements Comparator<Set<String>> {
	@Override
	public int compare(Set<String> o1, Set<String> o2) {
		int comp = o2.size() - o1.size();
		if (comp == 0) {
			Iterator<String> it1 = o1.iterator();
			Iterator<String> it2 = o2.iterator();
			while (it1.hasNext() && it2.hasNext() && comp == 0)
				comp = it1.next().compareTo(it2.next());
		}
		return comp;
	}
}
