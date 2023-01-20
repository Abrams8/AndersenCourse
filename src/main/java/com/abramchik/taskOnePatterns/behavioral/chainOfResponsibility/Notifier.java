package com.abramchik.taskOnePatterns.behavioral.chainOfResponsibility;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class Notifier {

    @NonNull
    int priority;

    @Setter
    Notifier nextNotifier;

    public void notifyManager(String message, int level) {
        if (level >= priority) {
            write(message);
        }
        if (nextNotifier != null) {
            nextNotifier.notifyManager(message, level);
        }
    }
    public abstract void write(String message);
}
