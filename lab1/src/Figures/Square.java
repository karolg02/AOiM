package Figures;

public class Square extends Figure implements Printing {

    double a;

    public Square(double a) {
        this.a = a;
    }

    @Override
    double calculateArea() {
        return a*a;
    }

    @Override
    double calculatePerimeter() {
        return 4*a;
    }

    @Override
    public void print() {
        System.out.println("Figures.Square: ");
        System.out.println("Length a = "+a);
        System.out.println("The area of the square is " + calculateArea());
        System.out.println("The perimeter of the square is " + calculatePerimeter());
    }
}
