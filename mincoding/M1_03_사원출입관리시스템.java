import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class M1_03_사원출입관리시스템 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main(String[] args) throws Exception {
		int n = Integer.parseInt(br.readLine());
		HashMap<Integer, String> user = new HashMap<>();
		HashMap<Integer, Boolean> check = new HashMap<>();
		
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int type = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			
			if (type == 1) {
				String name = st.nextToken();
				if (!user.containsKey(num)) {
					user.put(num, name);
					System.out.println(num + " OK");
				} else
					System.out.println(num + " ERROR");
			} else {
				if (!user.containsKey(num)) {
					System.out.println(num + " ERROR");
					continue;
				}
				
				if (check.get(num) == null) { // 처음
					check.put(num, true);
					System.out.println(num + " " + user.get(num) + " ENTER");
				} else {
					if (check.get(num)) { // true
						check.put(num, false);
						System.out.println(num + " " + user.get(num) + " EXIT");
					} else { // false
						check.put(num, true);
						System.out.println(num + " " + user.get(num) + " ENTER");
					}
				}
			}
		}
	}
}
