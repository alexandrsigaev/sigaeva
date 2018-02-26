package ru.job4j.array;

/**
 * Class
 * @author sigaevaleksandr
 * @since 26.02.2018
 */
public class MergeArrays {

    public int[] merge(int[] first, int[] second) {
        int[] mergeArr = new int[first.length + second.length];
        int countFirst = 0;
        int countSecond = 0;

        for (int barrier = 0; barrier < first.length + second.length; ) {
            mergeArr[barrier++] = first[countFirst] < second[countSecond] ? first[countFirst++] : second[countSecond++];
            if (countFirst == first.length){
                System.arraycopy(second, countSecond, mergeArr, barrier, second.length - countSecond);
                break;
            } else if (countSecond == second.length) {
                System.arraycopy(first, countFirst, mergeArr, barrier, first.length - countFirst);
                break;
            }
        }
        return mergeArr;
    }
}
