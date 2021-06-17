import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class M2_04_회의실배정 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static void main(String[] args) throws Exception {
		int N = Integer.parseInt(br.readLine());
		List<conf> reserv = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			reserv.add(new conf(s, e));
		}
		
		Collections.sort(reserv);
		conf c = reserv.get(0);
		int cnt = 1;
		
		for (int i = 1; i < reserv.size(); i++) {
			if (c.end <= reserv.get(i).start) {
				c = reserv.get(i);
				cnt++;
			}
		}
		
		System.out.println(cnt);
	}

	public static class conf implements Comparable<conf> {
		int start, end;

		public conf(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(conf o) {
			if (this.end == o.end)
				return this.start - o.start; // 2. 시작 시간 우선
			return this.end - o.end; // 1. 끝나는 시간 우선
		}
	}
}
