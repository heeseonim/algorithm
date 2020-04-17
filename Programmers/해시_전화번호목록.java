import java.util.*;

public class 해시_전화번호목록 {
	public static void main(String[] args) {
		System.out.println(solution(new String[] { "12", "123", "1235", "567", "88" }));
	}

	public static boolean solution(String[] phone_book) {
		Set<String> set = new HashSet<>();
		for (String p : phone_book) {
			set.add(p);
		}
		for (String p : phone_book) {
			for (String s : set) {
				if (s.equals(p))
					continue;
				if (s.startsWith(p))
					return false;
			}
		}

		return true;
	}
}
