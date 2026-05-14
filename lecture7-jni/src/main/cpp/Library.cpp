#include "Library.h"

#include <iostream>

using namespace std;

JNIEXPORT void JNICALL Java_com_mborodin_javacourse_Main_sayHello(JNIEnv *, jclass) {
    cout << "Hello, World!" << endl;
}
