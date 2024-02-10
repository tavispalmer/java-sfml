#ifndef CPPOBJECT_HPP
#define CPPOBJECT_HPP

#include <jni.h>

namespace java::CppObject {
    jlong getPtr(JNIEnv *, jobject);
}

#endif
