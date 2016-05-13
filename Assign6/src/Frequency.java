import java.util.Comparator;

public class Frequency implements Comparator<Word> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Word o1, Word o2) {
        // sorted by frequency and descending
        return o2.getFrequency() - o1.getFrequency();
    }

}
