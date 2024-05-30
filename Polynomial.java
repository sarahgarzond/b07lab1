import java.io.*;
import java.util.*;

public class Polynomial{
	private double[] coefficients;
	private int[] exponents;
	
	public Polynomial(){
		this.coefficients = null;
		this.exponents = null;
	}

	public Polynomial(double[] coefficients, int[] exponents) {
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

	public Polynomial add(Polynomial other){
		int maxExponent = Math.max(this.exponents[this.exponents.length - 1], 
		other.exponents[other.exponents.length - 1]);
		double[] newCoefficients = new double[maxExponent + 1];
		for (int i = 0; i < this.coefficients.length; i++) {
			newCoefficients[this.exponents[i]] += this.coefficients[i];
		}
		for (int i = 0; i < other.coefficients.length; i++) {
			newCoefficients[other.exponents[i]] += other.coefficients[i];
		}

		// Count non-zero coefficients
		int count = 0;
		for (int i = 0; i< newCoefficients.length; i++) {
			if (coeff != 0) 
				count++;
		}

		// Fill new arrays with non-zero coefficients and corresponding exponents
		double[] resultCoefficients = new double[count];
		int[] resultExponents = new int[count];
		int index = 0;
		for (int i = 0; i < newCoefficients.length; i++) {
				if (newCoefficients[i] != 0) {
					resultCoefficients[index] = newCoefficients[i];
					resultExponents[index] = i;
					index++;
			}
		}
		return new Polynomial(resultCoefficients, resultExponents);
	}

	public double evaluate(double x) {
		double result = 0;
		for (int i = 0; i < coefficients.length; i++) {
			result += coefficients[i] * Math.pow(x, exponents[i]);
		}
		return result;
    }

	public boolean hasRoot(double x) {
        return evaluate(x) == 0;
    }

	public Polynomial multiply(Polynomial p){
		int maxLength = this.coefficients.length * p.coefficients.length;
        double[] tempCoefficients = new double[maxLength];
        int[] tempExponents = new int[maxLength];
        int currentSize = 0;

        // Step 2: Multiply each term of the calling polynomial with each term of the argument polynomial
        for (int i = 0; i < this.coefficients.length; i++) {
            for (int j = 0; j < p.coefficients.length; j++) {
                double newCoefficient = this.coefficients[i] * p.coefficients[j];
                int newExponent = this.exponents[i] + p.exponents[j];

                // Step 3: Check if the new exponent already exists in the temporary arrays
                boolean found = false;
                for (int k = 0; k < currentSize; k++) {
                    if (tempExponents[k] == newExponent) {
                        tempCoefficients[k] += newCoefficient;
                        found = true;
                        break;
                    }
                }

                // Step 4: If the exponent does not exist, add the new term to the temporary arrays
                if (!found) {
                    tempCoefficients[currentSize] = newCoefficient;
                    tempExponents[currentSize] = newExponent;
                    currentSize++;
                }
            }
        }

        // Step 5: Create the resulting polynomial arrays without unused elements
        double[] resultCoefficients = new double[currentSize];
        int[] resultExponents = new int[currentSize];

        for (int k = 0; k < currentSize; k++) {
            resultCoefficients[k] = tempCoefficients[k];
            resultExponents[k] = tempExponents[k];
        }

        return new Polynomial(resultCoefficients, resultExponents);
	}

	public Polynomial(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        br.close();

        // Parsing the polynomial string from the file
        List<Double> coeffList = new ArrayList<>();
        List<Integer> expList = new ArrayList<>();

        int i = 0;
        while (i < line.length()) {
            // Parse the coefficient
            int sign = 1;
            if (line.charAt(i) == '-') {
                sign = -1;
                i++;
            } else if (line.charAt(i) == '+') {
                i++;
            }
            StringBuilder coeffSb = new StringBuilder();
            while (i < line.length() && (Character.isDigit(line.charAt(i)) || line.charAt(i) == '.')) {
                coeffSb.append(line.charAt(i));
                i++;
            }
            double coeff = sign * (coeffSb.length() > 0 ? Double.parseDouble(coeffSb.toString()) : 1);
            coeffList.add(coeff);

            // Parse the exponent
            int exp = 0;
            if (i < line.length() && line.charAt(i) == 'x') {
                i++;
                if (i < line.length() && line.charAt(i) == '^') {
                    i++;
                    StringBuilder expSb = new StringBuilder();
                    while (i < line.length() && Character.isDigit(line.charAt(i))) {
                        expSb.append(line.charAt(i));
                        i++;
                    }
                    exp = Integer.parseInt(expSb.toString());
                } else {
                    exp = 1;
                }
            }
            expList.add(exp);
        }

        this.coefficients = new double[coeffList.size()];
        this.exponents = new int[expList.size()];
        for (int j = 0; j < coeffList.size(); j++) {
            this.coefficients[j] = coeffList.get(j);
            this.exponents[j] = expList.get(j);
        }
    }

    public void saveToFile(String fileName) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(fileName));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < coefficients.length; i++) {
            if (coefficients[i] > 0 && i > 0) {
                sb.append("+");
            }
            sb.append(coefficients[i]);
            if (exponents[i] != 0) {
                sb.append("x");
                if (exponents[i] != 1) {
                    sb.append("^").append(exponents[i]);
                }
            }
    	}

        bw.write(sb.toString());
        bw.close();
    }

}