import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 08722 Data Structures for Application Programmers. Homework Assignment 2
 * Solve Josephus problem with different data structures and different
 * algorithms and compare running timess
 */
public class Josephus {

    /**
     * Uses ArrayDeque class as Queue/Deque to find the survivor's position.
     *
     * @param size
     *            Number of people in the circle that is bigger than 0
     * @param rotation
     *            Elimination order in the circle. The value has to be greater
     *            than 0
     * @return The position value of the survivor
     */
    public int playWithAD(int size, int rotation) {
        // TODO implement this
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException();
        }
        Deque<Integer> arrayDeque = new ArrayDeque<Integer>();
        for (int i = 1; i < size + 1; i++) {
            arrayDeque.offer(i);
        }
        while (size > 1) {
            // pos is the position element should be killed
            int pos = rotation % size; // deal with rotation more then size.
            if (pos == 0) {
                pos = size;
            }
            // add the element before pos to the tail of queue.
            for (int i = 1; i < pos; i++) {
                int temp = arrayDeque.poll();
                arrayDeque.offer(temp);
            }
            arrayDeque.poll(); // kill the element.
            size--;
        }
        return arrayDeque.poll();
    }

    /**
     * Uses LinkedList class as Queue/Deque to find the survivor's position.
     *
     * @param size
     *            Number of people in the circle that is bigger than 0
     * @param rotation
     *            Elimination order in the circle. The value has to be greater
     *            than 0
     * @return The position value of the survivor
     */
    public int playWithLL(int size, int rotation) {
        // TODO implement this
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException();
        }
        Deque<Integer> listDeque = new LinkedList<Integer>();
        for (int i = 1; i < size + 1; i++) {
            listDeque.offer(i);
        }
        while (size > 1) {
            // pos is the position element should be killed
            int pos = rotation % size; // deal with rotation more then size.
            if (pos == 0) {
                pos = size;
            }
            // add the element before pos to the tail of queue.
            for (int i = 1; i < pos; i++) {
                int temp = listDeque.poll();
                listDeque.offer(temp);
            }
            listDeque.poll(); // kill the element.
            size--;
        }
        return listDeque.poll();
    }

    /**
     * Uses LinkedList class to find the survivor's position. However, do NOT
     * use the LinkedList as Queue/Deque Instead, use the LinkedList as "List"
     * That means, it uses index value to find and remove a person to be
     * executed in the circle
     *
     * Note: Think carefully about this method!! When in doubt, please visit one
     * of the office hours!!
     *
     * @param size
     *            Number of people in the circle that is bigger than 0
     * @param rotation
     *            Elimination order in the circle. The value has to be greater
     *            than 0
     * @return The position value of the survivor
     */
    public int playWithLLAt(int size, int rotation) {
        // TODO implement this
        if (size <= 0 || rotation <= 0) {
            throw new RuntimeException();
        }
        LinkedList<Integer> list = new LinkedList<Integer>();
        for (int i = 1; i < size + 1; i++) {
            list.add(i);
        }
        // pos is the position element should be killed
        int pos = 0;
        while (size > 1) {
            // kill element is not from the first position, so should add
            // previous position
            pos = (rotation + pos) % size;
            if (pos == 0) {
                pos = size;
            }
            pos--; // linkedList index begain from 0.
            list.remove(pos);
            size--;
        }
        return list.get(0);
    }

}
