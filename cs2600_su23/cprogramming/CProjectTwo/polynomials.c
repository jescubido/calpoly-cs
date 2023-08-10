#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main()
{
	int m;

	printf("Enter max power of polynomial 1: ");
	scanf("%d", &m);

	int a[m+1];
	printf("Enter coefficients for polynomial 1:\n");
	int i = 0;
	while (i <= m)
	{
		printf("Enter coefficent of x^%d: ", i);
		scanf("%d", &a[i]);
		i++;
	}
	printf("\n");
	
	int n;
	printf("Enter max power of polynomial 2: ");
	scanf("%d", &n);
	
	int b[n+1];
	printf("Enter coefficients for polynomial 2:\n");
	i = 0;
	while (i <= n)
	{
		printf("Enter coefficient of x^%d: ", i);
		scanf("%d", &b[i]);
		i++;
	}
	printf("\n");

	printf("Polynomial 1: ");
	for (int j = 0; j <= m; j++)
        {	
                printf("%dx^%d ",a[j],j);
		if(j == m)
			break;
		printf("+ ");
        }

	printf("\n");

	printf("Polynomial 2: ");
	for (int j = 0; j <= n; j++)
        {	
                printf("%dx^%d ",b[j],j);
		if(j == n)
			break;
		printf("+ ");
        }
			
	printf("\n");
	
	int largerArr = m;
	int smallerArr = m;

	if (n > m)
	{
		largerArr = n;
		smallerArr = m;
	}
	else if(n < m)
	{
		largerArr = m;
		smallerArr = n;
	}


	int size = largerArr;
	int choice;
	printf("\nChoose from one of the following:\n");
	printf("(1) Addition\n(2) Subtraction\n(3) Multiplication\n(4) Enter two new polynomials\n(5) Exit\n");
	printf("\nEnter choice: ");
	scanf("%d", &choice);

	printf("\nResult: ");

	int result[m + n - 1];
	switch(choice)
	{
		case 1:
			for(i = 0; i <= largerArr; i++)
			{
				if (smallerArr == i-1)
				{	if (m > n)
						printf("%dx^%d ", a[i], i);
					else
						printf("%dx^%d ", b[i], i);
				}
				else
					printf("%dx^%d ", a[i]+b[i], i);
				if (i == largerArr)
						break;
					printf("+ ");
			}
			printf("\n");
			break;	

		case 2:
			for(i = 0; i <= largerArr; i++)
                        {       
                                if (smallerArr == i-1)
                                {       if (m > n)
                                                printf("%dx^%d ", a[i] - 0, i);
                                        else    
                                                printf("%dx^%d ", 0 - b[i], i);
                                }
                                else    
                                        printf("%dx^%d ", a[i]-b[i], i);
                                if (i == largerArr)
                                                break;
                                        printf("+ ");
                        }
			printf("\n");
			break;	

		case 3:
			for (int i = 0; i < m + n + 1; i++)
				result[i] = 0;
			for (int i = 0; i <= largerArr; i++)
                        {       
				for (int j = 0; j <= smallerArr; j++)
				{
					result[i+j] += a[i]*b[j];
					printf("%dx^%d + ", result[i+j]);
				}
			}
			printf("\n");
			break;

		case 4:
	
			main();

		case 5:
			return 0;
	}
	return 0;
}
