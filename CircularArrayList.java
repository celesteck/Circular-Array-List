import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularArrayList<T> implements ArrayListADT<T>, Iterable<T> {
    int size;
    int start;
    T[] arrayList;
    int capacity;

    @SuppressWarnings("unchecked")
    public CircularArrayList() {
        //The constructor for the CircularArrayList
        this.start = 0;
        this.size = 0;
        this.arrayList = (T[]) (new Object[2]);
    }

    @SuppressWarnings("unchecked")
    public CircularArrayList(int initialCapacity) {
        //The constructor for the CircularArrayList
        this.start = 0;
        this.size = 0;
        this.arrayList = (T[]) (new Object[initialCapacity]);
    }

    private int indexFor(int index) {
        int ans = (this.start + index) % this.arrayList.length;
        return ans;
    }
	
    @Override
    public void addRear(T element) {
        //Method to add element at the rear of the arraylist
        if (this.size == arrayList.length)
            this.expandCapacity();

        this.arrayList[this.indexFor(this.size)] = element;
        this.size++;
    }

    @Override
    public void addFront(T element) {
        //Method to add element at the front of the arraylist (prepend)
        if (this.size == this.arrayList.length)
            this.expandCapacity();
		
	this.start = (this.start - 1) % this.arrayList.length;
	if (start < 0) {
	    this.start += this.arrayList.length;
	}
		
        this.arrayList[this.start] = element;
        this.size++;
    }

    public T get(int index) throws Exception {
        //Method to get element at a given index of the CircularArrayList
        int position = this.indexFor(index);
        if (index > this.size) {
            throw new Exception("Element doesn't exist");
        }
        return this.arrayList[position];
    }

    @SuppressWarnings("unchecked")
    public void expandCapacity() {
    	//pre-lecture code
    	int currentCapacity = this.arrayList.length;

    	//if size is less than capacity, then its ok
    	if (this.size < currentCapacity) {
    		return;
    	}
        T[] expanded = (T[])(new Object[currentCapacity*2]);

        for (int i = 0; i < this.size; i++) {
            expanded[i] = this.arrayList[i];
        }
        this.arrayList = expanded;
    }

    @Override
    public void insert(T element, int position) {
        //fill this method to insert element at a given position
    	if (position < 0) {
    		return;
    	}
    	else if (position > this.size) {
    		return;
    	}
    	if (this.size >= capacity) {
    		expandCapacity();
    	}
    	
    	arrayList[indexFor(position)] = element;
    	size ++;

    }

    public class MyArrayListIterator<T> implements Iterator<T> {
    	
		@Override
		public boolean hasNext() {
		
			return start > size;
		}

		@Override
		public T next() {
			if (!this.hasNext()) {
				throw new NoSuchElementException();
			}
			@SuppressWarnings("unchecked")
			T answer = (T) arrayList[indexFor(start)];
			start +=1;
			return answer;
		}
	//fill this class to implment the iterator
		
}
    @SuppressWarnings("rawtypes")
	public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

}
