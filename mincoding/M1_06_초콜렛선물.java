import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class M1_06_초콜렛선물 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	
	public static void main(String[] args) throws Exception {
		TreeSet<Character> ts = new TreeSet<>();
		String s = br.readLine();
		for (int i = 0; i < s.length(); i++) {
			ts.add(s.charAt(i));
		}
		
		for(Character c : ts) {
			sb.append(c);
		}
		
		System.out.println(sb);
	}
}
