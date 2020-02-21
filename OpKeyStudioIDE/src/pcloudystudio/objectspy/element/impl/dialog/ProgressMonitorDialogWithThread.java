package pcloudystudio.objectspy.element.impl.dialog;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.concurrent.ExecutionException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.Callable;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;

public class ProgressMonitorDialogWithThread extends ProgressMonitorDialog {
	private Thread thread;

	public ProgressMonitorDialogWithThread(final Shell parent) {
		super(parent);
	}

	private void setThread(final Thread thread) {
		this.thread = thread;
	}

	protected void cancelPressed() {
		if (this.thread != null && this.thread.isAlive()) {
			this.thread.interrupt();
		}
		super.cancelPressed();
	}

	private void startThreadAndWait() {
		if (this.thread == null) {
			return;
		}
		this.thread.run();
		while (this.thread.isAlive()) {
		}
	}

	public <V> V runAndWait(final Callable<V> callable) throws InterruptedException, InvocationTargetException {
		final FutureTask<V> futureTask = new FutureTask<V>(callable);
		this.setThread(new Thread(futureTask));
		try {
			this.startThreadAndWait();
			return futureTask.get();
		} catch (ExecutionException e) {
			throw new InvocationTargetException(e);
		}
	}
}
