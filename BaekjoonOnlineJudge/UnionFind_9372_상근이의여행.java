import java.io.*;
import java.util.*;

public class UnionFind_9372_상근이의여행 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			for (int i = 0; i < M; i++) {
				String temp = br.readLine();
			}
			// 최소 비행기 수 = MST 로 완성되는 간선 수 = 총 노드수 - 1
			System.out.println(N - 1);
		}
	}
}