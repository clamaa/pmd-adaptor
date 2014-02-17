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
 * pmd源码扫描处理工具类
 * 
 * @author mazhqa
 * @since V2.3
 */
public final class SourceCodeProcessor {

	/**
	 * 解析规则文件，得出解析结果上下文
	 * 
	 * @param filePath
	 *            - 源代码文件路径
	 * @param rule
	 *            - 要执行的规则实现
	 * @return
	 * @throws FileNotFoundException
	 */
	public static RuleContext parseRule(String filePath, Rule rule) throws FileNotFoundException {
		ASTCompilationUnit astCompilationUnit = parseJavaFile(filePath);
		return executeRuleValidator(astCompilationUnit, rule);
	}

	/**
	 * 将Java源文件解析成对应的编译单元用于分析，通常此方法用于获得编译单元的comments列表
	 * 
	 * @param filePath
	 *            - java源文件路径
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
	 * 执行规则验证
	 * 
	 * @param astCompilationUnit - 编译单元
	 * @param rule - 对应的规则
	 * @return
	 */
	public static RuleContext executeRuleValidator(ASTCompilationUnit astCompilationUnit, Rule rule) {
		RuleContext ruleContext = new RuleContext();
		if (astCompilationUnit.jjtGetNumChildren() > 0) {
			// 忽略编译单元为空，即类中无任何信息的类文件
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
	 * 将规则执行的错误结果整理成字符串
	 * 
	 * @param ruleViolationList
	 * @return
	 */
	public static String generateErrorDetail(List<RuleViolation> ruleViolationList) {
		StringBuilder result = new StringBuilder();
		for (RuleViolation ruleViolation : ruleViolationList) {
			String violationDetail = String.format("开始(行:%s,列:%s), 结束(行:%s,列:%s),方法:%s 中出现错误: %s\n",
					ruleViolation.getBeginLine(), ruleViolation.getBeginColumn(), ruleViolation.getEndLine(),
					ruleViolation.getEndColumn(), ruleViolation.getMethodName(), ruleViolation.getDescription());
			result.append(violationDetail);
		}
		return result.toString();
	}

}
