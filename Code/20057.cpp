#include <iostream>

using namespace std;

int sand[504][504];

int dx[4] = { 0, 1, 0, -1 };
int dy[4] = { -1, 0, 1, 0 };

int tx[9] = { -1, 1, -2, -1, 1, 2, -1, 1, 0 }; // 1 1 2 7 7 2 10 10 5
int ty[9] = { 0, 0, -1, -1, -1, -1, -2, -2, -3 };

float per[9] = { 0.01, 0.01, 0.02, 0.07, 0.07, 0.02, 0.1, 0.1, 0.05 };

int main()
{
	int n;
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> sand[i][j];
		}
	}
		
	int x = (n - 1) / 2;
	int y = (n - 1) / 2;

	int count = 1;
	int dir = 0; //left down right up

	int out = 0;
	while (!(x == 0 && y == 0)) {
		for (int i = 0; i < 2; i++) {
			if (x == 0 && y == 0) break;

			for (int j = 0; j < count; j++) {
				if (x == 0 && y == 0) break;
				
				int amount = sand[x + dx[dir]][y + dy[dir]];
				int sum = 0;
				for (int k = 0; k < 9; k++) {
					int newX, newY;
					int move = (int)(amount*per[k]);

					if (dir == 0) {
						newX = x + tx[k];
						newY = y + ty[k];
					}

					else if (dir == 1) {
						newX = x - ty[k];
						newY = y + tx[k];
					}

					else if (dir == 2) {
						newX = x + tx[k];
						newY = y - ty[k];
					}

					else if (dir == 3) {
						newX = x + ty[k];
						newY = y + tx[k];
					}

					if ((newX < 0 || newX >= n) || (newY < 0 || newY >= n)) sum += move;
					else { 
						sand[newX][newY] += move;
						sand[x + dx[dir]][y + dy[dir]] -= move;
					}
				}
				

				if ((x + 2 * dx[dir] < 0 || x + 2 * dx[dir] >= n) || 
					(y + 2 * dy[dir] < 0 || y + 2 * dy[dir] >= n)) {
					int temp = sand[x + dx[dir]][y + dy[dir]] - sum;
					sum += temp;
				}
				else {
					sand[x + 2 * dx[dir]][y + 2 * dy[dir]] += sand[x + dx[dir]][y + dy[dir]] - sum;
				}
				sand[x + dx[dir]][y + dy[dir]] = 0;
				out += sum;
				x += dx[dir];
				y += dy[dir];
			}
			dir = (dir + 1) % 4;
		}
		count++;
	}

	cout << out;

    return 0;
}

