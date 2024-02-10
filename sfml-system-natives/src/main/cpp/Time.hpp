#ifndef TIME_HPP
#define TIME_HPP

#include <jni.h>

namespace java::Time {
    jobject microseconds(JNIEnv *, jlong amount);
}

#endif
