
public class Solution_12919 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] { "jane", "Kim" }));
	}

	public static String solution(String[] seoul) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < seoul.length; i++) {
			if (seoul[i].equals("Kim")) {
				sb.append("김서방은 ").append(i).append("에 있다");
				return sb.toString();
			}
		}
		return null;
	}
}
