#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int main()
{
	int a=0, e=0, i=0, o=0, u=0, count=0, totalCons=0, totalPunc=0;
	FILE *input;
	FILE *output;
	char ch;
	char file[100];

	printf("Enter filename: ");
	scanf("%s", file);
	input = fopen(file, "r");
	if (input == NULL)
	{
		printf("Error occurred opening file\n");
		return 1;
	}

	output = fopen("output.txt", "w");
	if(output == NULL)
	{
		printf("output.txt file not found\n");
		return 1;
	}

	printf("Contents of file: \n");
	while((ch = getc(input)) != EOF)
	{
		if (isalpha(ch))
		{
			if (ch == 'a'||ch == 'A')
			{
				a++;
			}
			else if (ch == 'e'||ch == 'E')
			{
				e++;
			}
			else if (ch == 'i'||ch == 'I')
			{
				i++;
			}
			else if (ch == 'o'||ch == 'O')
			{
				o++;
			}
			else if (ch == 'u'||ch == 'U')
			{
				u++;
			}
			else
			{
				totalCons++;
				count++;
			}
		}
		else if (ispunct(ch))
		{
			totalPunc++;
			count++;
		}
		printf("%c", ch);
	}

	a = (a * 100) / count;
	e = (e * 100) / count;
	i = (i * 100) / count;
	o = (o * 100) / count;
	u = (u * 100) / count;
	totalCons = (totalCons * 100) / count;
	totalPunc = (totalPunc * 100) / count;
	
	printf("\nPercentages of various characters: \n");
	printf("a %d%;\te %d%;\ti %d%;\to %d%;\tu %d%;\tconsonants %d%;\tpunct %d%\n", a, e, i, o, u, totalCons, totalPunc);
		
	fprintf(output, "\nPercentages of various characters: \n");
	fprintf(output, "a %d%;\te %d%;\ti %d%;\to %d%;\tu %d%;\tconsonants %d%;\tpunct %d%\n", a, e, i, o, u, totalCons, totalPunc);

	fclose(input);
	fclose(output);
	return 0;
}
