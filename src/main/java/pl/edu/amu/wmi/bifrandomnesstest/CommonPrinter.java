package pl.edu.amu.wmi.bifrandomnesstest;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Stworzone przez Eryk Mariankowski dnia 04.06.18.
 */
public class CommonPrinter {

    private static final int RANDOM_NUMBERS_COUNT = 100000000;
    private static final String HEADER = "#==================================================================\n" +
            "# generator MyGenerator seed = 0\n" +
            "#==================================================================\n" +
            "type: d\n" +
            "count: 100000000\n" +
            "numbit: 32\n";

    private CommonPrinter() {
    }

    public static void print(RandomAdapter adapter) {
        try (OutputStream out = new BufferedOutputStream(System.out)) {
            out.write(HEADER.getBytes());
            for (int i = 0; i < RANDOM_NUMBERS_COUNT; ++i) {
                out.write((Math.abs(adapter.nextInt()) + "\n").getBytes());
            }
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
