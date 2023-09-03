#include <stdio.h>

int main()
{
	char name[] = "Jarisse";
	int age = 20;
	int month = 12;
	int day = 28;
	int birthyear = 2002;
	char year[] = "Junior";

	printf("My name is %s \n", name);
	printf("I am %d years old \n", age);
	printf("My birthday is on %d %d %d \n", month, day, birthyear);
	printf("I am now a %s at CPP\n", year);
	return 0;
}
