package utils;

import java.util.Comparator;

public class ProfitComparator implements Comparator<double[]> {

    /**
     * Compares two arrays of double from largest to smallest
     * @param o1
     * @param o2
     * @return integer
     */
    @Override
    public int compare(double[] o1, double[] o2) {
        return (int) ((int) o2[4]-o1[4]);
    }
}
