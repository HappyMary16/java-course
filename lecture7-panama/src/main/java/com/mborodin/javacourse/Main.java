package com.mborodin.javacourse;

import java.lang.foreign.*;
import java.lang.invoke.MethodHandle;

public class Main {

    static void main() throws Throwable {
        // Library (.dll, .dylib file) should be located in folder where you start an app
        System.loadLibrary("lecture7_panama");

        Linker linker = Linker.nativeLinker();
        SymbolLookup lookup = SymbolLookup.loaderLookup();

        MemorySegment myFuncAddr = lookup.find("say_hello").orElseThrow();
        FunctionDescriptor descriptor = FunctionDescriptor.ofVoid(
                ValueLayout.ADDRESS
        );
        MethodHandle sayHello = linker.downcallHandle(myFuncAddr, descriptor);

        try (Arena arena = Arena.ofConfined()) {
            MemorySegment cString = arena.allocateFrom("Hello Project Panama!");
            sayHello.invoke(cString);
        }
    }
}