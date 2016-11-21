package core.base.WrapperClass;

import org.junit.Test;

/** 测试Java整型数据类型缓存机制
 * Java 5以后为优化内存占用对整数类型提供了自动装箱缓存机制，在一定范围内的整数自动装箱时生成的对象是指向缓存地址的。
 * Created by jzhung on 2016/11/19.
 */
public class IntegerTypeCacheTest {

        @Test
        public void test() {

            //Java整数类型的类包装类的缓存机制
            //Integer 自动装箱缓存范围 -128-127，server模式下可以通过 JVM 的启动参数 -XX:AutoBoxCacheMax=size 修改最大范围
            Integer i1 = 127, i2 = 127;
            System.out.println("i1==i2 " + (i1 == i2));
            Integer i3 = 128, i4 = 128;
            System.out.println("i3==i4 " + (i3 == i4));

            // Character 自动装箱缓存范围 0-127
            Character c1 = 127, c2 = 127;
            System.out.println("c1==c2 " + (c1 == c2));
            Character c3 = 128, c4 = 128;
            System.out.println("c3==c4 " + (c3 == c4));

            // Byte，Short，Long 有固定范围: -128 到 127
            Long l1 = 127L, l2 = 127L;
            System.out.println("l1==l2 " + (l1 == l2));
            Long l3 = 128L, l4 = 128L;
            System.out.println("l3==l4 " + (l3 == l4));
        }
}
