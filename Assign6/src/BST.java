import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Queue;
import java.util.Stack;

/**
 * @param <T> generic type
 */

public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * private field value.
     */
    private Node<T> root;
    /**
     * private field value.
     */
    private Comparator<T> comparator;
    /**
     * private field value.
     */
    private int numberOfNodes = 0;

    /**
     * constructor.
     */
    public BST() {
        this(null);
    }

    /**
     * constructor with parameters.
     *
     * @param comparator parameter
     */
    public BST(Comparator<T> comparator) {
        this.comparator = comparator;
        root = null;
    }

    /**
     * comparator method.
     *
     * @return return comparator.
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     *
     * @return root
     */
    public T getRoot() {
        if (root == null) {
            return null;
        }
        return root.data;
    }

    /**
     *
     * @return tree height.
     */
    public int getHeight() {
        if (root == null) {
           return 0;
        }
        return getHeightHelper(root);
    }

    /**
     * getHeight help method. Traversal and return the height of a tree.
     *
     * @param node input tree
     * @return return value
     */
    private int getHeightHelper(Node<T> node) {
        if (node == null) {
            return -1;
        }
        return Math.max(getHeightHelper(node.left), getHeightHelper(node.right)) + 1;
    }

    /**
     *
     * @return number of tree node
     */
    public int getNumberOfNodes() {
        return numberOfNodes;
    }

    /**
     * search method.
     */
    @Override
    public T search(T toSearch) {
        if (toSearch == null) {
            return null;
        }
        return searchHelper(root, toSearch);
    }

    /**
     * search help method.
     * @param node input tree
     * @param toSearch input search value
     * @return return value
     */
    private T searchHelper(Node<T> node, T toSearch) {
        // using comparator compare or just regard it as object using
        // compareTo method to compare

        // base case
        if (node == null) {
            return null;
        }
        if (comparator == null) {
            // equal to 0 means find the node.
            if (node.data.compareTo(toSearch) == 0) {
                return node.data;
            }
            // traversal to find toSearch
            if (node.data.compareTo(toSearch) > 0) {
                return searchHelper(node.left, toSearch);
            } else {
                return searchHelper(node.right, toSearch);
            }
        } else {
            // equal to 0 means find the node.
            if (comparator.compare(node.data, toSearch) == 0) {
                return node.data;
            }
            // traversal to find toSearch
            if (comparator.compare(node.data, toSearch) > 0) {
                return searchHelper(node.left, toSearch);
            } else {
                return searchHelper(node.right, toSearch);
            }
        }
    }

    /**
     * insert method.
     */
    @Override
    public void insert(T toInsert) {
        // base case
        if (toInsert == null) {
            return;
        }
        if (root == null) {
            // if root is null, build Node using toInsert value.
            numberOfNodes = 1;
            root = new Node<T>(toInsert);
            return;
        } else {
            // exists tree, find position first and then insert
            insertHelper(root, toInsert);
        }
    }

    /**
     * insert helper.
     *
     * @param node
     *            tree
     * @param toInsert
     *            insert value.
     */
    private void insertHelper(Node<T> node, T toInsert) {
        if (comparator == null) {
            if (node.data.compareTo(toInsert) == 0) {
                // node have exist, just return
                return;
            }
            if (node.data.compareTo(toInsert) > 0) {
                // node.value big then to toInsert, toInsert should on left
                // subtree
                // find the most left position to insert
                if (node.left == null) {
                    node.left = new Node<T>(toInsert);
                    numberOfNodes++; // success insert, increase tree node
                                     // number
                } else {
                    insertHelper(node.left, toInsert);
                }
            } else {
                // toInsert value should on right subtree
                if (node.right == null) {
                    node.right = new Node<T>(toInsert);
                    numberOfNodes++;
                } else {
                    insertHelper(node.right, toInsert); // here occur a mistake,
                                                        // forget check right
                                                        // subtree.
                }
            }
        } else {
            if (comparator.compare(node.data, toInsert) == 0) {
                // exist, just return
                return;
            }
            if (comparator.compare(node.data, toInsert) > 0) {
                // insert position in left subtree
                if (node.left == null) {
                    node.left = new Node<T>(toInsert);
                    numberOfNodes++;
                } else {
                    insertHelper(node.left, toInsert); // here occur a mistake
                }
            } else {
                // toInsert value should on right subtree
                if (node.right == null) {
                    node.right = new Node<T>(toInsert);
                    numberOfNodes++;
                } else {
                    insertHelper(node.right, toInsert);
                }
            }
        }
    }

    /**
     * Iterator method.
     */
    @Override
    public Iterator<T> iterator() {
        return new InorderIterator();
    }

    /**
     * iterator class.
     *
     * @author-Rui Wang rw1
     */
    private class InorderIterator implements Iterator<T> {
        /**
         * private values.
         */
        private Node<T> nextNode;
        /**
         * Using queue to implement it.
         */
        private Queue<Node<T>> queue = new ArrayDeque<Node<T>>();

        /**
         * Constructor.
         */
        public InorderIterator() {
            // base case
            if (root == null) {
                nextNode = null;
            }
            Node<T> node = root;
            Stack<Node<T>> stack = new Stack<Node<T>>();
            // inorder, to be done iteratively, NOT recursively. L P R
            while (node != null || !stack.isEmpty()) {
                // iterate get most left child
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
                //get most left child
                node = stack.pop();
                queue.offer(node);
                // iterate right child
                node = node.right;
            }
            nextNode = queue.poll();
        }

        /*
         * implement method
         *
         * @return node whether has next value.
         */
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        /*
         * value.
         *
         * @see java.util.Iterator#next()
         */
        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T nextValue = nextNode.data;
            nextNode = queue.poll();
            return nextValue;
        }

        /**
         * remove value.
         */
        @Override
        public void remove() {
            return;
        }
    }

    /**
     * @param <T> generic type
     * @author-Rui Wang rw1
     */
    private static class Node<T> {
        /**
         * private value.
         */
        private T data;
        /**
         * private value.
         */
        private Node<T> left;
        /**
         * private value.
         */
        private Node<T> right;

        /**
         *constructor.
         * @param data value
         */
        Node(T data) {
            this(data, null, null);
        }

        /**
         * constructor.
         * @param data value
         * @param left left child
         * @param right right child
         */
        Node(T data, Node<T> left, Node<T> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        /**
         * toString method.
         */
        @Override
        public String toString() {
            return data.toString();
        }
    }
}
