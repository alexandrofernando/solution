import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * This program determine if two cities are connected.
 */
public class Connected {
	public static final String DELIMITER = ",";

	public static void main(String[] args) {
		if (args == null || args.length != 3) {
			printUtilisation();
			System.exit(1);
		} else {
			String inputFilename = args[0];
			String cityOne = args[1];
			String cityTwo = args[2];

			try {
				Map<String, Set<String>> cityToNodeMap = parseIntoConnections(inputFilename);
				boolean result = isConnected(cityToNodeMap, cityOne, cityTwo);
				printFinalResult(result);
				System.exit(0);
			} catch (Exception e) {
				String message = e.getMessage();
				System.err.println("Something went wrong. " + message);
				e.printStackTrace();
				System.exit(2);
			}
		}
	}

	private static void printUtilisation() {
		
		System.out.println("This program takes 3 arguments");

	}
	

	// The key in the map are the cities that we have available.
	// The value is a set of string, a set of cities that this city is connected to

	private static void printFinalResult(boolean result) {
		if (result) {
			System.out.println("Yes");
		} else {
			System.out.println("No");
		}
	}



	private static Map<String, Set<String>> parseIntoConnections(String filename) throws IOException {
		Map<String, Set<String>> cityToNodeMap = new HashMap<String, Set<String>>();

		BufferedReader bufferedReader = null;
		try {
			Reader fileReader = new FileReader(filename);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine();
			while (line != null && !line.isEmpty()) {
				String[] cities = line.split(DELIMITER);
				String firstCity = cities[0].trim();
				String secondCity = cities[1].trim();

				Set<String> firstCityConnections = getConnections(cityToNodeMap, firstCity);
				Set<String> secondCityConnections = getConnections(cityToNodeMap, secondCity);
				firstCityConnections.add(secondCity);
				secondCityConnections.add(firstCity);

				line = bufferedReader.readLine();
			}
		} finally {
			if (bufferedReader != null) {
				bufferedReader.close();
			}
		}

		return cityToNodeMap;
	}

	//Default the connected cities 
	
	private static Set<String> getConnections(Map<String, Set<String>> map, String city) {
		if (!map.containsKey(city)) {
			map.put(city, new HashSet<String>());
		}
		return map.get(city);
	}

	// When there is a key-value pair (or key to set pair) then those cities are connected
	private static boolean isConnected(Map<String, Set<String>> cityToNodeMap, String cityOne, String cityTwo) {
		boolean isFound = cityOne.equals(cityTwo);
		if (cityToNodeMap.containsKey(cityOne) && cityToNodeMap.containsKey(cityTwo)) {
			
			// This will find the shortest path between two cities
			Queue<String> citiesYetToVisit = new LinkedList<String>();

			// We keep a set of the cities we have already visited. 
			
			Set<String> citiesVisited = new HashSet<String>();

			citiesYetToVisit.add(cityOne);

			while (!citiesYetToVisit.isEmpty() && !isFound) {
				String city = citiesYetToVisit.poll();
				isFound = city.equals(cityTwo);

				Set<String> possibleConnections = cityToNodeMap.get(city);
				for (String possibleCity : possibleConnections) {
					if (!citiesVisited.contains(possibleCity)) {
						citiesYetToVisit.add(possibleCity);
						citiesVisited.add(possibleCity);
					}
				}
			}
		}

		return isFound;
	}
}