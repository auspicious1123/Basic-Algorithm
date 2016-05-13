import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Similarity {
    /**
     * private values.
     */
    private Map<String, BigInteger> map = new HashMap<>();
    /**
     * private values.
     */
    private int numOfLines = 0;
    /**
     * Construtor.
     * @param string input string
     */
    public Similarity(String string) {
        if (string == null || string.length() == 0) {
            return;
        }
        // separate string according line and calculate lineNum
        String[] lines = string.split("\n");
        if (lines != null) {
            numOfLines = lines.length;
        } else {
            numOfLines = 1;
        }
        // separate string according space.
        String[] words = string.split("\\W");
        for (int i = 0; i < words.length; i++) {
            // check and put valid string lower case into map.
            if (isValidWord(words[i])) {
                BigInteger freq = map.get(words[i].toLowerCase());
                if (freq == null) {
                    freq = BigInteger.ONE;
                } else {
                    freq = freq.add(BigInteger.ONE);
                }
                map.put(words[i].toLowerCase(), freq);
            }
        }
    }
    /**
     * Construtor.
     * @param file input file
     */
    public Similarity(File file) {
        if (file == null) {
            return;
        }
        Scanner sc = null;
        try {
            sc = new Scanner(file, "latin1");
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                numOfLines++;
                // separate according to continue word.
                String[] words = line.split("\\W");
                for (int i = 0; i < words.length; i++) {
                    // check and put valid string lower case into map.
                    if (isValidWord(words[i])) {
                        BigInteger freq = map.get(words[i].toLowerCase());
                        if (freq == null) {
                            freq = BigInteger.ONE;
                        } else {
                            freq = freq.add(BigInteger.ONE);
                        }
                        map.put(words[i].toLowerCase(), freq);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find the file");
        } finally {
            // close the file
            if (sc != null) {
                sc.close();
            }
        }
    }

    /**
     *
     * @param str input string value
     * @return return result.
     */
    private boolean isValidWord(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }
        if (str.matches("[a-zA-Z]+")) {
            return true;
        }
        return false;
    }
    /**
     *
     * @return return line numbers.
     */
    public int numOfLines() {
        if (map == null || map.isEmpty()) {
            return 0;
        }
        return numOfLines;
    }
    /**
     *
     * @return return num of words
     */
    public BigInteger numOfWords() {
        if (map == null) {
            return BigInteger.ZERO;
        }
        BigInteger totalWords = BigInteger.ZERO;
        for (BigInteger freq: map.values()) {
            totalWords = totalWords.add(freq);
        }
        return totalWords;
    }
    /**
     *
     * @return the size of map.
     */
    public int numOfWordsNoDups() {
        if (map == null) {
            return 0;
        }
        return map.size();
    }
    /**
     *
     * @return euclidean norm
     */
    public double euclideanNorm() {
        if (map == null) {
            return 0;
        }
        double norm = 0;
        for (BigInteger x : map.values()) {
            norm = norm + x.multiply(x).doubleValue();
        }
        return Math.sqrt(norm);
    }
    /**
     *
     * @param inputMap input map for comparing
     * @return return norm
     */
    public double dotProduct(Map<String, BigInteger> inputMap) {
        if (map == null || inputMap == null) {
            return 0;
        }
        BigInteger dotProduct = BigInteger.ZERO;
        for (String word: map.keySet()) {
            if (inputMap.containsKey(word)) {
                dotProduct = dotProduct.add(map.get(word).multiply(inputMap.get(word)));
            }
        }
        return dotProduct.doubleValue();
    }
    /**
     *
     * @param inputMap input map for comparing
     * @return return distance
     */
    public double distance(Map<String, BigInteger> inputMap) {
        if (map == null || inputMap == null || map.isEmpty() || inputMap.isEmpty()) {
            return Math.PI / 2;
        }
        if (map.equals(inputMap)) {
            return 0;
        }
        double dotProduct = this.dotProduct(inputMap);
        double dis1 = this.euclideanNorm();
        double dis2 = 0;
        for (BigInteger x:inputMap.values()) {
            dis2 = dis2 + (x.multiply(x).doubleValue());
        }
        dis2 = Math.sqrt(dis2);
        return Math.acos(dotProduct / dis1 / dis2);
    }
    /**
     *
     * @return return map.
     */
    public Map<String, BigInteger> getMap() {
        return map;
    }
}
