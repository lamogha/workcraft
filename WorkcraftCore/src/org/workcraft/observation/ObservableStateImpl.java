package org.workcraft.observation;

import java.util.HashSet;

public class ObservableStateImpl implements ObservableState {
    private final HashSet<StateObserver> observers = new HashSet<>();

    public void addObserver(StateObserver obs) {
        observers.add(obs);
    }

    public void removeObserver(StateObserver obs) {
        observers.remove(obs);
    }

    public void sendNotification(StateEvent e) {
        for (StateObserver o : observers) {
            o.notify(e);
        }
    }
}
