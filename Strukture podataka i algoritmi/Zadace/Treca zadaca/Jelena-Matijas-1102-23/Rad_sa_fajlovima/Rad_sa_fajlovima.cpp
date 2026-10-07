#include <iostream>
#include <fstream>
#include "BTree.h"

using namespace std;

FileInfo processFile(const string& fileName) {
    string filePath = "files\\" + fileName;
    ifstream file(filePath);
    if (!file.is_open()) {
        cout << "Error: File " << fileName << " could not be opened.\n";
        return FileInfo{ 0, 0, filePath };
    }
    FileInfo fileInfo;
    fileInfo.fileName = fileName;
    fileInfo.numWords = 0;
    fileInfo.numCharacters = 0;

    string word;
    while (file >> word) fileInfo.numWords++;

    file.clear();
    file.seekg(0, ios::beg);

    char ch;
    while (file.get(ch)) fileInfo.numCharacters++;

    return fileInfo;
}

void saveTextToFile(const string& text, const string& fileName) {
    string filePath = "files\\" + fileName;
    ofstream file(filePath);
    if (!file.is_open()) {
        cout << "Error: Could not create file " << fileName << ".\n";
        return;
    }
    file << text;
    file.close();
}

int main()
{
    BTree btree(3);
    string fileNames[] = {
        "datoteka1.txt",
        "datoteka2.txt",
        "datoteka3.txt",
        "datoteka4.txt",
        "datoteka5.txt",
        "datoteka6.txt",
        "datoteka7.txt",
        "datoteka8.txt",
        "datoteka9.txt",
        "datoteka10.txt",
        "datoteka11.txt",
        "datoteka12.txt",
        "datoteka13.txt",
        "datoteka14.txt",
        "datoteka15.txt",
    };

    for (int i = 0;i < 15;i++) {
        string fileName = fileNames[i];
        btree.insert(processFile(fileName));
    }

    int choice;
    do {
        cout << "1. Display B-Tree" << endl;
        cout << "2. Search by number of words" << endl;
        cout << "3. Add new file" << endl;
        cout << "4. Delete file" << endl;
        cout << "5. Sort files" << endl;
        cout << "6. Exit" << endl;
        cout << "Enter your choice: ";
        cin >> choice;


        switch (choice) {
        case 1:
            btree.printBTree();
            break;
        case 2: {
            int wordCount;
            cout << "Enter word count to search: ";
            cin >> wordCount;
            vector<FileInfo*> results;
            results= btree.search(wordCount);
            for (auto result : results) {
               cout<<" File name:" << result->fileName << ", number of words: " << result->numWords << '\n';
            }
            if (results.size() == 0) {
                cout << "No file found with given word count.\n";
            }
            break;
        }
        case 3: {
            string text;
            cin.ignore();
            cout << "Enter text: ";
            getline(cin, text);

            static int fileCounter = 1;
            string fileName = "userFile" + to_string(fileCounter++) + ".txt";
            saveTextToFile(text, fileName);
            btree.insert(processFile(fileName));
            break;
        }
        case 4: {
            int wordCount;
            cout << "Enter word count to delete: ";
            cin >> wordCount;
            btree.deleteFiles(wordCount);
            break;
        }
        case 5: {
            int way;
            cout << " 1. Descending" << endl;
            cout << " 2. Ascending" << endl;
            cout << " Enter way of sorting: ";
            cin >> way;
            btree.sortByCharacter(way);
            break;
        }
        case 6:
            cout << " EXIT." << endl;
            break;
        default:
            cout << "Invalid choice. Try again.\n";
        }
        cout << endl;
    } while (choice != 6);


    return 0;
}
