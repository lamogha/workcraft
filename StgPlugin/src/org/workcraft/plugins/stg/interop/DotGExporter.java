package org.workcraft.plugins.stg.interop;

import java.util.UUID;

import org.workcraft.interop.AbstractSerialiseExporter;
import org.workcraft.plugins.stg.serialisation.DotGSerialiser;
import org.workcraft.serialisation.Format;

public class DotGExporter extends AbstractSerialiseExporter {

    DotGSerialiser serialiser = new DotGSerialiser();

    @Override
    public UUID getTargetFormat() {
        return Format.STG;
    }

    @Override
    public DotGSerialiser getSerialiser() {
        return serialiser;
    }

}
