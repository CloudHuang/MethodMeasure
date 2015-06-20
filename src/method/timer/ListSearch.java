package method.timer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huntter
 */
@Measurable
public class ListSearch {
    private List<Integer> numbers;
    private int probe;

    public ListSearch(int size) {
        numbers = new ArrayList<Integer>(size);

        for (int i = 0; i < size; i++) {
            numbers.add(i);
            probe = size / 2;
        }
    }

    @Measure
    public void search() {
        numbers.contains(probe);
    }

    public void add() {
        numbers.add(probe, 10);
    }
}
