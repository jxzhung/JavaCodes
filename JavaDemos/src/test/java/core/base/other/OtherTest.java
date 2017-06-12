package core.base.other;

import org.junit.Test;

import java.util.UUID;

/**
 * Created by Jzhung on 2017/3/29.
 */
public class OtherTest {

    @Test
    public void uuid(){
        for (int i = 0; i < 23; i++) {
            String uuid = UUID.randomUUID().toString().replace("-", "");
            System.out.println(uuid);
        }
    }


}
