package net.sourceforge.pmd.lang.java.ast;

public abstract interface JavaParserVisitor
{
  public abstract Object visit(JavaNode paramJavaNode, Object paramObject);

  public abstract Object visit(ASTCompilationUnit paramASTCompilationUnit, Object paramObject);

  public abstract Object visit(ASTPackageDeclaration paramASTPackageDeclaration, Object paramObject);

  public abstract Object visit(ASTImportDeclaration paramASTImportDeclaration, Object paramObject);

  public abstract Object visit(ASTTypeDeclaration paramASTTypeDeclaration, Object paramObject);

  public abstract Object visit(ASTClassOrInterfaceDeclaration paramASTClassOrInterfaceDeclaration, Object paramObject);

  public abstract Object visit(ASTExtendsList paramASTExtendsList, Object paramObject);

  public abstract Object visit(ASTImplementsList paramASTImplementsList, Object paramObject);

  public abstract Object visit(ASTEnumDeclaration paramASTEnumDeclaration, Object paramObject);

  public abstract Object visit(ASTEnumBody paramASTEnumBody, Object paramObject);

  public abstract Object visit(ASTEnumConstant paramASTEnumConstant, Object paramObject);

  public abstract Object visit(ASTTypeParameters paramASTTypeParameters, Object paramObject);

  public abstract Object visit(ASTTypeParameter paramASTTypeParameter, Object paramObject);

  public abstract Object visit(ASTTypeBound paramASTTypeBound, Object paramObject);

  public abstract Object visit(ASTClassOrInterfaceBody paramASTClassOrInterfaceBody, Object paramObject);

  public abstract Object visit(ASTClassOrInterfaceBodyDeclaration paramASTClassOrInterfaceBodyDeclaration, Object paramObject);

  public abstract Object visit(ASTFieldDeclaration paramASTFieldDeclaration, Object paramObject);

  public abstract Object visit(ASTVariableDeclarator paramASTVariableDeclarator, Object paramObject);

  public abstract Object visit(ASTVariableDeclaratorId paramASTVariableDeclaratorId, Object paramObject);

  public abstract Object visit(ASTVariableInitializer paramASTVariableInitializer, Object paramObject);

  public abstract Object visit(ASTArrayInitializer paramASTArrayInitializer, Object paramObject);

  public abstract Object visit(ASTMethodDeclaration paramASTMethodDeclaration, Object paramObject);

  public abstract Object visit(ASTMethodDeclarator paramASTMethodDeclarator, Object paramObject);

  public abstract Object visit(ASTFormalParameters paramASTFormalParameters, Object paramObject);

  public abstract Object visit(ASTFormalParameter paramASTFormalParameter, Object paramObject);

  public abstract Object visit(ASTConstructorDeclaration paramASTConstructorDeclaration, Object paramObject);

  public abstract Object visit(ASTExplicitConstructorInvocation paramASTExplicitConstructorInvocation, Object paramObject);

  public abstract Object visit(ASTInitializer paramASTInitializer, Object paramObject);

  public abstract Object visit(ASTType paramASTType, Object paramObject);

  public abstract Object visit(ASTReferenceType paramASTReferenceType, Object paramObject);

  public abstract Object visit(ASTClassOrInterfaceType paramASTClassOrInterfaceType, Object paramObject);

  public abstract Object visit(ASTTypeArguments paramASTTypeArguments, Object paramObject);

  public abstract Object visit(ASTTypeArgument paramASTTypeArgument, Object paramObject);

  public abstract Object visit(ASTWildcardBounds paramASTWildcardBounds, Object paramObject);

  public abstract Object visit(ASTPrimitiveType paramASTPrimitiveType, Object paramObject);

  public abstract Object visit(ASTResultType paramASTResultType, Object paramObject);

  public abstract Object visit(ASTName paramASTName, Object paramObject);

  public abstract Object visit(ASTNameList paramASTNameList, Object paramObject);

  public abstract Object visit(ASTExpression paramASTExpression, Object paramObject);

  public abstract Object visit(ASTAssignmentOperator paramASTAssignmentOperator, Object paramObject);

  public abstract Object visit(ASTConditionalExpression paramASTConditionalExpression, Object paramObject);

  public abstract Object visit(ASTConditionalOrExpression paramASTConditionalOrExpression, Object paramObject);

  public abstract Object visit(ASTConditionalAndExpression paramASTConditionalAndExpression, Object paramObject);

  public abstract Object visit(ASTInclusiveOrExpression paramASTInclusiveOrExpression, Object paramObject);

  public abstract Object visit(ASTExclusiveOrExpression paramASTExclusiveOrExpression, Object paramObject);

  public abstract Object visit(ASTAndExpression paramASTAndExpression, Object paramObject);

  public abstract Object visit(ASTEqualityExpression paramASTEqualityExpression, Object paramObject);

  public abstract Object visit(ASTInstanceOfExpression paramASTInstanceOfExpression, Object paramObject);

  public abstract Object visit(ASTRelationalExpression paramASTRelationalExpression, Object paramObject);

  public abstract Object visit(ASTShiftExpression paramASTShiftExpression, Object paramObject);

  public abstract Object visit(ASTAdditiveExpression paramASTAdditiveExpression, Object paramObject);

  public abstract Object visit(ASTMultiplicativeExpression paramASTMultiplicativeExpression, Object paramObject);

  public abstract Object visit(ASTUnaryExpression paramASTUnaryExpression, Object paramObject);

  public abstract Object visit(ASTPreIncrementExpression paramASTPreIncrementExpression, Object paramObject);

  public abstract Object visit(ASTPreDecrementExpression paramASTPreDecrementExpression, Object paramObject);

