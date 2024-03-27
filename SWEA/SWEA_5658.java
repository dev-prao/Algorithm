package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class SWEA_5658 { //보물상자 비밀번호
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder res = new StringBuilder();
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			String[] arr = br.readLine().split("");
			Set<String> set = new TreeSet<>(Collections.reverseOrder());
			int size = N / 4;
			//라운드별 순회, 전체 인덱스 순회, 섹션별로 순회
			for (int round = 0; round < size; round++) {
				for (int idx = 0; idx < arr.length; idx += size) {
					for (int section = idx; section < idx + size; section++) {
						sb.append(arr[section]);
					}
					set.add(sb.toString());
					sb.setLength(0);
				}

				// 배열 회전
				String tmp = arr[N - 1];
				for (int j = N - 1; j > 0; j--) {
					arr[j] = arr[j - 1];
				}
				arr[0] = tmp;
			}
			String[] ans = set.toArray(new String[0]);
			res.append("#").append(tc).append(" ").append(Long.parseLong(ans[K - 1], 16)).append("\n");
		}
		bw.write(res.toString());
		bw.close();
		br.close();
	}
}
