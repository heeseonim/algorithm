import java.io.*;
import java.util.*;

public class M3_01_사랑의스튜디오 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		
		User[] arr = new User[N];
		for (int i = 0; i < N; i++) {
			arr[i] = new User(i, 0);
		}
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				int cur = Integer.parseInt(st.nextToken());
				if (cur == 1) {
					arr[j].score++;
				}	
			}
		}
		
		Arrays.sort(arr);
		sb.append(arr[N-1].num).append(' ').append(arr[0].num);
		System.out.println(sb);		
	}
	
	public static class User implements Comparable<User>{
		int num, score;

		public User(int num, int score) {
			this.num = num;
			this.score = score;
		}

		@Override
		public int compareTo(User o) {
			return this.score - o.score;
		}
	}
}
