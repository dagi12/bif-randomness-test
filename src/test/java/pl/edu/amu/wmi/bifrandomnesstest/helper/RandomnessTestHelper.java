package pl.edu.amu.wmi.bifrandomnesstest.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Assert;
import pl.edu.amu.wmi.bifrandomnesstest.RandomAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by erykmariankowski on 04.06.2018.
 */
public class RandomnessTestHelper {

    private static final int NUMBER_COUNT = 1000;

    private RandomnessTestHelper() {

    }

    private static int[] genRandNumArr(RandomAdapter adapter, int count) {
        int[] arr = new int[count];
        for (int i = 0; i < count; ++i) {
            int randomInt = adapter.nextInt();
            arr[i] = randomInt;
        }
        return arr;
    }

    private static List<Integer> genRandNumList(RandomAdapter adapter, int i) {
        Integer[] list = ArrayUtils.toObject(genRandNumArr(adapter, i));
        return Arrays.asList(list);
    }

    public static String genConcatRandNumString(RandomAdapter adapter, int i) {
        return genRandNumList(adapter, i)
                .stream()
                .map(Integer::toBinaryString)
                .collect(Collectors.joining());
    }

    public static String genConcatRandNumString(RandomAdapter adapter) {
        return genConcatRandNumString(adapter, NUMBER_COUNT);
    }

    public static void assertCountResult(CountResult overallResult, int acceptableDifference) {
        int difference = Math.abs(overallResult.zeroCount - overallResult.oneCount);
        System.out.printf("Zero count: %d, one count: %d, difference: %d\n",
                overallResult.zeroCount, overallResult.oneCount, difference);
        Assert.assertTrue(difference < acceptableDifference);
    }

}
