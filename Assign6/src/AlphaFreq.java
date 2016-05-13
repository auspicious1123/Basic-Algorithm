import java.util.Comparator;


public class AlphaFreq implements Comparator<Word> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Word o1, Word o2) {
        int compareResult = o1.getWord().compareTo(o2.getWord());
        // alpha not the same
        if (compareResult != 0) {
            return compareResult;
        }
        // if alpha is the same, sort by frequency ascending
        return o1.getFrequency() - o2.getFrequency();
    }
}
