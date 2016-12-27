package core.other;

import org.junit.Test;

/**
 * Created by Jzhung on 2016/12/22.
 */
public class OtherTest {
    private String name;

    @Test
    public void foo1(){
        foo2(name);
        System.out.println(name);
    }

    private void foo2(String a) {
        a = "1231";
        System.out.println(a);
    }
}
