import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Utilities {
	public void hexStringToFile(String filename, String result) throws IOException {
		FileOutputStream fos = new FileOutputStream(filename);
		int resultlength = result.length();
		int filesize = (int) Math.round((double)resultlength/2);
		int j=0;
		
		byte[] resultByte = new byte[filesize];
		String resultHex;
		for(int i=0 ; i<filesize ; i++){
			j = i*2;
			if( j+2 > resultlength) {
				resultHex = result.substring(resultlength-1, resultlength);
				resultHex = resultHex+"0";
				resultByte[i] = hexStringToByte(resultHex);
			} else {
				resultHex = result.substring(j, j+2);
				resultByte[i] = hexStringToByte(resultHex);
			}
			
			//System.out.format("%02X", resultByte[i]);
		}
		fos.write(resultByte);
		fos.close();
	}
	// byte to hex String
		public static String byteToHexString(byte b) {
			String hexString;
			hexString = String.format("%02X", 0xff & b);
			return hexString;
		}
		
		//hexString to byte
		public static byte hexStringToByte(String hexString) {
			byte hexByte;
			hexByte = (byte) Integer.parseInt(hexString, 16);
			return hexByte;
		}
		
			
		public static Integer hexStringToHexInteger(String hexString) {
			int hexInteger;
			hexInteger = Integer.parseInt(hexString, 16);
			return hexInteger;
		}
		
		// hex Integer to hex String
		public static String hexIntegerToHexString(Integer hexInteger) {
			String hexString;
			hexString = String.format("%02X", 0xff & hexInteger);
			return hexString;	
		}
		
		//file to hex String
		public String fileToHexString(String fileName) throws IOException {
			int length;
			byte[] b = new byte[65536];
			String hexString = "";

			FileInputStream fileinputstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fileinputstream);

			while ((length = in.read(b)) > 0) {
				for (int i = 0; i < length; i++) {
					hexString += byteToHexString(b[i]);
				}
			}

			in.close();

			return hexString;

		}
		//값차이로 압축
		public String diffComperssor(String HexString) {
			int diff;
			Integer hexInteger1, hexInteger2;
			String hexString1, hexString2;
			String diffComperssedHexString = "0" + HexString.substring(0, 2);

			for (int i = 0; i < HexString.length()-2; i = i + 2) {
				hexString1 = HexString.substring(i, i+2);
				hexString2 = HexString.substring(i+2, i+4);
				hexInteger1 = hexStringToHexInteger(hexString1);
				hexInteger2 = hexStringToHexInteger(hexString2);
				diff = hexInteger1 - hexInteger2;
				if (diff < 8 && diff > -8) {
					diffComperssedHexString += String.format("%01X", 0xff & (diff + 8));
				} else {
					diffComperssedHexString += 0;
					diffComperssedHexString += hexString2;
				}

			}		

			return diffComperssedHexString;

		}
		
		public String diffDecomperssor(String HexString) {
			String diffDecomperssedHexString = "";
			Integer hexInteger1,hexInteger2;
			String hexString1;
			String checker;
			
			for (int i = 0; i < HexString.length(); ) {
				checker = HexString.substring(i, i+1);
				if(checker.equals("0")) {
					hexString1 = HexString.substring(i+1, i+3);
					diffDecomperssedHexString += hexString1;
					i=i+3;
				} else {
					hexInteger1 = hexStringToHexInteger(checker);
					int resultLength = diffDecomperssedHexString.length();
					hexString1 = diffDecomperssedHexString.substring(resultLength-2, resultLength);
					hexInteger2 = hexStringToHexInteger(hexString1);
					hexInteger1 = hexInteger2 - (hexInteger1 - 8);
					diffDecomperssedHexString += hexIntegerToHexString(hexInteger1);
					i++;
				}	
			}
			
			return diffDecomperssedHexString;
			
		}
}
