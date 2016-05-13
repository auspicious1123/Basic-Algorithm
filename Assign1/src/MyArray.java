
public class MyArray {
    /**
     * my array storage.
     */
    private String[] myStore;
    /**
     * actual size of my array.
     */
    private int actSize = 0;

    /**
     * Constructor.
     */
    public MyArray() {
        myStore = new String[10];
    }

    /**
     *
     * @param initialCapacity
     *            given size
     */
    public MyArray(int initialCapacity) {
        myStore = new String[initialCapacity];
    }

    /**
     *
     * @param text
     *            add content.
     */
    public void add(String text) {
        // make sure capacity no less than 5
        if (myStore.length <= actSize) {
            increaseArraySize();
        }
        if (validString(text)) {
            myStore[actSize++] = text;
        }
    }

    /**
     * @param text
     *            check valid.
     * @return whether it is valid.
     */
    private boolean validString(String text) {
        if (text == null || text.trim().length() == 0) {
            return false;
        }
        for (int i = 0; i < text.length(); i++) {
            // if (!(text.charAt(i) <= 'z' && text.charAt(i) >= 'a' ||
            // (text.charAt(i) <= 'Z' && text.charAt(i) >= 'A'))) {
            // return false;
            // }
            if (!Character.isLetter(text.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * change the size automatically.
     */
    private void increaseArraySize() {
        int newLenght = 0;
        if (myStore.length == 0) {
            newLenght = 1;
        } else {
            newLenght = myStore.length * 2;
        }
        String[] newString = new String[newLenght];
        for (int i = 0; i < actSize; i++) {
            newString[i] = myStore[i];
        }
        myStore = newString;
    }

    /**
     *
     * @param key
     *            search world
     * @return return value
     */
    public boolean search(String key) {
        for (int i = 0; i < actSize; i++) {
            if (myStore[i].equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @return actual size.
     */
    public int size() {
        return actSize;
    }

    /**
     *
     * @return capacity.
     */
    public int getCapacity() {
        return myStore.length;
    }

    /**
     * display the array.
     */
    public void display() {
        for (int i = 0; i < actSize - 1; i++) {
            System.out.print(myStore[i] + " ");
        }
        System.out.print(myStore[actSize - 1]);
        System.out.println();
    }

    /**
     * remove duplicate values.
     */
    public void removeDups() {
        String[] newString = new String[myStore.length];
        int index = 0;
        for (int i = 0; i < actSize; i++) {
            String cur = myStore[i];
            boolean flag = false;
            for (int j = 0; j < index; j++) {
                if (newString[j].equals(cur)) {
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                newString[index++] = cur;
            }
        }
        myStore = newString;
        actSize = index;
    }
}
