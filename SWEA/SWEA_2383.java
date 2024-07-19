package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class SWEA_2383 {

	static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, answer;
	static int[] match;
	static Point[] stair;
	static ArrayList<Point> people;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			answer = Integer.MAX_VALUE;
			N = Integer.parseInt(br.readLine());
			people = new ArrayList<>(); // 사람정보
			stair = new Point[2]; // 계단정보
			map = new int[N][N];

			int idx = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] != 0) {
						if (map[i][j] == 1) people.add(new Point(i, j));
						else stair[idx++] = new Point(i, j);
					}
				}
			}

			M = people.size();
			match = new int[M];
			dfs(0);
			sb.append("#").append(tc).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	static void dfs(int idx) {
		if (idx == M) {
			calculate();
			return;
		}
		for (int sidx = 0; sidx < 2; sidx++) {
			match[idx] = sidx; // 계단 선택
			dfs(idx + 1);
		}
	}

	static void calculate() {
		int totalTime = 0; // 모든 사람들이 내려가는 데 필요한 최소시간
		for (int sIdx = 0; sIdx < 2; sIdx++) {
			Point temp = stair[sIdx];
			int[] pq = new int[2 * N + 2]; //(0,0) -> (N-1,N-1)까지의 거리 = 2N - 2, + 4(
			int[] pq2 = new int[2 * N + N * N]; // 시간 t일때 계단 내려가는 사람들
			int pe = Integer.MAX_VALUE;
			for (int mIdx = 0; mIdx < M; mIdx++) {
				if (match[mIdx] == sIdx) {
					int te = getDistance(mIdx, sIdx) + 1;
					pe = Math.min(pe, te);
					pq[te]++; // 계단 도착뒤 1분후 아래로 내려갈 수 있음
				}
			}
			if (pe == Integer.MAX_VALUE) continue; // null case 제거
			int currTime = pe; // sIdx 계단을 내려가는 사람이 모두 작업을 마치는 데 필요한 최소시간
			int stairTime = map[temp.r][temp.c]; // 계단 내려가는 데 걸리는 시간
			for (int idx = 1; idx < 2 * N + 2; idx++) {
				while (pq[idx] > 0) {
					pq[idx]--;
					int remainTime = stairTime;
					for (int j = idx; j < pq2.length; j++) { // 시간 흘려보내기 (1초씩)
						if (pq2[j] < 3) {
							pq2[j]++;
							remainTime--;
						}
						if (remainTime == 0) {
							currTime = Math.max(currTime, j + 1);
							break;
						}
					}
				}
			}
			totalTime = Math.max(totalTime, currTime); // 두계단 중 최대 시간
		}
		answer = Math.min(answer, totalTime);
	}

	static int getDistance(int pIdx, int sIdx) {
		return Math.abs(stair[sIdx].r - people.get(pIdx).r) + Math.abs(
			stair[sIdx].c - people.get(pIdx).c);
	}
}


