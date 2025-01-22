#pragma once
#include "sqlite3.h"
#include "User.h"
#include <vector>
#include <iostream>
//#include <openssl/sha.h>
#include <sstream>
#include <iomanip>
#include "Global.h"

class UserTable
{
private:
    sqlite3* db;
public:
    UserTable(sqlite3* db):db(db){}
    
    bool createTable();
    bool addUser(const User& user);
    bool removeUser(const User& user);
    bool viewAllUsers();
    bool userAuthentication(const User& user);
    int getUserId(const User& user);
    std::string getUserName( int id);
private:
   // std::string hashPassword(const std::string& password );
    int countUsersByType(const std::string& accType) const;

};

