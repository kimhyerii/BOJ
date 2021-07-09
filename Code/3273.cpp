#include <stdio.h>
#include <iostream>
using namespace std;

int main()
{
	int n, x;
	bool arr[1000001] = { false };

	cin >> n;

	for (int i = 0; i < n; i++) {
		int temp;
		cin >> temp;
		arr[temp] = true;
	}

	cin >> x;

	int count = 0;
	for (int i = 1; i < (x + 1) / 2; i++) {
		if (arr[i] && arr[x - i]) {
			count++;
		}
	}
	cout << count;

    return 0;
}
