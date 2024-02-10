#include <org_sfml_dev_system_Clock.h>

#include <SFML/System.hpp>

#include "CppObject.hpp"
#include "Time.hpp"

/*
 * Class:     org_sfml_dev_system_Clock
 * Method:    getSizeof
 * Signature: ()J
 */
jlong Java_org_sfml_1dev_system_Clock_getSizeof(JNIEnv *, jclass) {
    return sizeof(sf::Clock);
}

/*
 * Class:     org_sfml_dev_system_Clock
 * Method:    init
 * Signature: ()V
 */
void Java_org_sfml_1dev_system_Clock_init(JNIEnv *env, jobject jself) {
    void *self = (void *)java::CppObject::getPtr(env, jself);

    new (self) sf::Clock;
}

/*
 * Class:     org_sfml_dev_system_Clock
 * Method:    getElapsedTime
 * Signature: ()Lorg/sfml_dev/system/Time;
 */
jobject Java_org_sfml_1dev_system_Clock_getElapsedTime(JNIEnv *env, jobject jself) {
    sf::Clock *self = (sf::Clock *)java::CppObject::getPtr(env, jself);

    sf::Time time = self->getElapsedTime();

    return java::Time::microseconds(env, time.asMicroseconds());
}

/*
 * Class:     org_sfml_dev_system_Clock
 * Method:    restart
 * Signature: ()Lorg/sfml_dev/system/Time;
 */
jobject Java_org_sfml_1dev_system_Clock_restart(JNIEnv *env, jobject jself) {
    sf::Clock *self = (sf::Clock *)java::CppObject::getPtr(env, jself);

    sf::Time time = self->restart();

    return java::Time::microseconds(env, time.asMicroseconds());
}
