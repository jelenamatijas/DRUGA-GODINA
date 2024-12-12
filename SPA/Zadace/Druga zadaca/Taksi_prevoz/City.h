#pragma once
#include <iostream>
#include <fstream>
#include <iomanip>
#include <climits>

using namespace std;

class City{
    int numAddress;
    int **streets;
public:
    City(int initialnumAddress, const string& input):numAddress(initialnumAddress), streets(new int*[numAddress]){
        for(int i=0;i<numAddress;i++)
            streets[i] = new int[numAddress];
        initialize(input);
    }
    ~City(){
        for(int i=0;i<numAddress;i++)
            delete[] streets[i];
        delete[] streets;
    }

    void printMatrix() const {
        cout << "Street weights:" << endl;
        for (int i=0;i<numAddress;i++) {
            for (int j=0;j<numAddress;j++) {
                cout << setw(3) << streets[i][j];
            }
            cout << endl;
        }
    }

    int getWeight(int a, int b) const {
        return streets[a][b];
    }

    void shortestPath(int source, int *distances, int *previous) const {
        bool* visited = new bool[numAddress];
        
        for(int i=0;i<numAddress;i++){
            visited[i] = false;
            distances[i] = (i==source)? 0 : INT_MAX;
            previous[i] = -1;
        }

        distances[source] = 0;

        for(int i=0;i<numAddress-1;i++){
            int u=minDist(distances, visited);
            visited[u] = true;

            for(int j=0;j<numAddress;j++){
                if(!visited[j] && streets[u][j]>0 && distances[u] != INT_MAX && distances[u] + streets[u][j] < distances[j]) {
                    distances[j] = distances[u] + streets[u][j];
                    previous[j] = u;
                }
            }
            
        }
        delete[] visited;
    }

    void createPath(int *previous, int dest)const{
        if(previous[dest] == -1){
            cout<<dest<<" ";
            return;
        }
        createPath(previous, previous[dest]);
        cout<<dest<<" ";
    }

private:
    int minDist(int* distances, bool* visited)const{
        int minimum = INT_MAX;
        int minId;

        for(int i=0; i<numAddress; i++){
            if(!visited[i] && distances[i]<=minimum){
                minimum = distances[i];
                minId = i;
            }
        }
        return minId;
    }

    void initialize(const string& input){
        ifstream file(input);
        if(!file.is_open()){
            cout<<"ERROR: there is no such file."<<endl;
            return;
        }

        string line;
        int row=0;
        int weigth = 0;
        int column=0;
        while(getline(file, line)){
            
            for(int i=0;i<line.length();i++){
                if(line[i] != ','){
                    weigth*=10;
                    weigth+=line[i]-'0';
                }
                else if(line[i] == ','){
                    streets[row][column] = weigth;
                    weigth=0;
                    column++;
                }
            }
            streets[row][column] = weigth;
            row++;
            column=0;
            weigth=0;
        }
        file.close();
    }
};

