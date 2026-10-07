#include "System.h"

bool System::upgradeToCommercial(const std::string &key)
{
    std::string filename="random_keys.txt";
    std::ifstream file(filename);
    if(!file.is_open())
    {
        std::cerr<<"Error opening file.";
        return false;
    }

    std::string line;
    
    while(std::getline(file,line))
    {
        if(line==key)
        {
            commercialVersion=true;
            std::cout<<"Key authentification succesfull.Upgraded to the commercial version."<<std::endl;
            return true;
        }
    }
    file.close();
    std::cout<<"Key authentification unsuccesfull. Upgrading to the commercial version failed."<<std::endl;
    commercialVersion=false;
    return false;
}
