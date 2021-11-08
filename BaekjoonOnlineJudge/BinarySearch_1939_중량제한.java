import java.io.*;
import java.util.*;

public class Main {
	public static int N, M, start, end;
	public static long answer, max;
	public static List<Point>[] adjList;
	
	public static void main(String[] args) throws Exception {
		// 이분탐색
		// 1 ~ max 값 사이에서 탐색 시 mid 이상을 만족하는 최대 중량 찾기
		// 만들 수 없는 경우는 없음
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		adjList = new ArrayList[N+1];
		for (int i = 1; i <= N; i++) {
			adjList[i] = new ArrayList<>();
		}
		max = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			long val = Long.parseLong(st.nextToken());
			adjList[x].add(new Point(y, val));
			adjList[y].add(new Point(x, val));
			max = Math.max(max, val);
		}
		st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		answer = 0;
		binarySearch(0, max);
		System.out.println(answer);
	}
	
	public static void binarySearch(long s, long e) {
		long left = s;
		long right = e; // [] 양쪽 값 모두 포함구간
		
		while(left <= right) {
			long mid = (left + right) >> 1;
			if (bfs(mid)) { // mid 보다 모두 큰 값의 중량을 가지고 있다면
				left = mid + 1; // 최댓값 탐색
				answer = mid; // 답에 모두 포함되므로, mid 값 저장
			} else {
				right = mid - 1;
			}
		}
	}
	
	public static boolean bfs(long mid) {
		Queue<Point> q = new LinkedList<>();
		boolean[] visited = new boolean[N+1];
		q.add(new Point(start, 0));
		visited[start] = true;
		
		while(!q.isEmpty()) {
			Point cur = q.poll();
			if (cur.num == end) { // 끝에 도달했으면 종료
				return true;
			}
			
			for(Point next : adjList[cur.num]) {
				if (visited[next.num])
					continue;
				if (next.val >= mid) { // mid 와 같거나, 보다 크다면
					visited[next.num] = true;
					q.add(next);
				}
			}
		}
		
		return false;
	}
	
	public static class Point {
		int num;
		long val;
		
		public Point(int num, long val) {
			this.num = num;
			this.val = val;
		}
	}
}
