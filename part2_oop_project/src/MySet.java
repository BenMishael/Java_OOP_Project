import java.io.Serializable;
import java.util.Iterator;

public class MySet<T> implements Serializable{
	private final int ENLARGE_FACTOR = 2;
	private T[] arr;
	private int size;

	public MySet() {
		arr = (T[]) new Object[ENLARGE_FACTOR];
		size = 0;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int newSize) {
		this.size = newSize;
	}

	public int capacity() {
		return arr.length;
	}

	public T get(int index) {
		return arr[index];
	}

	public void setItem(T newValue, int index) {
		arr[index] = newValue;
	}

	public boolean add(T newValue) {
		if (contain(newValue))
			return false;
		if (size == arr.length)
			enlargeArray();
		arr[size++] = newValue;
		return true;
	}

	public void remove(int index) {
		T[] arrTemp = (T[]) new Object[size - 1];
		System.arraycopy(arr, 0, arrTemp, 0, index);
		System.arraycopy(arr, index + 1, arrTemp, index, size - 1 - index);
		setSize(size - 1);
		arr = arrTemp;
	}

	public boolean replace(int index, T newValue) {
		if (contain(newValue))
			return false;
		arr[index] = newValue;
		return true;
	}

	public boolean contain(T newValue) {
		for (int i = 0; i < size; i++) {
			if (arr[i].equals(newValue))
				return true;
		}
		return false;
	}

	private void enlargeArray() {
		T[] temp = (T[]) new Object[arr.length * ENLARGE_FACTOR];
		for (int i = 0; i < arr.length; i++) {
			temp[i] = arr[i];
		}
		arr = temp;
	}

	@Override
	public String toString() {
		String str = " ";
		for (int i = 0; i < size; i++) {
			str += arr[i] + "\n";
		}
		return str;
	}
}
