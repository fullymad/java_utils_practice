import java.util.ArrayList;

public class Queue<E> {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private int head;
	private int tail;

	private ArrayList<E> elements;

	public Queue() {
		this(Queue.DEFAULT_SIZE);
	}

	public Queue(int size) {
		this.elements = new ArrayList<E>(size);
		this.size = size;
		this.head = -1;
		this.tail = -1;

		for (int i = 0; i < size; i++) {
			this.elements.add(i, null);
		}
	}

	public boolean isEmpty() {
		boolean result = false;

		if (head == -1) {
			result = true;
		}

		return result;
	}

	public void add(E element) throws IllegalStateException {
		int indexTail = (tail + 1) % this.size;
		int indexHead = head % this.size;

		if (head != -1 && indexTail == indexHead) {
			throw new IllegalStateException("Queue is full, can't add " +
					element.toString());
		}

		elements.set(indexTail, element);
		tail++;

		if (head == -1) {
			head = 0;
			indexHead = 0;
		}

		return;
	}

	public E poll() {
		if (head == -1) {
			throw new IllegalStateException(
				"Queue is empty, nothing to return");
		}

		int indexTail = tail % this.size;
		int indexHead = head % this.size;

		E element = elements.get(indexHead);
		elements.set(indexHead, null);

		// If last available element returned, reset to indicate empty queue
		if (indexTail == indexHead) {
			head = -1;
			tail = -1;
		}
		else {
			head++;
		}

		return element;
	}

	public E peek() {
		E element = null;

		if (head != -1) {
			element = elements.get(head % this.size);
		}

		return element;
	}

	public String toString() {
		StringBuffer strBuffer = new StringBuffer();

		if (isEmpty()) {
			System.out.println("Queue is empty - head: " + head + ", tail: "
						+ tail);
		}
		else {
			System.out.println("Queue contents - head: " + head + ", tail: "
								+ tail + "...");

			for (int i = head; i <= tail; i++) {
				strBuffer.append(elements.get(i % this.size).toString());
				strBuffer.append("\n");
			}
		}

		return strBuffer.toString();
	}
}
