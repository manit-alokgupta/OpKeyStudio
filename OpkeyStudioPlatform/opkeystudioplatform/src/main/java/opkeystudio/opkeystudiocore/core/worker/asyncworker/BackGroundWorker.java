package opkeystudio.opkeystudiocore.core.worker.asyncworker;

public class BackGroundWorker extends Thread {
	private boolean taskCompleted = false;

	public boolean isTaskCompleted() {
		return taskCompleted;
	}

	public void setTaskCompleted(boolean taskCompleted) {
		this.taskCompleted = taskCompleted;
	}
}
