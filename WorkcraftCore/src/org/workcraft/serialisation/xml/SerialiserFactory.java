package org.workcraft.serialisation.xml;

public interface SerialiserFactory {
    XMLSerialiser getSerialiserFor(Class<?> cls) throws InstantiationException, IllegalAccessException;
}
