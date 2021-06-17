import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class M1_02_어설픈모내기 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());
		int[] result = new int[height];
		
		for (int i = 0; i < height; i++) {
			String s = br.readLine();
			for (int j = 0; j < width; j++) {
				result[i] += s.charAt(j) - '0';
			}
		}
		
		for(int r : result) {
			System.out.println(r);
		}
	}
}
