import java.util.*;
import java.util.stream.IntStream;

class Shape
{
    public double volume;

    public double getVolume()
    {
        return this.volume;
    }
}


class Box extends Shape
{
    public Box()
    {
        this.volume=100;


    }

    public boolean add(Shape shape)
    {
        if(shape.getVolume()>this.volume)
        {
            return false;
        }
        else
        {

            this.volume-=shape.getVolume();
            return true;
        }
    }
}



class Pyramid extends Shape
{
    private final double s;
    private final double h;

    public Pyramid(double s, double h)
    {
        this.s=s;
        this.h=h;
        this.volume=s*h/3;
    }
    public void out()
    {
        System.out.println(s+" "+h+" "+this.volume);
    }

}

class SolidRevolution extends Shape
{
    public final double radius;
    public SolidRevolution(double radius)
    {
        this.radius=radius;
    }


}


class Cylinder extends SolidRevolution
{
    private final double height;
    public Cylinder(double radius, double height)
    {
        super(radius);

        this.height=height;
        this.volume=3.14* Math.pow(radius,2)*height;
    }

    public void out()
    {
        System.out.println(radius+" "+height+" "+this.volume);
    }

}
class Ball extends SolidRevolution
{
    public Ball(double radius)
    {
        super(radius);
        this.volume=4*3.14*Math.pow(radius,3)/3;
    }
    public void out()
    {
        System.out.println(radius+" "+this.volume);
    }

}


public class Main {
    public static void main(String args[])
    {
        Box box=new Box();
        Pyramid pyramid=new Pyramid(4,5);
        Cylinder cylinder=new Cylinder(3,2);
        Ball ball=new Ball(3);

        System.out.println("Box volume: "+box.getVolume());
        System.out.println("Pyramid volume: "+pyramid.getVolume());
        System.out.println("Cylinder volume: "+cylinder.getVolume());
        System.out.println("Ball volume: "+ball.getVolume());

        boolean a=box.add(pyramid);
        System.out.println("Adding pyramid to box finished with "+a+", current box volume: "+box.getVolume());
        a=box.add(cylinder);
        System.out.println("Adding cylinder to box finished with "+a+", current box volume: "+box.getVolume());
        a=box.add(ball);
        System.out.println("Adding ball to box finished with "+a+", current box volume: "+box.getVolume());
    }


}
