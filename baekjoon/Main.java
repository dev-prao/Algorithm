package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int total = 0;
	static int index = 1;

	static boolean[] canEscape;
	static int[][] maze, visited;

	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static HashMap<Character, Integer> directions = new HashMap<>();

	public static void main(String[] args) throws IOException {

		directions.put('U', 0);
		directions.put('R', 1);
		directions.put('D', 2);
		directions.put('L', 3);

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		maze = new int[N][M];
		visited = new int[N][M];

		canEscape = new boolean[N * M + 1];

		for (int i = 0; i < N; i++) {
			String input = br.readLine();
			for (int j = 0; j < M; j++) {
				char c = input.charAt(j);
				maze[i][j] = directions.get(c);
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (visited[i][j] != 0) continue;
				dfs(i, j, 0);
				index++;
			}
		}

		System.out.println(total);
		br.close();
	}

	public static void dfs(int r, int c, int count) {
		if (isOutOfMap(r, c)) {
			canEscape[index] = true;
			total += count;
			return;
		}

		if (visited[r][c] != 0 && canEscape[visited[r][c]]) {
			canEscape[index] = true;

			total += count;
		}

		if(visited[r][c] != 0) return;

		visited[r][c] = index;
		dfs(r + dr[maze[r][c]], c + dc[maze[r][c]], count + 1);
	}

	public static boolean isOutOfMap(int r, int c) {
		return r < 0 || r >= N || c < 0 || c >= M;
	}
}
