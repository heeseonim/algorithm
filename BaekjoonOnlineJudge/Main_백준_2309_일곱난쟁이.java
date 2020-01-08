import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main_2309 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[] height = new int[9];
		int total = 0;
		for (int i = 0; i < 9; i++) {
			total += height[i] = Integer.parseInt(br.readLine());
		}

		ex: for (int i = 0; i < height.length; i++) {
			int temp = total;
			temp -= height[i];
			for (int j = i+1; j < height.length; j++) {
				int temp2 = temp;
				temp2 -= height[j];
				if (temp2 == 100) {
					height[i] = height[j] = 0;
					break ex;
				}
			}
		}
		
		Arrays.sort(height);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 2; i < height.length; i++) {
			sb.append(height[i]).append('\n');
		}
		System.out.println(sb);
	}
}
