import java.util.HashSet;
import java.util.Set;


public class Word implements Comparable<Word> {
    /**
     * Private value.
     */
    private String word;
    /**
     * Private value.
     */
    private Set<Integer> index;
    /**
     * Private value.
     */
    private int frequency;
    /*
     * @see Implement comparable methods
     */
    @Override
    public int compareTo(Word o) {
        return word.compareTo(o.word);
    }
    /**
     * constructor.
     * @param word input word
     */
    public Word(String word) {
        if (word == null) {
            throw new NullPointerException();
        }
        index = new HashSet<Integer>();
        this.word = word;
        frequency = 1;
    }

    // getter and setter
    /**
     * @return the word
     */
    public String getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(String word) {
        this.word = word;
    }

    /**
     * @return the index
     */
    public Set<Integer> getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(Set<Integer> index) {
        this.index = index;
    }

    /**
     * @return the frequency
     */
    public int getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }
    /**
     *
     * @param line input integer
     */
    public void addToIndex(Integer line) {
        index.add(line);
    }
    /**
     * Improve frequency.
     */
    public void addFrequency() {
        frequency++;
    }
    /**
     * Override the toString method.
     */
    @Override
    public String toString() {
        return this.word + " " + String.valueOf(frequency) + " " + index.toString();
    }
}
