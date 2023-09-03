
/**
 * Name: Escubido, Jarisse
 * Project: #1
 * Due: June 18, 2023
 * Course: cs-3010-01-su23
 * 
 * Description:
 * 		A program that asks the user for the number of linear
 *  equations to solve using the Gaussian elimination with scaled
 *  partial pivoting method. User has opttion to manually enter
 *  coefficients by first entering number of equations then value
 *  value of coefficients from command line or provide a filename
 *  with the coefficients.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.lang.Math;

public class program_1 {
    // 2D array list
    public static ArrayList<ArrayList<Double>> a = new ArrayList<ArrayList<Double>>();

    public static void main(String args[]) {
        Scanner myObj = new Scanner(System.in);
        System.out.print("Menu:\n1)Read from file\n2)Enter manually\nSelection: ");

        try {
            int x = myObj.nextInt();
            if (x == 1) {
                readFile();
                gaussianElim();
            } else if (x == 2) {
                manualEntry();
                gaussianElim();
            } else {
                System.out.println("Invalid Input! Goodbye!");
            }
        } catch (InputMismatchException a) {
            System.out.println("Invalid Data Type or Mismatch");
        }
        myObj.close();
    }

    public static void readFile() {
        try {
            Scanner userInput = new Scanner(System.in);
            System.out.print("Enter relative path: ");
            String input = userInput.nextLine();
            File file = new File(input);
            Scanner myReader = new Scanner(file);
            int row = 0;
            while (myReader.hasNextLine()) {
                String[] line = myReader.nextLine().trim().split(" ");
                a.add(new ArrayList<Double>());

                for (int col = 0; col < line.length; col++) {
                    a.get(row).add(col, Double.parseDouble(line[col]));
                }
                row++;
            }
            userInput.close();
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found check path");
            e.printStackTrace();
        }
    }

    public static void manualEntry() {
        Scanner myObj1 = new Scanner(System.in);
        Scanner myObj2 = new Scanner(System.in);

        System.out.print("\nEnter the number of functions: ");
        int numOfFunc = myObj1.nextInt();
        System.out.print("Enter coefficient: ");
        String coefficient = myObj2.nextLine();

        String[] line = coefficient.trim().split(" ");
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
        myObj1.close();
        myObj2.close();
    }

    public static void gaussianElim() {
        double max = 0;
        int index = 0;
        int counter = 0; // keeps track of number of times we pivoted
        double maxDiv[] = new double[a.size()];

        // This loop allows it to rotate multiple times given X number of equations and
        // variables
        while (counter < a.size() - 1) {
            System.out.println("\n--------------------------------------------------------");
            printMatrix();
            System.out.println("Step 1: Identify largest int from each row");
            for (int i = counter; i < a.size(); i++) {
                max = 0;
                for (int j = 0; j < a.get(i).size() - 1; j++) {
                    if (max <= Math.abs(a.get(i).get(j))) { // Problem if you have multiple of the same large number
                        max = Math.abs(a.get(i).get(j));
                        index = j;
                    }
                }
                System.out.println("\tEquation " + i + "  Max: " + max + " Index: " + index);
                maxDiv[i] = max;
            }
            System.out.println("");

            // Dividing column 1 with its respective largest row value that was stored in an array
            max = 0;
            index = 0;
            double divValue = 0;
            System.out.println("Step 2: AbsValue of First coeff / largest int in its row");
            for (int i = counter; i < a.size(); i++) {
                divValue = Math.abs(a.get(i).get(counter)) / maxDiv[i];
                System.out.println("\tEquation " + i + "  First Coeff:  " + Math.abs(a.get(i).get(counter)) + " / "
                        + maxDiv[i] + " = " + divValue);
                if (divValue > max) {
                    max = divValue;
                    index = i;
                }
            }

            // Doing the pivot
            double temp = 0;
            System.out.println("\nStep 3: Pivot equation (if needed)");
            for (int i = counter; i <= a.size(); i++) {
                temp = a.get(counter).get(i);
                a.get(counter).set(i, a.get(index).get(i));
                a.get(index).set(i, temp);
            }
            counter++;
            printMatrix();

            // Canceling coefficient
            index = 0;
            double multiplier = 0;
            System.out.println("\nStep 4: Elimination Coeff");
            for (int i = counter; i < a.get(0).size() - 1; i++) {
                temp = a.get(i).get(counter - 1);
                multiplier = temp / a.get(counter - 1).get(counter - 1);
                for (int j = counter - 1; j < a.get(i).size(); j++) {
                    temp = a.get(i).get(j) - (multiplier * a.get(counter - 1).get(j));
                    a.get(i).set(j, temp);
                }
                index++;
            }
            printMatrix();
        }
        backSubstition();
    }

    public static void backSubstition() {
        // Substituion
        double denominator = 0;
        double ans = 0;
        double answer[] = new double[a.size()];
        char[] var = {'x', 'y', 'z', 'a', 'b', 'c', 'd', 'e', 'f', 'g'};

        for (int i = a.size() - 1; i >= 0; i--) {
            denominator = a.get(i).get(i); // goes in diagonal [2][2] [1][1] [0][0]
            ans = a.get(i).get(a.size()); // always last value [2][3] [1][3] [0][3]
            double temp = 0;
            for (int j = i + 1; j < a.size(); j++) {
                temp += a.get(i).get(j) * answer[j];
            }
            answer[i] = (ans - temp) / denominator;
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.print(var[i]);
            System.out.printf(" = %.2f\n", answer[i]);
        }
    }

    public static void printMatrix() {
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