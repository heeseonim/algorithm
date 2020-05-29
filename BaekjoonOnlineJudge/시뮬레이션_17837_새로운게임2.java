import java.io.*;
import java.util.*;

public class 시뮬레이션_17837_새로운게임2 {
	public static int[][] dir = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
	public static int N;
	public static List<Integer>[][] list;
	public static int[][] map;
	public static info[] horses;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken()); // 체스판 크기
		int K = Integer.parseInt(st.nextToken()); // 말의 개수

		map = new int[N][N]; // 0 흰색 1 빨강 2 파랑
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		// 위치마다 리스트 존재
		list = new LinkedList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				list[i][j] = new LinkedList<Integer>();

		horses = new info[K + 1];
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken()) - 1;
			int y = Integer.parseInt(st.nextToken()) - 1;
			int d = Integer.parseInt(st.nextToken()) - 1;
			list[x][y].add(i);
			horses[i] = new info(i, x, y, d);
		}

		int turn = 1;
		while (turn <= 1000) {
			// 1 부터 K 까지 순서대로 말 이동
			for (int i = 1; i <= K; i++) {
				info cur = horses[i];

				int nx = cur.x + dir[cur.d][0];
				int ny = cur.y + dir[cur.d][1];

				// 방향 벗어나는 경우와 파랑 먼저 체크
				if (!isIn(nx, ny) || map[nx][ny] == 2) { // 파랑
					cur.d = changeDir(cur.d);
					nx = cur.x + dir[cur.d][0];
					ny = cur.y + dir[cur.d][1];
				}

				// 다시 체크 - 파랑은 움직일 필요가 없기 떄문에 제외
				if (isIn(nx, ny) && map[nx][ny] != 2) {
					moveList(cur, nx, ny);
					if (list[nx][ny].size() >= 4) { // 이동을 마친 후 크기 확인
						System.out.println(turn);
						return;
					}
				}
			}

			turn++;
		}

		System.out.println(-1);

	}

	public static void moveList(info cur, int nx, int ny) {
		List<Integer> curList = list[cur.x][cur.y]; // 현재 리스트
		List<Integer> nextList = list[nx][ny]; // 이동할 위치의 리스트

		int index = curList.indexOf(cur.num); // 현재 말의 리스트에서의 인덱스

		if (map[nx][ny] == 0) // 흰색
			for (int i = index; i < curList.size(); i++) {
				nextList.add(curList.get(i)); // 새 리스트에 더해주기
				updateHorses(curList.get(i), nx, ny); // 각 말의 위치 업데이트
			}
		else // 빨강
			for (int i = curList.size() - 1; i >= index; i--) {
				nextList.add(curList.get(i));
				updateHorses(curList.get(i), nx, ny);
			}

		// 리스트 부분 삭제
		removeList(curList, index, curList.size() - 1);
	}

	public static void removeList(List<Integer> list, int from, int to) {
		for (int i = to; i >= from; i--)
			list.remove(i);
	}

	// 말 업데이트
	public static void updateHorses(int i, int x, int y) {
		horses[i].x = x;
		horses[i].y = y;
	}

	public static int changeDir(int d) {
		if (d % 2 == 0)
			return d + 1;
		else
			return d - 1;
	}

	public static boolean isIn(int r, int c) {
		return r >= 0 && r < N && c >= 0 && c < N;
	}

	public static class info {
		int num;
		int x, y;
		int d;

		public info(int num, int x, int y, int d) {
			this.num = num;
			this.x = x;
			this.y = y;
			this.d = d;
		}
	}
}
