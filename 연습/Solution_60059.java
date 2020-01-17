public class Solution_60059 {

	// 2020카카오공채 프로그래머스 60059 자물쇠와 열쇠
	public static void main(String[] args) {
		System.out.println(
				solution(new int[][] { { 1, 0 }, { 0, 1 } }, new int[][] { { 1, 1, 1 }, { 0, 1, 1 }, { 1, 0, 1 } }));
	}

	public static boolean solution(int[][] key, int[][] lock) {
		int size = key.length * 2 + lock.length;
		int[][] map = new int[size][size];

		for (int i = 0; i < lock.length; i++)
			for (int j = 0; j < lock.length; j++)
				map[i + key.length][j + key.length] = lock[i][j];

		int cnt = 0; // 자물쇠의 홈의 개수
		for (int i = 0; i < lock.length; i++)
			for (int j = 0; j < lock.length; j++)
				if (lock[i][j] == 0)
					cnt++;

		for (int i = 0; i < 4; i++) {
			rotate(key);
			if (search(key, map, cnt)) // 발견하면 바로 종료하기
				return true;
		}

		return false;
	}

	// key배열 시계방향으로 90도 회전
	public static void rotate(int[][] key) {
		int[][] copyKey = new int[key.length][key.length];

		for (int i = 0; i < key.length; i++)
			for (int j = 0; j < key.length; j++)
				copyKey[j][key.length - i - 1] = key[i][j];

		for (int i = 0; i < key.length; i++) {
			key[i] = copyKey[i].clone();
		}
	}

	// key 사이즈만큼 map을 돌면서 검사
	public static boolean search(int[][] key, int[][] map, int cnt) {
		int temp = 0;

		for (int i = 0; i < map.length; i++) {
			ex: for (int j = 0; j < map.length; j++) {
				temp = 0;
				for (int one = 0; one < key.length; one++) {
					for (int two = 0; two < key.length; two++) {
						if (map[i][j] == 0) { // 자물쇠의 홈일 경우
							if (key[one][two] == 0)
								continue ex;
							else {
								temp++;
							}
						} else { // 자물쇠가 돌기일 경우
							if (key[one][two] == 1)
								continue ex;
						}
					}
				}
			}
		}
		
		System.out.println(temp);

		if (temp == cnt)
			return true;
		else
			return false;
	}
}
