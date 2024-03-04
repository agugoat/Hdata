package bag;

import java.util.AbstractCollection;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class HDbase<T> extends AbstractCollection<T> {

	Hashtable<T, Integer> heavyTable;
	// just created a hashtable for the bag

	public HDbase() {
		heavyTable = new Hashtable<T, Integer>();
		// initializing a new hashtable
	}

	// method will add to the "bag"
	@Override
	public boolean add(T o) {
		if (heavyTable.containsKey(o)) {
			// Element already exists, increment its count
			heavyTable.put(o, heavyTable.get(o) + 1);
		} else {
			// Element not in the bag, add it with a count of 1
			heavyTable.put(o, 1);
		}
		return true;
		// always returns true
	}

	public boolean addMany(T o, int count) {
		if (count < 1) {
			throw new IllegalArgumentException("Your less than one");

		}
		// if the count is less than 1

		if (count > 1000000000) {
			throw new IllegalArgumentException("the count is bigger than 1 Billion");
		}
		// if the count is bigger than 1000000000

		if (heavyTable.containsKey(o)) {
			heavyTable.put(o, heavyTable.get(o) + count);
			// if the key already exists add on to the existing element
		} else {
			heavyTable.put(o, count);
			// put the the element plus the count
		}
		return true;
		// always returns true
	}

	@Override
	// Simple tooString method
	public String toString() {
		String answer = "";
		for (T elements : heavyTable.keySet()) {
			answer += elements.toString();
		}
		return answer;
	}
	// Probably a bit basic but it works

	@Override
	public boolean equals(Object o) { // works
		if (this == o) {
			return true;
			// if the object and this equals return true
		}
		if (!(o instanceof HDbase)) {
			return false;

			// if o is not a instance of heavybag return false
		}

		HDbase<?> other = (HDbase<?>) o;
		// making a hashtable object
		// Check if the Hashtables have the same size
		if (this.heavyTable.size() != other.heavyTable.size()) {
			return false;
			// Check if the heavybags have the same size
		}
		for (T element : heavyTable.keySet()) {
			if (other.contains(element) != this.contains(element)
					|| other.getCount(element) != this.getCount(element)) {
				return false;
			}
			// checking if the heavybags have the same elements and counts

		}
		return true;

		// alwats returning true
	}

	@Override
	// hashcode method
	public int hashCode() {
		final int prime = 31;
		// prime number to multiply result with
		int results = 1;
		// results variable
		for (T elements : heavyTable.keySet()) {
			if (elements == null) {
				return 0;
				// if elements = null return 0
			} else {
				int hash = (elements.hashCode() + heavyTable.get(elements));
				// getting hashcode
				int trueHash = hash * prime;
				// caluating the true hash

				results += trueHash;
				// setting results equal too true hash

			}

		}
		return results;
		// return the results
	}

	// iterator method
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Iterator<T> keyIterator = heavyTable.keySet().iterator();
			// making a new keyIterator
			private T currentKey = null;
			// currentKey
			private int currentCount = 0;
			// current count

			@Override
			public boolean hasNext() {
				if (currentCount > 0 || keyIterator.hasNext()) {
					return true;
					// return true if count is bigger than 0 and keyIterator has next
				}
				return false;
			}

			@Override
			public T next() {
				if (currentCount == 0) {
					currentKey = keyIterator.next();
					currentCount = heavyTable.get(currentKey);
					// Move to the next key and update the current count
				}
				currentCount--;
				// Decrement the count for each occurrence of the element
				return currentKey;
			}

			@Override
			public void remove() {
				if (contains(currentKey)) {
					int countInBag = heavyTable.get(currentKey);
					if (countInBag > 1) {
						// if count in the bag is bigger than 1
						heavyTable.put(currentKey, countInBag - 1);
						// removing count of one of the elements
					} else {
						keyIterator.remove();
						// Removes the currentKey entirely if count is 1
					}
				}
			}
		};
	};

	public Set<T> uniqueElements() {
		HashSet<T> uniqueElements = new HashSet<>();
		// makes a new hashset

		for (T uniElement : heavyTable.keySet()) {
			uniqueElements.add(uniElement);
			// loop through the set and add one of each element
			// sets to not allow duplicates
		}
		return uniqueElements;
		// return the set

	}

	public int getCount(Object o) {
		if (o == null) {
			return 0;
			// null check
		}
		Integer count = heavyTable.get(o);
		// getting count
		if (count == null) {
			return 0;
			// if the count equals null return 0
		}
		return count;
		// just return the count
	}

	// choosing a random element
	public T choose(Random r) {
		int totalSize = size();
		// size of table
		int randomIndex = r.nextInt(totalSize) + 1; // Generate a random number
		// randomly generate number
		for (T element : heavyTable.keySet()) {
			int count = heavyTable.get(element);
			// getting count

			randomIndex = randomIndex - count;
			if (randomIndex <= 0) {
				// When the randomIndex is within the count range of the current element
				return element;

			}
		}
		throw new IllegalStateException("Not here");

	}

	@Override
	// contains method
	public boolean contains(Object o) {
		for (T element : heavyTable.keySet()) {
			// looping through the heavy bag
			if (element.equals(o)) {
				// if elment equals the object
				return true;
				// returns true
			}

		}
		return false;
		// returns false
	}

	@Override
	public boolean remove(Object o) { // may not work , but it works
		if (heavyTable.containsKey(o)) {
			int count = heavyTable.get(o);
			if (count > 1) {
				heavyTable.put((T) o, count - 1);
				// unchecked cast
				// putting the element and the count minus 1

			} else {
				heavyTable.remove(o);
				// remove 1 if the count is just 1
			}

		}

		return true;
	}

	@Override
	public int size() {
		int totalSize = 0;
		for (Integer size : heavyTable.values()) {
			totalSize += size;
			// just gets the total number of object instances
		}
		return totalSize;

	}
}
