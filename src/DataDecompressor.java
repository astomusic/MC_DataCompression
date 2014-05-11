import java.io.IOException;


public class DataDecompressor {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Utilities util = new Utilities();
		String hexString = util.fileToHexString("05g-a.next");
		String differDecomperssedHexString = util.diffDecomperssor(hexString);
		
		System.out.println(differDecomperssedHexString);
		
		util.hexStringToFile("05g-d.next", differDecomperssedHexString);
	}

}
