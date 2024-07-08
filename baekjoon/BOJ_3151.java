package baekjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_3151 {
    public static void main(String[] args) throws Exception {
        // BufferedReader를 사용하여 입력을 받음
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 첫 번째 입력값으로 배열의 크기를 받음
        int n = Integer.parseInt(br.readLine());
        // 입력값을 저장할 배열 생성
        int[] arr = new int[n];

        // 배열 요소를 입력받아 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        // 배열을 오름차순으로 정렬
        Arrays.sort(arr);
        br.close();

        int start = 0;
        int end = n;
        // 음수와 양수의 경계를 찾기 위해 배열을 탐색
        for (int i = 0; i < n; i++) {
            if (arr[i] < 0) {
                start = i;
            } else if (arr[i] > 0) {
                end = i;
                break;
            }
        }

        long answer = 0;

        // 음수 부분에서 두 수의 합이 양수 부분에서 찾고자 하는 값과 같은 경우의 수를 계산
        for (int i = 0; i <= start; i++) {
            for (int j = i + 1; j <= start; j++) {
                int ub = upperBound(arr, end, n, -(arr[i] + arr[j]));
                int lb = lowerBound(arr, end, n, -(arr[i] + arr[j]));
                answer += ub - lb;
            }
        }

        // 양수 부분에서 두 수의 합이 음수 부분에서 찾고자 하는 값과 같은 경우의 수를 계산
        for (int i = start + 1; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int ub = upperBound(arr, 0, start + 1, -(arr[i] + arr[j]));
                int lb = lowerBound(arr, 0, start + 1, -(arr[i] + arr[j]));
                answer += ub - lb;
            }
        }

        // 0의 개수를 세고 이를 이용해 조합의 수를 계산
        int cntZero = end - start - 1;
        if (0 < cntZero) {
            long[][] dp = new long[cntZero + 1][3 + 1];
            dp[0][0] = 1;
            for (int i = 1; i <= cntZero; i++) {
                for (int j = 0; j <= 3; j++) {
                    if (j == 0) {
                        dp[i][0] = 1;
                    } else {
                        dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
                    }
                }
            }

            // 0이 3개인 경우의 수를 더함
            answer += dp[cntZero][3];
        }
        // 최종 답을 출력
        System.out.println(answer);
    }

    // lowerBound: 주어진 값보다 크거나 같은 첫 번째 위치를 찾는 이진 탐색
    static int lowerBound(int[] arr, int left, int right, int value) {
        if (right <= left) {
            return left;
        }

        int mid = (left + right) / 2;
        if (arr[mid] < value) {
            return lowerBound(arr, mid + 1, right, value);
        } else {
            return lowerBound(arr, left, mid, value);
        }
    }

    // upperBound: 주어진 값보다 큰 첫 번째 위치를 찾는 이진 탐색
    static int upperBound(int[] arr, int left, int right, int value) {
        if (right <= left) {
            return left;
        }

        int mid = (left + right) / 2;
        if (arr[mid] <= value) {
            return upperBound(arr, mid + 1, right, value);
        } else {
            return upperBound(arr, left, mid, value);
        }
    }
}
