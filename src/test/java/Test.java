import java.io.IOException;
import java.util.Map;

public class Test {


    public static void main(String[] args) {







        long l10 = 1;
        try {
            System.out.println("class " + Class.forName("java.lang.Class"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            throw new IOException("TestExction");
        }catch (IOException e) {
            e.printStackTrace();
        }


        System.exit(0);
    }
}
