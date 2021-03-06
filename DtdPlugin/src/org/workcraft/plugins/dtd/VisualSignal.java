package org.workcraft.plugins.dtd;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Collection;
import java.util.HashSet;

import org.workcraft.annotations.DisplayName;
import org.workcraft.annotations.Hotkey;
import org.workcraft.annotations.SVGIcon;
import org.workcraft.dom.Container;
import org.workcraft.dom.DefaultGroupImpl;
import org.workcraft.dom.Node;
import org.workcraft.dom.visual.Alignment;
import org.workcraft.dom.visual.BoundingBoxHelper;
import org.workcraft.dom.visual.CustomTouchable;
import org.workcraft.dom.visual.DrawRequest;
import org.workcraft.dom.visual.Positioning;
import org.workcraft.dom.visual.Touchable;
import org.workcraft.dom.visual.VisualComponent;
import org.workcraft.gui.Coloriser;
import org.workcraft.gui.propertyeditor.PropertyDeclaration;
import org.workcraft.observation.HierarchyObserver;
import org.workcraft.observation.ObservableHierarchy;
import org.workcraft.plugins.shared.CommonSignalSettings;
import org.workcraft.serialisation.xml.NoAutoSerialisation;
import org.workcraft.util.Hierarchy;

@Hotkey(KeyEvent.VK_X)
@DisplayName("Signal")
@SVGIcon("images/dtd-node-signal.svg")
public class VisualSignal extends VisualComponent implements Container, CustomTouchable, ObservableHierarchy {

    public static final String PROPERTY_COLOR = "Color";
    protected DefaultGroupImpl groupImpl = new DefaultGroupImpl(this);

    public VisualSignal(Signal signal) {
        super(signal);
        configureProperties();
    }

    private void configureProperties() {
        renamePropertyDeclarationByName(PROPERTY_FOREGROUND_COLOR, PROPERTY_COLOR);
        removePropertyDeclarationByName(PROPERTY_FILL_COLOR);
        removePropertyDeclarationByName(PROPERTY_NAME_POSITIONING);
        removePropertyDeclarationByName(PROPERTY_NAME_COLOR);
        removePropertyDeclarationByName(PROPERTY_LABEL);
        removePropertyDeclarationByName(PROPERTY_LABEL_POSITIONING);
        removePropertyDeclarationByName(PROPERTY_LABEL_COLOR);

        addPropertyDeclaration(new PropertyDeclaration<VisualSignal, Signal.Type>(
                this, Signal.PROPERTY_TYPE, Signal.Type.class, true, true, true) {
            protected void setter(VisualSignal object, Signal.Type value) {
                object.setType(value);
            }
            protected Signal.Type getter(VisualSignal object) {
                return object.getType();
            }
        });

        addPropertyDeclaration(new PropertyDeclaration<VisualSignal, Signal.State>(
                this, Signal.PROPERTY_INITIAL_STATE, Signal.State.class, true, true, true) {
            protected void setter(VisualSignal object, Signal.State value) {
                object.setInitialState(value);
            }
            protected Signal.State getter(VisualSignal object) {
                return object.getInitialState();
            }
        });
    }

    public Signal getReferencedSignal() {
        return (Signal) getReferencedComponent();
    }

    @NoAutoSerialisation
    public Signal.Type getType() {
        return getReferencedSignal().getType();
    }

    @NoAutoSerialisation
    public void setType(Signal.Type value) {
        getReferencedSignal().setType(value);
    }

    @NoAutoSerialisation
    public Signal.State getInitialState() {
        return getReferencedSignal().getInitialState();
    }

    @NoAutoSerialisation
    public void setInitialState(Signal.State value) {
        getReferencedSignal().setInitialState(value);
    }

    public Shape getShape() {
        double h = 0.2 * size;
        double h2 = 0.4 * h;
        double w = 0.1 * size;
        double w2 = 0.5 * w;
        double s = 0.5 * size;
        double s2 = 0.5 * s;
        Path2D shape = new Path2D.Double();
        // "1" symbol
        Path2D oneShape = new Path2D.Double();
        oneShape.moveTo(-w2, -s2 - h2 + w2);
        oneShape.lineTo(0.0, -s2 - h2);
        oneShape.lineTo(0.0, -s2 + h2);
        oneShape.moveTo(-w2, -s2 + h2);
        oneShape.lineTo(+w2, -s2 + h2);
        shape.append(oneShape, false);
        // "0" shape
        Ellipse2D zeroShape = new Ellipse2D.Double(-w2, s2 - h2, w, h);
        shape.append(zeroShape, false);
        return shape;
    }

