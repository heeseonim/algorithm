
public class 나머지한점 {
	public static void main(String[] args) {
		나머지한점 직 = new 나머지한점();
		System.out.println(직.solution(new int[][] { { 1, 4 }, { 3, 4 }, { 3, 10 } }));
	}

	public int[] solution(int[][] v) {
		int[] answer = new int[2];
		
		System.out.println(v[0][0] + "  " + v[1][0] + " " + (v[0][0] ^ v[1][0]));
		System.out.println(2 + "  " + v[2][0] + " " + (2 ^ v[2][0]));

		answer[0] = v[0][0] ^ v[1][0] ^ v[2][0];
		answer[1] = v[0][1] ^ v[1][1] ^ v[2][1];

		return answer;
	}
}
