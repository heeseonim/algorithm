import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_1931_회의실배정 {
	
	public static class Point implements Comparable<Point> {
		int s;
		int e;
		
		public Point(int s, int e) {
			this.s = s;
			this.e = e;
		}
		
		@Override
		public int compareTo(Point o) {
			if(this.e == o.e) { // end 가 같을 경우 start 기준 정렬
				return this.s-o.s;
			}
			return this.e - o.e;
		}	
		
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		Point[] room = new Point[N];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			room[i] = new Point(s, e);
		}
		// end를 기준으로 정렬
		Arrays.sort(room);
		
		Point p = room[0]; //맨 처음값 (제일 먼저 끝나는 회의) 저장
		int count = 1; // 현재 회의수 = 1
		int i = 1;
		while(i < N) {
			if (room[i].s >= p.e) {
				p = room[i];
				count++;
			}
			i++;
		}
		System.out.println(count);
		
	}
}
