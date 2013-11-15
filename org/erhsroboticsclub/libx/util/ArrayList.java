package org.erhsroboticsclub.libx.util;

/* TODO list
- add ArrayList
- add array
- step size

*/

/**
 * Making up for the sad deficiencies of FRC Java
 *
 * @author David
 */
public class ArrayList {
	
	private boolean returnNull = false;
	private int stepSize = 10;
	
	private Object[] array;
	private int size = 0;
	
	public ArrayList(Object[] array) {
		this.array = array;
		size = array.length;
	}
	
	public ArrayList() {
		array = new Object[10];
	}
	
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
	
	public void allocateSpace(int space) {
		size += space;
		Object[] newArray = new Object[size];
		for(int i=0; i<size-1; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	private void deallocateSpace(int space) {
		size -= space;
		Object[] newArray = new Object[size];
		for(int i=0; i<newArray.length; i++) {
			newArray[i] = array[i];
		}
		array = newArray;
	}
	
	public int size() {
		return size;
	}
	
	public Object get(int index) {
		if(!checkBounds(index)) {
			if(returnNull) return null;
			else throw new ArrayIndexOutOfBoundsException();
		}
		return array[index];
	}
	
	public void set(Object o, int index) {
		if(!checkBounds(index)) {
			if(!returnNull) throw new ArrayIndexOutOfBoundsException();
			return;
		}
		array[index] = o;
	}
	
	public void add(Object o) {
		size++;
		if(size=array.length)
			allocateSpace(step);
		array[size-1] = o;
	}
	
	public void remove(int index) {
		if(!checkBounds(index)) {
			if(!returnNull) throw new ArrayIndexOutOfBoundsException();
			return;
		}
		for(int i=index; i<size-1; i++) {
			array[i] = array[i+1]; //Make sure this works;
			
		}
		deallocateSpace[1]; //maybe not...
	}
	
	private boolean checkBounds(int index) {
		if(index >= 0 && index < size) return true;
	}
}