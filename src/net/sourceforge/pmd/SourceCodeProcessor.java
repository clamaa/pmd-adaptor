package net.sourceforge.pmd;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import net.sourceforge.pmd.lang.VisitorStarter;
import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.Java17Parser;
import net.sourceforge.pmd.lang.java.ast.ASTCompilationUnit;
import net.sourceforge.pmd.lang.java.symboltable.SymbolFacade;

/**
 * pmdԴ��ɨ�账������
 * 
 * @author mazhqa
 * @since V2.3
 */
public final class SourceCodeProcessor {

	/**
	 * ���������ļ����ó��������������
	 * 
	 * @param filePath
	 *            - Դ�����ļ�·��
	 * @param rule
	 *            - Ҫִ�еĹ���ʵ��
	 * @return
	 * @throws FileNotFoundException
	 */
	public static RuleContext parseRule(String filePath, Rule rule) throws FileNotFoundException {
		ASTCompilationUnit astCompilationUnit = parseJavaFile(filePath);
		return executeRuleValidator(astCompilationUnit, rule);
	}

	/**
	 * ��JavaԴ�ļ������ɶ�Ӧ�ı��뵥Ԫ���ڷ�����ͨ���˷������ڻ�ñ��뵥Ԫ��comments�б�
	 * 
	 * @param filePath
	 *            - javaԴ�ļ�·��
	 * @return
	 * @throws FileNotFoundException
	 */
	public static ASTCompilationUnit parseJavaFile(String filePath) throws FileNotFoundException {
		Java17Parser parser = new Java17Parser();
		InputStreamReader sourceReader = new InputStreamReader(new FileInputStream(filePath));
		Node rootNode = parser.parse(filePath, sourceReader);
		ASTCompilationUnit astCompilationUnit = (ASTCompilationUnit) rootNode;
		return astCompilationUnit;
	}

	/**
	 * ִ�й�����֤
	 * 
	 * @param astCompilationUnit - ���뵥Ԫ
	 * @param rule - ��Ӧ�Ĺ���
	 * @return
	 */
	public static RuleContext executeRuleValidator(ASTCompilationUnit astCompilationUnit, Rule rule) {
		RuleContext ruleContext = new RuleContext();
		if (astCompilationUnit.jjtGetNumChildren() > 0) {
			// ���Ա��뵥ԪΪ�գ����������κ���Ϣ�����ļ�
			VisitorStarter visitorStarter = new VisitorStarter() {
				public void start(Node rootNode) {
					new SymbolFacade().initializeWith((ASTCompilationUnit) rootNode);
				}
			};
			visitorStarter.start(astCompilationUnit);
			ArrayList<Node> nodeList = new ArrayList<Node>();
			nodeList.add(astCompilationUnit);
			rule.apply(nodeList, ruleContext);
		}
		return ruleContext;
	}

	/**
	 * ������ִ�еĴ�����������ַ���
	 * 
	 * @param ruleViolationList
	 * @return
	 */
	public static String generateErrorDetail(List<RuleViolation> ruleViolationList) {
		StringBuilder result = new StringBuilder();
		for (RuleViolation ruleViolation : ruleViolationList) {
			String violationDetail = String.format("��ʼ(��:%s,��:%s), ����(��:%s,��:%s),����:%s �г��ִ���: %s\n",
					ruleViolation.getBeginLine(), ruleViolation.getBeginColumn(), ruleViolation.getEndLine(),
					ruleViolation.getEndColumn(), ruleViolation.getMethodName(), ruleViolation.getDescription());
			result.append(violationDetail);
		}
		return result.toString();
	}

}
