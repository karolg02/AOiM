import Figures.Circle;
import Figures.Square;
import Figures.ThreeDim;
import Figures.Triangle;

import java.util.Scanner;

public class Menu{

    void menu(Scanner scanner){
        System.out.println("Choose the figure:");
        System.out.println("1) Triangle");
        System.out.println("2) Square");
        System.out.println("3) Circle");
        System.out.println("4) ThreeDim");
        System.out.println("5) Exit");

        System.out.println("Choose an option:");
        int option = scanner.nextInt();

        switch (option){
            case 1:
                menuTriangle(scanner);
                break;
            case 2:
                menuSquare(scanner);
                break;
            case 3:
                menuCircle(scanner);
                break;
            case 4:
                menuThreeDim(scanner);
                break;
            case 5:
                    System.out.println("Closing...");
                    System.exit(0);
        }
    }

    private void menuTriangle(Scanner scanner){
        System.out.println("Enter the first value for a:");
        double a = scanner.nextDouble();
        System.out.println("Enter the second value for b:");
        double b = scanner.nextDouble();
        System.out.println("Enter the third value for c:");
        double c = scanner.nextDouble();


        System.out.println('\n');

        Triangle triangle = new Triangle(a, b, c);
        triangle.print();

        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void menuSquare(Scanner scanner) {
        System.out.println("Enter the side:");
        double a = scanner.nextDouble();

        System.out.println('\n');

        Square square = new Square(a);
        square.print();

        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void menuCircle(Scanner scanner) {
        System.out.println("Enter the radius of the circle: ");
        double radius = scanner.nextDouble();

        System.out.println('\n');

        Circle circle = new Circle(radius);
        circle.print();

        System.out.println("\nPress ENTER to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }

    private void menuThreeDim(Scanner scanner) {

        double a,b,c,h;

        System.out.println("Choose the base of the ThreeDim figure:");
        System.out.println("1) Triangle");
        System.out.println("2) Square");
        System.out.println("3) Circle");
        int option = scanner.nextInt();

        switch (option){
            case 1:
                System.out.println("Enter the first value for a:");
                a = scanner.nextDouble();
                System.out.println("Enter the second value for b:");
                b = scanner.nextDouble();
                System.out.println("Enter the third value for c:");
                c = scanner.nextDouble();
                System.out.println("\nEnter the height");
                h = scanner.nextDouble();

                Triangle triangle = new Triangle(a,b,c);
                ThreeDim threeDimTriangle = new ThreeDim(triangle,h);
                threeDimTriangle.print();
                System.out.println("\nPress ENTER to continue...");
                scanner.nextLine();
                scanner.nextLine();
                break;

            case 2:
                System.out.println("\nEnter the side:");
                a = scanner.nextDouble();
                System.out.println("\nEnter the height");
                h = scanner.nextDouble();

                Square square = new Square(a);
                ThreeDim threeDimSquare = new ThreeDim(square,h);
                threeDimSquare.print();
                System.out.println("\nPress ENTER to continue...");
                scanner.nextLine();
                scanner.nextLine();
                break;

            case 3:
                System.out.println("\nEnter the radius:");
                double radius = scanner.nextDouble();
                System.out.println("\nEnter the height");
                h = scanner.nextDouble();

                Circle circle = new Circle(radius);
                ThreeDim threeDimCircle = new ThreeDim(circle,h);
                threeDimCircle.print();
                System.out.println("\nPress ENTER to continue...");
                scanner.nextLine();
                scanner.nextLine();
                break;
        }


    }

    Menu(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            menu(scanner);
        }
    }
}
