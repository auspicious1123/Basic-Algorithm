
/**
 * 08722 Data Structures for Application Programmers.
 *
 * Homework Assignment 4 HashTable Implementation with linear probing
 */
public class MyHashTable implements MyHTInterface {

    /**
     * private value.
     */
    private final double initialLoadFactor = 0.5;
    /**
     * private value.
     */
    private final int intialTableSize = 10;
    /**
     * private value.
     */
    private final int characterNum = 27;
    /**
     * define deleted value.
     */
    private static final DataItem DELETED = new DataItem(null, 0);

    /**
     * private value.
     */
    private int tableSize = intialTableSize;
    /**
     * private value.
     */
    private int size = 0;
    /**
     * private value.
     */
    private DataItem[] hashArray = new DataItem[intialTableSize];
    /**
     * private value.
     */
    private double loadFactor = initialLoadFactor;

    /**
     * Constructor without parameter.
     */
    public MyHashTable() {
    }

    /**
     * Constructor with given size.
     *
     * @param tableSize
     *            give table size
     */
    public MyHashTable(int tableSize) {
        this.tableSize = tableSize;
        hashArray = new DataItem[tableSize];
    }

    // TODO implement required methods

    /**
     * Instead of using String's hashCode, you are to implement your own here.
     * You need to take the table length into your account in this method.
     *
     * In other words, you are to combine the following two steps into one step.
     * 1. converting Object into integer value 2. compress into the table using
     * modular hashing (division method)
     *
     * Helper method to hash a string for English alphabet and blank, we have 27
     * total. But, you can assume that blank will not be added into your table.
     * Refer to the instructions for the definition of words.
     *
     * For example, "cats" : 3*27^3 + 1*27^2 + 20*27^1 + 19*27^0 = 60,337
     *
     * But, to make the hash process faster, Horner's method should be applied
     * as follows;
     *
     * var4*n^4 + var3*n^3 + var2*n^2 + var1*n^1 + var0*n^0 can be rewritten as
     * (((var4*n + var3)*n + var2)*n + var1)*n + var0
     *
     * Note: You must use 27 for this homework.
     *
     * However, if you have time, I would encourage you to try with other
     * constant values than 27 and compare the results but it is not required.
     *
     * @param input
     *            input string for which the hash value needs to be calculated
     * @return int hash value of the input string
     */
    private int hashFunc(String input) {
        int hashVal = 0; // set default hash val is -1
        if (isValidWord(input)) {
            for (int i = 0; i < input.length(); i++) {
                // deal with the upcase
                if (input.charAt(i) - 'a' + 1 < 0) {
                    return -1;
                }
                hashVal = (characterNum * hashVal + (input.charAt(i) - 'a' + 1)) % tableSize;
            }
            return hashVal;
        }
        return -1;
    }

    /**
     * check whether the input string is valid or not.
     *
     * @param string
     *            input string
     * @return return value.
     */
    private boolean isValidWord(String string) {
        if (string == null) {
            return false;
        }
        // match string to a-z A-Z
        if (string.matches("[a-zA-Z]+")) {
            return true;
        }
        return false;

    }

    /**
     * doubles array length and rehash items whenever the load factor is
     * reached.
     */
    private void rehash() {
        if (loadFactor > initialLoadFactor) {
            int newSize = findNextPrime(tableSize * 2);
            System.out.println("Rehashing " + size + " items, new size is " + newSize);
            MyHashTable newMyHashTable = new MyHashTable(newSize);
            for (int i = 0; i < hashArray.length; i++) {
                if (hashArray[i] != null && hashArray[i] != DELETED) {
                    while (hashArray[i].frequency > 0) {
                        // seperate values.
                        newMyHashTable.insert(hashArray[i].value);
                        hashArray[i].decreaseFrequency(); // decrease frequency.
                    }
                }
            }
            // update hash field values.
            hashArray = newMyHashTable.hashArray;
            tableSize = newMyHashTable.tableSize;
            size = newMyHashTable.size;
            loadFactor = newMyHashTable.loadFactor;
        }
    }

    /**
     * Find next prime.
     *
     * @param num
     *            num
     * @return return value
     */
    private int findNextPrime(int num) {
        while (!isPrime(num)) {
            num++;
        }
        return num;
    }

