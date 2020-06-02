import java.io.*;
import java.util.*;

public class 기출_시험감독 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		int[] people = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			people[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());

		long answer = N;
		for (int i = 0; i < people.length; i++) {
			people[i] -= A;
			if (people[i] > 0) {
				answer += people[i] / B;
				if (people[i] % B != 0)
					answer++;
			}
		}

		System.out.println(answer);
	}
}
