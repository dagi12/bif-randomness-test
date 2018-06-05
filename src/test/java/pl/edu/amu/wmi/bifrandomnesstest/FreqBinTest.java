package pl.edu.amu.wmi.bifrandomnesstest;

import org.junit.Test;
import pl.edu.amu.wmi.bifrandomnesstest.helper.CountResult;
import pl.edu.amu.wmi.bifrandomnesstest.helper.RandomnessTestHelper;
import pl.edu.amu.wmi.bifrandomnesstest.lcg.LCG;
import pl.edu.amu.wmi.bifrandomnesstest.mersennetwister.MTRandom;

/**
 * Created by erykmariankowski on 04.06.2018.
 * Test polegający na sprawdzeniu liczby zer i jedynek w ciągu binarnym ciąg przechodzi test, jeśli liczba zer i jedynek jest mniej więcej taka sama
 */
public class FreqBinTest {

    private static final int FREQ_BIN_ACCEPTABLE_DIFFERENCE = 1200;

    CountResult count0and1(String numString) {
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

    void shouldTestFrequencyBinary(RandomAdapter adapter) {
        String s = RandomnessTestHelper.genConcatRandNumString(adapter);
        CountResult overallResult = count0and1(s);
        RandomnessTestHelper.assertCountResult(overallResult, FREQ_BIN_ACCEPTABLE_DIFFERENCE);
    }

    @Test
    public void shouldTestLCGFrequencyBinary() {
        shouldTestFrequencyBinary(new LCG());
    }

    @Test
    public void shouldTestMTrequencyBinary() {
        shouldTestFrequencyBinary(new MTRandom());
    }

}
