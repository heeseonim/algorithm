import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution_SWEA_7701_염라대왕의이름정렬 {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			Set<String> set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				set.add(br.readLine());
			}
			
			PriorityQueue<String> pq = new PriorityQueue<>(comparator);
			Iterator<String> iterator = set.iterator();
			while(iterator.hasNext()) {
				pq.add(iterator.next());
			}
			
			System.out.println("#" + tc);
			while(!pq.isEmpty()) {
				System.out.println(pq.poll());
			}
			
		}
	}
	
	public static Comparator<String> comparator = new Comparator<String>() {
		
		@Override
		public int compare(String o1, String o2) {
			if (o1.length() == o2.length()) {
				return o1.compareTo(o2);
			}
			return o1.length() - o2.length();
		}
	};
	
}
