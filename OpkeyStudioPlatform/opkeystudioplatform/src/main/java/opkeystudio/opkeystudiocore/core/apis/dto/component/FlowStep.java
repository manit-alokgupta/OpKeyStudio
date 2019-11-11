package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;
import opkeystudio.opkeystudiocore.core.keywordmanager.dto.Keyword;

public class FlowStep extends Modified implements Comparable<FlowStep> {
	private String keywordid;
	private boolean wantsnapshot;
	private boolean continueonerror;
	private String flow_id;
	private int clustering_key;
	private String flow_stepid;
	private String comment;
	private boolean isnegative;
	private boolean shouldrun;
	private int position;

	private Keyword keyword;
	private FunctionLibraryComponent functionLibraryComponent;
	private List<ORObject> orObject;
	private List<FlowInputArgument> flowInputArgs;
	private List<FlowOutputArgument> flowOutputArgs;
	private String component_id;
	private String comments;
	private String stepid;

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
}
