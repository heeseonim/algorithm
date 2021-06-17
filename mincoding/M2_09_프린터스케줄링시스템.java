import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class M2_09_프린터스케줄링시스템 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		List<Printer> list = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			list.add(new Printer(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}

		Collections.sort(list);
		int[] cur = new int[M];

		int curTime = 0;
		int cnt = 0;

		// 수정필요...
		while (cnt <= list.size()) {
			for (int i = 0; i < list.size(); i++) {
				if (!list.get(i).flag)
					continue;
				Printer p = list.get(i);
				int minTime = 0;
				for (int j = 0; j < M; j++) {
					if (curTime <= cur[j])
						cur[j] = 0;

					if (cur[j] <= p.start) {
						cur[j] = p.start + p.time;
						list.get(i).flag = false;
						cnt++;
						sb.append(j + 1).append('\n');
						if (minTime > cur[j])
							minTime = cur[j];
					}
					
				}
				curTime = minTime;
			}
		}

		System.out.println(sb);
	}

	public static class Printer implements Comparable<Printer> {
		int start, time;
		boolean flag;

		public Printer(int start, int time) {
			this.start = start;
			this.time = time;
			this.flag = true;
		}

		@Override
		public int compareTo(Printer o) {
			if (this.time == o.time)
				return this.start - o.start;
			return this.time - o.time; // 소요시간 오름차순 정렬
		}
	}
}
