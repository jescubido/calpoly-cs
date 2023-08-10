#include <stdio.h>

int main()
{
	int square;
	int cube;

	printf("Number\tSquare\tCube\n");

	for (int i = 0; i < 11; i++)
	{
		square = i * i;
		cube = i * i * i;
		printf("%d\t%d\t%d\n", i, square, cube);
	}
	return 0;
}
