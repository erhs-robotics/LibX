package org.erhsroboticsclub.libx.util;


/**
 * Making up for the sad deficiencies of FRC Java
 *
 * @author David Gardner
 */
public class ArrayList {
	
	private boolean returnNull = false;
	private int step = 10;
	
	private Object[] array;
	private int size = 0;
	
	/**
	 * Constructs an ArrayList containing the given array.
	 * @param array the Object array to use
	 */
	public ArrayList(Object[] array) {
		this.array = array;
		size = array.length;
	}
	/**
	 * Constructs an empty array
	 */
	public ArrayList() {
		array = new Object[10];
	}
	/**
	 * Constructs an empty array with the specified amount of space allocated. size() will still be 0.
	 * @param initSize initial number of spaces to allocate
	 */
	public ArrayList(int initSize) {
		array = new Object[initSize];
	}
	
	/**
	 * Taking this opportunity to add something I wish the real ArrayList had. If this is true, 
	 *  out-of-bounds exceptions will not be thrown, the get function will just return null.
	*/
	public void setReturnNull(boolean returnNull) {
		this.returnNull = returnNull;
	}
	/**
	 * If this is true, 
	 *  out-of-bounds exceptions will not be thrown, the get function will just return null.
	 */
	public boolean willReturnNull() {
		return returnNull;
	}
	
	/**
	 * Every time the array needs more space, it allocates step more spaces. 
	 * @param step The default amount of spaces to allocate. Cannot be <= 0. 
	 */
	public void setStepSize(int step) {
		if(step>0)
			this.step = step;
	}
	
	/**
	 * A way to manually allocate more space to the array. This is automatically called
	 *  when it no longer has enough spaces to append more elements, but could be used
	 *  to allocate a large amount of space at once.
	 * @param space number of additional spaces to allocate. Will not change size.
	 */
	public void allocateSpace(int space) {
		Object[] newArray = new Object[size+space];
		for(int i=0; i<array.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	private void deallocateSpace(int space) {
		Object[] newArray = new Object[array.length-space];
		for(int i=0; i<newArray.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
		if(size>array.length) size = array.length;
	}
	
	private Object[] prune() {
		Object[] newArray = new Object[size];
		for(int i=0; i<size; i++) {
			newArray[i] = array[i];
		}
		return newArray;
	}
	
	/**
	 * Returns the amount of space allocated to the array, not the number of elements.
	 * @see size()
	 * @return the number of spaces currently allocated to the array.
	 */
	public int getAllocatedSpace() {
		return array.length;
	}
	/**
	 * The number of elements currently contained by the array.
	 * @return The size of the array
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Converts to an Object array
	 * @return this ArrayList as an Object[]
	 */
	public Object[] toArray() {
		return this.prune();
	}
	
	/**
	 * Retrieves the object at the given index
	 * @param index
	 * @return The object at index
	 */
	public Object get(int index) {
		if(!checkBounds(index)) {
			if(returnNull) return null;
			else throw new ArrayIndexOutOfBoundsException();
		}
		return array[index];
	}
	
	/**
	 * Replaces the object at index with o
	 * @param o the object
	 * @param index the index to set
	 */
	public void set(Object o, int index) {
		if(!checkBounds(index)) {
			if(!returnNull) throw new ArrayIndexOutOfBoundsException();
			return;
		}
		array[index] = o;
	}
	
	/**
	 * Appends to the end of the list. If not enough space is available, 'step' more spaces will be allocated.
	 * @param o The object to append
	 */
	public void add(Object o) {
		size++;
		if(size>=array.length)
			allocateSpace(step);
		array[size-1] = o;
	}
	
	/**
	 * Appends the given array to the end of the list. If not enough space is available,
	 *  the size of the array plus 'step' spaces will be allocated
	 * @param o the array to append
	 */
	public void add(Object[] o) {
		if(size+o.length >= array.length) allocateSpace(o.length + step);
		for(int i=size; i<size+o.length; i++) {
			array[i] = o[i-size];
		}
		size += o.length;
	}
	
	/**
	 * Appends the given ArrayList to the end of the list. If not enough space is available,
	 *  the size of the list plus 'step' spaces will be allocated
	 * @param list The ArrayList to append.
	 */
	public void add(ArrayList list) {
		if(size+list.size >= array.length) allocateSpace(list.size() + step);
		for(int i=size; i<size+list.size(); i++) {
			array[i] = list.get(i-size);
		}
		size += list.size();
	}
	
	/**
	 * Adds the given array starting at list[index] = o[0].
	 *  Shifts everything past index over o.length spaces.
	 * @param o The array to add
	 * @param index The index at which to add it. 
	 */
	public void add(Object[] o, int index) {
		if(!checkBounds(index)) {
			if(!returnNull) throw new ArrayIndexOutOfBoundsException();
			return;
		}
		if(size+o.length >= array.length) allocateSpace(o.length + step);
		for(int i=size-1; i>=index; i--) {
			array[i+o.length] = array[i];
		}
		for(int i=index; i<index+o.length; i++) {
			array[i] = o[i-index];
		}
		size += o.length;
	}
	
	/**
	 * Adds the specified object to the specified index. All elements past the index are shifted over.
	 * @param o the object to add
	 * @param index the index at which to add it
	 */
	public void add(Object o, int index) {
		add(new Object[] {o}, index);
	}
	
	/**
	 * Adds the given ArrayList starting at this[index] = list[0].
	 *  Shifts everything past index over o.length spaces.
	 * @param list The ArrayList to add
	 * @param index The index at which to add it. 
	 */
	public void add(ArrayList list, int index) {
		add(list.toArray(), index);
	}
	
	/**
	 * Removes the object at the specified index and moves all other objects to fill the space.
	 * @param index of the object to remove
	 */
	public void remove(int index) {
		if(!checkBounds(index)) {
			if(!returnNull) throw new ArrayIndexOutOfBoundsException();
			return;
		}
		for(int i=index; i<size; i++) {
			array[i] = array[i+1];
			
		}
		size--;
		deallocateSpace(1); //maybe not...
	}
	
	/**
	 * Removes all elements between lBound (inclusive) and uBound (exclusive)
	 * @param lBound lower bound (removed)
	 * @param uBound upper bound (not removed)
	 */
	public void remove(int lBound, int uBound) {
		if(!checkBounds(lBound) || !checkBounds(uBound-1) || lBound >= uBound) {
			if(!returnNull) throw new ArrayIndexOutOfBoundsException();
			return;
		}
		size -= (uBound-lBound);
		for(int i=lBound; i<size; i++) {
			array[i] = array[uBound + i-lBound];
//			System.out.println("array[" + i + "] = " + array[i]);
		}
		
	}
	
	/**
	 * Removes all elements from the array and returns it to the state given by the default constructor.
	 */
	public void clear() {
		array = new Object[10];
		size = 0;
	}
	
	/**
	 * Conducts a linear search of the array for the specified object
	 * @param o the object to locate
	 * @return The index of the specified object or -1 if it is not found.
	 */
	public int indexOf(Object o) {
		for(int i=0; i<size; i++) {
			if(array[i].equals(o)) return i;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return "ArrayList of size " + size;
	}
	
	private boolean checkBounds(int index) {
		return (index >= 0 && index < size);
	}
}