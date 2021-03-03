import java.io.*;
import java.util.*;

public class Main {

    static int N, M;
    static boolean[] visited;
    static StringBuilder sb;

    public static void permutation(int idx, int preNum, int[] result) {
        if(idx == M) {
            for(int num: result) {
                sb.append(num+" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=preNum+1; i<N+1; i++) {
            if(!visited[i]) {
                visited[i] = true;
                result[idx] = i;
                permutation(idx+1, i, result);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N+1];
        sb = new StringBuilder();

        permutation(0, 0, new int[M]);
        System.out.println(sb.toString());
    }
}
