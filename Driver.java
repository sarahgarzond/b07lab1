import java.io.*;

public class Driver {
    public static void main(String[] args) {
        try {
            // Test the default constructor and evaluate method
            Polynomial p = new Polynomial();
            System.out.println("p(3) = " + p.evaluate(3)); // Expecting 0.0 as default is no terms

            // Test the constructor with coefficients and exponents
            double[] c1 = {6, -2, 5};
            int[] e1 = {0, 1, 3};
            Polynomial p1 = new Polynomial(c1, e1);
            System.out.println("p1(3) = " + p1.evaluate(3)); // Should evaluate 6 - 2*3 + 5*3^3

            // Test the add method
            double[] c2 = {5, -3, 7};
            int[] e2 = {0, 2, 8};
            Polynomial p2 = new Polynomial(c2, e2);
            Polynomial s = p1.add(p2);
            System.out.println("s(0.1) = " + s.evaluate(0.1));

            // Test the hasRoot method
            if (s.hasRoot(1)) {
                System.out.println("1 is a root of s");
            } else {
                System.out.println("1 is not a root of s");
            }

            // Test the multiply method
            Polynomial p3 = p1.multiply(p2);
            System.out.println("p3(2) = " + p3.evaluate(2));

            // Test the File constructor and saveToFile method
            Polynomial pFromFile = new Polynomial(new File("polynomial.txt"));
            System.out.println("pFromFile(2) = " + pFromFile.evaluate(2));
            pFromFile.saveToFile("output_polynomial.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}





















