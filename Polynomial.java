public class Polynomial{
	private double[] coefficients;
	
	public Polynomial(){
		this.coefficients = new double[1];
		this.coefficients [0] = 0;
	}

	public Polynomial(double[] coefficients){
		this.coefficients = coefficients.clone();
	}

	public Polynomial add(Polynomial arg){
		int maxLength = Math.max(this.coefficients.length, arg.coefficients.length);
        	double[] resultCoefficients = new double[maxLength];

		for (int i = 0; i < maxLength; i++) {
            		double thisCoeff;
			if (i < this.coefficients.length) {
    			thisCoeff = this.coefficients[i];
			} else {
    				thisCoeff = 0;
			}

			double otherCoeff;
			if (i < arg.coefficients.length) {
    				otherCoeff = arg.coefficients[i];
			} else {
    				otherCoeff = 0;
			}
            		resultCoefficients[i] = thisCoeff + otherCoeff;
        	}
		return new Polynomial(resultCoefficients);
	}

	public double evaluate(double x) {
        	double result = 0;
        	for (int i = 0; i < coefficients.length; i++) {
            	result += coefficients[i] * Math.pow(x, i);
        	}
       		return result;
    	}

	public boolean hasRoot(double x) {
        	return evaluate(x) == 0;
    	}

}