  public abstract Object visit(ASTUnaryExpressionNotPlusMinus paramASTUnaryExpressionNotPlusMinus, Object paramObject);

  public abstract Object visit(ASTPostfixExpression paramASTPostfixExpression, Object paramObject);

  public abstract Object visit(ASTCastExpression paramASTCastExpression, Object paramObject);

  public abstract Object visit(ASTPrimaryExpression paramASTPrimaryExpression, Object paramObject);

  public abstract Object visit(ASTMemberSelector paramASTMemberSelector, Object paramObject);

  public abstract Object visit(ASTPrimaryPrefix paramASTPrimaryPrefix, Object paramObject);

  public abstract Object visit(ASTPrimarySuffix paramASTPrimarySuffix, Object paramObject);

  public abstract Object visit(ASTLiteral paramASTLiteral, Object paramObject);

  public abstract Object visit(ASTBooleanLiteral paramASTBooleanLiteral, Object paramObject);

  public abstract Object visit(ASTNullLiteral paramASTNullLiteral, Object paramObject);

  public abstract Object visit(ASTArguments paramASTArguments, Object paramObject);

  public abstract Object visit(ASTArgumentList paramASTArgumentList, Object paramObject);

  public abstract Object visit(ASTAllocationExpression paramASTAllocationExpression, Object paramObject);

  public abstract Object visit(ASTArrayDimsAndInits paramASTArrayDimsAndInits, Object paramObject);

  public abstract Object visit(ASTStatement paramASTStatement, Object paramObject);

  public abstract Object visit(ASTLabeledStatement paramASTLabeledStatement, Object paramObject);

  public abstract Object visit(ASTBlock paramASTBlock, Object paramObject);

  public abstract Object visit(ASTBlockStatement paramASTBlockStatement, Object paramObject);

  public abstract Object visit(ASTLocalVariableDeclaration paramASTLocalVariableDeclaration, Object paramObject);

  public abstract Object visit(ASTEmptyStatement paramASTEmptyStatement, Object paramObject);

  public abstract Object visit(ASTStatementExpression paramASTStatementExpression, Object paramObject);

  public abstract Object visit(ASTSwitchStatement paramASTSwitchStatement, Object paramObject);

  public abstract Object visit(ASTSwitchLabel paramASTSwitchLabel, Object paramObject);

  public abstract Object visit(ASTIfStatement paramASTIfStatement, Object paramObject);

  public abstract Object visit(ASTWhileStatement paramASTWhileStatement, Object paramObject);

  public abstract Object visit(ASTDoStatement paramASTDoStatement, Object paramObject);

  public abstract Object visit(ASTForStatement paramASTForStatement, Object paramObject);

  public abstract Object visit(ASTForInit paramASTForInit, Object paramObject);

  public abstract Object visit(ASTStatementExpressionList paramASTStatementExpressionList, Object paramObject);

  public abstract Object visit(ASTForUpdate paramASTForUpdate, Object paramObject);

  public abstract Object visit(ASTBreakStatement paramASTBreakStatement, Object paramObject);

  public abstract Object visit(ASTContinueStatement paramASTContinueStatement, Object paramObject);

  public abstract Object visit(ASTReturnStatement paramASTReturnStatement, Object paramObject);

  public abstract Object visit(ASTThrowStatement paramASTThrowStatement, Object paramObject);

  public abstract Object visit(ASTSynchronizedStatement paramASTSynchronizedStatement, Object paramObject);

  public abstract Object visit(ASTTryStatement paramASTTryStatement, Object paramObject);

  public abstract Object visit(ASTResourceSpecification paramASTResourceSpecification, Object paramObject);

  public abstract Object visit(ASTResources paramASTResources, Object paramObject);

  public abstract Object visit(ASTResource paramASTResource, Object paramObject);

  public abstract Object visit(ASTCatchStatement paramASTCatchStatement, Object paramObject);

  public abstract Object visit(ASTFinallyStatement paramASTFinallyStatement, Object paramObject);

  public abstract Object visit(ASTAssertStatement paramASTAssertStatement, Object paramObject);

  public abstract Object visit(ASTRUNSIGNEDSHIFT paramASTRUNSIGNEDSHIFT, Object paramObject);

  public abstract Object visit(ASTRSIGNEDSHIFT paramASTRSIGNEDSHIFT, Object paramObject);

  public abstract Object visit(ASTAnnotation paramASTAnnotation, Object paramObject);

  public abstract Object visit(ASTNormalAnnotation paramASTNormalAnnotation, Object paramObject);

  public abstract Object visit(ASTMarkerAnnotation paramASTMarkerAnnotation, Object paramObject);

  public abstract Object visit(ASTSingleMemberAnnotation paramASTSingleMemberAnnotation, Object paramObject);

  public abstract Object visit(ASTMemberValuePairs paramASTMemberValuePairs, Object paramObject);

  public abstract Object visit(ASTMemberValuePair paramASTMemberValuePair, Object paramObject);

  public abstract Object visit(ASTMemberValue paramASTMemberValue, Object paramObject);

  public abstract Object visit(ASTMemberValueArrayInitializer paramASTMemberValueArrayInitializer, Object paramObject);

  public abstract Object visit(ASTAnnotationTypeDeclaration paramASTAnnotationTypeDeclaration, Object paramObject);

  public abstract Object visit(ASTAnnotationTypeBody paramASTAnnotationTypeBody, Object paramObject);

  public abstract Object visit(ASTAnnotationTypeMemberDeclaration paramASTAnnotationTypeMemberDeclaration, Object paramObject);

  public abstract Object visit(ASTAnnotationMethodDeclaration paramASTAnnotationMethodDeclaration, Object paramObject);

  public abstract Object visit(ASTDefaultValue paramASTDefaultValue, Object paramObject);
}