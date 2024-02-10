package org.sfml_dev.system;

/**
 * Utility class that measures the elapsed time
 * 
 * <p>{@link Clock} is a lightweight class for measuring time.</p>
 * 
 * <p>Its provides the most precise time that the underlying
 * OS can achieve (generally microseconds or nanoseconds).
 * It also ensures monotonicity, which means that the returned
 * time can never go backward, even if the system time is
 * changed.</p>
 * 
 * <p>Usage example:</p>
 * <pre>
 * Clock clock = new Clock();
 * ...
 * Time time1 = clock.getElapsedTime();
 * ...
 * Time time2 = clock.restart();
 * </pre>
 * 
 * <p>The {@link Time} value returned by the clock can then be
 * converted to a number of seconds, milliseconds or even
 * microseconds.</p>
 * 
 * @see Time
 */
public class Clock extends CppObject {

    /**
     * Default constructor
     * 
     * <p>The clock starts automatically after being constructed.</p>
     */
    public Clock() {
        init();
    }

    /**
     * Get the elapsed time
     * 
     * <p>This function returns the time elapsed since the last call
     * to restart() (or the construction of the instance if restart()
     * has not been called).</p>
     * 
     * @return Time elapsed
     */
    public native Time getElapsedTime();

    /**
     * Restart the clock
     * 
     * <p>This function puts the time counter back to zero.
     * It also returns the time elapsed since the clock was started.</p>
     * 
     * @return Time elapsed
     */
    public native Time restart();

    protected long sizeof() {
        return sizeof;
    }

    ////////////////////////////////////////////////////////////
    // Native methods
    ////////////////////////////////////////////////////////////
    private static long sizeof = getSizeof();
    private static native long getSizeof();
    private native void init();

    static {
        SharedLibrary.load();
    }
}
