import java.lang.Math;
import java.util.Scanner;
/**
 * Name: Escubido, Jarisse
 * Project: #3
 * Due: July , 2023
 * Course: cs-3010-01-su23
 * 
 * Description:
 *      A program for all methods (Bisection, Newton-Raphson, Secant,
 *          False-Position, and Modified Secant) for locating roots.
 * 
 */

public class program_3 {
    public static void main(String[] args) {
        // perform iterative methods
        Scanner sc = new Scanner(System.in);
        //bisection(0, 0, sc);
        //newtonRaphson(0, sc);
        //secant(0, sc);
        falsePosition(0, sc);
    }

    private static double function1(double x) {
        // function is f(x) = 2x^3 - 11.7x^2 + 17.7x - 5
        return 2*x*x*x - 11.7*x*x + 17.7*x - 5;
    }

    private static double derivative1(double x) {
        // derivative is f'(x) = 6x^2 - 23.4x + 17.7
        return 6*x*x - 23.4*x + 17.7;
    }

    private static double function2(double x) {
        // function is f(x) = x + 10 - xcosh(50/x)
        return x + 10 - x*Math.cosh(50 / x);
    }

    private static double derivative2(double x) {
        return (((50*Math.sinh(50/x))/x)-(Math.cosh(50/x)) + 1);
    }

    private static void bisection(double a, double b, Scanner sc) {
        System.out.println("\nThe function is f(x) = 2x^3 - 11.7x^2 + 17.7x - 5");
        // Ask for a value, b value, and error value

        System.out.print("Enter value of a: ");
        a = sc.nextDouble();
        System.out.print("Enter value of b: ");
        b = sc.nextDouble();
        System.out.print("Enter approximate relative error: ");
        double relativeError = sc.nextDouble();

        // check if a < b
        if (a < b) {
            System.out.println("Bisection method may continue: a < b is true.");
        }

        if (function1(a) * function1(b) > 0.0) {
            System.out.println("Your values for the function has the same sign, therefore cannot determnine root.");
            return;
        }

        int func1Iterations = 0;
        System.out.printf("\n%3s%8s%16s%20s%23s%25s%27s%25s", "iterations", "a", "b", "c", "function(a)", "function(b)", "function(c)", "error");

        // check if f(a) * f(b) < 0 to determine change in sign
        double c = ((a+b)/2.0);
        while ((Math.abs(b-a) >= relativeError) && (func1Iterations < 100)) {
            System.out.printf("\n%3d%19.3f%16.3f%20.3f%19.4f%24.4f%28.4f%28.3f", func1Iterations, a, b, c, function1(a), function1(b), function1(c), Math.abs(b-c));
            c = ((a+b)/2.0);
            if (function1(c) == 0.00) {
                System.out.printf("\nc = " + c + " is a root.");
                break;
            }

            else if (function1(a) * function1(c) < 0) {
                b = c;
            }

            else {
                a = c;
            }
            func1Iterations++;
            relativeError = Math.abs(b-a);
        }
    }

    private static void newtonRaphson(double x, Scanner sc) {
        System.out.print("\n----------Newton-Raphson Method-----------");
        // get user's initial guess
        System.out.print("\nEnter initial guess: ");
        x = sc.nextDouble();
        System.out.print("\nEnter approximate relative error: ");
        double NRrelativeError = sc.nextDouble();

        System.out.printf("\n%3s%10s%18s%20s%23s", "Iterations", "xn", "f(xn)",  "f'(xn)", "error");
        int NRiterations = 0;
        double rootFunction = function1(x)/derivative1(x);
        while ((Math.abs(function1(x)) >= NRrelativeError) && (NRiterations < 100)) {
            System.out.printf("\n%3d%19.5f%16.3f%20.3f%23.3f", NRiterations, x, function1(x), derivative1(x), NRrelativeError);
            rootFunction = function1(x)/derivative1(x);
            x = x - rootFunction;
            NRiterations++;
            if (NRiterations > 100) {
                System.out.println("The function does not converge.");
                break;
            }
        }
        System.out.println("\nThe root of the function is " + x);
    }

    private static void secant(double x, Scanner sc) {
        System.out.println("\n----------------Secant Method-----------------");
        // get user's intial approximations
        System.out.print("\nEnter first initial approximation x0: ");
        double x0 = sc.nextDouble();
        System.out.print("\nEnter second initial approximation x1: ");
        double x1 = sc.nextDouble();
        System.out.print("Enter approximate relative error: ");
        double SError = sc.nextDouble();

        System.out.printf("\n%3s%10s%18s%20s%23s%25s", "Iterations", "x(n-1)", "xn", "x(n+1)", "|f(x(n+1))|", "error");
        int SIterations = 0;
        double x2 = x1 - ((x1-x0)/function1(x1)-function1(x0))*function1(x1);
        while (function1(x2) >= SError) {
            x2 = x1 - ((x1-x0)/function1(x1)-function1(x0))*function1(x1);
            System.out.printf("\n%3d%19.5f%16.3f%20.3f%23.3f%25.3f", SIterations, x0, x1, x2, Math.abs(function1(x2)), SError);
            x0 = x1;
            x1 = x2;
            SIterations++;
        }
        System.out.println("\nThe root of the function is " + x1);
    }

    private static void falsePosition(double x, Scanner sc) {
        System.out.println("\n---------False Position Method------------");
        // Ask user for initial guesses a and b
        System.out.print("\nEnter initial guess a: ");
        double a = sc.nextDouble();
        System.out.print("\nEnter initial guess b: ");
        double b = sc.nextDouble();
        System.out.print("\nEnter approximate relative error: ");
        double FPerror = sc.nextDouble();

        double c = (a * function1(b) - b * function1(a)) / (function1(b) - function1(a));
        int FPiterations = 0;
        double FPapprox = Math.abs(c-a);
        System.out.printf("\n%3s%9s%16s%18s%21s%24s%28s%31s", "Iterations", "an", "bn", "f(an)", "f(bn)", "cn", "f(cn)", "error");
        while ((FPapprox > FPerror) && (FPiterations < 100))
        {
            // Find the point that touches x axis
            c = a - function1(a)*((b - a)/function1(b) - function1(a));
            FPapprox = Math.abs(c-a);
            FPiterations++;

            System.out.printf("\n%3d%16.3f%16.3f%18.3f%21.3f%24.3f%28.3f%31.3f", FPiterations, a, b, function1(a), function1(b), c, function1(c), FPapprox);
 
            // Check if the above found point is root
            if (function1(c) == 0)
                break;
 
            // Decide the side to repeat the steps
            else if (function1(c) * function1(a) < 0)
                b = c;
            else
                a = c;
        }
    }

    private static void modifiedSecant() {

    }
}
