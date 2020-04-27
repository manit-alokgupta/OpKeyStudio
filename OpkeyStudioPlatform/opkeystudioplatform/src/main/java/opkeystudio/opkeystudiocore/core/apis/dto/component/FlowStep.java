package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.ArrayList;
import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;
import opkeystudio.opkeystudiocore.core.query.DBField;

public class FlowStep extends Modified implements Comparable<FlowStep>, Cloneable {
	@DBField
	private String keywordid;
	@DBField
	private boolean wantsnapshot;
	@DBField
	private boolean continueonerror;
	@DBField
	private String flow_id;

	private int clustering_key;

	@DBField
	private String flow_stepid;
	@DBField
	private String comment;
	@DBField
	private boolean isnegative;
	@DBField
	private boolean shouldrun;
	@DBField
	private int position;

	private Keyword keyword;

	private FunctionLibraryComponent functionLibraryComponent;
	private CodedFunctionArtifact codedFunctionArtifact;

	private List<ORObject> orObject = new ArrayList<ORObject>();

	private List<FlowInputArgument> flowInputArgs = new ArrayList<FlowInputArgument>();

	private List<FlowOutputArgument> flowOutputArgs = new ArrayList<FlowOutputArgument>();

	@SuppressWarnings("unused")
	private List<ComponentInputArgument> componentInputArgs = new ArrayList<ComponentInputArgument>();

	@SuppressWarnings("unused")
	private List<ComponentOutputArgument> componentOutputArgs = new ArrayList<ComponentOutputArgument>();

	@DBField
	private String component_id;

	@DBField
	private String codedfunction_id;
	@DBField
	private String stepcodedfunction_id;
	@DBField
	private String comments;
	@DBField
	private String stepid;
	@DBField
	private String stepcomponent_id;
	private boolean istestcase;
	private boolean isfunctionlibrary;
	private boolean istestsuites;

	private boolean NullKeyword = false;

	private String variableName;

	public String getKeywordid() {
		return keywordid;
	}

	public void setKeywordid(String keywordid) {
		this.keywordid = keywordid;
	}

	public boolean isWantsnapshot() {
		return wantsnapshot;
	}

	public void setWantsnapshot(boolean wantsnapshot) {
		this.wantsnapshot = wantsnapshot;
	}

	public boolean isContinueonerror() {
		return continueonerror;
	}

	public void setContinueonerror(boolean continueonerror) {
		this.continueonerror = continueonerror;
	}

	public String getFlow_id() {
		return flow_id;
	}

	public void setFlow_id(String flow_id) {
		this.flow_id = flow_id;
	}

	public int getClustering_key() {
		return clustering_key;
	}

	public void setClustering_key(int clustering_key) {
		this.clustering_key = clustering_key;
	}

	public String getFlow_stepid() {
		return flow_stepid;
	}

	public void setFlow_stepid(String flow_stepid) {
		this.flow_stepid = flow_stepid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isIsnegative() {
		return isnegative;
	}

	public void setIsnegative(boolean isnegative) {
		this.isnegative = isnegative;
	}

	public boolean isShouldrun() {
		return shouldrun;
	}

	public void setShouldrun(boolean shouldrun) {
		this.shouldrun = shouldrun;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public List<FlowInputArgument> getFlowInputArgs() {
		return flowInputArgs;
	}

	public void setFlowInputArgs(List<FlowInputArgument> flowInputArgs) {
		this.flowInputArgs = flowInputArgs;
	}

	public List<FlowOutputArgument> getFlowOutputArgs() {
		return flowOutputArgs;
	}

	public void setFlowOutputArgs(List<FlowOutputArgument> flowOutputArgs) {
		this.flowOutputArgs = flowOutputArgs;
	}

	public Keyword getKeyword() {
		return keyword;
	}

	public void setKeyword(Keyword keyword) {
		this.keyword = keyword;
	}

	public List<ORObject> getOrObject() {
		return orObject;
	}

	public void setOrObject(List<ORObject> orObject) {
		this.orObject = orObject;
	}

	@Override
	public int compareTo(FlowStep arg0) {
		return this.getPosition() - arg0.getPosition();
	}

	public String getComponent_id() {
		return component_id;
	}

	public void setComponent_id(String component_id) {
		this.component_id = component_id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getStepid() {
		return stepid;
	}

	public void setStepid(String stepid) {
		this.stepid = stepid;
	}

	public FunctionLibraryComponent getFunctionLibraryComponent() {
		return functionLibraryComponent;
	}

	public void setFunctionLibraryComponent(FunctionLibraryComponent functionLibraryComponent) {
		this.functionLibraryComponent = functionLibraryComponent;
	}

	public boolean isIstestcase() {
		return istestcase;
	}

	public void setIsTestCaseStep(boolean istestcase) {
		this.istestcase = istestcase;
	}

	public boolean isIsfunctionlibrary() {
		return isfunctionlibrary;
	}

	public void setIsfunctionlibraryStep(boolean isfunctionlibrary) {
		this.isfunctionlibrary = isfunctionlibrary;
	}

	public boolean isIstestsuites() {
		return istestsuites;
	}

	public void setIstestsuites(boolean istestsuites) {
		this.istestsuites = istestsuites;
	}

	public String getStepcomponent_id() {
		return stepcomponent_id;
	}

	public void setStepcomponent_id(String stepcomponent_id) {
		this.stepcomponent_id = stepcomponent_id;
	}

	public boolean isNullKeyword() {
		return NullKeyword;
	}

	public void setNullKeyword(boolean nullKeyword) {
		NullKeyword = nullKeyword;
	}

	public FlowStep clone() {
		try {
			return (FlowStep) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String getVariableName() {
		setVariableName("step" + getClustering_key());
		return variableName;
	}

	public void setVariableName(String variableName) {
		this.variableName = variableName;
	}

	public CodedFunctionArtifact getCodedFunctionArtifact() {
		return codedFunctionArtifact;
	}

	public void setCodedFunctionArtifact(CodedFunctionArtifact codedFunctionArtifact) {
		this.codedFunctionArtifact = codedFunctionArtifact;
	}

	public String getCodedfunction_id() {
		return codedfunction_id;
	}

	public void setCodedfunction_id(String codedfunction_id) {
		this.codedfunction_id = codedfunction_id;
	}

	public String getStepcodedfunction_id() {
		return stepcodedfunction_id;
	}

	public void setStepcodedfunction_id(String stepcodedfunction_id) {
		this.stepcodedfunction_id = stepcodedfunction_id;
	}
}
