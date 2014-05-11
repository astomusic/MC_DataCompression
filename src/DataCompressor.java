import java.io.IOException;
import java.util.Comparator;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class DataCompressor {

	public static void main(String[] args) throws IOException {

		//Map<String, Integer> testMap = new TreeMap<String, Integer>();
		Utilities util = new Utilities();
		String hexString = util.fileToHexString("05g.next");
		String differComperssedHexString = util.diffComperssor(hexString);
		
		System.out.println(differComperssedHexString);
		
		util.hexStringToFile("05g-a.next", differComperssedHexString);
		
//		r[i / 2] = (byte) Integer.parseInt(resultHex, 16);
//		System.out.format("%02X ", r[i / 2]);
//		test = testMap.get(resultHex);
//		testMap.put(resultHex, test == null ? 1 : test + 1);
		
//		counter++;
//		if (counter % 256 == 0 && i != 0) {
//			System.out.println();
//		}
		// test = testMap.get(hexNumber);
		// testMap.put(hexNumber, test == null ? 1 : test + 1);


		// System.out.println("["+counter+"바이트를 읽어서 출력]");
		//
		// System.out.println("tcount:"+tcount);
		// System.out.println("result:"+ result.length());
		// //System.out.println(result);
		// System.out.println("mapsize:"+testMap.size());
		// System.out.println("sortbykey:"+testMap);
		// System.out.println("sortbyvalue"+entriesSortedByValues(testMap));

	}
	
	static <K, V extends Comparable<? super V>> SortedSet<Map.Entry<K, V>> entriesSortedByValues(
			Map<K, V> map) {
		SortedSet<Map.Entry<K, V>> sortedEntries = new TreeSet<Map.Entry<K, V>>(
				new Comparator<Map.Entry<K, V>>() {
					@Override
					public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
						return e2.getValue().compareTo(e1.getValue());
					}
				});
		sortedEntries.addAll(map.entrySet());
		return sortedEntries;
	}


}
