package org.sfml_dev.system;

/**
 * Represents a time value
 * 
 * <p>{@link Time} encapsulates a time value in a flexible way.
 * It allows to define a time value either as a number of
 * seconds, milliseconds or microseconds. It also works the
 * other way round: you can read a time value as either
 * a number of seconds, milliseconds or microseconds.</p>
 * 
 * <p>By using such a flexible interface, the API doesn't
 * impose any fixed type or resolution for time values,
 * and let the user choose its own favorite representation.</p>
 * 
 * <p>Time values support the usual mathematical operations:
 * you can add or subtract two times, multiply or divide
 * a time by a number, compare two times, etc.</p>
 * 
 * <p>Since they represent a time span and not an absolute time
 * value, times can also be negative.</p>
 * 
 * <p>Usage example:</p>
 * <pre>
 * Time t1 = Time.seconds(0.1f);
 * int milli = t1.asMilliseconds(); // 100
 * 
 * Time t2 = Time.milliseconds(30);
 * long micro = t2.asMicroseconds(); // 30000
 * 
 * Time t3 = Time.microseconds(-800000);
 * float sec = t3.asSeconds(); // -0.8
 * </pre>
 * 
 * <pre>
 * void update(Time elapsed)
 * {
 *     position += speed * elapsed.asSeconds();
 * }
 * 
 * update(Time.milliseconds(100));
 * </pre>
 * 
 * @see Clock
 */
public class Time implements Comparable<Time> {

    /**
     * Default constructor
     * 
     * <p>Sets the time value to zero.</p>
     */
    public Time() {
        this.m_microseconds = 0;
    }

    /**
     * Return the time value as a number of seconds
     * 
     * @return Time in seconds
     * 
     * @see #asMilliseconds
     * @see #asMicroseconds
     */
    public float asSeconds() {
        return (float)((double)m_microseconds / 1000000.0);
    }

    /**
     * Return the time value as a number of milliseconds
     * 
     * @return Time in milliseconds
     * 
     * @see #asSeconds
     * @see #asMicroseconds
     */
    public int asMilliseconds() {
        return (int)(m_microseconds / 1000);
    }

    /**
     * Return the time value as a number of microseconds
     * 
     * @return Time in microseconds
     * 
     * @see #asSeconds
     * @see #asMilliseconds
     */
    public long asMicroseconds() {
        return m_microseconds;
    }

    // Static member data

    /**
     * Predefined "zero" time value
     */
    public static final Time Zero = new Time();

    /**
     * Construct from a number of microseconds
     * 
     * <p>This function is internal. To construct time values,
     * use {@link #seconds}, {@link #milliseconds} or {@link #microseconds} instead.</p>
     * 
     * @param microseconds Number of microseconds
     */
    private Time(long microseconds) {
        this.m_microseconds = microseconds;
    }

    // Member data

    /**
     * Time value stored as microseconds
     */
    private long m_microseconds;

    /**
     * Construct a time value from a number of seconds
     * 
     * @param amount Number of seconds
     * 
     * @return Time value constructed from the amount of seconds
     * 
     * @see #milliseconds
     * @see #microseconds
     */
    public static Time seconds(float amount) {
        return new Time((long)(amount * 1000000));
    }

    /**
     * Construct a time value from a number of milliseconds
     * 
     * @param amount Number of milliseconds
     * 
     * @return Time value constructed from the amount of milliseconds
     * 
     * @see #seconds
     * @see #microseconds
     */
    public static Time milliseconds(int amount) {
        return new Time((long)amount * 1000);
    }

    /**
     * Construct a time value from a number of microseconds
     * 
     * @param amount Number of microseconds
     * 
     * @return Time value constructed from the amount of microseconds
     * 
     * @see #seconds
     * @see #milliseconds
     */
    public static Time microseconds(long amount) {
        return new Time(amount);
    }

    /**
     * Overload of == operator to compare two time values
     * 
     * @param obj Right operand (a time)
     * 
     * @return True if both time values are equal
     */
    public boolean equals(Object obj) {
        if (obj instanceof Time time) {
            return this.asMicroseconds() == time.asMicroseconds();
        }
        return false;
    }

    /**
     * Overload of comparison operators to compare two time values
     * 
     * @param time Right operand (a time)
     * 
     * @return -1 if {@code this} is lesser than {@code time},
     *         0 if both time values are equal,
     *         or 1 if {@code this} is greater than {@code time}
     */
    public int compareTo(Time time) {
        long left = this.asMicroseconds();
        long right = time.asMicroseconds();
        return (left < right) ? -1 : ((left == right) ? 0 : 1);
    }

    /**
     * Overload of unary - operator to negate a time value
     * 
     * @return Opposite of the time value
     */
    public Time negate() {
        return Time.microseconds(-this.asMicroseconds());
    }

    /**
     * Overload of binary + operator to add two time values
     * 
     * @param time Right operand (a time)
     * 
     * @return Sum of the two times values
     */
    public Time add(Time time) {
        return Time.microseconds(this.asMicroseconds() + time.asMicroseconds());
    }

    /**
     * Overload of binary - operator to subtract two time values
     * 
     * @param time Right operand (a time)
     * 
     * @return Difference of the two times values
     */
    public Time subtract(Time time) {
        return Time.microseconds(this.asMicroseconds() - time.asMicroseconds());
    }

    /**
     * Overload of binary * operator to scale a time value
     * 
     * @param val Right operand (a number)
     * 
     * @return {@code this} multiplied by {@code val}
     */
    public Time multiply(float val) {
        return Time.seconds(this.asSeconds() * val);
    }

    /**
     * Overload of binary * operator to scale a time value
     * 
     * @param val Right operand (a number)
     * 
     * @return {@code this} multiplied by {@code val}
     */
    public Time multiply(long val) {
        return Time.microseconds(this.asMicroseconds() * val);
    }

    /**
     * Overload of binary / operator to scale a time value
     * 
     * @param val Right operand (a number)
     * 
     * @return {@code this} divided by {@code val}
     */
    public Time divide(float val) {
        return Time.seconds(this.asSeconds() / val);
    }

    /**
     * Overload of binary / operator to scale a time value
     * 
     * @param val Right operand (a number)
     * 
     * @return {@code this} divided by {@code val}
     */
    public Time divide(long val) {
        return Time.microseconds(this.asMicroseconds() / val);
    }

    /**
     * Overload of binary / operator to compute the ratio of two time values
     * 
     * @param time Right operand (a time)
     * 
     * @return {@code this} divided by {@code time}
     */
    public float divide(Time time) {
        return this.asSeconds() / time.asSeconds();
    }

    /**
     * Overload of binary / operator to compute remainder of a time values
     * 
     * @param time Right operand (a time)
     * 
     * @return {@code this} modulo {@code time}
     */
    public Time remainder(Time time) {
        return Time.microseconds(this.asMicroseconds() % time.asMicroseconds());
    }
}
