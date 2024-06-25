package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	static int N, M, u, v;
	static String S, c;
	static String[][] tree;
	static boolean[][] visit;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] info = br.readLine().split(" ");
		N = Integer.parseInt(info[0]);
		M = Integer.parseInt(info[1]);
		S = br.readLine();

		tree = new String[N + 1][N + 1];
		visit = new boolean[N + 1][N + 1];

		for(int i = 0; i < M; i++) {
			info = br.readLine().split(" ");
			u = Integer.parseInt(info[0]);
			v = Integer.parseInt(info[1]);
			c = info[2];
			tree[u][v] = c;
		}

		dfs(1, "");

	}

	static void dfs(int curNode, String history) {
		boolean isEnd = true;
		for(int nextNode = 1; nextNode < N + 1; nextNode++) {
			//트리에 연결된 곳이 있고, 그곳을 방문하지 않았다면
			if(tree[curNode][nextNode] != "" && !visit[curNode][nextNode]) isEnd = false;
		}
		//끝점까지 방문했다면 종료 후 LCS 계산
		if(isEnd) {
			System.out.println(history);
			LCS();
			return;
		}

		//현재 노드와 연결된 노드 중 방문하지 않은 노드 방문
		for(int nextNode = 1; nextNode < N + 1; nextNode++) {
			//값이 없거나 방문했다면 건너뜀
			if(tree[curNode][nextNode] == null || visit[curNode][nextNode]) continue;
			visit[curNode][nextNode] = true;
			dfs(nextNode, history + tree[curNode][nextNode]);
			visit[curNode][nextNode] = false;
		}
	}

	static int LCS() {
		return 1;
	}
}
