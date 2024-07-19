package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int n = Integer.parseInt(br.readLine());

		int[] A = new int[n];
		StringTokenizer st = new StringTokenizer(br.readLine());
		A[0] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < n; i++) {
			A[i] = Integer.parseInt(st.nextToken()) + A[i - 1];
		}



		int m = Integer.parseInt(br.readLine());

		int[] B = new int[m];
		st = new StringTokenizer(br.readLine());
		B[0] = Integer.parseInt(st.nextToken());

		for (int i = 1; i < m; i++) {
			B[i] = Integer.parseInt(st.nextToken()) + B[i - 1];
		}

		int lenA = (n * (n + 1)) / 2;
		int lenB = (m * (m + 1)) / 2;

		int[] sumA = new int[lenA];

		int idx = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if(i == 0) {
					sumA[idx++] = A[j];
					continue;
				}
				sumA[idx++] = A[j] - A[i - 1];
			}
		}

		int[] sumB = new int[lenB];

		idx = 0;
		for (int i = 0; i < m; i++) {
			for (int j = i; j < m; j++) {
				if(i == 0) {
					sumB[idx++] = B[j];
					continue;
				}
				sumB[idx++] = B[j] - B[i - 1];
			}
		}

		Arrays.sort(sumA);
		Arrays.sort(sumB);

		int res = 0;
		int left = 0;
		int right = lenB - 1;

		while (left < lenA && right >= 0) {
			int curA = sumA[left];
			int curB = sumB[right];
			int sum = curA + curB;
			if (sum == T) {
				int cntA = 0;
				int cntB = 0;
				while (left < lenA && sumA[left] == curA) {
					cntA++;
					left++;
				}

				while (right >= 0 && sumB[right] == curB) {
					cntB++;
					right--;
				}

				res += cntA * cntB;
				continue;
			}

			if (sum < T) {
				left++;
				continue;
			}

			right--;
		}
		System.out.println(res);
	}
}
