package BiShiAlgorithms;

import java.util.Scanner;

public class Toutiao_2_ {
    /*

    #include <iostream>
#include <algorithm>
#include <string>
#include <map>
#include <vector>
    using namespace std;

    void dfs(vector<vector<char>>& grid, int i, int j){
        if (i - 1 >= 0 && grid[i - 1][j] == '1'){
            grid[i - 1][j] = '0';
            dfs(grid, i - 1, j);
        }
        if (i + 1 <= grid.size() - 1 && grid[i + 1][j] == '1'){
            grid[i + 1][j] = '0';
            dfs(grid, i + 1, j);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == '1'){
            grid[i][j - 1] = '0';
            dfs(grid, i, j - 1);
        }
        if (j + 1 <= grid[0].size() - 1 && grid[i][j + 1] == '1'){
            grid[i][j + 1] = '0';
            dfs(grid, i, j + 1);
        }
    }
    int numDept(vector<vector<char>>& grid) {
        if (grid.size() == 0)
            return 0;
        int count = 0;
        int row = grid.size();
        int col = grid[0].size();
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if (grid[i][j] == '1'){
                    grid[i][j] = '0';
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    int main(){
        int line;
        cin >> line;
        vector<vector<char>> grid;
        char ch;

        while (cin >> ch){

            vector<char> tmp;
            tmp.push_back(ch);
            for (int i = 1; i < line; i++){
                cin >> ch;
                tmp.push_back(ch);
            }
            grid.push_back(tmp);
        }

        cout << numDept(grid) << endl;

    }
    */

    /*
    #include <iostream>
#include <algorithm>
#include <string>
#include <map>
#include <vector>
using namespace std;

void dfs(vector<vector<char>>& team, int p, int q){
	if (p - 1 >= 0 && team[p - 1][q] == '1'){
		team[p - 1][q] = '0';
		dfs(team, p - 1, q);
	}
	if (p + 1 <= team.size() - 1 && team[p + 1][q] == '1'){
		team[p + 1][q] = '0';
		dfs(team, p + 1, q);
	}
	if (q - 1 >= 0 && team[p][q - 1] == '1'){
		team[p][q - 1] = '0';
		dfs(team, p, q - 1);
	}
	if (q + 1 <= team[0].size() - 1 && team[p][q + 1] == '1'){
		team[p][q + 1] = '0';
		dfs(team, p, q + 1);
	}
}
int getnum(vector<vector<char>>& team) {
	if (team.size() == 0)
		return 0;
	int count = 0;
    int col = team[0].size();
	int row = team.size();
	for (int i = 0; i < row; i++){
		for (int j = 0; j < col; j++){
			if (team[i][j] == '1'){
				team[i][j] = '0';
				count++;
				dfs(team, i, j);
			}
		}
	}
	return count;
}

int main(){
	int line;
	cin >> line;
	char ch;
	vector<vector<char>> team;
	while (cin >> ch){

		vector<char> tmp;
		tmp.push_back(ch);
		for (int i = 1; i < line; i++){
			cin >> ch;
			tmp.push_back(ch);
		}
		team.push_back(tmp);
	}

	cout << getnum(team) << endl;

}
     */


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String ipStr = in.next();
        if(ipStr == null||ipStr.length() <=3)
            System.out.println(0);
        int len = ipStr.length();
        char[] chs = ipStr.toCharArray();
        if(len == 4)
            System.out.println(1);
        int count = 0;
        for(int i1 = 0;i1 < len-3;i1++){
            if(isLegal(new String(chs,0,i1-0+1))) {
                for(int i2 = i1+1;i2 < len-2;i2++){
                    if(isLegal(new String(chs,i1+1,i2-i1+2))){
                        for(int i3 = i2+1;i3<len-1;i3++) {
                            if (isLegal(new String(chs, i2 + 1,i3-i2+2))) {
                                for(int i4 = i3+1;i4 < len;i4 ++) {
                                    if (isLegal(new String(chs, i3 + 1, i4-i3+2))) {
                                            count++;
                                    }
                                }
                            }
                        }
                }

                }
            }
        }
        System.out.println(count);
    }

    public static boolean isLegal(String str){
        if(str.length() == 0) return false;
        char[] chs = str.toCharArray();
        if(chs.length>=1&&chs[0]=='0') return false;
        return true;
    }


}
