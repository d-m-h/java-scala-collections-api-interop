package com.github.dmh;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

public class DemonstrationFactory {
    public Demonstration getDemonstration() throws RuntimeException {
        ServiceLoader<Demonstration> serviceLoader = ServiceLoader.load(Demonstration.class);
        List<Demonstration> demonstrations = new ArrayList<>();
        serviceLoader.forEach(demonstrations::add);

        if (demonstrations.isEmpty()) {
            throw new RuntimeException("No implementations of " + Demonstration.class.getCanonicalName() + " found");
        } else if (demonstrations.size() > 1) {
            throw new RuntimeException("Multiple implementations of " + Demonstration.class.getCanonicalName() + " found");
        } else {
            return demonstrations.get(0);
        }
    }
}
