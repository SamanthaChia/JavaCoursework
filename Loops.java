// Name: Samantha Chia Eileen 
// Student Number: 190428594
import java.util.Random;

public class Loops {
	
			//loop until n is equal to m
			private static void loop1() {
				int m = 1;
				int n = m + 2;
				while (n > m) {
					System.out.println("In loop.");
					n--;
				}
				System.out.println("Out of loop.");
			}
			/*
			* PROBLEM: the loop is infinite, once started it does not stop.
			* DIAGNOSIS: m is decremented in the loop, meaning that m gets smaller while n stays the same.
			  The effect that the guard is always true (n is always bigger than m), so the loop once started will not stop 
			* CORRECTION: decrement n instead of m
			*/


					/*************************************************************/	
	
	
	
	
		//loop 5 times
		public static void loop2() {
			int i = 0;
			while(i < 5){
				System.out.println("In loop "+i);
				i++;
			}
			System.out.println("Out of loop");
		}
		/*
		 * PROBLEM:	The loop is infinite, once it starts it will be stuck in loop 0.
		 * DIAGNOSIS: Because without brackets after a while loop, the code will only execute the first line after the while loop.
		 *  		  Thus being stuck in an infinite loop because right after the while loop you only print the statement without
		 * 			  increasing the value of i.
		 * CORRECTION: Place an opening bracket at the start of while loop and place a closing bracket right after "i++;" 
	 	*/
	
	
					/*************************************************************/	
	
	
	
	
	
		/*
		  Draws a trapezoid (American English)/trapezium (British English) using asterisks, the shape has 7 lines, the top line has 5 stars, the next row 1 more (ie 6), rows increase by 1 until the bottom row has 12 stars as follows:
		            ******
					*******
					********
					*********
					**********
					***********
					************			
		*/
		private static void loop3() {
			int k = 7;
			int m = 13;
			int y = 6;
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < m; j++) {
					if (j<y) System.out.print("*");
				}
				System.out.println();
				// y++;
			}
		}

		private static void loop3Solution() {
			int k = 7;
			int m = 13;
			int y = 6;
			for (int i = 0; i < k; i++) {
				for (int j = 0; j < m; j++) {
					if (j<y+i) System.out.print("*");
				}
				System.out.println();
				//y++; DELETE ME
			}
		}
			/* 
			 * PROBLEM: When the code is ran the stars per row are not increasing by 1, they repeat in rows consisting of 6
			 * 			and in columns of 7.
			 * DIAGNOSIS: after each row, the '*' do not increase by 1, because the y value is not increasing. 
			 * CORRECTION: in the if statement where j < y, change it to j<y+i
			*/		
	
	
					/*************************************************************/	



	
	
			//loop until x is equal to y
			private static void loop4(){
				Random r = new Random();
				int x = 0;
				int y = 10;
				for (;;){
					x = r.nextInt(y);
					if (x==y){
						break;
					}
					System.out.println("x = "+x+" and y = "+y);
				}
				System.out.println("out of loop");
			}

			private static void loop4Solution(){
				Random r = new Random();
				int x = 0;
				int y = 10;
				for (;;){
					x = r.nextInt(y+1);
					System.out.println("x = "+x+" and y = "+y);
					if (x==y){
						break;
					}
					System.out.println("x = "+x+" and y = "+y);
				}
				System.out.println("out of loop");
			}
			/*
			 * PROBLEM: The x value never reaches 10 and it will keep looping forever.
			 * DIAGNOSIS: The value in nextInt is the value of y, so it will select a random value from 0 to 9 and never reach 10.
			 * 				In order to reach 10, need to increase the y value in r.nextInt(y) by 1.
			 * CORRECTION: Instead of putting x = r.nextInt(y), change it to x=r.nextInt(y+1)
			 */
			  
			  
			  
					/*************************************************************/	
	


	
	
		/* print the first 52 Fibonacci numbers
		   NOTE: Fibonacci numbers start 0, 1 and after this the next number is the sum of the previous two
		   hence 0, 1, 1 (=0+1), 2 (=1+1), 3 (=1+2), 5, 8, 13...
		*/

		public static void loop5() {
			int fibNext = 0;
			int fib1 = 0; 
			int fib2 = 1;
			System.out.println(fib1);
			System.out.println(fib2);
			for(int i = 0; i <50; i++){ 
				fibNext = fib1 + fib2;
				System.out.println(fibNext);
				fib1 = fib2;
				fib2 = fibNext;	
			}
		}

		public static void loop5Solution() {
			long fibNext = 0;
			long fib1 = 0; 
			long fib2 = 1;
			System.out.println(fib1);
			System.out.println(fib2);
			for(int i = 0; i <= 50; i++){ 
				fibNext = fib1 + fib2;
				System.out.println(fibNext);
				fib1 = fib2;
				fib2 = fibNext;	
			}
		}
		/*
		  * PROBLEM: The value printed eventually becomes negative.
		  * DIAGNOSIS: By using int as the datatype to store the values, it has a max range of 2147483647 then any value more than 
		  				that will be turned into a negative value.
		  * CORRECTION: instead of using int as the datatype for fibNext, fib1 and fib2, change it to the long datatype.
		*/

		
					/*************************************************************/	
	
	
	
		
			//print a vertical line of 10 stars
			public static void loop6() {
				for (int i = 0; i < 10; i++) {
					System.out.println("*");
				}
			}

			/*
			 * PROBLEM: The end results only printed one star 
			 * DIAGNOSIS: the semicolon is outside the for loop, it being outside will just run the loop once and end it there.
			 * CORRECTION: remove the semicolon after the for loop.
			 */
			 
			 
					/*************************************************************/	
	


			 
	
		public static void runLoops() {
			loop1();
			System.out.println();
			loop2();
			System.out.println();
			loop3Solution();
			System.out.println();
			loop4Solution();
			System.out.println();
			loop5Solution();
			System.out.println();
			loop6();
			System.out.println();
		}
	
		public static void main(String[] args) {
			runLoops();
	}
}