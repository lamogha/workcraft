package org.workcraft.plugins.circuit;

import java.net.URL;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.workcraft.Framework;
import org.workcraft.exceptions.DeserialisationException;
import org.workcraft.gui.DesktopApi;
import org.workcraft.plugins.circuit.commands.CircuitVerificationCommand;
import org.workcraft.plugins.circuit.commands.PropagateInversionTransformationCommand;
import org.workcraft.plugins.mpsat.MpsatSettings;
import org.workcraft.plugins.pcomp.PcompSettings;
import org.workcraft.plugins.punf.PunfSettings;
import org.workcraft.workspace.WorkspaceEntry;
import org.workcraft.workspace.WorkspaceUtils;

public class PropagateInversionTransformationCommandTests {

    @BeforeClass
    public static void init() {
        final Framework framework = Framework.getInstance();
        framework.init();
        switch (DesktopApi.getOs()) {
        case LINUX:
            PcompSettings.setCommand("../dist-template/linux/tools/UnfoldingTools/pcomp");
            PunfSettings.setCommand("../dist-template/linux/tools/UnfoldingTools/punf");
            MpsatSettings.setCommand("../dist-template/linux/tools/UnfoldingTools/mpsat");
            break;
        case MACOS:
            PcompSettings.setCommand("../dist-template/osx/Contents/Resources/tools/UnfoldingTools/pcomp");
            PunfSettings.setCommand("../dist-template/osx/Contents/Resources/tools/UnfoldingTools/punf");
            MpsatSettings.setCommand("../dist-template/osx/Contents/Resources/tools/UnfoldingTools/mpsat");
            break;
        case WINDOWS:
            PcompSettings.setCommand("..\\dist-template\\windows\\tools\\UnfoldingTools\\pcomp.exe");
            PunfSettings.setCommand("..\\dist-template\\windows\\tools\\UnfoldingTools\\punf.exe");
            MpsatSettings.setCommand("..\\dist-template\\windows\\tools\\UnfoldingTools\\mpsat.exe");
            break;
        default:
        }
    }

    @Test
    public void testVmePropagateInversionTransformationCommand() throws DeserialisationException {
        testPropagateInversionTransformationCommand("org/workcraft/plugins/circuit/vme-tm.circuit.work", 12, 9);
    }

    private void testPropagateInversionTransformationCommand(String work, int expMappedGateCount, int expUnmappedGateCount)
            throws DeserialisationException {

        final Framework framework = Framework.getInstance();
        final ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        URL url = classLoader.getResource(work);

        WorkspaceEntry we = framework.loadWork(url.getFile());
        VisualCircuit circuit = WorkspaceUtils.getAs(we, VisualCircuit.class);

        circuit.selectAll();

        PropagateInversionTransformationCommand command = new PropagateInversionTransformationCommand();
        command.execute(we);

        int dstMappedGateCount = 0;
        int dstUnmappedGateCount = 0;
        for (VisualFunctionComponent component: circuit.getVisualFunctionComponents()) {
            if (component.isMapped()) {
                dstMappedGateCount++;
            } else {
                dstUnmappedGateCount++;
            }
        }

        Assert.assertEquals(expMappedGateCount, dstMappedGateCount);
        Assert.assertEquals(expUnmappedGateCount, dstUnmappedGateCount);

        CircuitVerificationCommand verificationCommand = new CircuitVerificationCommand();
        Assert.assertEquals(true, verificationCommand.execute(we));

        framework.closeWork(we);
    }

}
