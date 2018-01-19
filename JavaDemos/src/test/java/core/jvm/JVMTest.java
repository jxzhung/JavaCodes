package core.jvm;

import org.junit.Test;

import java.lang.reflect.Array;
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

    @Test
    public void testGetType() throws ClassNotFoundException {
        Class clazz =  Class.forName("[I");
        System.out.println(clazz.getName());
        clazz =  Class.forName("[[[[Ljava.lang.String;");
        System.out.println(clazz.getName());

        Class eClass = Object.class;
        Class<?> aClass = Array.newInstance(eClass, 1).getClass();

    }
}
