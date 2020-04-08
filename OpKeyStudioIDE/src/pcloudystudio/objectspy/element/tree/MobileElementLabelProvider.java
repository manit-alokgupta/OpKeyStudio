package pcloudystudio.objectspy.element.tree;

// Created by Alok Gupta on 20/02/2020.
// Copyright � 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.resource.FontDescriptor;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.swt.widgets.Display;

import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.providers.TypeCheckedStyleTreeCellLabelProvider;
import pcloudystudio.objectspy.resources.image.ImageManager;

public class MobileElementLabelProvider extends TypeCheckedStyleTreeCellLabelProvider<TreeMobileElement> {
	public MobileElementLabelProvider() {
		super(0);
	}

	protected Class<TreeMobileElement> getElementType() {
		return TreeMobileElement.class;
	}

	protected Image getImage(final TreeMobileElement element) {
		return ImageManager.getImage("pcloudystudio/start_spy.png");
	}

	protected String getElementToolTipText(final TreeMobileElement element) {
		return this.getText(element);
	}

	protected String getText(final TreeMobileElement element) {
		return element.getName();
	}
	
	@SuppressWarnings("unused")
	private class BoldStyler extends StyledString.Styler {
		private Font currentFont;

		private BoldStyler(final Font font) {
			this.currentFont = font;
		}

		public void applyStyles(final TextStyle textStyle) {
			final FontDescriptor boldDescriptor = FontDescriptor.createFrom(this.currentFont.getFontData()[0])
					.setStyle(1);
			final Font boldFont = boldDescriptor.createFont((Device) Display.getCurrent());
			// textStyle.foreground = ColorUtil.getTextSuccessfulColor();
			textStyle.font = boldFont;
		}
	}
}