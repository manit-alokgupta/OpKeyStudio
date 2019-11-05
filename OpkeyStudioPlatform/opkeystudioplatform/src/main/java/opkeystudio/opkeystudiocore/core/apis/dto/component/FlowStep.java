package opkeystudio.opkeystudiocore.core.apis.dto.component;

import java.util.List;

import opkeystudio.opkeystudiocore.core.apis.dto.Modified;

public class FlowStep extends Modified {
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

	private List<FlowInputArguments> flowInputArgs;
	private List<FlowOutputArgument> flowOutputArgs;

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

	public List<FlowInputArguments> getFlowInputArgs() {
		return flowInputArgs;
	}

	public void setFlowInputArgs(List<FlowInputArguments> flowInputArgs) {
		this.flowInputArgs = flowInputArgs;
	}

	public List<FlowOutputArgument> getFlowOutputArgs() {
		return flowOutputArgs;
	}

	public void setFlowOutputArgs(List<FlowOutputArgument> flowOutputArgs) {
		this.flowOutputArgs = flowOutputArgs;
	}
}
