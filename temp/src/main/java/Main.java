import org.sfml_dev.system.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("hello");

        Clock clock = new Clock();

        for (int i = 0; i < 10; ++i)
            System.out.println(clock.restart().asMicroseconds());
    }
}
