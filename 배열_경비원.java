package practice_0820;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_2564_경비원 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int width = Integer.parseInt(st.nextToken());
		int height = Integer.parseInt(st.nextToken());
		int store = Integer.parseInt(br.readLine());
		int[] point = new int[store + 1];
		for (int i = 0; i <= store; i++) {
			st = new StringTokenizer(br.readLine());
			int direction = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			switch (direction) {
			case 1:
				point[i] = value;
				break;
			case 2:
				point[i] = 2 * width + height - value;
				break;
			case 3:
				point[i] = 2 * (width + height) - value;
				break;
			case 4:
				point[i] = width + value;
				break;
			}
		}

		int sum = 0;
		for (int i = 0; i < store; i++) {
			int temp = Math.abs(point[store] - point[i]);
			sum += (temp > (width + height)) ? 2 * (width + height) - temp : temp;
		}
		System.out.println(sum);
	}

}
