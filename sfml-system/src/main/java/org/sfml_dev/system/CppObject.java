package org.sfml_dev.system;

import java.lang.ref.Cleaner;
import java.util.function.LongConsumer;

public abstract class CppObject {
    
    private static final Cleaner cleaner = Cleaner.create();

    static class State implements Runnable {

        long ptr;
        LongConsumer destructor;

        State(long ptr, LongConsumer destructor) {
            this.ptr = ptr;
            this.destructor = destructor;
        }

        public void run() {
            this.destructor.accept(this.ptr);
        }
    }

    private final State state;

    public CppObject() {
        this.state = new State(operator_new(sizeof()), destructor());
        cleaner.register(this, state);
    }

    public CppObject(long ptr) {
        this.state = new State(ptr, null);
    }

    public long getPtr() {
        return this.state.ptr;
    }

    protected abstract long sizeof();

    protected LongConsumer destructor() {
        return ptr -> {};
    }

    private static native long operator_new(long count);
    private static native void operator_delete(long ptr);

    static {
        SharedLibrary.load();
    }
}
