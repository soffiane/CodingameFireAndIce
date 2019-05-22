import java.util.Scanner;

public class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        Game g = new Game();
        g.init(in);
        // game loop
        while (true) {
            g.update(in);
            g.debug();
            g.buildOutput();
            g.output();
        }
    }
}