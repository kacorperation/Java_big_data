package java_big_data;

import src.big_data_utilities.HashMap;
import src.big_data_utilities.Integer;
import src.big_data_utilities.Map;
import src.big_data_utilities.String;

public class Big_data {
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
	public int[] enumerate(Int[] data) {
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
