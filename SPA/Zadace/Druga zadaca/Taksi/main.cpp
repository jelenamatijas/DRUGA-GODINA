#include <iostream>
#include <fstream>
#include <cmath>
#include "City.h"
#include "Tree.h"

using namespace std;

//--------- LIST STRUCTURE ---------

typedef struct list{
    string id;
    int currAddr;
    int numRides;
    bool available;
    int traveled;
    struct list *next;
    
    list():id(""), 
            currAddr(-1), 
            numRides(0),
            available(true),
            traveled(0),
            next(nullptr){}
}LIST;

void initialize(const string& input, LIST** list){
    ifstream file(input);
    if(!file.is_open()){
        cout<<"ERROR: there is no such file."<<endl;
        return;
    }

    string line;

    while(getline(file, line)){
        LIST* newNode = new LIST;
        int addr = 0;
        string id="";
        int i=0;
        while(line[i]!=','){
            id+=line[i];
            i++;
        }
        i++;
        for(;i<line.length();i++){
            addr*=10;
            addr+=line[i]-'0';
        }

        newNode->id = id;
        newNode->currAddr = addr-1;

        if(*list == nullptr)
            *list = newNode;
        else{
            newNode -> next = *list;
            *list = newNode;
        }
    }
    file.close();
}

void printTaxies(LIST* list){
    cout<<"\nAVAILABLE TAXIES:\n"<<endl;
    while(list){
        cout<<"ID: "<<list->id<<endl;
        cout<<"Current address: "<<list->currAddr+1<<"\nAvailable: "<<list->available<<endl<<endl;
        list = list->next;
    }    
}

void freeTaxies(LIST** list){
    while(*list){
        LIST* pom = (*list)->next;
        delete *list;
        *list = pom;
    }
    *list = nullptr;
}

//--------- END OD LIST STRUCTURE ---------

int getNumOfLines(const string& input) {
    ifstream file(input);
    if (!file.is_open()) {
        cout << "ERROR: there is no such file." << endl;
        return 0;
    }

    string line;
    int i = 0;

    while (getline(file, line)) {
        i++;
    }
    file.close();
    return i;
}

void getAddresses(const string& input, int* from, int* to) {
    ifstream file(input);
    if (!file.is_open()) {
        cout << "ERROR: there is no such file." << endl;
        return;
    }

    string line;
    int index = 0;

    while (getline(file, line)) {
        int i = 0;
        while (line[i] != ',') {
            from[index] *= 10;
            from[index] += line[i] - '0';
            i++;
        }
        i++;
        for (; i < line.length(); i++) {
            to[index] *= 10;
            to[index] += line[i] - '0';
        }
        index++;
    }
    file.close();
}

void shortestPath(City& city, int begin, int* dist, int* prev) {
    int n = city.getNumAddresses(); 
    int** matrix = city.getStreets(); 
    bool visited[n] = {false};

    for (int i = 0; i < n; i++) {
        dist[i] = INT_MAX; 
        prev[i] = -1;     
    }

    dist[begin] = 0; // Početna udaljenost mora biti 0

    for (int i = 0; i < n - 1; i++) {
        int min = INT_MAX;
        int u = -1;

        for (int j = 0; j < n; j++) {
            if (!visited[j] && dist[j] <= min) {
                min = dist[j];
                u = j;
            }
        }

        if (u == -1) break; 
        visited[u] = true;

        for (int v = 0; v < n; v++) {
            if ((u == begin && v == begin) || u == v) {
                continue;
            }

            if (!visited[v] && matrix[u][v] > 0 && dist[u] != INT_MAX && dist[u] + matrix[u][v] < dist[v]) {
                dist[v] = dist[u] + matrix[u][v];
                prev[v] = u;
            }
        }
    }
}


void recreatePath(int *prev, int begin, int end){
    if(end == -1 || end == begin){
        cout<<begin+1;
        return;
    }
    else{
        recreatePath(prev, begin, prev[end]);
        cout<<" -> "<<end+1;
    }
}


