import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeMap;

public class Main_4358 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		TreeMap<String, Integer> tree = new TreeMap<>();

		String species;
		int total = 0;
		while ((species = br.readLine()) != null) {
			tree.put(species, tree.getOrDefault(species, 0) + 1);
			total++;
		}
		
		for(String s : tree.keySet()) {
			System.out.print(s + " ");
			System.out.printf("%.4f", ((double)tree.get(s)/total)*100);
			System.out.println();
		}
	}
}
