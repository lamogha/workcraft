package org.workcraft.plugins.stg.tools;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

import org.workcraft.NodeTransformer;
import org.workcraft.TransformationTool;
import org.workcraft.dom.Model;
import org.workcraft.dom.Node;
import org.workcraft.plugins.stg.Stg;
import org.workcraft.plugins.stg.StgUtils;
import org.workcraft.plugins.stg.VisualDummyTransition;
import org.workcraft.plugins.stg.VisualSignalTransition;
import org.workcraft.plugins.stg.VisualStg;
import org.workcraft.workspace.ModelEntry;

public class SignalToDummyTransitionConverterTool extends TransformationTool implements NodeTransformer {
    private HashSet<VisualDummyTransition> dummyTransitions = null;

    @Override
    public String getDisplayName() {
        return "Convert selected signal transitions to dummies";
    }

    @Override
    public String getPopupName() {
        return "Convert to dummy";
    }

    @Override
    public boolean isApplicableTo(ModelEntry me) {
        return me.getMathModel() instanceof Stg;
    }

    @Override
    public boolean isApplicableTo(Node node) {
        return node instanceof VisualSignalTransition;
    }

    @Override
    public boolean isEnabled(ModelEntry me, Node node) {
        return true;
    }

    @Override
    public int getPriority() {
        return 1;
    }

    @Override
    public Position getPosition() {
        return Position.TOP;
    }

    @Override
    public Collection<Node> collect(Model model) {
        Collection<Node> signalTransitions = new HashSet<>();
        if (model instanceof VisualStg) {
            VisualStg stg = (VisualStg) model;
            signalTransitions.addAll(stg.getVisualSignalTransitions());
            Collection<Node> selection = stg.getSelection();
            if (!selection.isEmpty()) {
                signalTransitions.retainAll(selection);
            }
        }
        return signalTransitions;
    }

    @Override
    public void transform(Model model, Collection<Node> nodes) {
        if (model instanceof VisualStg) {
            VisualStg stg = (VisualStg) model;
            dummyTransitions = new HashSet<VisualDummyTransition>(nodes.size());
            for (Node node: nodes) {
                transform(model, node);
            }
            stg.select(new LinkedList<Node>(dummyTransitions));
            dummyTransitions = null;
        }
    }

    @Override
    public void transform(Model model, Node node) {
        if ((model instanceof VisualStg) && (node instanceof VisualSignalTransition)) {
            VisualStg stg = (VisualStg) model;
            VisualSignalTransition signalTransition = (VisualSignalTransition) node;
            VisualDummyTransition dummyTransition = StgUtils.convertSignalToDummyTransition(stg, signalTransition);
            if (dummyTransitions == null) {
                dummyTransitions.add(dummyTransition);
            }
        }
    }

}