int main() {
    City city(getNumOfLines("matricaGrada1.csv"), "matricaGrada1.csv");
    LIST* list = nullptr;
    initialize("vozila1.txt", &list);

    cout<<"---------- INPUT INFO ----------"<<endl;
    city.printMatrix();
    printTaxies(list);
    cout<<"---------- END OF INPUT INFO ----------"<<endl;
    
    int numUsers = getNumOfLines("upitiKorisnika1.csv");
    int from[numUsers]{0}, to[numUsers]{0};
    getAddresses("upitiKorisnika1.csv", from, to);

    int waitingTime[numUsers]{0};
    int drivingTime[numUsers]{0};

    for (int i = 0; i < numUsers; i++) {
        //Increase by one when printing
        int userAddress = from[i]-1;
        int destination = to[i]-1;

        cout << "\nProcessing user request: " << userAddress+1<< " -> " << destination+1<< endl;

        Tree tree;
        LIST* pom = list;
        //Calculating distances from taxies to address
        while(pom){

            int n = city.getNumAddresses();
            int* dist = new int[n];
            int* prev = new int[n];

            shortestPath(city, pom->currAddr, dist, prev);
            if(dist[userAddress]!=0)
                tree.add(dist[userAddress], pom->id, pom->currAddr, pom->numRides, pom->available);
            pom = pom->next;

            delete[] dist;
            delete[] prev;
            dist=prev=nullptr;
        }

        TAXI* closest = tree.closestTaxi();
        if(closest){

            int n = city.getNumAddresses();
            int* dist = new int[n];
            int* prev = new int[n];

            shortestPath(city, closest->currAddr, dist, prev);

            if(dist[userAddress] == INT_MAX){
                cout<<"There is no path from "<< closest->currAddr+1<<" to "<<userAddress+1<<"."<<endl;
            }else{
                cout<<"Path: ";
                recreatePath(prev, closest->currAddr, userAddress);
                cout<<" |-> ";
                waitingTime[i] = dist[userAddress];

                shortestPath(city, userAddress, dist, prev);

                if(dist[destination] != INT_MAX) {  
                    recreatePath(prev, userAddress, destination);
                    cout<<endl;
                    drivingTime[i]=dist[destination];
                    pom = list;
                    while(pom && pom->id != closest->id) {
                        pom = pom->next;
                    }
                    if (pom) {
                        if (pom->currAddr != destination && pom->available) {  
                            pom->currAddr = destination;
                            pom->numRides++;
                            pom->traveled += waitingTime[i] + drivingTime[i];  
                            pom->available =  i>13?(i%2==1):true;  
                        }
                    }
                } else{
                    cout<<"There is no path to destination."<<endl;
                }           
            }

            delete[] dist;
            delete[] prev;
        }else{
            cout<<"There is no available taxi."<<endl;
        }
    }

    cout<<"---------- END OF SIMULATION ----------"<<endl<<endl;

    cout<<"Total waiting time for all users: ";
    for(int i=1;i<numUsers;i++)
        waitingTime[0]+=waitingTime[i];
    cout<<waitingTime[0]<<endl;

    cout<<"Total driving time per user: "<<endl;
    for(int i=0;i<numUsers;i++)
        cout<<"User "<<i+1<<": "<<waitingTime[i]<<endl;
    
    cout<<"Taxi datas: "<<endl;
    
    int travelWay=0;
    LIST* pom = list;
    while(pom){
        cout<<"ID: "<<pom->id<<endl;
        cout<<"Number of rides: "<<pom->numRides<<" "<<pom->traveled<<endl;
        travelWay+=pom->traveled;
        pom = pom->next;
    }  
    cout<<"Total traveled time: "<<travelWay<<"."<<endl;
    freeTaxies(&list);
    return 0;
}