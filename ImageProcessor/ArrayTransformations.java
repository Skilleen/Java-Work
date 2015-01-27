
public class ArrayTransformations {
	/**
	 * 
	 * Scott Killeen
	 * January 27, 2014
	 * 10093303
	 * This creates a program that will open an image and flip it either horizontally or vertically, as
	 * well as rotate it right and left.
	 */
	public static void main(String args[]){
		
	}
	public static void horizontalFlip(int pixels[][]){
		for (int i = 0; i < pixels.length; i++) {
			for(int j=0;j<(pixels[0].length/2);j++){
				int temp=pixels[i][j];
				pixels[i][j]=pixels[i][pixels[0].length-j-1];
				pixels[i][pixels[0].length-j-1]=temp;

		}
		}
	}
	public static void verticalFlip(int pixels[][]){
		for(int i=0;i<(pixels.length/2);i++){
			int temp[]=pixels[i];
			pixels[i]=pixels[pixels.length-i-1];
			pixels[pixels.length-i-1]=temp;
		}
		
	}
	public static int[][] rotateRight(int pixels[][]){
		int rows = pixels.length;
		int cols = pixels[0].length;
		int rotate[][]=new int[cols][rows];
		for (int i = 0; i < rows; i++) {
			for(int j=0; j<cols; j++){
				rotate[j][rows-1-i] = pixels[i][j];			      					    		
			}
		}
		return rotate;
	}
	public static int[][] rotateLeft(int pixels[][]){
		int rows = pixels.length;
		int cols = pixels[0].length;
		int rotate[][]=new int[cols][rows];
		for (int i = 0; i < rows; i++) {
			for(int j=0; j<cols; j++){
				rotate[cols-1-j][i] = pixels[i][j];			      					    		
			}
		}
		return rotate;
	}
}
