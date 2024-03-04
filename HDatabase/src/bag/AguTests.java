package bag;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class AguTests {
	public static List<String> makeListOfCharacters(String s) {
		List<String> lst = new ArrayList<String>();
		for (int i = 0; i < s.length(); i++)
			lst.add(s.substring(i, i + 1));
		return lst;
	}

	@Test
	public void testEquals() { // works ig
		List<String> lst = makeListOfCharacters("aaabbc");
		List<String> epop = makeListOfCharacters("yahoowithnoLaptop");
		HDbase<String> zoro = new HDbase<String>();
		HDbase<String> sanji = new HDbase<String>();
		HDbase<String> nami = new HDbase<String>();
		zoro.addAll(lst);
		sanji.addAll(lst);
		nami.addAll(epop);

		assertTrue(sanji.equals(zoro));
		assertFalse(sanji.equals(nami));
	}

	@Test
	public void testaddAll() { // works ig
		List<String> lst = makeListOfCharacters("aaabbc");
		List<String> epop = makeListOfCharacters("yahoowithnoLaptop");
		HDbase<String> zoro = new HDbase<String>();
		HDbase<String> sanji = new HDbase<String>();
		zoro.addAll(lst);
		sanji.addAll(lst);

		String Answer = "";
		for (String pee : zoro) {
			Answer += pee;

		}
		System.out.print(Answer);
	}

	@Test
	public void testAddAll() { // actually makes no sense
		List<String> lst = makeListOfCharacters("aaabbc");
		List<String> epop = makeListOfCharacters("yahoowithnoLaptop");
		HDbase<String> zoro = new HDbase<String>();
		HDbase<String> sanji = new HDbase<String>();
		zoro.addAll(lst);
		sanji.addAll(lst);

		assertTrue(zoro.size() == 6);
		assertFalse(zoro.size() == 992);

	}

	@Test
	public void testSize() { // actually makes no sense
		List<String> lst = makeListOfCharacters("aaabbc");
		List<String> epop = makeListOfCharacters("yahoowithnoLaptop");
		HDbase<String> zoro = new HDbase<String>();
		HDbase<String> sanji = new HDbase<String>();
		zoro.addAll(lst);
		sanji.addAll(lst);

		assertTrue(zoro.size() == 6);

		assertFalse(zoro.size() == 992);

	}

	@Test
	public void testRemove() { // works apparently
		String epop = "I got lost";
		String freeJojo = "no siwa";
		HDbase<String> zoro = new HDbase<String>();
		zoro.add(epop);
		zoro.add(epop);
		// zoro.add(freeJojo);

		zoro.remove(epop);
		// zoro.remove(epop);

		assertEquals(zoro.size(), 1);
		assertFalse(zoro.size() == 2);
		assertFalse(zoro.size() == 992);

	}

	@Test
	public void testHash() {
		String epop = "I got lost";
		String freeJojo = "no siwa";
		HDbase<String> zoro = new HDbase<String>();
		zoro.add(epop);
		zoro.add(epop);
		zoro.add(freeJojo);

		System.out.print(epop.hashCode());
	}

}
