package HSAT;

import java.io.*;
import java.util.*;

public class 함께하는_효도 {

	static class Node {

		int r, c, fruit;

		Node(int r, int c, int fruit) {
			this.r = r;
			this.c = c;
			this.fruit = fruit;
		}
	}

	static int n, m, res;
	static List<Node> nodes;
	static int[][] map;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		res = Integer.MIN_VALUE;
		nodes = new ArrayList<>();
		map = new int[n][n];

		for (int r = 0; r < n; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < n; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			Node start = new Node(r, c, map[r][c]);
			nodes.add(start);
			map[r][c] = 0;
		}
		dfs(nodes.get(0), 1, 0, 0);
		System.out.println(res);
	}

	private static void dfs(Node curr, int idx, int cnt, int maxFruit) {
		if (cnt == 3) {
			if (idx < m) {
				dfs(nodes.get(idx), idx + 1, 0, maxFruit + curr.fruit);
				return;
			}

			res = Math.max(res, maxFruit + curr.fruit);
			return;
		}

		for (int d = 0; d < 4; d++) {
			int nr = curr.r + dr[d];
			int nc = curr.c + dc[d];
			if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
			Node next = new Node(nr, nc, curr.fruit + map[nr][nc]);
			int tmp = map[nr][nc];
			map[nr][nc] = 0;
			dfs(next, idx, cnt + 1, maxFruit);
			map[nr][nc] = tmp;
		}
	}
}
