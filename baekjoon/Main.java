package baekjoon;

import java.util.*;
import java.io.*;

class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		int l = Integer.parseInt(st.nextToken());

		//배열 생성(고속도로 시작, 끝 고려)
		int[] ns = new int[n + 2];
		ns[0] = 0;
		ns[n + 1] = l;

		//휴게소 입력
		if (n > 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				ns[i] = Integer.parseInt(st.nextToken());
			}
		}

		//이분탐색 이용하기 전 휴게소 정렬 필수
		Arrays.sort(ns);

		//이분탐색 이용해서 "휴게소가 없는 구간의 최댓값"의 최솟값을 계산

		int left = 1;
		int right = l - 1;

		int ans = 0;

		while (left <= right) { //해가 존재할 때까지 반복

			int dist = (left + right) / 2; //현재 구간에서 휴게소가 없는 구간 값 계산 (시작지점, 끝지점 중간 값으로)
			int cnt = countRest(dist, ns); //휴게소가 없는 구간에서 현재 dist를 기준으로 분할한다면 몇개의 휴게소를 더 세울 수 있는지 계산

			if (cnt > m) {
				//더 많이 세울 수 있는 경우 = 현재 dist값이 너무 작다 = 현재 dist값을 증가시켜야 한다 (현재 dist보다 작은 것들은 고려대상에서 미포함 되도록)
				left = dist + 1;

				//더 많이 세울 수 있다는 건 휴게소가 없는 구간의 최댓값이 아니라는 소리라서 별도의 처리 x
			} else {
				//현재 세우기로 한 휴게소보다 더 적게 세울 수 밖에 없는 경우 = 현재 dist 값이 너무 크다 = 현재 dist를 감소시켜야 한다 (없는 구간의 최댓값의 최솟값을 구하기 위함)
				right = dist - 1;

				//현재 m값과 동일하게 휴게소를 세우거나 휴게소가 더 적게 세워지는 건 최댓값이라는 소리라서 현재의 최댓값을 정답으로 넣어줘야 함
				//왜 cnt<m일 때도 만족하는지? -> 현재는 동일한 값으로 분할한다고 가정해서 m보다 작은 거지 해당 dist가 최댓값이고 그거보다 작은 값으로 분할한다고 하면 휴게소를 m개로 세울 수 있음
				ans = dist;
			}
		}

		System.out.print(ans);
	}

	//휴게소 갯수 카운팅하는 메소드
	private static int countRest(int dist, int[] ns) {
		int cnt = 0;

		for (int i = 0; i <= ns.length - 2; i++) {
			int tempDist = ns[i + 1] - ns[i]; //현재 휴게소가 없는 길이 계산

			cnt += (tempDist) / dist; //일정한 dist로 분할

			//dist로 분할 시에 딱 맞아 떨어지면 이미 휴게소가 세워져있는 구간에 휴게소를 세운다고 카운트되어서 이를 빼줌
			if (tempDist % dist == 0)
				cnt--;
		}

		return cnt;
	}
}
