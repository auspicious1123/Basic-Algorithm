
public class SortedLinkedList implements MyListInterface {
    /**
     * Inner class.
     *
     * @author-Rui Wang rw1
     */
    private class Node {
        /**
         * private value.
         */
        private String val;
        /**
         * private value.
         */
        private Node next;
        /**
         * Constructors.
         * @param s string
         * @param node node
         */
        Node(String s, Node node) {
            val = s;
            next = node;
        }
    }

    /**
     * private file value.
     */
    private Node head;

    /**
     * constructor.
     */
    public SortedLinkedList() {
        head = null;
    }

    /**
     *
     * @param unsorted strings
     */
    public SortedLinkedList(String[] unsorted) {
        if (unsorted == null || unsorted.length == 0) {
            head = null;
        }
        head = null;
        sortedLinkedListHelper(0, unsorted);
    }

    /**
     * used to recursive.
     *
     * @param unsorted strings
     * @param index string array index
     */
    private void sortedLinkedListHelper(int index, String[] unsorted) {
        if (index >= unsorted.length) {
            return;
        } else {
            add(unsorted[index]);
            index++;
            sortedLinkedListHelper(index, unsorted);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see MyListInterface#add(java.lang.String)
     */
    @Override
    public void add(String str) {
        if (str == null) {
            return;
        }
        if (head == null) {
            head = new Node(str, null);
            return;
        }
        Node pre = null;
        addHelper(pre, head, str);
    }

    /**
     * used to recursive.
     *
     * @param pre previous node
     * @param runner runner the linkedlist
     * @param str insert string
     */
    private void addHelper(Node pre, Node runner, String str) {
        // first time and end of list.
        if ((runner == null && pre == null) || runner == null) {
            pre.next = new Node(str, null);
            return;
        }
        // check the duplicate situation
        if (runner.val.compareTo(str) == 0) {
            return;
        }
        // find the first position bigger than str to insert str.
        if (runner.val.compareTo(str) > 0) {
            if (pre == null) {
                head = new Node(str, runner); // only one element.
            } else {
                pre.next = new Node(str, runner);
            }
            return;
        } else {
            pre = runner;
            runner = runner.next;
            addHelper(pre, runner, str);
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see MyListInterface#size()
     */
    @Override
    public int size() {
        return sizeHelper(head);
//        if (head == null) return 0; 
//        head = head.next;
//        return size() + 1;
    }

    /**
     * used to recursive.
     *
     * @param node list
     * @return return value
     */
    private int sizeHelper(Node node) {
        if (node == null) {
            return 0;
        }
        return sizeHelper(node.next) + 1;
    }

    /*
     * (non-Javadoc)
     *
     * @see MyListInterface#display()
     */
    @Override
    public void display() {
        if (head == null) {
            return;
        }
        System.out.print("[");
        displayHelper(head);
        System.out.println("]");
    }

    /**
     * used to recursive.
     *
     * @param node list
     */
    private void displayHelper(Node node) {
        if (node == null) {
            return;
        }
        if (node.next == null) {
            System.out.print(node.val);
        } else {
            System.out.print(node.val + ", ");
        }
        displayHelper(node.next);
    }

    /*
     * (non-Javadoc)
     *
     * @see MyListInterface#contains(java.lang.String)
     */
    @Override
    public boolean contains(String key) {
        return containsHelper(head, key);
    }

    /**
     * used to recursive.
     *
     * @param node list
     * @param str value
     * @return return value
     */
    private boolean containsHelper(Node node, String str) {
        if (node == null) {
            return false;
        }
        if (node.val.compareTo(str) == 0) {
            return true;
        }
        return containsHelper(node.next, str);
    }

    /*
     * (non-Javadoc)
     *
     * @see MyListInterface#isEmpty()
     */
    @Override
    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     *
     * @see MyListInterface#removeFirst()
     */
    @Override
    public String removeFirst() {
        if (head == null) {
            return null;
        }
        String str = head.val; // save the return string.
        head = head.next; // remove first string.
        return str;
    }

    /*
     * (non-Javadoc)
     *
     * @see MyListInterface#removeAt(int)
     */
    @Override
    public String removeAt(int index) {
        if (index >= size() || index < 0 || head == null || size() == 0) {
            return null;
        }
        return removeAtHelper(0, index, head);
    }

    /**
     * used to recursive.
     *
     * @param i index find delete position
     * @param index delete position
     * @param node list
     * @return return value
     */
    private String removeAtHelper(int i, int index, Node node) {
        if (node == null) {
            return null;
        }
        if (index == 0) {
            return removeFirst();
        } else if (i == index - 1) {
            String temp = node.next.val;
            node.next = node.next.next;
            return temp;
        }
        i++;
        return removeAtHelper(i, index, node.next);
    }
}
