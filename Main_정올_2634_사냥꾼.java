import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main_정올_2634_사냥꾼 {
	public static int M, N, L;
	public static int[] hunter;
	public static Point[] animal;
	public static int count, start;
	
	public static class Point implements Comparable<Point> {
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Point o) {
			return this.x - o.x;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken()); // 사대
		N = Integer.parseInt(st.nextToken()); // 동물
		L = Integer.parseInt(st.nextToken()); // 사정거리
		
		hunter = new int[M];
		animal = new Point[N];
		
		// 사대 입력
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			hunter[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(hunter);
		
		// 동물 좌표 입력
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			animal[i] = new Point(r, c);		
		}
		
		Arrays.sort(animal);
		
		count = 0;
		start = 0;
		for (int i = 0; i < N; i++) {
			calc(animal[i].x, animal[i].y);
		}
		
		System.out.println(count);
		
	}
	
	public static void calc(int r, int c) {
		for (int i = start; i < hunter.length; i++) {
			if (Math.abs(hunter[i]-r) + c <= L) { // 잡은 경우
				count++;
				start = i; // 다음 동물은 전 동물을 잡은 사냥꾼의 위치부터 시작
				return;
			} else { // 잡지 못한 경우
				if (r < hunter[i]) { // 잡을 수 없는 동물
					break; // 반복 멈추기
				}
			}
		}
	}
}
