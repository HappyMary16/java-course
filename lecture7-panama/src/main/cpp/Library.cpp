#include <iostream>

extern "C" {
    void say_hello(const char* greeting) {
        std::cout << greeting << std::endl;
    }
}
