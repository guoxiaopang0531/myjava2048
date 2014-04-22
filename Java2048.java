package my2048;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Java2048 {

	private static final int MAX = 4;

	private static final int MAX_MAX = MAX * MAX;

	private static final int INIT_SIZE = 2;

	// ���ݾ���
	private static int[][] matrix = new int[MAX][MAX];

	// �ϲ���ʱ����
	private static int[] current = new int[MAX];

	// ������
	private static int iCount = 0;

	// ��������
	private static char ch;

	// �÷�
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
				System.out.println("!!!!!!!!!!!!��Ч����...");
				break;
			}
			print();
		}
		System.out.println("Game Over !!");
		System.out.println("���յ÷�Score =>" + score);
		System.exit(0);
	}

	/**
	 * ��ӡ����
	 * */
	private static void print() {

		System.out.println("-------------------------------------");
		System.out.println("�÷�Socre => " + score);
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
	 * ����
	 * */
	private static void help() {

		System.out.println("wasd => ��������");
		System.out.println("�����벢�س�:");
	}

	/**
	 * ��ʼ��
	 * */
	private static void init() {

		iCount = 0;
		score = 0;
		for (int i = 0; i < INIT_SIZE; i++)
			random_data();
	}

	/**
	 * �������
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
			// �������...��һ���п�Ԫ��...
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
			// �������...��һ���п�Ԫ��...
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
				// ��Ԫ���ұ���Ԫ����ΪTrue...
				// ʹ�����Ҽл���...
				int i = 0, j = MAX - 1;
				// i �ǿ�Ԫ��λ�ã�j�Ƿǿ�Ԫ��λ��
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
			// �������...��һ���п�Ԫ��...
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
				// ��Ԫ���Ϸ���Ԫ����ΪTrue... => �±ߵ�һ����Ԫ�����ϱߵ�һ���ǿ�Ԫ���±߼���...
				// ʹ�����Ҽл���...
				int i = 0, j = MAX - 1;
				// i �Ƿǿ�λ�ã�j�ǿ�Ԫ��λ��
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
			// �������...��һ����һ���п�Ԫ��...
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
				// ��Ԫ�������Ԫ����ΪTrue... => �ұߵ�һ����Ԫ������ߵ�һ���ǿ�Ԫ���ұ߼���...
				// ʹ�����Ҽл���...
				int i = 0, j = MAX - 1;
				// i �Ƿǿ�λ�ã�j�ǿ�Ԫ��λ��
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

		System.out.println("�������ϼ�\n");
		if (b_up()) {
			System.out.println("�������Ϻϲ�");
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
			System.out.println("!!!!!!!!!!!������Ч\n");
		}
	}

	static void _left() {

		System.out.println("���������\n");
		if (b_left()) {
			System.out.println("��������ϲ�");
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
			System.out.println("!!!!!!!!!!!������Ч\n");
		}
	}

	static void _down() {

		System.out.println("�������¼�\n");
		if (b_down()) {
			System.out.println("�������ºϲ�");
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
			System.out.println("!!!!!!!!!!!������Ч\n");
		}
	}

	static void _right() {

		System.out.println("�������Ҽ�\n");
		if (b_right()) {
			System.out.println("�������Һϲ�\n");
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
			System.out.println("!!!!!!!!!!!������Ч\n");
		}
	}
}
