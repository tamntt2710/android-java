#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL

// stringFromJNI : method trả về string từ native code , sẽ được trả về từ mã Java / Kotlin
Java_com_example_ndk_1android_MainActivity_stringFromJNI(
        JNIEnv* env, // JNIEnv là môi trường JNI mà sử dụng để tương tác với máy ảo
        jobject /* this */) { // phương thức được khai báo tĩnh
    std::string hello = "Hello from C++ world";
    return env->NewStringUTF(hello.c_str());
}