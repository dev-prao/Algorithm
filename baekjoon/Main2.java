package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main2 {
	static class Point {
		int r;
		int c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int N, M, answer;
	static int[] dr = {-1, -1, -1, 0, 0, 1, 1, 1};
	static int[] dc = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int[][] map;
	static boolean[][] visit, top;
	static Deque<Point> q;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visit = new boolean[N][M];
		top = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] != 0 && top[i][j]) {
					bfs(i, j);
				}
			}
		}

		System.out.println(answer);

	}

	static void bfs(int r, int c) {
		visit = new boolean[N][M];
		visit[r][c] = true;
		q = new ArrayDeque<>();
		q.add(new Point(r, c));
		ArrayList<Point> topList = new ArrayList<>();
		while (!q.isEmpty()) {
			Point cur = q.poll();

			for (int d = 0; d < 8; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				if (isValid(nr, nc) && !visit[nr][nc]) {
					if (map[nr][nc] > map[cur.r][cur.c]) return;
					if (map[nr][nc] == map[cur.r][cur.c]) {
						q.add(new Point(nr, nc));
						topList.add(new Point(nr, nc));
					}
					visit[nr][nc] = true;
				}
			}
		}

		for (Point cur : topList) {
			top[cur.r][cur.c] = true;
		}

		answer++;
	}

	static boolean isValid(int r, int c) {
		return r >= 0 && c >= 0 && r < N && c < M;
	}
}
