#include <stdio.h>
#include <stdlib.h>
#include <time.h>

struct matrix
{
	float **arr; // holds values in array
	int m; // m rows
	int n; // n columns
};

struct matrix multiply(struct matrix matrix1, struct matrix matrix2)
{
	struct matrix result;
	result.m = matrix1.m;
	result.n = matrix2.n;
	result.arr = (float **)malloc(result.m*sizeof(float *));

	for (int i = 0; i < result.m; i++)
	{
		result.arr[i] = (float *)malloc(result.n*sizeof(float));
		for (int j = 0; j < result.n; j++)
		{
			result.arr[i][j] = 0;
		}
	}

	for (int i = 0; i < matrix1.m; ++i)
	{
		for (int j = 0; j < matrix2.n; ++j)
		{
			for (int k = 0; k < matrix1.n; ++k)
			{
				result.arr[i][j] += matrix1.arr[i][k] * matrix2.arr[k][j];
			}
		}
	}
	return result;
}

int main()
{
	struct matrix matrix1;
	struct matrix matrix2;
	clock_t start, end;
	start = clock();

	printf("Enter number of rows for matrix 1: ");
	scanf("%d", &matrix1.m);

	printf("Enter number of columns for matrix 1: ");
	scanf("%d", &matrix1.n);

	matrix1.arr = (float **)malloc(matrix1.m*sizeof(float *));

	printf("Enter coefficients for matrix 1: ");
	for (int i = 0; i < matrix1.m; i++)
	{
		matrix1.arr[i] = (float *)malloc(matrix1.n*sizeof(float));
		for (int j = 0; j < matrix1.n; j++)
		{
			scanf("%f", &matrix1.arr[i][j]);
		}
	}
	printf("\nEnter number of rows for matrix 2: ");
	scanf("%d", &matrix2.m);

	printf("Enter number of columns for matrix 2: ");
	scanf("%d", &matrix2.n);

	matrix2.arr = (float **)malloc(matrix2.m*sizeof(float *));

	printf("Enter coefficients for matrix 2: ");
	for (int i = 0; i < matrix2.m; i++)
	{
		matrix2.arr[i] = (float *)malloc(matrix2.n*sizeof(float));
		for (int j = 0; j < matrix2.n; j++)
		{
			scanf("%f", &matrix2.arr[i][j]);
		}
	}

	// printing matrix
	printf("\nMatrix 1:\n");
	for (int i = 0; i < matrix1.m; i++)
	{
		printf("\t");
		for (int j = 0; j < matrix1.n; j++)
		{
			printf("| %3.2f ", matrix1.arr[i][j]);
		}
		printf("\n");
	}
	printf("\n\n");
	
	printf("Matrix 2:\n");
	for (int i = 0; i < matrix2.m; i++)
	{
		printf("\t");
		for (int j = 0; j < matrix2.n; j++)
		{
			printf("| %3.2f ", matrix2.arr[i][j]);
		}
		printf("\n");
	}
	printf("\n");
		
	struct matrix result = multiply(matrix1, matrix2); // perform multiply() function
	end = clock();
	double time = ((double)end - start)/CLOCKS_PER_SEC;

	// printing result
	printf("\nResult: \n");
	for (int i = 0; i < result.m; i++)
	{
		for (int j = 0; j < result.n; j++)
		{
			printf("\t| %3.2f ", result.arr[i][j]);
		}
		printf("\n");
	}

	printf("\nTime taken to execute: %f\n", time);
	return 0;
}
