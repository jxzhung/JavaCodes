package core.other;

/**
 * Created by Jzhung on 2016/12/22.
 */
public class MemberTest {
    private String name;
    {
        name = "asd";
    }

    public static void main(String[] args) {
        new MemberTest().start();
    }

    public void start(){
        System.out.println("" + name);
    }
}
