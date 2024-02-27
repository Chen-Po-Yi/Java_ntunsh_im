package hw1;
import java.lang.Math;
public class hw1 {
	static int pv;
	static int fv;
	static final double interest=0.05;
	static int interval;
	public static void main(String[] args) {
		pv=500000;
		interval=12;
		fv= (int) (pv * Math.pow((1+interest), interval));
		System.out.println("My pv is "+pv+" and after "+interval+" year it would be"+fv);
	}
}
