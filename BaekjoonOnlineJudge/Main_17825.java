import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_17825 {

	// jump[index][0] = 해당 판 점수
	// jump[index][1~5] = 주사위가 나오면 이동할 위치
	// jump[33][6]
	public static int[][] jump = new int[][] { { 0, 1, 2, 3, 4, 5 }, { 2, 2, 3, 4, 5, 9 }, { 4, 3, 4, 5, 9, 10 },
			{ 6, 4, 5, 9, 10, 11 }, { 8, 5, 9, 10, 11, 12 }, { 10, 6, 7, 8, 20, 29 }, { 13, 7, 8, 20, 29, 30 },
			{ 16, 8, 20, 29, 30, 31 }, { 19, 20, 29, 30, 31, 32 }, { 12, 10, 11, 12, 13, 14 },
			{ 14, 11, 12, 13, 14, 15 }, { 16, 12, 13, 14, 15, 16 }, { 18, 13, 14, 15, 16, 17 },
			{ 20, 18, 19, 20, 29, 30 }, { 22, 15, 16, 17, 24, 25 }, { 24, 16, 17, 24, 25, 26 },
			{ 26, 17, 24, 25, 26, 27 }, { 28, 24, 25, 26, 27, 28 }, { 22, 19, 20, 29, 30, 31 },
			{ 24, 20, 29, 30, 31, 32 }, { 25, 29, 30, 31, 32, 32 }, { 26, 20, 29, 30, 31, 32 },
			{ 27, 21, 20, 29, 30, 31 }, { 28, 22, 21, 20, 29, 30 }, { 30, 23, 22, 21, 20, 29 },
			{ 32, 26, 27, 28, 31, 32 }, { 34, 27, 28, 31, 32, 32 }, { 36, 28, 31, 32, 32, 32 },
			{ 38, 31, 32, 32, 32, 32 }, { 30, 30, 31, 32, 32, 32 }, { 35, 31, 32, 32, 32, 32 },
			{ 40, 32, 32, 32, 32, 32 }, { 0, 32, 32, 32, 32, 32 } };

	public static int[] dice;
	public static int answer;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		dice = new int[10];
		for (int i = 0; i < 10; i++) {
			dice[i] = Integer.parseInt(st.nextToken());
		}

		// 중복순열 4^10 = 2^20 경우의 수
		for (int bit = 0; bit < (1 << 20); bit++) {
			check(bit);
		}

		System.out.println(answer);

	}

	public static void check(int bit) {
		int score = 0;
		int[] occupation = new int[35]; // 위치마다 말의 개수
		int[] pos = new int[4]; // 각 말의 현재 위치를 저장할 배열
		occupation[0] = 4; // 시작점에 4개의 말이 있음
		
		for (int turn = 0; turn < 10; turn++) {
			int horse = (bit >> (turn * 2)) & 0x3; // 이동할 말
			int current_pos = pos[horse]; // 이동할 말의 위치
			int next_pos = jump[current_pos][dice[turn]]; // 이동할 위치
			int get_score = jump[next_pos][0]; // 이동하면 얻을 점수

			// 처음이나 끝이 아닌데 말이 여러개인 경우 return
			if (occupation[next_pos] > 0 && next_pos != 0 && next_pos != 32) {
				return;
			} else {
				score += get_score;
				occupation[next_pos]++;
				occupation[current_pos]--;
				pos[horse] = next_pos;
			}
		}
		
		if (answer < score)
			answer = score;
	}
}