    @Override
    public void draw(DrawRequest r) {
        Graphics2D g = r.getGraphics();
        Color colorisation = r.getDecoration().getColorisation();
        g.setColor(Coloriser.colorise(getForegroundColor(), colorisation));
        drawLabelInLocalSpace(r);
        drawNameInLocalSpace(r);
    }

    @Override
    public Rectangle2D getInternalBoundingBoxInLocalSpace() {
        return new Rectangle2D.Double(-0.25 * size, -0.25 * size, 0.5 * size, 0.5 * size);
    }

    @Override
    public Rectangle2D getBoundingBoxInLocalSpace() {
        Rectangle2D bb = super.getBoundingBoxInLocalSpace();
        Collection<Touchable> touchableChildren = Hierarchy.getChildrenOfType(this, Touchable.class);
        Rectangle2D childrenBB = BoundingBoxHelper.mergeBoundingBoxes(touchableChildren);
        return BoundingBoxHelper.union(bb, childrenBB);
    }

    @Override
    public boolean hitTestInLocalSpace(Point2D pointInLocalSpace) {
        return getBoundingBoxInLocalSpace().contains(pointInLocalSpace);
    }

    @Override
    public Positioning getNamePositioning() {
        return Positioning.LEFT;
    }

    @Override
    public Positioning getLabelPositioning() {
        return Positioning.CENTER;
    }

    @Override
    public Alignment getLabelAlignment() {
        return Alignment.CENTER;
    }

    @Override
    public boolean getLabelVisibility() {
        return true;
    }

    @Override
    public boolean getNameVisibility() {
        return true;
    }

    @Override
    public String getLabel() {
        if ((getReferencedSignal() != null) && (getInitialState() != null)) {
            return "[" + getInitialState().getSymbol() + "]";
        }
        return null;
    }

    @Override
    public Color getNameColor() {
        switch (getType()) {
        case INPUT:    return CommonSignalSettings.getInputColor();
        case OUTPUT:   return CommonSignalSettings.getOutputColor();
        case INTERNAL: return CommonSignalSettings.getInternalColor();
        default:       return CommonSignalSettings.getDummyColor();
        }
    }

    @Override
    public Color getLabelColor() {
        return getForegroundColor();
    }

    @Override
    public void add(Node node) {
        groupImpl.add(node);
    }

    @Override
    public Collection<Node> getChildren() {
        return groupImpl.getChildren();
    }

    @Override
    public Node getParent() {
        return groupImpl.getParent();
    }

    @Override
    public void setParent(Node parent) {
        groupImpl.setParent(parent);
    }

    @Override
    public void remove(Node node) {
        groupImpl.remove(node);
    }

    @Override
    public void add(Collection<Node> nodes) {
        groupImpl.add(nodes);
    }

    @Override
    public void remove(Collection<Node> nodes) {
        for (Node n : nodes) {
            remove(n);
        }
    }

    @Override
    public void reparent(Collection<Node> nodes, Container newParent) {
        groupImpl.reparent(nodes, newParent);
    }

    @Override
    public void reparent(Collection<Node> nodes) {
        groupImpl.reparent(nodes);
    }

    @Override
    public Node hitCustom(Point2D point) {
        Point2D pointInLocalSpace = getParentToLocalTransform().transform(point, null);
        for (Node node : getChildren()) {
            if (node instanceof VisualSignalEvent) {
                VisualSignalEvent event = (VisualSignalEvent) node;
                if (event.hitTest(pointInLocalSpace)) {
                    return event;
                }
            }
        }
        return hitTest(point) ? this : null;
    }

    @Override
    public void addObserver(HierarchyObserver obs) {
        groupImpl.addObserver(obs);
    }

    @Override
    public void removeObserver(HierarchyObserver obs) {
        groupImpl.removeObserver(obs);
    }

    @Override
    public void removeAllObservers() {
        groupImpl.removeAllObservers();
    }

    public Collection<VisualSignalTransition> getVisualTransitions() {
        HashSet<VisualSignalTransition> result = new HashSet<>();
        for (Node node: getChildren()) {
            if (node instanceof VisualSignalTransition) {
                result.add((VisualSignalTransition) node);
            }
        }
        return result;
    }

    public VisualSignalEntry getVisualSignalEntry() {
        for (Node node: getChildren()) {
            if (node instanceof VisualSignalEntry) {
                return (VisualSignalEntry) node;
            }
        }
        return null;
    }

    public VisualSignalExit getVisualSignalExit() {
        for (Node node: getChildren()) {
            if (node instanceof VisualSignalExit) {
                return (VisualSignalExit) node;
            }
        }
        return null;
    }

}
