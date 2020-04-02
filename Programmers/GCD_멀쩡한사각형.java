
public class Solution_62048 {
	public static void main(String[] args) {
		System.out.println(solution(8, 12));
	}

	public static long solution(int w, int h) {
		long gcd;
		long sum = (long) w * (long) h;

		if (w == h)
			gcd = w;
		else if (w > h)
			gcd = gcd(w, h);
		else
			gcd = gcd(h, w);

		return sum - gcd * ((w / gcd) + (h / gcd) - 1);
	}

	public static long gcd(int w, int h) {
		long big = (long) w;
		long small = (long) h;

		while (small != 0) {
			long temp = big % small;
			big = small;
			small = temp;
		}

		return big;
	}
}
