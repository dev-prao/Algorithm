package HSAT;

import java.io.*;
import java.util.*;

public class 택배_마스터_광우 {

	static int N, M, K;
	static int[] arr;
	static boolean[] visit;
	static int[] result;
	static int answer = Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		arr = new int[N];
		visit = new boolean[N];
		result = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0; i < N; i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0);
		System.out.println(answer);
	}

	static void dfs(int cnt) {
		if(cnt == N) {
			getResult();
			return;
		}

		for(int i = 0 ; i < N; i++) {
			if(!visit[i]) {
				visit[i] = true;
				result[cnt] = arr[i];
				dfs(cnt + 1);
				visit[i] = false;
			}
		}
	}

	static void getResult() {
		int total = 0;
		int idx = 0;
		for(int i = 0; i < K; i++) {
			int m = M;
			while(m >= result[idx]) {
				total += result[idx];
				m -= result[idx];
				idx = (idx + 1) % N;
			}
		}

		answer = Math.min(total, answer);
	}
}
