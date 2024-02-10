#include <org_sfml_dev_system_CppObject.h>

/*
 * Class:     org_sfml_dev_system_CppObject
 * Method:    operator_new
 * Signature: (J)J
 */
jlong Java_org_sfml_1dev_system_CppObject_operator_1new(JNIEnv *, jclass, jlong count) {
    return (jlong)operator new((unsigned long)count);
}

/*
 * Class:     org_sfml_dev_system_CppObject
 * Method:    operator_delete
 * Signature: (J)V
 */
void Java_org_sfml_1dev_system_CppObject_operator_1delete(JNIEnv *, jclass, jlong ptr) {
    operator delete((void *)ptr);
}

namespace java::CppObject {
    thread_local jclass CppObject = NULL;
    thread_local jmethodID CppObject_getPtr = NULL;

    jlong getPtr(JNIEnv *env, jobject self) {
        if (!CppObject) {
            CppObject = (jclass)env->NewGlobalRef(env->FindClass("org/sfml_dev/system/CppObject"));
        }
        if (!CppObject_getPtr) {
            CppObject_getPtr = env->GetMethodID(CppObject, "getPtr", "()J");
        }
        return env->CallLongMethod(self, CppObject_getPtr);
    }
}
