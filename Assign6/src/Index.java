import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Index {

    /**
     * build tree according to index.
     * @param fileName input filename.
     * @return return BST
     */
    public BST<Word> buildIndex(String fileName) {
        if (fileName == null) {
            return null;
        }
        return buildIndex(fileName, null);
    }
    /**
     * Build tree according comparator index.
     * @param fileName input filename
     * @param comparator comparator parameter
     * @return return binary search tree
     */
    public BST<Word> buildIndex(String fileName, Comparator<Word> comparator) {
        // create a binary tree
        BST<Word> bTree = new BST<Word>(comparator);
        Scanner sc = null;
        try {
            // read word from file
            int lineIndex = 0;
            sc = new Scanner(new File(fileName));
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                lineIndex++;
                String[] words = line.split("\\W");
                for (String w: words) {
                    if (w.matches("[a-zA-Z]+")) {
                     // using comparator to compare string
                        if (comparator instanceof IgnoreCase) {
                            w = w.toLowerCase();
                        }
                        Word wordNode = new Word(w);
                        // set flag to mark exist word
                        boolean flag = false;
                        for (Word word: bTree) {
                            // word exist
                            if (word.compareTo(wordNode) == 0) {
                                word.addToIndex(lineIndex);
                                // update word frequency
                                word.setFrequency(word.getFrequency() + 1);
                                // set flag to mark word has existed in array
                                flag = true;
                                break;
                            }
                        }
                        // word not exist in wordArray, update index and add word into wordArray
                        if (!flag) {
                            wordNode.addToIndex(lineIndex);
                            bTree.insert(wordNode);
                        }
                        flag = false;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (sc != null) {
                sc.close();
            }
        }
        return bTree;
    }

    /**
     * @param list word list
     * @param comparator comparator
     * @return return tree
     */
    public BST<Word> buildIndex(ArrayList<Word> list, Comparator<Word> comparator) {
        //get word arrayList of word, build bTree
        BST<Word> bTree = new BST<Word>(comparator);
        // base case list is null
        if (list == null) {
            return bTree;
        }
        for (Word word: list) {
            bTree.insert(word);
        }
        return bTree;
    }

    /**
     * Sorted by alpha.
     * @param tree input tree
     * @return return value
     */
    public ArrayList<Word> sortByAlpha(BST<Word> tree) {
        ArrayList<Word> wordList = new ArrayList<>();
        // base case
        if (tree == null) {
            return wordList;
        }
        // traversal a tree using inorder to get a sorted array
        for (Word word: tree) {
            wordList.add(word);
        }
        return wordList;
    }

    /**
     * Sorted by frequency.
     * @param tree input tree.
     * @return return value
     */
    public ArrayList<Word> sortByFrequency(BST<Word> tree) {
        ArrayList<Word> wordList = new ArrayList<>();
        // base case tree is empty
        if (tree == null) {
            return wordList;
        }
        // traversal the tree
        for (Word word:tree) {
            wordList.add(word);
        }
        // sorting
        Collections.sort(wordList, new Frequency());
        return wordList;
    }

    /**
     * Get highest frequency value.
     * @param tree input tree
     * @return return value
     */
    public ArrayList<Word> getHighestFrequency(BST<Word> tree) {
        ArrayList<Word> highestFreList = new ArrayList<>();
        ArrayList<Word> wordList = new ArrayList<>();
        if (tree == null) {
            return highestFreList;
        }
        wordList = sortByFrequency(tree);
        int highestFre = wordList.get(0).getFrequency();
        for (Word word: wordList) {
            if (word.getFrequency() < highestFre) {
                break;
            } else {
                highestFreList.add(word);
            }
        }
        return highestFreList;
    }

}
