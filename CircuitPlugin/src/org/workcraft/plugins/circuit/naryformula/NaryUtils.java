package org.workcraft.plugins.circuit.naryformula;

import java.util.List;

import org.workcraft.formula.BooleanVariable;

public class NaryUtils {

    public static NaryBooleanFormula getVar(final BooleanVariable var) {
        return new NaryBooleanFormula() {
            @Override
            public <T> T accept(NaryBooleanFormulaVisitor<T> visitor) {
                return visitor.visit(var);
            }
        };
    }

    public static NaryBooleanFormula getNot(final NaryBooleanFormula arg) {
        return new NaryBooleanFormula() {
            @Override
            public <T> T accept(NaryBooleanFormulaVisitor<T> visitor) {
                return visitor.visitNot(arg);
            }
        };
    }

    public static NaryBooleanFormula getAnd(final List<NaryBooleanFormula> args) {
        return new NaryBooleanFormula() {
            @Override
            public <T> T accept(NaryBooleanFormulaVisitor<T> visitor) {
                return visitor.visitAnd(args);
            }
        };
    }

    public static NaryBooleanFormula getOr(final List<NaryBooleanFormula> args) {
        return new NaryBooleanFormula() {
            @Override
            public <T> T accept(NaryBooleanFormulaVisitor<T> visitor) {
                return visitor.visitOr(args);
            }
        };
    }

    public static NaryBooleanFormula getXor(final List<NaryBooleanFormula> args) {
        return new NaryBooleanFormula() {
            @Override
            public <T> T accept(NaryBooleanFormulaVisitor<T> visitor) {
                return visitor.visitXor(args);
            }
        };
    }

}
