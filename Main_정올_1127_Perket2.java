import java.util.Scanner;

public class Main_정올_1127_Perket2 {
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
		makeFood(0, 1, 0, 0);
		System.out.println(min);
		sc.close();
	}

	public static void makeFood(int index, int sSum, int bSum, int count) {
		if (index == size) {
			if (count > 0) {
				int temp = Math.abs(sSum - bSum);
				if (temp < min)
					temp = min;
			}
			return;
		}

		makeFood(index + 1, sSum * materials[index][0], bSum + materials[index][1], count + 1);
		makeFood(index + 1, sSum, bSum, count);
	}
}
