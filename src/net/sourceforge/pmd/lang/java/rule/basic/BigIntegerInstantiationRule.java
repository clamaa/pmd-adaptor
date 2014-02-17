package net.sourceforge.pmd.lang.java.rule.basic;

import java.math.BigDecimal;
import java.math.BigInteger;

import net.sourceforge.pmd.RuleContext;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.ASTAllocationExpression;
import net.sourceforge.pmd.lang.java.ast.ASTArguments;
import net.sourceforge.pmd.lang.java.ast.ASTArrayDimsAndInits;
import net.sourceforge.pmd.lang.java.ast.ASTClassOrInterfaceType;
import net.sourceforge.pmd.lang.java.ast.ASTLiteral;
import net.sourceforge.pmd.lang.java.rule.AbstractJavaRule;
import net.sourceforge.pmd.lang.java.typeresolution.TypeHelper;

public class BigIntegerInstantiationRule extends AbstractJavaRule {

    @Override
    public Object visit(ASTAllocationExpression node, Object data) {
        Node type = node.jjtGetChild(0);

        if (!(type instanceof ASTClassOrInterfaceType)) {
            return super.visit(node, data);
        }

//        boolean jdk15 = ((RuleContext) data).getLanguageVersion().compareTo(LanguageVersion.JAVA_15) >= 0;
        boolean jdk15 = true;
        if ((TypeHelper.isA((ASTClassOrInterfaceType) type, BigInteger.class) || jdk15 && TypeHelper.isA((ASTClassOrInterfaceType) type, BigDecimal.class)) &&
                !node.hasDescendantOfType(ASTArrayDimsAndInits.class)
        ) {
            ASTArguments args = node.getFirstChildOfType(ASTArguments.class);
            if (args.getArgumentCount() == 1) {
                ASTLiteral literal = node.getFirstDescendantOfType(ASTLiteral.class);
                if (literal == null || literal.jjtGetParent().jjtGetParent().jjtGetParent().jjtGetParent().jjtGetParent() != args) {
                    return super.visit(node, data);
                }

                String img = literal.getImage();
                if (literal.isStringLiteral()) {
                    img = img.substring(1, img.length() - 1);
                }

                if ("0".equals(img) || "1".equals(img) || jdk15 && "10".equals(img)) {
                    addViolation(data, node);
                    return data;
                }
            }
        }
        return super.visit(node, data);
    }

}
