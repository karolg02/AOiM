public class Triangle extends Figure implements Printing {

    double a;
    double b;
    double c;

    Triangle(double a, double b, double c) {
        if(a+b<c || a+c<b || b+c<a){
            System.out.println("Invalid triangle");
        }else{
            this.a = a;
            this.b = b;
            this.c = c;
        }
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
        if(a+b<c || a+c<b || a+c>c){
            System.out.println("Triangle:");
            System.out.println("Length a = " + a + ", b = " + b + ", c = " + c);
            System.out.println("Area: " + calculateArea());
            System.out.println("Perimeter: " + calculatePerimeter());
        }else{
            System.out.println("Cannot form the triangle.");
        }
    }
}
