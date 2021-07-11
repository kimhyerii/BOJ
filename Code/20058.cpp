#include <iostream>
#include <queue>

using namespace std;

int ice[65][65];
int dr[4] = { -1, 1, 0, 0 };
int dc[4] = { 0, 0, -1, 1 };
int turn[1000];

int main()
{
	int N, Q;
	cin >> N >> Q;
	int n = 1 << N;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> ice[i][j];
		}
	}

	for (int i = 0; i < Q; i++) {
		cin >> turn[i];
	}

	for (int storm = 0; storm < Q; storm++) {
		int grid = 1 << turn[storm];
		int temp[65][65];

		for (int i = 0; i < n; i = i + grid) {
			for (int j = 0; j < n; j = j + grid) {
				for (int a = 0; a < grid; a++) {
					for (int b = 0; b < grid; b++) {
						temp[i + b][j + grid - a - 1] = ice[i + a][j + b];
					}
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ice[i][j] = temp[i][j];
			}
		}

		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				int count = 0;

				for (int i = 0; i < 4; i++) {
					int newR = r + dr[i];
					int newC = c + dc[i];

					if (newR < 0 || newR >= n) continue;
					if (newC < 0 || newC >= n) continue;
					if (ice[newR][newC] <= 0) continue;
					count++;
				}
				if (count < 3) {
					temp[r][c] -= 1;
				}
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				ice[i][j] = temp[i][j];
			}
		}
	}

	int sum = 0;
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (ice[i][j] > 0) {
				sum += ice[i][j];
			}
		}
	}

	int large = 0;
	bool visited[65][65];

	for (int i = 0; i < n; i++) {
		fill(visited[i], visited[i] + n, false);
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (visited[i][j]) continue;
			if (ice[i][j] <= 0) continue;

			queue<pair<int, int> > q;
			q.push(make_pair(i, j));
			visited[i][j] = true;
			int sz = 1;

			while (!q.empty()) {
				int r = q.front().first;
				int c = q.front().second;
				q.pop();

				for (int k = 0; k < 4; k++) {
					int newR = r + dr[k];
					int newC = c + dc[k];

					if (newR < 0 || newR >= n) continue;
					if (newC < 0 || newC >= n) continue;
					if (ice[newR][newC] <= 0) continue;
					if (visited[newR][newC]) continue;

					q.push(make_pair(newR, newC));
					visited[newR][newC] = true;
					sz++;
				}
			}
			large = max(sz, large);
		}
	}

	cout << sum << "\n" << large;

    return 0;
}
