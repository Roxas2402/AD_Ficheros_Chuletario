import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            opcion = menu(scanner);
            switch (opcion) {
                case 1:
                    funcion1();
                    break;
                case 2:
                    funcion2();
                    break;
                case 3:
                    funcion3();
                    break;
                case 4:
                    break;
            }
        } while (opcion != 4);
    }

    private static int menu(Scanner scanner) {
        int opcion;
        do {
            System.out.println("1: Opción 1");
            System.out.println("2: Opción 2");
            System.out.println("3: Opción 3");
            System.out.println("4: Salir");

            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException exception) {
                opcion = 0;
                System.out.println("Error");
                scanner.nextLine();
            }
        } while (opcion < 1 || opcion > 4);
        scanner.nextLine();
        return opcion;
    }
}
