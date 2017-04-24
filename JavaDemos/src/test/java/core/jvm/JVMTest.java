package core.jvm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jzhung on 2016/11/20.
 */
public class JVMTest {

    @Test
    public void collectionTest() {
        long t1 = System.currentTimeMillis();
        List<Integer> numList = new ArrayList<Integer>(10000000);
        for (int i = 0; i < 10000000; i++) {
            numList.add(i);
        }
        long t2 = System.currentTimeMillis();
        long time = t2 - t1;
        System.out.println(time);

        long t3 = System.currentTimeMillis();
        List<Integer> numList2 = new ArrayList<Integer>();
        for (int i = 0; i < 10000000; i++) {
            numList2.add(i);
        }

        long time2 = System.currentTimeMillis() - t3;
        System.out.println(time2);

    }
}
