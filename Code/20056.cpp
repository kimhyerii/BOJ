#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int dr[8] = { -1, -1, 0, 1, 1, 1, 0, - 1 };
int dc[8] = { 0, 1, 1, 1, 0, -1, -1, -1 };
vector<vector<int>> map[51][51];

int main()
{
	int N, M, k;
	cin >> N >> M >> k;

	for (int i = 0; i < M; i++) {
		int r, c, m, s, d;
		vector<int> v;

		cin >> r >> c >> m >> s >> d;
		v.push_back(m);
		v.push_back(s);
		v.push_back(d);
		
		map[r][c].push_back(v);
	}

	while (k > 0) {
		// move
		vector<vector<int>> temp_map[51][51];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				for (auto v : map[i][j]) {
					vector<int> temp;

					temp.push_back(v[0]); //m
					temp.push_back(v[1]); //s
					temp.push_back(v[2]); //d

					int r = i + (dr[v[2]] * v[1]);
					int c = j + (dc[v[2]] * v[1]);

					if (r > N) r = (r % N);
					if (c > N) c = (c % N);
					if (r <= 0) r = (r % N) + N;
					if (c <= 0) c = (c % N) + N;

					temp_map[r][c].push_back(temp);
				}
			}
		}

		vector<vector<int>> next_map[51][51];

		// sum
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				int m = 0, d = 0, s = 0;
				int num = temp_map[i][j].size();
				int plus = 0, mul = 1;
				if (num >= 1) {
					for (auto v : temp_map[i][j]) {
						m += v[0];
						s += v[1];
						d = v[2];
						mul *= v[2] % 2;
						plus += v[2] % 2;
					}

					if (num > 1) {
						m = int(m / 5);
						s = int(s / num);

						if (m > 0) {
							for (int ball = 0; ball < 4; ball++) {
								vector<int> temp;

								temp.push_back(m);
								temp.push_back(s);

								if (mul == 1 || plus == 0) {
									temp.push_back(ball * 2); //0 2 4 6
								}
								else {
									temp.push_back(ball * 2 + 1);
								}

								next_map[i][j].push_back(temp);

							}
						}
					}
					else {
						vector<int> temp;
						temp.push_back(m);
						temp.push_back(s);
						temp.push_back(d);
						next_map[i][j].push_back(temp);
					}
				}
			}
		}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				map[i][j] = next_map[i][j];
			}
		}

		k--;
	}

	int mass = 0;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			for (auto v : map[i][j]) {
				mass += v[0];
			}
		}
	}
	cout << mass;

    return 0;
}
