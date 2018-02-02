package java_big_data;

public class Big_data {
	/**
	 * 
	 * @param data
	 * @return the enumerated list of data
	 */
	public int[] enumerate(String[] data) {
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		//get unique elements of data
		String[] temp_list = Arrays.stream(data).distinct().toArray(String[]::new);
		
		//fill the dictionary
		for (int i=0;i<temp_list.length;i++) {
			dictionary.put(temp_list[i], i);
		}
		//enumerate
		int[] result = new int[data.length];
		for (int i = 0; i < temp_list.length ; i++) {
			result[i] = dictionary.get(data[i]);
		}
		return result;
	}
	/**
	 * 
	 * @param data
	 * @return the enumerated list of data
	 */
	public int[] enumerate(int[] data) {
		Map<String, Integer> dictionary = new HashMap<String, Integer>();
		//get unique elements of data
		String[] temp_list = Arrays.stream(data).distinct().toArray(String[]::new);
		
		//fill the dictionary
		for (int i=0;i<temp_list.length;i++) {
			dictionary.put(temp_list[i], i);
		}
		//enumerate
		int[] result = new int[data.length];
		for (int i = 0; i < temp_list.length ; i++) {
			result[i] = dictionary.get(data[i]);
		}
		return result;
	}
	
	
}
