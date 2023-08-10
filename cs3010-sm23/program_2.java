/**
 * Name: Escubido, Jarisse
 * Project: #2
 * Due: June 25, 2023
 * Course: cs-3010-01-su23
 * 
 * Description:
 * 		A program that asks the user for the number of linear
 *  equations to solve using Jacobi iterative method and
 *  Gauss-Seidel method.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.lang.Math;

public class program_2 {
    
    // 2D array list
    public static ArrayList<ArrayList<Double>> a = new ArrayList<ArrayList<Double>>();
    public static int numOfFunc;
    public static int r;
    public static double error;

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        Scanner string = new Scanner(System.in);

        System.out.print("Enter the number of equations: ");
        numOfFunc = sc.nextInt();

        System.out.print("\nMenu:\n1) Read coefficients from file\n2) Enter coefficients manually\nSelection: ");

        try {
            int x = sc.nextInt();
            if (x == 1) {
                readFile(input);
                desiredStoppingError(string);
                jacobiMethod();
                gaussSeidelMethod();
            } else if (x == 2) {
                manualEntry(input);
                desiredStoppingError(string);
                jacobiMethod();
                gaussSeidelMethod();
            } else {
                System.out.println("Invalid Input! Goodbye!");
            }
            sc.close();
        } catch (InputMismatchException a) {
            System.out.println("Invalid Data Type or Mismatch");
        }
    }

    private static void desiredStoppingError(Scanner string) {
        System.out.print("Enter desired stopping error: ");
        String desiredError = string.nextLine();
        error = Double.parseDouble(desiredError);

        System.out.print("Enter starting solution (i.e. 0 0 0): ");
        String startingSolution = string.nextLine();
        String[] strArray = startingSolution.split(" ");
        int[] intArray = new int[strArray.length];
        
        System.out.print("X = [");
        for (int i = 0; i < intArray.length; i++) {
            System.out.print(intArray[i]);
            if (i != intArray.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    private static void jacobiMethod() {
        System.out.println("\n---------- Jacobi Iterative Method ----------");
        int iterations = 0;

        double[] current = new double[a.size()];
        double[] previous = new double[a.size()];
        
        while (true) {
            for (int i = 0; i < a.size(); i++) {
            double total = a.get(i).get(a.size());

                for (int j = 0; j < a.size(); j++) {
                    if (j != i) {
                        total -= a.get(i).get(j) * previous[j];
                    }
                }
            current[i] = 1/a.get(i).get(i) * total;
            }

            // prints every iteration X = [ # # # ] of the method
            System.out.print("Iteration " + iterations + " = [ ");
            for (int i = 0; i < a.size(); i++) {
                System.out.print(current[i] + " ");
            }

            double approxError = 0.0;
            for (int i = 0; i < a.size(); i++){
                approxError = Math.abs(Math.sqrt((current[i]*current[i]) - (previous[i]*previous[i]))/current[i]*current[i]);
            }

            System.out.println("] " + approxError);

            iterations++;
            if (iterations == 1) continue;
            boolean stop = true;
            for (int i = 0; i < a.size() && stop; i++) {
                if (Math.abs(current[i] - previous[i]) > error) {
                    stop = false;
                }
            }

            if (stop || iterations == 51) break;
            previous = (double[]) current.clone();
        }
    }

    private static void gaussSeidelMethod() {
        System.out.println("\n------- Gauss-Seidel Iterative Method -------");

        int iterations = 0;
        double[] current = new double[a.size()];
        double[] previous = new double[a.size()];

        while (true) {
            for (int i = 0; i < a.size(); i++) {
                double total = a.get(i).get(a.size());
                for (int j = 0; j < a.size(); j++) {
                    if (j != i) {
                        total -= a.get(i).get(j) * current[j];
                    }
                }
                current[i] = 1/a.get(i).get(i) * total;
            }

            // prints every iteration X = [ # # # ] of the method
            System.out.print("Iteration " + iterations + " = [ ");
            for (int i = 0; i < a.size(); i++) {
                System.out.print(current[i] + " ");
            }

            double approxError = 0.0;
            for (int i = 0; i < a.size(); i++){
                approxError = Math.abs(Math.sqrt((current[i]*current[i]) - (previous[i]*previous[i]))/current[i]*current[i]);
            }

            System.out.println("] " + approxError);

            iterations++;
            if (iterations == 1) {
                continue;
            }
            boolean stop = true;
            for (int i = 0; i < a.size() && stop; i++) {
                if (Math.abs(current[i] - previous[i]) > error) {
                    stop = false;
                }
            }

            if (stop || iterations == 51) { // iterations must be 51 to print iteration 50.
                break;
            }
            
            // values in current array to be used as new values for next array
            previous = (double[]) current.clone(); 
        }
    }

    private static void manualEntry(Scanner input) {
        System.out.print("Enter coefficients: ");
        String coefficients = input.nextLine();

        String[] line = coefficients.trim().split(" ");
        int numOfCoe = line.length / numOfFunc;

        int row = 0;
        int col = 0;

        while (row < numOfFunc) {

            a.add(new ArrayList<Double>());

            for (int k = 0; k < numOfCoe; k++) {
                a.get(row).add(k, Double.parseDouble(line[col]));
                col++;
            }
            row++;
        }
        printMatrix();
    }

    private static void readFile(Scanner input) {
        System.out.print("Enter relative path: ");
        File file = new File(input.nextLine().toString());
        try {
            Scanner reader = new Scanner(file);
            int row = 0;
            while (reader.hasNextLine()) {
                String[] line = reader.nextLine().trim().split(" ");
                a.add(new ArrayList<Double>());

                for (int col = 0; col < line.length; col++) {
                    a.get(row).add(col, Double.parseDouble(line[col]));
                }
                row++;
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found check path");
            e.printStackTrace();
        }
        printMatrix();
    }

    public static void printMatrix() {
        System.out.print("\n");
        for (int i = 0; i < a.size(); i++) {
            System.out.print("\t");
            for (int j = 0; j < a.get(i).size(); j++) {
                System.out.printf("| %5.2f ", a.get(i).get(j));
            }
            System.out.println("");
        }
        System.out.println("");
    }
}
