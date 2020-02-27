package pcloudystudio.objectspy.dialog;

import pcloudystudio.objectspy.element.impl.CapturedMobileElement;

public interface MobileElementDialog {
	void updateSelectedElement(final CapturedMobileElement element);

	void updateCapturedElementSelectingColumnHeader();
}
