import java.util.Comparator;

public class IgnoreCase implements Comparator<Word> {

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Word o1, Word o2) {
        // get each word and compare
        return o1.getWord().toLowerCase().compareTo(o2.getWord().toLowerCase());
    }

}
