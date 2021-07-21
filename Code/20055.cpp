#include <iostream>
#include <vector>
using namespace std;

int main()
{
	int n, k;

	cin >> n >> k;
	
	vector<pair<int,int>> conveyor; // 내구도, 로봇

	for (int i = 0; i < 2*n; i++) {
		int temp;
		cin >> temp;
		pair<int, int> block = make_pair(temp, 0);
		conveyor.push_back(block);
	}

	int stage = 0;
	
	while (k > 0) {
		stage++;

		pair<int, int> last = conveyor[2 * n - 1];
		conveyor.pop_back();
		conveyor.insert(conveyor.begin(), last);

		conveyor[n - 1].second = 0;

		for (int i = n - 2; i >= 0; i--) {
			if (conveyor[i].second == 1) {
				if (conveyor[i + 1].first > 0 && conveyor[i + 1].second == 0) {
					conveyor[i].second = 0;

					conveyor[i + 1].first--;
					conveyor[i + 1].second = 1;

					if (conveyor[i + 1].first == 0) {
						k--;
					}
				}
			}
		}
		conveyor[n - 1].second = 0;

		for (int i = n; i < 2 * n; i++) {
			int next = (i + 1) % (2 * n);

			if (conveyor[i].second == 1) {
				if (conveyor[next].first > 0 && conveyor[next].second == 0) {
					conveyor[i].second = 0;

					conveyor[next].first--;
					conveyor[next].second = 1;

					if (conveyor[next].first == 0) {
						k--;
					}
				}
			}
		}

		if (conveyor[0].first > 0 && conveyor[0].second == 0) {
			conveyor[0].first--;
			conveyor[0].second = 1;

			if (conveyor[0].first == 0) {
				k--;
			}
		}
	}

	cout << stage;

    return 0;
}

