#include<iostream>
#include<string>
using namespace std;

void urlify(char str[], int truLen); 

int main()
{
	cout << "hi" << endl;	
	printf("wat %d\n", 5); 

	char str[] = "aa b    "; 
	int trueLength = 4;  

	cout << "URLify \"" << str;
	urlify(str, trueLength);	
	cout << "\" to \"" << str << "\"" << endl;

	// End program
	system("pause");
	return 0;
}

void urlify(char str[], int truLen)
{
	int spaces = 0; // no zero initialization default in methods		 
	
	// First pass: count spaces
	for (int k = 0; k < truLen; k++)
	{
		if (str[k] == ' ')
			spaces = spaces + 2; 
	}

	// Second pass: go go go
	// handle the case where you need to add a terminating null
	int a = truLen - 1; 
	int b = truLen - 1 + spaces; 	
	if (strlen(str) > b + 1) str[b + 1] = '\0'; 
	while (b > a)
	{
		if (str[a] == ' ')
		{
			str[b] = '0'; 
			str[b - 1] = '2'; 
			str[b - 2] = '%'; 
			b = b - 3; 
			a--; 
		}
		else
		{
			str[b--] = str[a--]; 
		}
	}	 	 
}