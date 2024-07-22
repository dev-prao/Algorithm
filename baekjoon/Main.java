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
        StringTokenizer st = new StringTokenizer(br.readLine());

        //A 배열의 누적합 저장
        int[] A = new int[n];
        A[0] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < A.length; i++) {
            A[i] = A[i - 1] + Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        //B 배열의 누적합 저장
        int[] B = new int[m];
        B[0] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < m; i++) {
            B[i] = B[i - 1] + Integer.parseInt(st.nextToken());
        }

        //A, B 배열 각각의 배열로 만들 수 있는 모든 합 저장
        int[] sumA = new int[n * (n + 1) / 2];
        int[] sumB = new int[m * (m + 1) / 2];

        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int cur = A[j];
				if (i > 0) {
					cur -= A[i - 1];
				}
                sumA[idx++] = cur;
            }
        }

        idx = 0;
        for (int i = 0; i < m; i++) {
            for (int j = i; j < m; j++) {
                int cur = B[j];
				if (i > 0) {
					cur -= B[i - 1];
				}
                sumB[idx++] = cur;
            }
        }

        Arrays.sort(sumA);
        Arrays.sort(sumB);

        int left = 0;
        int right = sumB.length - 1;
        long cnt = 0;

        while (left < sumA.length && right > -1) {
            long curA = sumA[left], curB = sumB[right];
            long sum = curA + curB;
            if (sum == T) {
                long cntA = 0, cntB = 0;
                while (left < sumA.length && curA == sumA[left]) {
                    left++;
                    cntA++;
                }

                while (right > -1 && curB == sumB[right]) {
                    right--;
                    cntB++;
                }
                cnt += cntA * cntB;
            }
            if (sum > T) {

                right--;

            } else if (sum < T) {

                left++;

            }
        }


        System.out.println(cnt);
    }
}
