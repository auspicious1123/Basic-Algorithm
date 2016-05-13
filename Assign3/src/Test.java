
public class Test {
    public static void main(String[] args) {
        String[] unsorted = new String[]{"I", "Love", "You"};
        SortedLinkedList list = new SortedLinkedList(unsorted);
        list.display();
        System.out.println(list.removeAt(1));
        System.out.println(list.size());
        list.display();
        
        
    }
}
