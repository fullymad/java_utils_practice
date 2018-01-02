import java.util.*;
import java.io.*;

class JTest {

	private static final String zero = "Zero";
	private static final String ten = "Ten";
	private static final String hundred = "Hundred";
	private static final String units[] = {"Billion", "Million", "Thousand"};
	private static final String ones[] = {"", "One", "Two", "Three", "Four",
		"Five", "Six", "Seven", "Eight", "Nine"};
	private static final String tens[] = {"", "", "Twenty", "Thirty", "Forty",
		"Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
	private static final String tenToNineteen[] = {"Ten", "Eleven", "Twelve",
		"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
		"Nineteen"};
	private static final int divisors[] = {1000000000, 1000000, 1000};

	public static void printPath(Graph graph, String source,
			 String destination, boolean useDFS) throws Exception {
		String path = graph.getPath(source, destination, useDFS);

		if (path == null) {
			System.out.println("**There is no path from " + source +
					" to " + destination);
		}
		else {
			System.out.println("**Path from " + source + " to " + destination +
				": " + path);
		}
	}

	public static void pollPrint(Queue<String> queue) {
		String result = null;

		if (! queue.isEmpty()) {
			result = queue.poll();
			System.out.println("Got " + result + " from queue");
		}
		else {
			System.out.println("Nothing found in the queue");
		}
	}

	public static void peekPrint(Queue<String> queue) {
		String result = queue.peek();

		System.out.println("**Peeked " + result + " from queue");
	}

	public static void testGraph() {

		Node goldNode = null;
		Node silverNode = null;
		Node bronzeNode = null;
		Node aluminumNode = null;
		Node copperNode = null;
		Node micaNode = null;
		Node diamondNode = null;
		Node zincNode = null;
		Node funkyNode = null;

		try {
			boolean useDFS = false;
			Graph graph = new Graph();

			goldNode = graph.addNode("gold");
			silverNode = graph.addNode("silver");
			bronzeNode = graph.addNode("bronze");
			aluminumNode = graph.addNode("aluminum");
			copperNode = graph.addNode("copper");
			micaNode = graph.addNode("mica");
			diamondNode = graph.addNode("diamond");
			zincNode = graph.addNode("zinc");
			funkyNode = graph.addNode("funky");

			goldNode.addAdjacentNode(silverNode);

			bronzeNode.addAdjacentNode(aluminumNode);
			bronzeNode.addAdjacentNode(copperNode);
			bronzeNode.addAdjacentNode(copperNode);
			bronzeNode.addAdjacentNode(diamondNode);

			copperNode.addAdjacentNode(micaNode);
			micaNode.addAdjacentNode(diamondNode);
			micaNode.addAdjacentNode(aluminumNode);

			diamondNode.addAdjacentNode(copperNode);
			//newNode.removeAdjacentNode(copperNode);
			//newNode.addAdjacentNode(copperNode);
			//newNode.addAdjacentNode(null);

			System.out.println(graph.getNodes());

			//JTest.printPath(graph, "Bronze", "copper", useDFS);
			JTest.printPath(graph, "bronze", "copper", useDFS);
			JTest.printPath(graph, "bronze", "silver", useDFS);
			JTest.printPath(graph, "bronze", "diamond", useDFS);
			JTest.printPath(graph, "silver", "zinc", useDFS);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
		}
	}

	public static void testQueue() {
		try {
			Queue<String> strQueue = new Queue<String>(5);

			JTest.peekPrint(strQueue);

			strQueue.add("boston");
			strQueue.add("chicago");
			strQueue.add("miami");
			strQueue.add("sunnyvale");
			strQueue.add("cupertino");

			System.out.println(strQueue.toString());

			JTest.pollPrint(strQueue);
			strQueue.add("mountain view");

			JTest.pollPrint(strQueue);
			System.out.println(strQueue.toString());

			JTest.pollPrint(strQueue);
			System.out.println(strQueue.toString());

			JTest.pollPrint(strQueue);
			System.out.println(strQueue.toString());

			JTest.pollPrint(strQueue);
			System.out.println(strQueue.toString());

			strQueue.add("fremont");
			strQueue.add("los altos");

			JTest.pollPrint(strQueue);
			System.out.println(strQueue.toString());

			strQueue.add("san jose");
			strQueue.add("santa clara");
			strQueue.add("los gatos");
			System.out.println(strQueue.toString());

			JTest.peekPrint(strQueue);
			System.out.println(strQueue.toString());

			strQueue.add("san francisco");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
		}
	}

	public static void wordFreqInBook(String path, String word) {
		try {
			String nextWord, key;
			Integer value;
			HashMap<String, Integer> wordMap = new HashMap<String, Integer>();

			System.out.println("Path: " + path + " Word: " + word);
			Scanner sc = new Scanner(new File(path));

			while (sc.hasNext()) {
				nextWord = sc.next();

				Integer count = wordMap.getOrDefault(nextWord, 0);
				wordMap.put(nextWord, count + 1);
			}

			// Print all words and their frequency
			Set<String> set = wordMap.keySet();
			Iterator<String> it = set.iterator();
			while (it.hasNext()) {
				key = it.next();
				value = wordMap.get(key);

				System.out.println("Key: " + key + " Value: " + value);
			}

			Integer num = wordMap.getOrDefault(word, 0);
			System.out.println("Word " + word + " occurs " + num + " time(s)");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
		}
	}

	public static void printNumberAsPhrase(int number) throws Exception {
		ArrayList<String> phraseParts = new ArrayList<String>();

		if (number < 0) {
			System.out.println("ERROR: Negative number is not allowed");
		}
		else if (number == 0) {
			System.out.println("Zero");
		}
		else {
			System.out.println("Input number: " + number + "\n");

			// Print Billion, Million and Thousand parts
			for (int i = 0; i < divisors.length; i++) {
				int quotient = number / divisors[i];
				number = number % divisors[i];
				printUnit(quotient, units[i], phraseParts);
			}

			// Print the final Hundred/Ten/One part (like Two Hundred Forty)
			printUnit(number, "", phraseParts);

			Iterator<String> it = phraseParts.iterator();
			String prefix = "";

			while (it.hasNext()) {
				System.out.print(prefix + it.next());
				prefix = " ";
			}
		}
	}

	public static void printUnit(int number, String unit,
		ArrayList<String> phraseParts) throws Exception {
			if (number < 0 || number >= 1000) {
				throw new Exception(
					"printUnit needs a number >= 0 and < 1000");
			}

			if (number == 0) {
				return;
			}

			// Print the hundred part (if any)
			int quotient = number / 100;
			number = number % 100;

			if (quotient != 0) {
				phraseParts.add(ones[quotient] + " " + hundred);
			}

			// Print the last 2 digits 
			quotient = number / 10;
			number = number % 10;

			if (quotient != 0) {
				// Numbers from 11 - 19 have special phrases
				if (quotient == 1) {
					phraseParts.add(tenToNineteen[number]);
				}
				else {
					phraseParts.add(tens[quotient]);
					if (number != 0) {
						phraseParts.add(ones[number]);
					}
				}
			}
			else {
				if (number != 0) {
					phraseParts.add(ones[number]);
				}
			}

			if (! unit.equals("")) {
				phraseParts.add(unit);
			}
	}

    public static void main(String[] args) throws Exception {
		JTest.testGraph();

		//JTest.testQueue();

/*
		if (args.length != 2) {
			System.out.println(
				"Error: Input should specify file name and word");
		}
		else {
			JTest.wordFreqInBook(args[0], args[1]);
		}
*/

/*
		if (args.length != 1) {
			System.out.println(
				"Error: Input should specify a single number to print");
		}
		else {
			int number = Integer.parseInt(args[0]);
			JTest.printNumberAsPhrase(number);
			System.out.println("");
		}
*/
    }
}
