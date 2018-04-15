package com.codifyd;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class DataSorter {

	public String sort(String input) {
	
		//validateInput(input);
		
		String[] splitted = input.split(" ");
		StringBuilder output = new StringBuilder();

		// TreeMap with reverse to sort data in desc
		Map<BigDecimal, List<String>> map = new TreeMap<BigDecimal, List<String>>(Collections.reverseOrder());
		List<String> values;
		for (int i = 0; i < splitted.length; i++) {
			String[] temp = splitted[i].split("=");
			BigDecimal key = new BigDecimal(temp[1]);

			if (map.containsKey(key)) {
				map.get(key).add(temp[0]);
			} else {
				values = new LinkedList<String>();
				values.add(temp[0]);
				map.put(key, values);
			}
		}

		// Converting data into output format
		for (BigDecimal key : map.keySet()) {
			Iterator<String> lit = ((LinkedList<String>) map.get(key)).descendingIterator();
			while (lit.hasNext()) {
				output.append(lit.next()).append("=").append(key).append(";");
			}
		}
		// remove extra ; and return
		return output.toString().substring(0, output.toString().length() - 1);
	}
	
	/*private void validateInput(String input) {
		//if input is null return null
		if (null == input) {
			throw new RuntimeException("Input not provided");
		}

	}*/

}
