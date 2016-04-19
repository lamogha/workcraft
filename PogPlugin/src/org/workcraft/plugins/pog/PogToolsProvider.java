package org.workcraft.plugins.pog;

import java.util.ArrayList;

import org.workcraft.gui.graph.tools.CommentGeneratorTool;
import org.workcraft.gui.graph.tools.ConnectionTool;
import org.workcraft.gui.graph.tools.CustomToolsProvider;
import org.workcraft.gui.graph.tools.DefaultNodeGenerator;
import org.workcraft.gui.graph.tools.GraphEditorTool;
import org.workcraft.gui.graph.tools.NodeGeneratorTool;
import org.workcraft.gui.graph.tools.SelectionTool;
import org.workcraft.plugins.pog.tools.PogSimulationTool;

public class PogToolsProvider implements CustomToolsProvider {

    @Override
    public Iterable<GraphEditorTool> getTools() {
        ArrayList<GraphEditorTool> result = new ArrayList<>();

        result.add(new SelectionTool(false));
        result.add(new CommentGeneratorTool());
        result.add(new ConnectionTool());
        result.add(new NodeGeneratorTool(new DefaultNodeGenerator(Vertex.class)));
        result.add(new PogSimulationTool());
        return result;
    }

}
