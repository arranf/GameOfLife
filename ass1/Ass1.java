import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Ass1{
	public static void main(String[] args)
	throws FileNotFoundException {
		//Relative path to file
		String fileName = "ages.txt";
		
		int lineCount = 0;
		File file = new File(fileName);
		Scanner counter = new Scanner(file);
		
		//Counting
		while (counter.hasNextInt() ) {
			counter.nextInt();
			lineCount++;
		}
		counter.close();
		System.out.println(fileName+"contains "+lineCount+" lines");	
		
		//New scanner due to reopening file
		Scanner scanner = new Scanner(file);
		
		int[] buffer = new int[lineCount];
		int total = 0;
		int medianPosition, lowerQuartilePosition, upperQuartilePosition;
		double median, lowerQuartile, upperQuartile;
		median = lowerQuartile = upperQuartile = 0;
		
		//If there is a middle value, set it as the median position otherwise find the highest value of the pair of median values 
		boolean isOdd = false;
		if (buffer.length%2 == 1)
		{
			isOdd = true;
		}
		medianPosition = (int)(Math.ceil(buffer.length/2.0));
		if (!isOdd)
		{
			medianPosition += 1;
		}
		System.out.println(medianPosition);
		lowerQuartilePosition = (int)(Math.ceil(medianPosition/2.0));
		System.out.println(lowerQuartilePosition);
		upperQuartilePosition = (int)(Math.ceil(buffer.length*0.75));
		
		for (int i = 0; i < lineCount; i++){
			buffer[i] = scanner.nextInt();
			
			//Check to see if previous value is the same, if so it's a duplicate as the data is ordered
			if (i > 0 && buffer[i-1] == buffer[i])
			{
				System.out.println(buffer[i] + " is a duplicate");
			}
			
			//Find median
			if (i == (medianPosition-1) && isOdd)
			{
				median = buffer[i];
			}
			else if (i == (medianPosition-1) && !isOdd)
			{
				median = ((double)(buffer[i]+buffer[i-1])/2);
			}
			
			//Find upperQuartile
			if (i == (upperQuartilePosition-1) && isOdd)
			{
				upperQuartile = buffer[i]; 
			}
			else if (i == (upperQuartilePosition-1) && !isOdd)
			{
				upperQuartile = ((double)(buffer[i]+buffer[i-1])/2);
			}
			
			//Find lowerQuartile
			if (i == (lowerQuartilePosition-1) && isOdd)
			{
				lowerQuartile = buffer[i]; 
			}
			else if (i == (lowerQuartilePosition-1) && !isOdd)
			{
				lowerQuartile = ((double)(buffer[i]+buffer[i-1])/2);
			}
			
			//Add to total
			total += buffer[i];
		}
		
		double mean = total/buffer.length;
		
		System.out.println("Total: "+total);
		System.out.println("Mean: "+mean);
		System.out.println("Median: "+median);
		System.out.println("UQ: "+upperQuartile);
		System.out.println("LQ: "+lowerQuartile);
			
	}
}