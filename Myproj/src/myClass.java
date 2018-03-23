
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.io.File;
import java.util.Comparator;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.Connection;  
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.stream.StreamSupport;

 class Rextester {
	 
	 public static double squareRootGuess(double x, double g)
	    {
	        if (Math.abs(Math.sqrt(x) - g) <= 0.001)
	        {
	            return g;
	        }
	        else
	        {
	            double ans = (g + (x/g) / 2);
	            return squareRootGuess(x, ans);
	        }
	    }
	 
	    public static double squareRoot(double x)
	    {
	        return squareRootGuess(x, x-1);
	    }
    public static void main(String[] argv) throws Exception {
    	
      //  System.out.println(squareRoot(125));
        System.out.println(squareRoot(4));

        Map<String, Integer> unsortMap = new HashMap<>();
        unsortMap.put("z", 10);
        unsortMap.put("b", 5);
        unsortMap.put("a", 6);
        unsortMap.put("c", 20);
        unsortMap.put("d", 1);
        unsortMap.put("e", 7);
        unsortMap.put("y", 8);
        unsortMap.put("n", 99);
        unsortMap.put("g", 50);
        unsortMap.put("m", 2);
        unsortMap.put("f", 9);

        System.out.println("Original...");
        System.out.println(unsortMap);

        // sort by keys, a,b,c..., and return a new LinkedHashMap
        // toMap() will returns HashMap by default, we need LinkedHashMap to keep the order.
        Map<String, Integer> result = unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));


        // Not Recommend, but it works.
        //Alternative way to sort a Map by keys, and put it into the "result" map
        Map<String, Integer> result2 = new LinkedHashMap<>();
        unsortMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(x -> result2.put(x.getKey(), x.getValue()));

        System.out.println("Sorted...");
        System.out.println(result);
        System.out.println(result2);
        List<Integer> list = Arrays.asList(1,2,6,-1,4,4,4,-1);
        Map<Object, Long> counts =   	list.stream().collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        System.out.println(counts);
        
//        List<String> words = Files.lines(path)
//        		.parallel()
//        		.flatMap(line -> Arrays.asList(line.split("\\b")).stream())
//        		.collect(Collectors.groupingBy(w -> w , Collectors.counting()))
//        		.entrySet().stream()
//        		.sorted(Comparator.comparing(Map.Entry<String , Long>::getValue).reversed())
//        		.limit(20)
//        		.map(Map.Entry::getKey)
//        		.collect(Collectors.toList());
//        		
        List<String> words = Files.lines(Paths.get("test.txt"))
        		.parallel()
        		.flatMap(line -> Arrays.asList(line.split("\\b")).stream())
        		.collect(Collectors.groupingBy(w -> w , Collectors.counting()))
        		.entrySet().stream()
        		.sorted(Comparator.comparing(Map.Entry<String , Long>::getValue).reversed())
        		.limit(20)
        		.map(Map.Entry::getKey)
        		.collect(Collectors.toList());
        System.out.println(words);	
        
        List<Integer> list_ = Arrays.asList(1,2,6,-1,4,4,4,-1);
        list_.stream()
        .collect(Collectors.groupingBy(w -> w , Collectors.counting()))
		.entrySet().stream()
		.sorted(Comparator.comparing(Map.Entry<Integer , Long>::getValue).reversed())
		.limit(20)
		.map(Map.Entry::getValue)
		.collect(Collectors.toList());
System.out.println(list_);	

Map<Object, Object> sortedMap =         list_.stream()
.collect(Collectors.groupingBy(w -> w , Collectors.counting()))
.entrySet().stream()
.sorted(Map.Entry.comparingByValue())
  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
System.out.println(sortedMap);

Map<Object, Object> sortedMap_ =         list_.stream()
.collect(Collectors.groupingBy(w -> w , Collectors.counting()))
.entrySet().stream()
.sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
  .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
System.out.println(sortedMap_);
System.out.println(Math.ceil(-82.4));
System.out.println(Math.pow(-6,2));

    }

}