package pl.edu.amu.wmi.bifrandomnesstest.helper;

import org.junit.Assert;
import pl.edu.amu.wmi.bifrandomnesstest.RandomAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by erykmariankowski on 04.06.2018.
 */
public class RandomnessTestHelper {

    private static final int NUMBER_COUNT = 1000;
    private static final int ACCEPTABLE_DIFFERENCE = 100;

    private RandomnessTestHelper() {

    }

    private static List<Integer> genRandNumList(RandomAdapter adapter) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < NUMBER_COUNT; ++i) {
            int randomInt = adapter.nextInt();
            list.add(randomInt);
        }
        return list;
    }

    public static String genConcatRandNumString(RandomAdapter adapter) {
        return genRandNumList(adapter)
                .stream()
                .map(Integer::toBinaryString)
                .collect(Collectors.joining());
    }

    public static void assertCountResult(CountResult overallResult) {
        int difference = overallResult.zeroCount - overallResult.oneCount;
        Assert.assertTrue(difference < RandomnessTestHelper.ACCEPTABLE_DIFFERENCE);
    }


}
