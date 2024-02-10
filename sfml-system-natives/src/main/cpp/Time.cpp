#include "Time.hpp"

namespace java::Time {
    thread_local jclass Time = NULL;
    thread_local jmethodID Time_microseconds = NULL;

    jobject microseconds(JNIEnv *env, jlong amount) {
        if (!Time) {
            Time = (jclass)env->NewGlobalRef(env->FindClass("org/sfml_dev/system/Time"));
        }
        if (!Time_microseconds) {
            Time_microseconds = env->GetStaticMethodID(Time, "microseconds", "(J)Lorg/sfml_dev/system/Time;");
        }
        return env->CallStaticObjectMethod(Time, Time_microseconds, amount);
    }
}