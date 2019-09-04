// 2048 (easy)
import java.io.*;
import java.util.*;

public class Main {
  static int N;
  static int[][] board;
  static int[][] v;
  static int res;

  static int max(int[][] T) {
    int result = 0;
    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        if(result < T[i][j]) {
          result = T[i][j];
        }
      }
    }
    return result;
  }

  static void DFS(int[][] map, int cnt) {
    if(cnt==5) {
      if(res < max(map)) {
        // System.out.println("****변경 전****");
        // System.out.println(res);
        res = max(map);
        // System.out.println("****변경 후****");
        // System.out.println(res);
      }
      return;
    }

    for(int dir=0; dir<4; dir++) {
      int[][] table = new int[N][N];
      for(int c=0; c<N; c++) {
        System.arraycopy(map[c], 0, table[c], 0, N);
        // System.out.println(Arrays.toString(table[c]));
      }
      // System.out.println(cnt);
      // System.out.println("**************************");
      if(dir==0) {
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=N-2; j>=0; j--) {
            if(table[i][j]==0) continue;
            int z = j+1;

            while(table[i][z]==0) {
              if(z == N-1) break;
              z++;
            }
            if(table[i][z] == table[i][j] && v[i][z]==0) {
              table[i][z] += table[i][j];
              table[i][j] = 0;
              v[i][z] = 1;
            }
            else if(table[i][z] == table[i][j] && v[i][z]==1) {
              if(z-1 != j) {
                table[i][z-1] = table[i][j];
                table[i][j] = 0;
              }
            }
            else if(table[i][z]==0) {
              table[i][z] = table[i][j];
              table[i][j] = 0;
            }
            else if(table[i][z] != table[i][j]) {
              table[i][z-1] = table[i][j];
              if(z-1 != j)
                table[i][j] = 0;
            }
          }
        }
      }
      else if(dir==1) {
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=1; j<N; j++) {
            if(table[i][j]==0) continue;
            int z = j-1;

            while(table[i][z]==0) {
              if(z == 0) break;
              z--;
            }
            if(table[i][z] == table[i][j] && v[i][z]==0) {
              table[i][z] += table[i][j];
              table[i][j] = 0;
              v[i][z] = 1;
            }
            else if(table[i][z] == table[i][j] && v[i][z]==1) {
              if(z+1 != j) {
                table[i][z+1] = table[i][j];
                table[i][j] = 0;
              }
            }
            else if(table[i][z]==0) {
              table[i][z] = table[i][j];
              table[i][j] = 0;
            }
            else if(table[i][z] != table[i][j]) {
              table[i][z+1] = table[i][j];
              if(z+1 != j)
                table[i][j] = 0;
            }
          }
        }
      }
      else if(dir==2) {
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=N-2; j>=0; j--) {
            if(table[j][i]==0) continue;
            int z = j+1;
            
            while(table[z][i]==0) {
              if(z==N-1) break;
              z++;
            }
            if(table[z][i]==table[j][i] && v[z][i]==0) {
              table[z][i] += table[j][i];
              table[j][i] = 0;
              v[z][i] = 1;
            }
            else if(table[z][i]==table[j][i] && v[z][i]==1) {
              if(z-1 != j) {
                table[z-1][i] = table[j][i];
                table[j][i] = 0;
              }
            }
            else if(table[z][i] == 0) {
              table[z][i] = table[j][i];
              table[j][i] = 0;
            }
            else if(table[z][i] != table[j][i]) {
              table[z-1][i] = table[j][i];
              if(z-1 != j)
                table[j][i] = 0;
            }
          }
        }
      }
      else if(dir==3) {
        for(int i=0; i<N; i++) {
          v = new int[N][N];
          for(int j=1; j<N; j++) {
            if(table[j][i]==0) continue;
            int z = j-1;

            while(table[z][i]==0) {
              if(z==0) break;
              z--;
            }
            if(table[z][i] == table[j][i] && v[z][i]==0) {
              table[z][i] += table[j][i];
              table[j][i] = 0;
              v[z][i] = 1;
            }
            else if(table[z][i] == table[j][i] && v[z][i]==1) {
              if(z+1 != j) {
                table[z+1][i] = table[j][i];
                table[j][i] = 0;
              }
            }
            else if(table[z][i] == 0) {
              table[z][i] = table[j][i];
              table[j][i] = 0;
            }
            else if(table[z][i] != table[j][i]) {
              table[z+1][i] = table[j][i];
              if(z+1 != j)
                table[j][i] = 0;
            }
          }
        }
      }
      // if(cnt==1) return;
      DFS(table,cnt+1);
    }
    if(res < max(map))
      res = max(map);
  }

  static public void main(String[] args) throws IOException {
    System.setIn(new FileInputStream("input.txt"));
    Scanner in = new Scanner(System.in);

    N = in.nextInt();
    board = new int[N][N];
    v = new int[N][N];

    for(int i=0; i<N; i++) {
      for(int j=0; j<N; j++) {
        board[i][j] = in.nextInt();
      }
    }
    
    res = 0;
    DFS(board, 0);
    System.out.println(res);
  }
}