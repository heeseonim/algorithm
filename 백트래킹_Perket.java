import java.util.Scanner;

public class Main_1127 {
	public static int[][] materials;
	public static int size;
	public static boolean[] isSelected;
	public static int min;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		size = sc.nextInt();
		materials = new int[size][2];
		isSelected = new boolean[size];

		for (int i = 0; i < size; i++) {
			materials[i][0] = sc.nextInt();
			materials[i][1] = sc.nextInt();
		}

		min = Integer.MAX_VALUE;
		makeFood(0);
		System.out.println(min);
		sc.close();
	}

	public static void makeFood(int index) {
		if (index == size) {
			int sSum = 1, bSum = 0, cnt = 0;
			for (int i = 0; i < size; i++) {
				if (!isSelected[i])
					continue;
				cnt++;
				sSum *= materials[i][0];
				bSum += materials[i][1];
			}
			if (cnt > 0) {
				int temp = Math.abs(sSum - bSum);
				if (temp < min)
					min = temp;
			}
			return;
		}

		isSelected[index] = true;
		makeFood(index + 1);

		isSelected[index] = false;
		makeFood(index + 1);
	}
}
