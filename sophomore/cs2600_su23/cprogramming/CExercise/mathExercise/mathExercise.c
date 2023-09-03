#include <stdio.h>

int main()
{
	int x;
	int y;
	int z;

	printf("Input three different integers: ");
	scanf("%d %d %d", &x, &y, &z);

	int sum = x+y+z;
	int average = (x+y+z)/3;
	int product = x*y*z;

	int largest;
	if(x > y)
	{
		if(x > z)
		{
			largest = x;
		}
		else
		{
			largest = z;
		}
	}
	else if (y > z)
	{
		largest = y;
	}
	else
	{
		largest = z;
	}

	int smallest;
	if(x < y)
	{
		if(x < z)
		{
			smallest = x;
		}
		else
		{
			smallest = z;
		}
	}
	else if (y < z)
	{
		smallest = y;
	}
	else
	{
		smallest = z;
	}

	printf("Sum is %d \n", sum);
	printf("Average is %d \n", average);
	printf("Product is %d \n", product);
	printf("Smallest is %d \n", smallest);
	printf("Largest is %d \n", largest);

	return 0;
}
	
