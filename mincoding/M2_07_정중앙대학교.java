import java.io.*;
import java.util.*;

public class M2_07_정중앙대학교 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		List<Integer> list = new ArrayList<>();
		list.add(500);
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			list.add(Integer.parseInt(st.nextToken()));
			list.add(Integer.parseInt(st.nextToken()));

			Collections.sort(list);

			System.out.println(list.get(list.size() / 2));
		}

	}
}
