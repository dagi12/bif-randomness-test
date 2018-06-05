package pl.edu.amu.wmi.bifrandomnesstest;

import org.junit.Assert;
import pl.edu.amu.wmi.bifrandomnesstest.helper.CountResult;
import pl.edu.amu.wmi.bifrandomnesstest.helper.RandomnessTestHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Stworzone przez Eryk Mariankowski dnia 04.06.18.
 */
public class FreqBinBlockTest extends FreqBinTest {

    private static final int M_BLOCK_LEN = 7;
    private static final double ACCEPTABLE_DEVIATION = 0.017;
    private static final int N_LEN = 1000000;

    private static List<String> splitToNChar(String text, int size) {
        List<String> parts = new ArrayList<>();
        int length = text.length();
        for (int i = 0; i <= length - size; i += size) {
            parts.add(text.substring(i, i + size));
        }
        return parts;
    }

    @Override
    CountResult count0and1(String numString) {
        List<String> list = splitToNChar(numString, M_BLOCK_LEN);
        double averageSum = 0;
        for (String s : list) {
            CountResult countResult = super.count0and1(s);
            averageSum += (countResult.oneCount / (double) s.length());
        }
        double overallAverage = averageSum / list.size();
        double deviation = Math.abs(overallAverage - 0.5);
        System.out.printf("Average sum %f, size: %d, average: %f, deviation: %f\n", averageSum, list.size(), overallAverage, deviation);
        Assert.assertTrue(deviation < ACCEPTABLE_DEVIATION);
        return null;
    }

    @Override
    void shouldTestFrequencyBinary(RandomAdapter adapter) {
        String s = RandomnessTestHelper.genConcatRandNumString(adapter, N_LEN);
        count0and1(s);
    }

}
