#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#define ALPHABET_SIZE 26

typedef struct node
{
    bool isEndOfWord;
    struct node* next[ALPHABET_SIZE];
}Node;


Node* createNode()
{
    int i=0;
    Node* newNode=(Node*)malloc(sizeof(Node));
    for(i=0;i<ALPHABET_SIZE;i++)
    {
        newNode->next[i]=NULL;
    }
    newNode->isEndOfWord=false;
    return newNode;
}

Node *head;

void insertWord(char word[])
{
    int len=strlen(word),i;
    Node *temp=head;
    Node *newNode;
    for(i=0;i<len;i++)
    {

        if(temp->next[(word[i]-'a')]==NULL)
        {
            newNode=createNode();
            temp->next[(word[i]-'a')]=newNode;
        }
        temp=temp->next[(word[i]-'a')];

    }
    temp->isEndOfWord=true;
    //printf("Word '%s' is inserted!",word);
}

bool hasWord(char word[])
{
    int len=strlen(word),i;
    Node* temp=head;
    for(i=0;i<len;i++)
    {
        if(temp->next[(word[i]-'a')]==NULL)
        {
            return false;
        }
        temp=temp->next[(word[i]-'a')];
    }
    return temp->isEndOfWord;
}

int main()
{
    char word[30];
    head=createNode();

    FILE *fp=fopen("dictionary.txt","r");
    if(fp==NULL)
    {
        printf("File Not Found!");
    }
    while(!feof(fp))
    {
        fscanf(fp,"%s",word);
        insertWord(word);
    }
    fclose(fp);
    printf("Enter misspelt word: ");
    scanf("%s",word);
    swapChars(word);
    insertion(word);
    replace(word);
    deletion(word);


    return 0;
}


void insertion(char word[])
{
    int len=strlen(word),i,j,k;
    char* temp=malloc(strlen(word)+1);
    char* temp1=malloc(strlen(word)+2);
    j=0;
    strcpy(temp,word);
    for(i=0;i<=len;i++)
    {   k=0;
        for(j=0;j<=len;j++)
        {
            if(i==j)
             {
                 temp1[j]='0';
                continue;
             }
            temp1[j]=temp[k];
            k++;
        }temp1[len+1]='\0';
        for(j=0;j<26;j++)
        {
            temp1[i]='a'+j;
            if(hasWord(temp1))
                {
                    printf("\n\n%s",temp1);
                }
        }
    }
    free(temp);
    free(temp1);
}



void replace(char word[])
{
    int len=strlen(word),i,j;
    char* temp=malloc(strlen(word)+1);
    strcpy(temp,word);
    for(i=0;i<len;i++)
    {
        for(j=0;j<26;j++)
        {
            if(temp[i]=='a'+j)
                continue;
            else
            {
                temp[i]='a'+j;
                if(hasWord(temp))
                {
                    printf("\n\n%s",temp);
                }
            }
        }strcpy(temp,word);
    }
    free(temp);
}

void deletion(char word[])
{
    int i,k,len=strlen(word),j;
    char temp[30];
    for(i=0;i<len;i++)
    {
        k=0;
        for(j=0;j<=len;j++)
        {
            if(i==j)
             {
                continue;
             }
            temp[k]=word[j];
            k++;
        }temp[len]='\0';
        if(hasWord(temp))
        {
            printf("\n%s",temp);
        }
    }
}

void swapChars(char word[])
{
    int i,j,len=strlen(word);
    char temp[30],c;
    strcpy(temp,word);
    for(i=0;i<len-1;i++)
    {
        c=temp[i];
        temp[i]=temp[i+1];
        temp[i+1]=c;
        if(hasWord(temp))
        {
            printf("\n%s",temp);
        }
       // printf("\n%s",temp);
        strcpy(temp,word);
    }
}
