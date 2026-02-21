package QuantityMeasurementApplication;
import java.util.*;
public class QuantityMeasurementApp {
	 static class Feet {
	        private final double value;
	        public Feet(double value) {
	            this.value = value;
	        }
	        @Override
	        public boolean equals(Object obj) {
	            if (this == obj) {
	                return true;
	            }
	            if (obj == null) {
	                return false;
	            }

	            if (this.getClass() != obj.getClass()) {
	                return false;
	            }
	            Feet other = (Feet) obj;
	            return Double.compare(this.value, other.value) == 0;
	        }
	    }
	 public static void main(String[] args) {

			Scanner scanner = new Scanner(System.in);

			try {
				System.out.print("Enter first value in feet: ");
				double value1 = scanner.nextDouble();

				System.out.print("Enter second value in feet: ");
				double value2 = scanner.nextDouble();

				Feet feet1 = new Feet(value1);
				Feet feet2 = new Feet(value2);

				System.out.println("Are they equal? " + feet1.equals(feet2));

			} 
			catch (InputMismatchException e) {
				System.out.println("Invalid input! Please enter numeric values only.");
			} 
			finally {
				scanner.close();
			}
		}
}
