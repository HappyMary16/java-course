cmake_minimum_required(VERSION 3.15)

project(lecture7_jni)

find_package(JNI)

add_library(lecture7_jni SHARED Library.cpp)

target_include_directories (lecture7_jni PRIVATE ${JNI_INCLUDE_DIRS})
target_link_libraries (lecture7_jni PRIVATE ${JNI_LIBRARIES})
