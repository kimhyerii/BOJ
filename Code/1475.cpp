#include <stdio.h>
#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

int main()
{
	int arr[10] = { 0 };
	int n = 0;

	string input;
	cin >> input;

	for (int i = 0; i < input.size(); i++) {
		if (input[i] == '6' || input[i] == '9') {
			n++;
		}
		else {
			arr[int(input[i]) - 48] += 1;
		}
	}
	
	int count = *max_element(arr, arr + 10);
	count = max(count, (n + 1) / 2);
	cout << count;

    return 0;
}
