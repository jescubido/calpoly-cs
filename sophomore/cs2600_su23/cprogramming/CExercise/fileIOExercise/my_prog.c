#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[])
{
	FILE *input;
	FILE *output;
	float *arr;
	
	float sum = 0;
	float average = 0;
	float product = 1;
	float smallest = arr[0];
	float largest = arr[0];

	char *a = argv[1];
	int size = atoi(a);
	arr = (float *)malloc(size*sizeof(float));

	input = fopen(argv[2], "r");
	if(input == NULL)
	{
		printf("input.txt file not found");
		return 0;
	}
	
	for (int i = 0; i < size; i++)
	{
		fscanf(input, "%f", arr+i);
	}

	output = fopen(argv[3], "w");
	if(output == NULL)
	{
		printf("output.txt file not found");
		return 0;
	}

	for (int i = 0; i < size; i++)
	{
		sum += arr[i];
		average = sum/size;
		product *= arr[i];
		smallest = (arr[i] < smallest) ? arr[i] : smallest;
		largest = (arr[i] > largest) ? arr[i] : largest;
	}

	fprintf(output, "Sum: %.2f \n", sum);
	fprintf(output, "Average: %.2f \n", average);
	fprintf(output, "Product: %.2f \n", product);
	fprintf(output, "Smallest: %.2f \n", smallest);
	fprintf(output, "Largest: %.2f \n", largest);

	fclose(input);
	fclose(output);
	return 0;
}
