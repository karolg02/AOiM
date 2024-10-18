import java.awt.*;

public class ThreeDim extends Figure implements Printing{

    double a;
    double b;
    double c;
    double h;
    double radius;

    Triangle triangle;
    Circle circle;
    Square square;

    ThreeDim(Figure figure,double h){
        if(figure instanceof Triangle){
            this.a = ((Triangle) figure).a;
            this.b = ((Triangle) figure).b;
            this.c = ((Triangle) figure).c;
            triangle = (Triangle) figure;
        }else if(figure instanceof Square){
            this.a = ((Square) figure).a;
            square = (Square) figure;
        }else if(figure instanceof Circle){
            this.radius = ((Circle) figure).radius;
            circle = (Circle) figure;
        }
        this.h = h;
    }

    @Override
    double calculateArea() {
        if(triangle != null){
            return triangle.calculateArea();
        }else if(square != null){
            return square.calculateArea();
        }else{
            return circle.calculateArea();
        }
    }

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

    }
}
