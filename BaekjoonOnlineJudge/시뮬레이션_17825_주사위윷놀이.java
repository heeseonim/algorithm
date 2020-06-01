import java.io.*;
import java.util.*;

public class 시뮬레이션_17825_주사위윷놀이 {
	public static int[] dice; // 주사위의 수를 저장할 배열
	public static Node[] list; // 각 위치의 값과 좌표를 저장한 배열
	public static boolean[] isHorse; // 위치에 말이 존재하는지 표시
	public static int[] position; // 각 말의 위치를 표시할 배열
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		dice = new int[10];
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 10; i++)
			dice[i] = Integer.parseInt(st.nextToken());

		list = new Node[43];
		// 가지고 있는 값, 다음 좌표
		for (int i = 0; i <= 40; i += 2)
			list[i] = new Node(i, i + 2);

		// 파랑색 좌표 추가
		list[10].blue = 11;
		list[20].blue = 21;
		list[30].blue = 31;

		// 13, 16, 19 -> 11, 13, 15 로 바꿈
		list[11] = new Node(13, 13);
		list[13] = new Node(16, 15);
		list[15] = new Node(19, 25);

		// 22, 24 -> 21, 23 으로 바꿈
		list[21] = new Node(22, 23);
		list[23] = new Node(24, 25);

		// 28, 27, 26 -> 31, 29, 27 로 바꿈
		list[31] = new Node(28, 29);
		list[29] = new Node(27, 27);
		list[27] = new Node(26, 25);

		// 30 -> 33 으로 바꿈
		list[25] = new Node(25, 33);
		list[33] = new Node(30, 35);
		list[35] = new Node(35, 40);

		list[42] = new Node(0, -1); // 도착좌표

		answer = Integer.MIN_VALUE;
		int[] choice = new int[10]; // 0~3 까지 주사위를 중복으로 저장할 배열
		perm(choice, 10, 0);

		System.out.println(answer);
	}

	public static void perm(int[] choice, int n, int k) {
		if (n == k) { // 배열 완성
			int sum = 0;
			isHorse = new boolean[43]; // 각 위치에 말의 존재를 표시할 배열
			position = new int[4]; // 각 말의 위치를 표시할 배열

			for (int i = 0; i < choice.length; i++) {
				// 현재 선택된 말, 주사위의 수
				int horse = choice[i];
				int cnt = dice[i];

				// 이미 도착지점에 있는 말이라면
				if (position[horse] == 42)
					continue; // 다음 말 진행

				int dest = moveHorse(horse, cnt); // 도착지 반환

				// 도착지에 말이 있다면 이 순열은 가능하지 않음(42번 제외), return
				if (dest < 42 && isHorse[dest])
					return;

				// 조건에 걸리지 않는다면 sum 계산
				sum += list[dest].value;

				// 배열 처리
				isHorse[position[horse]] = false;
				isHorse[dest] = true;
				position[horse] = dest; // 위치 업데이트
			}

			answer = answer < sum ? sum : answer;

			return;
		}

		// 중복순열
		for (int i = 0; i < 4; i++) {
			choice[k] = i;
			perm(choice, n, k + 1);
		}
	}

	// 도착지 반환
	public static int moveHorse(int horse, int cnt) {
		int cur = position[horse]; // 현재 말의 위치
		int next = 0;

		for (int i = 0; i < cnt; i++) {
			// 첫 출발이고, 그 점이 파란방향을 포함한 위치라면
			if (i == 0 && (cur == 10 || cur == 20 || cur == 30))
				next = list[cur].blue;
			else
				next = list[cur].red;

			if (next == 42) // 도착 칸으로 도착하면 주사위 수와 관계없이 이동 마침
				break;

			cur = next;
		}

		return next;
	}

	public static class Node {
		int value; // 가지고 있는 값
		int red;
		int blue; // 블루로 가는 길이 있을 경우에만 직접 대입

		public Node(int value, int red) {
			this.value = value;
			this.red = red;
		}
	}
}
