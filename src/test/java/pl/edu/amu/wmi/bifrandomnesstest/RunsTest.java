package pl.edu.amu.wmi.bifrandomnesstest;

import org.junit.Test;
import pl.edu.amu.wmi.bifrandomnesstest.helper.CountResult;
import pl.edu.amu.wmi.bifrandomnesstest.helper.RandomnessTestHelper;
import pl.edu.amu.wmi.bifrandomnesstest.lcg.LCG;
import pl.edu.amu.wmi.bifrandomnesstest.mersennetwister.MTRandom;

import static pl.edu.amu.wmi.bifrandomnesstest.helper.RandomnessTestHelper.assertCountResult;

/**
 * Created by erykmariankowski on 04.06.2018.
 * test, który sprawdza ile w danym cigu jest podciągów złożonych z samych jedynek (11...1) i ile jest podciągów złożonych z samych zer (00...0)
 * liczba tych podcigów powinna byś zbliżona do tej w cigu losowym
 */
public class RunsTest {

    private static final int RUNS_ACCEPTABLE_DIFFERENCE = 2;

    private void shouldTestRuns(RandomAdapter adapter) {
        CountResult overallResult = new CountResult();
        char previousChar = 'a';
        String s = RandomnessTestHelper.genConcatRandNumString(adapter, 4);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != previousChar) {
                previousChar = c;
                if (c == '1') {
                    ++overallResult.oneCount;
                } else {
                    ++overallResult.zeroCount;
                }
            }
        }
        assertCountResult(overallResult, RUNS_ACCEPTABLE_DIFFERENCE);
    }

    @Test
    public void shouldTestLCGFrequencyBinary() {
        shouldTestRuns(new LCG());
    }

    @Test
    public void shouldTestMTrequencyBinary() {
        shouldTestRuns(new MTRandom());
    }
}
