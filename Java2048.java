package my2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Java2048 {

	private static final int MAX = 4;

	private static final int MAX_MAX = MAX * MAX;

	private static final int INIT_SIZE = 2;

	// 数据矩阵
	private static int[][] matrix = new int[MAX][MAX];

	// 合并临时数组
	private static int[] current = new int[MAX];

	// 计数器
	private static int iCount = 0;

	// 按键操作
	private static char ch;

	// 得分
	private static int score = 0;

	public static void main(String[] args) throws IOException {

		String str = "";
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		init();
		print();
		while (b_up() || b_left() || b_down() || b_right()) {
			help();
			str = in.readLine();
			if (str.length() > 0) {
				ch = str.charAt(0);
			}
			else {
				ch = '0';
			}
			switch (ch) {
			case 'w':
				_up();
				break;
			case 'a':
				_left();
				break;
			case 's':
				_down();
				break;
			case 'd':
				_right();
				break;
			default:
				System.out.println("!!!!!!!!!!!!无效输入...");
				break;
			}
			print();
		}
		System.out.println("Game Over !!");
		System.out.println("最终得分Score =>" + score);
		System.exit(0);
	}

	/**
	 * 打印数组
	 * */
	private static void print() {

		System.out.println("-------------------------------------");
		System.out.println("得分Socre => " + score);
		System.out.println("-------------------------------------");
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				System.out.print("\t" + matrix[i][j]);
			}
			System.out.println("\n");
		}
		System.out.println("-------------------------------------");
		;
	}

	/**
	 * 帮助
	 * */
	private static void help() {

		System.out.println("wasd => 上左下右");
		System.out.println("请输入并回车:");
	}

	/**
	 * 初始化
	 * */
	private static void init() {

		iCount = 0;
		score = 0;
		for (int i = 0; i < INIT_SIZE; i++)
			random_data();
	}

	/**
	 * 随机数据
	 * */
	static boolean random_data() {

		if (iCount == MAX_MAX)
			return false;
		int left = MAX_MAX - iCount;
		int tmp = (int) (Math.random() * left) + 1;
		int num = 2;
		if (Math.random() >= 0.5) {
			num = 4;
		}
		;
		int k = 0;
		for (int i = 0; i < MAX; i++) {
			for (int j = 0; j < MAX; j++) {
				if (matrix[i][j] == 0) {
					if (k++ == tmp) {
						matrix[i][j] = num;
						break;
					}
				}
			}
		}
		++iCount;
		return true;

	}

	static boolean b_up() {

		for (int k = 0; k < MAX; k++) {
			// 特殊情况...这一列有空元素...
			boolean flag = false;
			for (int i = 0; i < MAX - 1; i++) {
				if (matrix[i][k] == 0)
					flag = true;
				else {
					int j = i + 1;
					while (j < MAX) {
						if (matrix[j][k] > 0) {
							if (matrix[i][k] == matrix[j][k])
								return true;
							else
								break;
						}
						else {
							++j;
						}
					}
				}
			}
			if (flag) {
				int i = 0, j = MAX - 1;
				while (i < MAX) {
					if (matrix[i][k] > 0)
						++i;
					else
						break;
				}
				while (j >= 0) {
					if (matrix[j][k] == 0)
						--j;
					else
						break;
				}
				if (i < j)
					return true;
			}
		}
		return false;
	}

	static boolean b_left() {

		for (int k = 0; k < MAX; k++) {
			// 特殊情况...这一行有空元素...
			boolean flag = false;
			for (int i = 0; i < MAX - 1; i++) {
				if (matrix[k][i] == 0)
					flag = true;
				else {
					int j = i + 1;
					while (j < MAX) {
						if (matrix[k][j] > 0) {
							if (matrix[k][i] == matrix[k][j])
								return true;
							else
								break;
						}
						else {
							++j;
						}
					}
				}
			}
			if (flag) {
				// 空元素右边有元素则为True...
				// 使用左右夹击法...
				int i = 0, j = MAX - 1;
				// i 是空元素位置，j是非空元素位置
				while (i < MAX) {
					if (matrix[k][i] > 0)
						++i;
					else
						break;
				}
				while (j >= 0) {
					if (matrix[k][j] == 0)
						--j;
					else
						break;
				}
				if (i < j)
					return true;
			}
		}
		return false;
	}

	static boolean b_down() {

		for (int k = 0; k < MAX; k++) {
			// 特殊情况...这一列有空元素...
			boolean flag = false;
			for (int i = MAX - 1; i > 0; i--) {
				if (matrix[i][k] == 0)
					flag = true;
				else {
					int j = i - 1;
					while (j >= 0) {
						if (matrix[j][k] > 0) {
							if (matrix[i][k] == matrix[j][k])
								return true;
							else
								break;
						}
						else {
							--j;
						}
					}
				}
			}
			if (flag) {
				// 空元素上方有元素则为True... => 下边第一个空元素在上边第一个非空元素下边即可...
				// 使用左右夹击法...
				int i = 0, j = MAX - 1;
				// i 是非空位置，j是空元素位置
				while (i < MAX) {
					if (matrix[i][k] == 0)
						++i;
					else
						break;
				}
				while (j >= 0) {
					if (matrix[j][k] > 0)
						--j;
					else
						break;
				}
				if (i < j)
					return true;
			}
		}
		return false;
	}

	static boolean b_right() {

		for (int k = 0; k < MAX; k++) {
			// 特殊情况...这一行这一行有空元素...
			boolean flag = false;
			for (int i = MAX - 1; i > 0; i--) {
				if (matrix[k][i] == 0)
					flag = true;
				else {
					int j = i - 1;
					while (j >= 0) {
						if (matrix[k][j] > 0) {
							if (matrix[k][i] == matrix[k][j])
								return true;
							else
								break;
						}
						else {
							--j;
						}
					}
				}
			}
			if (flag) {
				// 空元素左边有元素则为True... => 右边第一个空元素在左边第一个非空元素右边即可...
				// 使用左右夹击法...
				int i = 0, j = MAX - 1;
				// i 是非空位置，j是空元素位置
				while (i < MAX) {
					if (matrix[k][i] == 0)
						++i;
					else
						break;
				}
				while (j >= 0) {
					if (matrix[k][j] > 0)
						--j;
					else
						break;
				}
				if (i < j)
					return true;
			}
		}
		return false;
	}

	static void _up() {

		System.out.println("按下了上键\n");
		if (b_up()) {
			System.out.println("可以向上合并");
			for (int i = 0; i < MAX; i++) {
				current = new int[MAX];
				int ii = 0;
				for (int j = 0; j < MAX; j++) {
					if (matrix[j][i] > 0)
						current[ii++] = matrix[j][i];
				}
				for (int k = 0; k < ii - 1; k++) {
					if (current[k] == current[k + 1]) {
						current[k] *= 2;
						score += current[k];
						current[k + 1] = 0;
						++k;
						--iCount;
					}
				}
				ii = 0;
				for (int j = 0; j < MAX; j++) {
					if (current[j] > 0)
						matrix[ii++][i] = current[j];
				}
				for (; ii < MAX; ii++)
					matrix[ii][i] = 0;
			}
			random_data();
		}
		else {
			System.out.println("!!!!!!!!!!!向上无效\n");
		}
	}

	static void _left() {

		System.out.println("按下了左键\n");
		if (b_left()) {
			System.out.println("可以向左合并");
			for (int i = 0; i < MAX; i++) {
				current = new int[MAX];
				int ii = 0;
				for (int j = 0; j < MAX; j++) {
					if (matrix[i][j] > 0)
						current[ii++] = matrix[i][j];
				}
				for (int k = 0; k < ii - 1; k++) {
					if (current[k] == current[k + 1]) {
						current[k] *= 2;
						score += current[k];
						current[k + 1] = 0;
						++k;
						--iCount;
					}
				}
				ii = 0;
				for (int j = 0; j < MAX; j++) {
					if (current[j] > 0)
						matrix[i][ii++] = current[j];
				}
				for (; ii < MAX; ii++)
					matrix[i][ii] = 0;
			}
			random_data();
		}
		else {
			System.out.println("!!!!!!!!!!!向左无效\n");
		}
	}

	static void _down() {

		System.out.println("按下了下键\n");
		if (b_down()) {
			System.out.println("可以向下合并");
			for (int i = 0; i < MAX; i++) {
				current = new int[MAX];
				int ii = 0;
				for (int j = MAX - 1; j >= 0; j--) {
					if (matrix[j][i] > 0)
						current[ii++] = matrix[j][i];
				}
				for (int k = 0; k < ii - 1; k++) {
					if (current[k] == current[k + 1]) {
						current[k] *= 2;
						score += current[k];
						current[k + 1] = 0;
						++k;
						--iCount;
					}
				}
				ii = MAX - 1;
				for (int j = 0; j < MAX; j++) {
					if (current[j] > 0)
						matrix[ii--][i] = current[j];
				}
				for (; ii >= 0; ii--)
					matrix[ii][i] = 0;
			}
			random_data();
		}
		else {
			System.out.println("!!!!!!!!!!!向下无效\n");
		}
	}

	static void _right() {

		System.out.println("按下了右键\n");
		if (b_right()) {
			System.out.println("可以向右合并\n");
			for (int i = 0; i < MAX; i++) {
				current = new int[MAX];
				int ii = 0;
				for (int j = MAX - 1; j >= 0; j--) {
					if (matrix[i][j] > 0)
						current[ii++] = matrix[i][j];
				}
				for (int k = 0; k < ii - 1; k++) {
					if (current[k] == current[k + 1]) {
						current[k] *= 2;
						score += current[k];
						current[k + 1] = 0;
						++k;
						--iCount;
					}
				}
				ii = MAX - 1;
				for (int j = 0; j < MAX; j++) {
					if (current[j] > 0)
						matrix[i][ii--] = current[j];
				}
				for (; ii >= 0; ii--)
					matrix[i][ii] = 0;
			}
			random_data();
		}
		else {
			System.out.println("!!!!!!!!!!!向右无效\n");
		}
	}
}
