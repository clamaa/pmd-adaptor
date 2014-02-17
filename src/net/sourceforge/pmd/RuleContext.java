/**
 * BSD-style license; for more info see http://pmd.sourceforge.net/license.html
 */
package net.sourceforge.pmd;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.sourceforge.pmd.lang.ast.Node;
import net.sourceforge.pmd.lang.java.ast.JavaNode;
import net.sourceforge.pmd.lang.java.rule.JavaRuleViolation;

/**
 * The RuleContext provides access to Rule processing state. This information
 * includes the following global information:
 * <ul>
 * <li>The Report to which Rule Violations are sent.</li>
 * <li>Named attributes.</li>
 * </ul>
 * As well as the following source file specific information:
 * <ul>
 * <li>A File for the source file.</li>
 * <li>A String for the name of the source file.</li>
 * <li>The Language Version of the source file.</li>
 * </ul>
 * It is <strong>required</strong> that all source file specific options be set
 * between calls to difference source files. Failure to do so, may result in
 * undefined behavior.
 */
public class RuleContext {

	// private Report report = new Report();
	private File sourceCodeFile;
	private String sourceCodeFilename;
	// private LanguageVersion languageVersion;
	private final Map<String, Object> attributes;

	private List<RuleViolation> ruleViolationList = new ArrayList<RuleViolation>();

	/**
	 * Default constructor.
	 */
	public RuleContext() {
		attributes = new ConcurrentHashMap<String, Object>();
	}

	/**
	 * Constructor which shares attributes and report listeners with the given
	 * RuleContext.
	 */
	public RuleContext(RuleContext ruleContext) {
		this.attributes = ruleContext.attributes;
		// this.report.addSynchronizedListeners(ruleContext.getReport().getSynchronizedListeners());
	}

	// /**
	// * Get the Report to which Rule Violations are sent.
	// * @return The Report.
	// */
	// public Report getReport() {
	// return report;
	// }
	//
	// /**
	// * Set the Report to which Rule Violations are sent.
	// * @param report The Report.
	// */
	// public void setReport(Report report) {
	// this.report = report;
	// }

	/**
	 * Get the File associated with the current source file.
	 * 
	 * @return The File.
	 */
	public File getSourceCodeFile() {
		return sourceCodeFile;
	}

	/**
	 * Set the File associated with the current source file. While this may be
	 * set to <code>null</code>, the exclude/include facilities will not work
	 * properly without a File.
	 * 
	 * @param sourceCodeFile
	 *            The File.
	 */
	public void setSourceCodeFile(File sourceCodeFile) {
		this.sourceCodeFile = sourceCodeFile;
	}

	/**
	 * Get the file name associated with the current source file.
	 * 
	 * @return The file name.
	 */
	public String getSourceCodeFilename() {
		return sourceCodeFilename;
	}

	/**
	 * Set the file name associated with the current source file.
	 * 
	 * @param filename
	 *            The file name.
	 */
	public void setSourceCodeFilename(String filename) {
		this.sourceCodeFilename = filename;
	}

	// /**
	// * Get the LanguageVersion associated with the current source file.
	// * @return The LanguageVersion, <code>null</code> if unknown.
	// */
	// public LanguageVersion getLanguageVersion() {
	// return this.languageVersion;
	// }
	//
	// /**
	// * Set the LanguageVersion associated with the current source file.
	// * This may be set to <code>null</code> to indicate the version is
	// * unknown and should be automatically determined.
	// *
	// * @param languageVersion The LanguageVersion.
	// */
	// public void setLanguageVersion(LanguageVersion languageVersion) {
	// this.languageVersion = languageVersion;
	// }

	/**
	 * Set an attribute value on the RuleContext, if it does not already exist.
	 * <p>
	 * Attributes can be shared between RuleContext instances. This operation is
	 * thread-safe.
	 * <p>
	 * Attribute values should be modified directly via the reference provided.
	 * It is not necessary to call <code>setAttribute(String, Object)</code> to
	 * update an attribute value. Modifications made to the attribute value will
	 * automatically be seen by other threads. Because of this, you must ensure
	 * the attribute values are themselves thread safe.
	 * 
	 * @param name
	 *            The attribute name.
	 * @param value
	 *            The attribute value.
	 * @exception IllegalArgumentException
	 *                if <code>name</code> or <code> value</code> are
	 *                <code>null</code>
	 * @return <code>true</code> if the attribute was set, <code>false</code>
	 *         otherwise.
	 */
	public boolean setAttribute(String name, Object value) {
		if (name == null) {
			throw new IllegalArgumentException(
					"Parameter 'name' cannot be null.");
		}
		if (value == null) {
			throw new IllegalArgumentException(
					"Parameter 'value' cannot be null.");
		}
		synchronized (this.attributes) {
			if (!this.attributes.containsKey(name)) {
				this.attributes.put(name, value);
				return true;
			} else {
				return false;
			}
		}
	}

	/**
	 * Get an attribute value on the RuleContext.
	 * <p>
	 * Attributes can be shared between RuleContext instances. This operation is
	 * thread-safe.
	 * <p>
	 * Attribute values should be modified directly via the reference provided.
	 * It is not necessary to call <code>setAttribute(String, Object)</code> to
	 * update an attribute value. Modifications made to the attribute value will
	 * automatically be seen by other threads. Because of this, you must ensure
	 * the attribute values are themselves thread safe.
	 * 
	 * @param name
	 *            The attribute name.
	 * @return The current attribute value, or <code>null</code> if the
	 *         attribute does not exist.
	 */
	public Object getAttribute(String name) {
		return this.attributes.get(name);
	}

	/**
	 * Remove an attribute value on the RuleContext.
	 * <p>
	 * Attributes can be shared between RuleContext instances. This operation is
	 * thread-safe.
	 * <p>
	 * Attribute values should be modified directly via the reference provided.
	 * It is not necessary to call <code>setAttribute(String, Object)</code> to
	 * update an attribute value. Modifications made to the attribute value will
	 * automatically be seen by other threads. Because of this, you must ensure
	 * the attribute values are themselves thread safe.
	 * 
	 * @param name
	 *            The attribute name.
	 * @return The current attribute value, or <code>null</code> if the
	 *         attribute does not exist.
	 */
	public Object removeAttribute(String name) {
		return this.attributes.remove(name);
	}

	public void addRuleViolation(RuleContext ruleContext, Rule rule, Node node,
			String message, Object[] args) {
		JavaRuleViolation ruleViolation = new JavaRuleViolation(rule, this,
				(JavaNode) node, message);
		ruleViolationList.add(ruleViolation);
	}

	public void addRuleViolation(RuleContext ruleContext, Rule rule, Node node,
			String message, int beginLine, int endLine, Object[] args) {
		JavaRuleViolation ruleViolation = new JavaRuleViolation(rule, this,
				(JavaNode) node, message, beginLine, endLine);
		ruleViolationList.add(ruleViolation);
	}
	
	public void addRuleViolation(RuleViolation ruleViolation){
		ruleViolationList.add(ruleViolation);
	}
	
	public List<RuleViolation> getRuleViolationList() {
		return ruleViolationList;
	}
}
