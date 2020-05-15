package opkeystudio.commandhandler;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.workbench.modeling.ISaveHandler;

import pcloudystudio.core.utils.notification.CustomNotificationUtil;
import pcloudystudio.core.utils.notification.CustomNotificationUtil.DialogResult;

public class CustomSaveHandler implements ISaveHandler {

	// https://github.com/eclipse/eclipse.platform.ui/blob/master/bundles/org.eclipse.e4.ui.workbench/src/org/eclipse/e4/ui/internal/workbench/PartServiceSaveHandler.java

	@Override
	public boolean save(MPart dirtyPart, boolean confirm) {
		if (confirm) {
			switch (promptToSave(dirtyPart)) {
			case NO:
				return true;
			case CANCEL:
				return false;
			case YES:
				break;
			}
		}
		Object client = dirtyPart.getObject();
		try {
			ContextInjectionFactory.invoke(client, Persist.class, dirtyPart.getContext());
		} catch (InjectionException e) {
			e.printStackTrace();
			return false;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean saveParts(Collection<MPart> dirtyParts, boolean confirm) {
		if (confirm) {
			List<MPart> dirtyPartsList = Collections.unmodifiableList(new ArrayList<>(dirtyParts));
			Save[] decisions = promptToSave(dirtyPartsList);
			for (Save decision : decisions) {
				if (decision == Save.CANCEL) {
					return false;
				}
			}

			for (int i = 0; i < decisions.length; i++) {
				if (decisions[i] == Save.YES) {
					if (!save(dirtyPartsList.get(i), false)) {
						return false;
					}
				}
			}
			return true;
		}

		for (MPart dirtyPart : dirtyParts) {
			if (!save(dirtyPart, false)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public Save promptToSave(MPart dirtyPart) {

		DialogResult response = CustomNotificationUtil.openConfirmDialog("OpKey", "Do you want to save changes to " + dirtyPart.getLabel() + "?");
		switch (response) {
		case Yes:
			return Save.YES;
		case No:
			return Save.NO;
		case Cancel:
			return Save.CANCEL;
		default:
			return null;

		}

	}

	@Override
	public Save[] promptToSave(Collection<MPart> dirtyParts) {
		MPart[] arr = dirtyParts.toArray(new MPart[dirtyParts.size()]);

		Save[] rc = new Save[arr.length];
		Boolean wasCancelled = false;
		for (int i = 0; i < rc.length; i++) {
			if (wasCancelled) {
				rc[i] = Save.CANCEL;
			} else {
				rc[i] = this.promptToSave(arr[i]);
				if (rc[i] == Save.CANCEL)
					wasCancelled = true;
			}
		}
		return rc;
	}

}
