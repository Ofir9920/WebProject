import java.util.Scanner;

public class Main {

    private MakoRobot makoRobot;
    private WallaRobot wallaRobot;
    private YnetRobot ynetRobot;


    public Main() {

        this.makoRobot = new MakoRobot();
        this.wallaRobot = new WallaRobot();
        this.ynetRobot = new YnetRobot();

    }

    public void Game () {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your robot number: ");
        System.out.println("1. MakoRobot \n" + "2. WallaRobot \n" + "3. YnetRobot");
        int number = scanner.nextInt();



    }

}
