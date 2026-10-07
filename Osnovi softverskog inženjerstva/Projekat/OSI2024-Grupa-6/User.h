#pragma once
#include "sqlite3.h"
#include <string>
//TODO modularizacija koda
//TODO potencijalno dodati sektor u kojem radi user tj operater kao atribut
class User
{
private:
    int id;
    std::string username;
    std::string password;
    std::string userType;

public:
    User(int id, std::string username, std::string password, std::string userType):
     id(id),username(username),password(password),userType(userType){}
    
    std::string determineRedirectUrl(){
        if (userType == "Administrator") {
            return "admin-page.html?username="+ username + "&userType=" + userType + "&id=";
        } else if (userType == "Client") {
            return "client-page.html?username="+ username + "&userType=" + userType + "&id=";
        } else if (userType == "Operator") {
            return "operator-page.html?username="+ username + "&userType=" + userType + "&id=";
        } else {
            return "sign-in";
        }
    }

    int getId() const
    {
        return id;
    }
    std::string getUsername() const
    {
        return username;
    }
    std::string getUserType() const
    {
        return userType;
    }

    std::string getPassword() const
    {
        return password;
    }
    void setUsername(const std::string& name)
    {
        username=name;
    }
    
    void setPassword(const std::string& pass)
    {
        password=pass;
    }

    void setUserType(const std::string& type)
    {
        userType=type;
    }
};

