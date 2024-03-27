package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1251 { //하나로
	static class Edge implements Comparable<Edge> {
		int start, end;
		double weight;

		public Edge(int start, int end, double weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			if (this.weight > o.weight) return 1;
			else if (this.weight < o.weight) return -1;
			else return 0;
		}
	}

	static int N, idx, start, end, cnt;
	static double E, weight, result;
	static int[] parents;
	static Edge[] edges;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			int[][] input = new int[N][2];
			//x값 받기
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input[i][0] = Integer.parseInt(st.nextToken());
			}
			//y값 받기
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				input[i][1] = Integer.parseInt(st.nextToken());
			}
			//출발점 - 도착점과 거리를 저장한 리스트
			edges = new Edge[N * (N - 1) / 2];
			//환경 변수
			E = Double.parseDouble(br.readLine());
			idx = 0;
			//(i ~ N - 2)번째 섬에서부터 (i + 1 ~ N - 1)번째 섬까지 거리를 측정하여 edgeList에 추가
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					start = i;
					end = j;
					weight = getDistance(input[i], input[j]);
					edges[idx++] = new Edge(start, end, weight);
				}
			}
			//가중치(거리) 순으로 오름차순 정렬
			Arrays.sort(edges);
			//makeSet 실시
			make();

			cnt = 0;
			result = 0;
			//리스트를 순회하며 union 실시
			for (Edge edge : edges) {
				//부모가 시작점과 끝점의 부모가 같으면 제외, 다르면 union 실시
				if (union(edge.start, edge.end)) {
					//결과에 E * L^2 더해줌
					result += (E * (Math.pow(edge.weight, 2)));
					//개수가 N - 1이 되면 종료
					if (++cnt == N - 1)
						break;
				}
			}
			sb.append("#").append(tc).append(" ").append(Math.round(result)).append("\n");
		}
		bw.write(sb.toString());
		bw.close();
		br.close();
	}

	private static void make() {
		parents = new int[N];
		for (int i = 0; i < N; i++) {
			parents[i] = i;
		}
	}

	private static boolean union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if (pa == pb)
			return false;
		parents[pb] = pa;
		return true;
	}

	private static int find(int a) {
		if (parents[a] == a)
			return a;
		return parents[a] = find(parents[a]);
	}

	// 거리 구하는 함수
	private static double getDistance(int[] a, int[] b) {
		return Math.sqrt(Math.pow(b[0] - a[0], 2) + Math.pow(b[1] - a[1], 2));
	}
}
