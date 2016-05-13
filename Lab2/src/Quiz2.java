import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class Quiz2 {
    public static void main(String[] args) {
        Deque<Integer> Q = new LinkedList<>();
        for (int i = 7; i >= 1; i--) {
            Q.offer(i);
        }
        for (int i = 0; i <= 3; i++) {
            Q.offer(Q.peekFirst());
            Q.removeLast();
            Q.offer(Q.removeLast());
            Q.removeLast();
        }
        System.out.println(Q);
    }
}
