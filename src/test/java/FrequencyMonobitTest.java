import org.junit.Assert;
import org.junit.Test;
import pl.edu.amu.wmi.bifrandomnesstest.RandomAdapter;
import pl.edu.amu.wmi.bifrandomnesstest.lcg.LCG;
import pl.edu.amu.wmi.bifrandomnesstest.mersennetwister.MTRandom;

/**
 * Created by erykmariankowski on 04.06.2018.
 * Test polegający na sprawdzeniu liczby zer i jedynek w ciągu binarnym ciąg przechodzi test, jeśli liczba zer i jedynek jest mniej więcej taka sama
 */
public class FrequencyMonobitTest {

    private static final int NUMBER_COUNT = 1000;
    private static final int ACCEPTABLE_DIFFERENCE = 100;

    private static CountResult count0and1(int num) {
        String numString = Integer.toBinaryString(num);
        CountResult result = new CountResult();
        for (int i = 0; i < numString.length(); i++) {
            char c = numString.charAt(i);
            if (c == '1') {
                ++result.oneCount;
            } else {
                ++result.zeroCount;
            }
        }
        return result;
    }

    private void shouldTestFrequencyMonobit(RandomAdapter adapter) {
        CountResult overallResult = new CountResult();
        for (int i = 0; i < NUMBER_COUNT; ++i) {
            int randomInt = adapter.nextInt();
            CountResult localResult = count0and1(randomInt);
            overallResult.zeroCount += localResult.zeroCount;
            overallResult.oneCount += localResult.oneCount;
        }
        int difference = overallResult.zeroCount - overallResult.oneCount;
        Assert.assertTrue(difference < ACCEPTABLE_DIFFERENCE);
    }

    @Test
    public void shouldTestLCFFrequencyMonobit() {
        shouldTestFrequencyMonobit(new LCG());
    }

    @Test
    public void shouldTestMTrequencyMonobit() {
        shouldTestFrequencyMonobit(new MTRandom());
    }

    private static class CountResult {
        private int zeroCount = 0;
        private int oneCount = 0;
    }

}
