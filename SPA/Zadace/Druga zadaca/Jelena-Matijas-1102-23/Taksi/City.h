#pragma once
#include <iostream>
#include <fstream>
#include <iomanip>
#include <climits>

using namespace std;

class City{
    int numAddresses;
    int **streets;
public:
    City(int initialnumAddresses, const string& input):numAddresses(initialnumAddresses), streets(new int*[numAddresses]){
        for(int i=0;i<numAddresses;i++)
            streets[i] = new int[numAddresses];
        initialize(input);
    }
    ~City(){
        for(int i=0;i<numAddresses;i++)
            delete[] streets[i];
        delete[] streets;
    }

    void printMatrix() const {
        cout << "\nSTREET WEIGHTS:\n" << endl;
        for (int i=0;i<numAddresses;i++) {
            for (int j=0;j<numAddresses;j++) {
                cout << setw(3) << streets[i][j];
            }
            cout << endl;
        }
    }

    int** getStreets() const { 
        return streets; 
    }
    int getNumAddresses() const { 
        return numAddresses; 
    }

private:
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