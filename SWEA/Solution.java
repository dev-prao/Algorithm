package SWEA;

import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {
	static final int[] dr = {0, 1, 0, -1};  // 우, 하, 좌, 상
	static final int[] dc = {1, 0, -1, 0};  // 우, 하, 좌, 상

	static int N;

	static int bfs(int[][] board, int dir) {
		int[][] dp = new int[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0, dir, 0});  //r, c, preDir, cost
		dp[0][0] = -1;

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0];
			int c = cur[1];
			int d = cur[2];
			int cost = cur[3];

			for (int i = 0; i < 4; i++) {
				int nr = r + dr[i];
				int nc = c + dc[i];
				if (nr >= 0 && nr < N && nc >= 0 && nc < N) {
					if (board[nr][nc] == 0) {
						int nCost = d == i ? cost + 100 : cost + 600;
						if (dp[nr][nc] == 0 || nCost < dp[nr][nc]) {
							dp[nr][nc] = nCost;
							q.offer(new int[] {nr, nc, i, nCost});
						}
					}
				}
			}
		}

		return dp[N - 1][N - 1];
	}

	public int solution(int[][] board) {
		int answer;
		N = board.length;

		answer = bfs(board, 0); // 오른쪽
		answer = Math.min(answer, bfs(board, 1));   // 아래쪽

		return answer;
	}
}

