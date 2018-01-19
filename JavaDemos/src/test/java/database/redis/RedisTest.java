package database.redis;

import com.jzhung.demo.core.util.RedisUtil;
import redis.clients.jedis.Jedis;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by jzhung on 2016/11/20.
 */
public class RedisTest {
    public static void main(String[] args) {
        Jedis jredis = RedisUtil.getJedis();

        long start = System.currentTimeMillis();
        /*for (int i = 0; i < 100000; i++) {
            jredis.set("key" + 100000, "123private static HashMap<String, ConnectionSpec> serverPools = new HashMap<String, ConnectionSpec>();  \n" +
                    "    static {  \n" +
                    "        ConnectionSpec connectionSpec1 = DefaultConnectionSpec.newSpe" + 100000);
        }*/
        long time = System.currentTimeMillis() - start;
        /*System.out.println("时间：" + time);
        Set<String> keys = jredis.keys("*");
        Iterator<String> it=keys.iterator() ;
        while(it.hasNext()){
            String key = it.next();
            System.out.println(key);
        }

        System.out.println("-------------");*/
        jredis.sadd("set", "tom", "jso3n");
        jredis.sadd("set", "tom2", "js2on");
        jredis.sadd("set", "tom1", "jso3n");
        jredis.sadd("set", "tom1", "jso3n");
        jredis.sadd("set", "tom1", "json2");
        Set<String> set = jredis.smembers("set");
        Iterator<String> it1=set.iterator() ;
        while(it1.hasNext()){
            Object obj = it1.next();
            System.out.println(obj);
        }
    }
}
