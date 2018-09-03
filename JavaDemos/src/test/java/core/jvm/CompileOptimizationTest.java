package core.jvm;

import org.junit.Test;

/**
 * Created by jzhung on 2017/12/26.
 */
public class CompileOptimizationTest {

    @Test
    public void stringTest1(){
        String a = "a" + "b" + 1;
        String b = "ab1";
        System.out.println(a == b);
    }
}