    /**
     * @param num
     *            check number
     * @return return value
     */
    private boolean isPrime(int num) {
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * private static data item nested class.
     */
    private static class DataItem {
        /**
         * String value.
         */
        private String value;
        /**
         * String value's frequency.
         */
        private int frequency;

        // TODO implement constructor and methods
        /**
         * Implement constructor.
         *
         * @param value
         *            DataItem value
         * @param frequency
         *            DataItem value frequency.
         */
        public DataItem(String value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }

        /**
         * Implement constructor.
         *
         * @param value
         *            DataItem value.
         */
        public DataItem(String value) {
            this.value = value;
            this.frequency = 1;
        }

        /**
         * increase frequency.
         */
        public void increaseFrequency() {
            frequency++;
        }

        /**
         * decrease frequency.
         */
        public void decreaseFrequency() {
            frequency--;
        }
    }

    /*
     * Insert value into MyHashTable. Finding insert position according to hash
     * values and update hashArray.
     */
    @Override
    public void insert(String value) {
        if (value == null || (!isValidWord(value))) {
            return;
        }
        int hashVal = hashValue(value);
        // valid input string and has hash value
        if (hashVal != -1) {
            DataItem data = new DataItem(value);
            // check hashArray if the value exits, increase frequency,otherwise
            // find a position to insert value

            while (hashArray[hashVal] != null) {
                // check the exist values
                if (hashArray[hashVal].value.equals(value)) {
                    hashArray[hashVal].increaseFrequency();
                    return;
                }
                // loop check all the element in hash table
                hashVal++;
                hashVal %= tableSize;
            }
            hashArray[hashVal] = data;
            size++;
            loadFactor = (double) size / (double) tableSize;
            rehash(); // make sure loadFactor is 0.5
        }
    }

    /*
     * return size.
     */
    @Override
    public int size() {
        return size;
    }

    /*
     * Show hash table.
     */
    @Override
    public void display() {
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] == null) {
                System.out.print("** ");
            } else if (hashArray[i] == DELETED) {
                System.out.print("#DEL# ");
            } else {
                System.out.print("[" + hashArray[i].value + ", " + hashArray[i].frequency + "] ");
            }
        }
        System.out.println();
    }

    /*
     * Check all hash table to check whether it contains.
     */
    @Override
    public boolean contains(String key) {
        if (key == null) {
            return false;
        }
        int hashVal = hashValue(key);
        if (hashVal != -1) {
            while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
                if (hashArray[hashVal].value.equals(key)) {
                    return true;
                }
                hashVal++;
                hashVal %= tableSize;
            }
        }
        return false;
    }

    /*
     * Check each cell. return collision nums.
     */
    @Override
    public int numOfCollisions() {
        // save all hash values.
        int[] hashValues = new int[size];
        int index = 0;
        int collisionsNum = 0;
        for (int i = 0; i < hashArray.length; i++) {
            if (hashArray[i] != null && hashArray[i] != DELETED) {
                // find out all values in the hash table.
                hashValues[index++] = hashValue(hashArray[i].value);
            }
        }
        // check all size element in the table and find the same hash values
        // To ignore the checked hashValue,using a flag to mark
        boolean[] flag = new boolean[index]; // default value is false
        for (int i = 0; i < index; i++) {
            if (!flag[i]) {
                for (int j = i + 1; j < index; j++) {
                    if (!flag[j]) {
                        if (hashValues[j] == hashValues[i]) {
                            collisionsNum++;
                            // mark the have checked value
                            flag[i] = true;
                            flag[j] = true;
                        }
                    }
                }
            }
        }
        return collisionsNum;
    }

    /*
     * Check input value and return hash value, if it is invalid return -1.
     */
    @Override
    public int hashValue(String value) {
        if (isValidWord(value)) {
            return hashFunc(value);
        }
        return -1;
    }

    /*
     * calculate frequency.
     */
    @Override
    public int showFrequency(String key) {
        int hashVal = hashValue(key);
        if (hashVal != -1) {
            while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
                if (hashArray[hashVal].value.equals(key)) {
                    return hashArray[hashVal].frequency;
                }
                hashVal++;
                hashVal %= tableSize;
            }
        }
        // Not found key, frequency is 0.
        return 0;
    }

    /*
     * remove given value.
     */
    @Override
    public String remove(String key) {
        int hashVal = hashValue(key);
        if (hashVal != -1) {
            while (hashArray[hashVal] != null && hashArray[hashVal] != DELETED) {
                if (hashArray[hashVal].value.equals(key)) {
                    // save return value and set hashVal position as DELETED
                    String value = hashArray[hashVal].value;
                    hashArray[hashVal] = DELETED;
                    // deccrease size.
                    size--;
                    loadFactor = (double) size / (double) tableSize;
                    return value;
                }
                hashVal++;
                hashVal %= tableSize;
            }
        }
        // hash table do not contains key.
        return null;
    }

}
