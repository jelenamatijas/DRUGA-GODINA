#include "UserTable.h"

bool UserTable::createTable()
{
        std::string sql= "CREATE TABLE IF NOT EXISTS users ("
                  "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                  "username TEXT NOT NULL UNIQUE, "
                  "password TEXT NOT NULL, "
                  "acc_type TEXT NOT NULL);";

        char* errorMess=nullptr;
        if(sqlite3_exec(db, sql.c_str(),nullptr,nullptr,&errorMess)!= SQLITE_OK)
        {
            std::cerr<<"Error creating table:"<<errorMess<<std::endl;
            sqlite3_free(errorMess);
            return false;
        }
        return true;

    }

bool UserTable::addUser(const User &user)
{
    if(isCommercialVersion==false)
    {
        if(user.getUserType()=="Administrator" && countUsersByType("Administrator")>1 )
    {
        
        std::cout<<"Limit of free version has been reached, upgrade to commercial to add more Administrators."<<std::endl;
        return false;
    }

    if(user.getUserType()=="Operator" && countUsersByType("Operator")>2)
    {
        std::cout<<"Limit of free version has been reached, upgrade to commercial to add more operators."<<std::endl;
        return false; 
    }


    }
    
    //std::string inputHash= hashPassword(user.getPassword());
    std::string inputHash= user.getPassword();
    std::string sql="INSERT INTO users (username, password,acc_type) VALUES (?, ?, ?);";
    sqlite3_stmt* stmt;

    if(sqlite3_prepare_v2(db,sql.c_str(),-1,&stmt,nullptr)!= SQLITE_OK)
    {
        std::cerr<<"Error preparing statement: "<< sqlite3_errmsg(db)<<std::endl;
        return false;
    }

    sqlite3_bind_text(stmt,1,user.getUsername().c_str(),-1,SQLITE_STATIC);
    //sqlite3_bind_text(stmt,2,hashedPassword.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_text(stmt,2,inputHash.c_str(),-1,SQLITE_STATIC);
    sqlite3_bind_text(stmt,3,user.getUserType().c_str(),-1,SQLITE_STATIC);

    if(sqlite3_step(stmt)!= SQLITE_DONE)
    {
        std::cerr<<"Error adding user: "<<sqlite3_errmsg(db)<<std::endl;
        sqlite3_finalize(stmt);
        return false;
    }

    sqlite3_finalize(stmt);
    return true;
}

bool UserTable::removeUser(const User &user)
{
    int userId= getUserId(user);
     if (userId == -1) {
        std::cerr << "User not found for deletion." << std::endl;
        return false;
    }
    std::string sql= "DELETE FROM users WHERE id= ?; ";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        return false;
    }

    if (sqlite3_bind_int(stmt, 1, userId) != SQLITE_OK) {
        std::cerr << "Error binding ID: " << sqlite3_errmsg(db) << std::endl;
        sqlite3_finalize(stmt);
        return false;
    }
    if( sqlite3_step(stmt)==SQLITE_DONE)
    {
     std::cout << "User with ID " << user.getId()<< " has been deleted." << std::endl;
    } else {
        std::cerr << "Error deleting user: " << sqlite3_errmsg(db) << std::endl;   
    }
        
    sqlite3_finalize(stmt);
    return true;
}

bool UserTable::viewAllUsers()
{
    std::string sql= "SELECT id, username, password, acc_type  FROM users; ";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        return false;
    }

        while (sqlite3_step(stmt) == SQLITE_ROW) {
        int id = sqlite3_column_int(stmt, 0);
        const char* username = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 1));
        const char* password = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 2));
        const char* acc_type = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 3));


        std::cout << id << " | "
          << username << " | "
          << password << " | "
          << acc_type << " | "
        << std::endl;

        }
    sqlite3_finalize(stmt);
    return true;
}

bool UserTable::userAuthentication(const User &user)
{
    std::string sql= "SELECT password FROM users WHERE username= ?;";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        return false;
    }

        
    sqlite3_bind_text(stmt,1, user.getUsername().c_str(),-1, SQLITE_STATIC);
    if (sqlite3_step(stmt) == SQLITE_ROW) {
    std::string dbHash(reinterpret_cast<const char*>(sqlite3_column_text(stmt, 0)));
    //std::string inputHash= hashPassword(user.getPassword());
    std::string inputHash= user.getPassword();

   
    sqlite3_finalize(stmt);
     return dbHash == inputHash;
    }
     sqlite3_finalize(stmt);
    return false;
    
}

int UserTable::getUserId(const User& user) {
    std::string sql = "SELECT id FROM users WHERE username = ? AND acc_type = ?;";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Failed to prepare statement: " << sqlite3_errmsg(db) << std::endl;
        return -1;
    }

    sqlite3_bind_text(stmt, 1, user.getUsername().c_str(), -1, SQLITE_TRANSIENT);
    sqlite3_bind_text(stmt, 2, user.getUserType().c_str(), -1, SQLITE_TRANSIENT);

    int userId = -1;
    if (sqlite3_step(stmt) == SQLITE_ROW) {
        userId = sqlite3_column_int(stmt, 0);
    }

    sqlite3_finalize(stmt);
    return userId;
}

std::string UserTable::getUserName(int inputId)
{
   std::string nameFromDb;
    std::string sql = "SELECT username FROM users WHERE id = ?;";
    sqlite3_stmt* stmt;

    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Failed to prepare statement: " << sqlite3_errmsg(db) << std::endl;
        throw std::runtime_error("Failed to prepare statemtn");
    }
    sqlite3_bind_int(stmt,1,inputId);
 
    if (sqlite3_step(stmt) == SQLITE_ROW) {
         const char* username = reinterpret_cast<const char*>(sqlite3_column_text(stmt, 0));
        if (username) {
            nameFromDb = username;
    }
    else{
         std::cerr << "No user found with ID: " << inputId << std::endl;
        sqlite3_finalize(stmt);
        throw std::runtime_error("User not found");
    }
    }

    sqlite3_finalize(stmt);
    return nameFromDb;
    
}

/*std::string UserTable::hashPassword(const std::string &password)
{
    unsigned char hash[SHA256_DIGEST_LENGTH];
    SHA256(reinterpret_cast<const unsigned char*>(password.c_str()), password.size(), hash);

    std::ostringstream oss;
    for (unsigned char c : hash) {
        oss << std::hex << std::setw(2) << std::setfill('0') << static_cast<int>(c);
    }
    return oss.str();
}*/

int UserTable::countUsersByType(const std::string &accType) const
{
    int userCount=0;
    std::string sql=" SELECT COUNT (*) FROM users WHERE acc_type = ?;";
    sqlite3_stmt* stmt;
    if (sqlite3_prepare_v2(db, sql.c_str(), -1, &stmt, nullptr) != SQLITE_OK) {
        std::cerr << "Error preparing statement: " << sqlite3_errmsg(db) << std::endl;
        return -1;
    }

    sqlite3_bind_text(stmt,1,accType.c_str(),-1,SQLITE_STATIC);

    if(sqlite3_step(stmt)== SQLITE_ROW)
    {
        userCount=sqlite3_column_int(stmt,0);

    }else{
        std::cerr << "Error counting by type: " << sqlite3_errmsg(db) << std::endl;
        userCount=-1;
    }
    sqlite3_finalize(stmt);
    return userCount;
}

