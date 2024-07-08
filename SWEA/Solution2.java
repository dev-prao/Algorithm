package SWEA;

import java.io.*;
import java.util.*;

public class Solution2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[][] map, mapCopy;
	static int[] dr = { 0, -1, 0, 1 }; // 왼, 상 , 우, 하
	static int[] dc = { -1, 0, 1, 0 };
	static int[] gameTurn;
	static boolean[] visited;
	static int balls, M, N, minNum = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		int tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			balls = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			mapCopy = new int[N][M];

			gameTurn = new int[balls];
			visited = new boolean[M];

			minNum = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					int value = Integer.parseInt(st.nextToken());
					map[i][j] = value;
					mapCopy[i][j] = value;
				}
			}

			permutation(0);
			System.out.println("#" + t + " " + minNum);
		}
	}

	private static void permutation(int cnt) { // 중복 순열 (구슬을 떨어뜨릴 열 balls개 고르기)
		if (cnt == balls) { // 구슬 떨어뜨릴 열 balls 개가 정해지면 
			// 공 게임시작
			gameStart();
			// 벽돌 남은 개수 최소 값 갱신
			minNum = Math.min(minNum, cntBox());
			// 맵 돌려놓기
			copyMap();
			return;
		}

		for (int i = 0; i < M; i++) {
			gameTurn[cnt] = i;
			permutation(cnt + 1);
		}
	}

	private static void gameStart() { // j = 행, turn = 열 
		int row = N - 1;
		for (int round = 0; round < balls; round++) {
			int col = gameTurn[round]; // 구슬 떨어뜨릴 열을 하나씩 받아온다.
			for (int r = 0; r < N; r++) { // 열은 고정 행만 변한다.
				if (map[r][col] > 0) { // 타격할 벽돌의 행이 정해짐.
					row = r;
					break;
				}
			}
			shotBall(row, col); // 구슬 떨어뜨리기
			dropBoxToEmptyArea(); // 벽돌들 아래로 떨어뜨리기
		}
	}


	private static void shotBall(int r, int c) {
		Deque<int[]> q = new ArrayDeque<>();
		q.add(new int[] { r, c, map[r][c] }); // r, c, power
		map[r][c] = 0;
		int nr, nc;

		while (!q.isEmpty()) { // 근접한 모든 유효한 벽돌들의 power만큼 깨뜨리기
			int[] pos = q.poll(); // 깨진 벽돌 꺼내기
			int power = pos[2];

			for (int p = 1; p < power; p++) { // power만큼 영향주기
				for (int i = 0; i < 4; i++) { // 왼, 상, 우, 하
					nr = pos[0] + dr[i] * p;
					nc = pos[1] + dc[i] * p;
					if (isOutOfMap(nr, nc) || map[nr][nc] == 0)
						continue;
					if (map[nr][nc] > 0) {
						q.add(new int[] { nr, nc, map[nr][nc] }); // 깨질 벽돌 넣기
						map[nr][nc]= 0; // 벽돌 깨뜨리기
					}
				}
			}
		}
	}

	private static void dropBoxToEmptyArea() {
		// 1. 큐 방법 (V)
		// 2. bottom 방법
		Deque<Integer> deque = new ArrayDeque<>();
		for (int c = 0; c < M; c++) { // 열
			for (int r = 0; r < N; r++) { // 행 채우고
				if(map[r][c] > 0) {
					deque.offer(map[r][c]);
				}
			}
			for(int i= N-1; i>= 0; i--) { // 비우고 
				if(deque.isEmpty()) {
					map[i][c] = 0;
				} else {
					map[i][c] = deque.pollLast();
				}
			}
		}
	}

	private static int cntBox() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] > 0)
					cnt++;
			}
		}
		return cnt;
	}

	private static void copyMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				map[i][j] = mapCopy[i][j];
			}
		}
	}

	private static boolean isOutOfMap(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}
