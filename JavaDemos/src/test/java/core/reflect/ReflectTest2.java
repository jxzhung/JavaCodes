package core.reflect;

/**
 * Created by Jzhung on 2018/1/3.
 */
public class ReflectTest2 {
    public static void main(String[] args) {
        //String aa = "Test";
        String aa = "core.reflect.T2";
        ReflectTest2 test = new ReflectTest2();
        Object test2 =  test.getObject(aa);
        System.out.println(test2.getClass().getName());
    }

    /**
     * 传入一个类名返回一个对象
     *
     * @param clazz
     */
    private Object getObject(String clazz) {
        Class<?> clz = null;
        Object o = null;
        try {
            clz = Class.forName(clazz);
            o = clz.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return o;
    }
}

class T2{

}
