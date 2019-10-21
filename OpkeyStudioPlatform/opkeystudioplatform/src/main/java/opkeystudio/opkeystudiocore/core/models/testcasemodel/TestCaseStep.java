package opkeystudio.opkeystudiocore.core.models.testcasemodel;

import opkeystudio.opkeystudiocore.core.models.objectrepositorymodel.ORObject;

public class TestCaseStep {
	private KeyWord keyword;
	private ORObject orObject;
	private InputValue inputValue;
	private OutputValue outputValue;

	public TestCaseStep() {

	}

	public TestCaseStep(KeyWord keyword, ORObject orobject, InputValue inputValue, OutputValue outputValue) {
		setKeyword(keyword);
		setOrObject(orobject);
		setInputValue(inputValue);
		setOutputValue(outputValue);
	}

	public KeyWord getKeyword() {
		return keyword;
	}

	private void setKeyword(KeyWord keyword) {
		this.keyword = keyword;
	}

	public ORObject getOrObject() {
		return orObject;
	}

	private void setOrObject(ORObject orObject) {
		this.orObject = orObject;
	}

	public InputValue getInputValue() {
		return inputValue;
	}

	private void setInputValue(InputValue inputValue) {
		this.inputValue = inputValue;
	}

	public OutputValue getOutputValue() {
		return outputValue;
	}

	private void setOutputValue(OutputValue outputValue) {
		this.outputValue = outputValue;
	}


}
