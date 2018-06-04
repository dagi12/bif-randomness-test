package pl.edu.amu.wmi.bifrandomnesstest.lcg;

import pl.edu.amu.wmi.bifrandomnesstest.RandomAdapter;

import java.util.Random;

/**
 * Created by erykmariankowski on 04.06.2018.
 * According to https://www.javamex.com/tutorials/random_numbers/java_util_random_algorithm.shtml#.WxVKXVPRBAY java.util.Random is in fact based on LCG algorithm
 */
public class LCG implements RandomAdapter {

    private static Random RND = new Random();

    @Override
    public int nextInt() {
        return RND.nextInt();
    }
}
