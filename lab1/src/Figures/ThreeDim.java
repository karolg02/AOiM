package Figures;

public class ThreeDim extends Figure implements Printing {

    double a;
    double b;
    double c;
    double h;
    double radius;

    Triangle triangle;
    Circle circle;
    Square square;

    String baseOfFigure;

    public ThreeDim(Figure figure, double h){
        if(figure instanceof Triangle){
            this.a = ((Triangle) figure).a;
            this.b = ((Triangle) figure).b;
            this.c = ((Triangle) figure).c;
            triangle = (Triangle) figure;
            baseOfFigure = "Triangle";
        }else if(figure instanceof Square){
            this.a = ((Square) figure).a;
            square = (Square) figure;
            baseOfFigure = "Square";
        }else if(figure instanceof Circle){
            this.radius = ((Circle) figure).radius;
            circle = (Circle) figure;
            baseOfFigure = "Circle";
        }
        this.h = h;
    }

    //in that case that's capacity
    @Override
    double calculateArea() {
        if(triangle != null){
            return triangle.calculateArea()*h;
        }else if(square != null){
            return square.calculateArea()*h;
        }else{
            return circle.calculateArea()*h;
        }
    }

    //in that case that's area
    @Override
    double calculatePerimeter() {
        if(triangle != null){
            return triangle.calculateArea()*2+h*a+h*b+h*c;
        }else if(square != null){
            return square.calculateArea()*2+4*h*a;
        }else{
            return circle.calculateArea()*2 + circle.calculatePerimeter()*h;
        }
    }

    @Override
    public void print() {
        System.out.println();
        System.out.println("Prism of the base of " + baseOfFigure);
        System.out.println("Capacity of given prism: " +calculateArea());
        System.out.println("Perimeter of given prism: " +calculatePerimeter());
    }
}
