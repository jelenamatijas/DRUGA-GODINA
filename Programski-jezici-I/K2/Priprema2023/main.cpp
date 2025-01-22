#include <iostream>
#include <fstream>
#include "Stack.h"
#include "BoundedStack.h"

void demoBoundedStack()
{
    BoundedStack<int, 3> stack;
    int *i1 = new int(1);
    int *i2 = new int(2);
    int *i3 = new int(3);
    int *i4 = new int(4);
    try 
    {
        stack.push(i1);
        stack.push(i2);
        stack.push(i3);
        stack.push(i4);
    }
    catch (const StackFullException&)
    {
        std::cout << "Stack is full" << std::endl;
    }

    int* value;
    while ((value = stack.pop()) != nullptr)
    {
        std::cout << *value;
        delete value;
    }

    delete i4; // i4 was not pushed onto the stack, so we must delete it manually
}

void demoSerialize()
{
    Stack<int> stack;
    stack.push(new int(1));
    stack.push(new int(2));
    stack.push(new int(3));

    std::cout << std::endl;

    std::string serialized = stack.serialize();

    std::cout << serialized << std::endl;
    std::ofstream file("stack.txt");
    file << serialized;
    file.close();

    std::ifstream file2("stack.txt");
    std::string str;
    file2 >> str;
    file2.close();

    Stack<int> stack2 = Stack<int>::deserialize(str);

    int* value;
    while ((value = stack2.pop()) != nullptr)
    {
        delete value;
    }

    while ((value = stack.pop()) != nullptr)
    {
        delete value;
    }
}

void demoIterator()
{
    Stack<int> stack;
    stack.push(new int(1));
    stack.push(new int(2));
    stack.push(new int(3));

    for (auto value : stack)
    {
        std::cout << *value << std::endl;
    }

    int *value;
    while ((value = stack.pop()) != nullptr)
    {
        delete value;
    }
}

void demoTransform()
{
    Stack<int> stack;
    stack.push(new int(1));
    stack.push(new int(2));
    stack.push(new int(3));

    Stack<int> stack2 = stack.transform([](const int& value) { return value * 2; });
    for (auto value : stack2)
    {
        std::cout << *value << std::endl;
    }

    int* value;
    while ((value = stack.pop()) != nullptr)
    {
        delete value;
    }
    while ((value = stack2.pop()) != nullptr)
    {
        delete value;
    }

    BoundedStack<int, 3> boundedStack;

    boundedStack.push(new int(1));
    boundedStack.push(new int(2));
    boundedStack.push(new int(3));

    BoundedStack<int, 3> boundedStack2 = boundedStack.transform([](const int& value) { return value * 2; });

    for (auto value : boundedStack2)
    {
        std::cout << *value << std::endl;
    }

    while ((value = boundedStack.pop()) != nullptr)
    {
        delete value;
    }

    while ((value = boundedStack2.pop()) != nullptr)
    {
        delete value;
    }
}

int main()
{
    demoBoundedStack();
    demoSerialize();
    demoIterator();
    demoTransform();
    return 0;
}