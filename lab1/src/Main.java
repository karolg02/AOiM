import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        double a = scanner.nextDouble();
//        double b = scanner.nextDouble();
//        double c = scanner.nextDouble();
//
        Triangle triangle = new Triangle(3,4,5);
//        triangle.print();

        Square square = new Square(5);
//        square.print();

        Circle circle = new Circle(5);
//        circle.print();

        ThreeDim threeDim = new ThreeDim(triangle,5);
        System.out.println(threeDim.calculatePerimeter());

    }
}