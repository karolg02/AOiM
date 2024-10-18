package Figures;

public class Triangle extends Figure implements Printing {

    double a;
    double b;
    double c;

    public Triangle(double a, double b, double c) {
        if (isValidTriangle(a, b, c)) {
            this.a = a;
            this.b = b;
            this.c = c;
        } else {
            System.out.println("Invalid triangle");
        }
    }

    private boolean isValidTriangle(double a, double b, double c) {
        return (a + b > c) && (a + c > b) && (b + c > a);
    }

    @Override
    double calculateArea() {
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    @Override
    double calculatePerimeter() {
        return a + b + c;
    }

    @Override
    public void print() {
        if(isValidTriangle(a, b, c)) {
            System.out.println("Triangle:");
            System.out.println("Length a = " + a + ", b = " + b + ", c = " + c);
            System.out.println("Area: " + calculateArea());
            System.out.println("Perimeter: " + calculatePerimeter());
        }else{
            System.out.println("Cannot form the triangle...");
        }
    }
}
