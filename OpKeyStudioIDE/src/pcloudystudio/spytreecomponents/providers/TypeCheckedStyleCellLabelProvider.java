package pcloudystudio.spytreecomponents.providers;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.graphics.TextStyle;
import org.eclipse.jface.viewers.ColumnViewer;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.widgets.Event;

import pcloudystudio.spytreecomponents.control.CustomColumnViewer;

import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.jface.viewers.StyledCellLabelProvider;

public abstract class TypeCheckedStyleCellLabelProvider<T> extends StyledCellLabelProvider {
	private static int DF_MARGIN = 0;
	protected int columnIndex;
	private TextLayout cachedTextLayout;
	private boolean customPaint;
	private int deltaOfLastMeasure;

	public TypeCheckedStyleCellLabelProvider(int columnIndex) {
		this(columnIndex, true);
	}

	public TypeCheckedStyleCellLabelProvider(int columnIndex, boolean customPaint) {
		this.columnIndex = columnIndex;
		this.customPaint = customPaint;
	}

	protected abstract Class<T> getElementType();

	private boolean isElementInstanceOf(Object element) {
		Class<?> clazz = this.getElementType();
		return clazz != null && clazz.isInstance(element);
	}

	protected void paint(Event event, Object element) {
		if (this.canNotDrawSafely(element)) {
			super.paint(event, element);
			return;
		}
		ViewerCell cell = this.getOwnedViewerCell(event);
		if (this.isCellNotExisted(cell)) {
			return;
		}
		GC gc = event.gc;
		boolean applyColors = this.useColors(event);
		Color oldForeground = gc.getForeground();
		Color oldBackground = gc.getBackground();
		if (applyColors) {
			this.drawCellColor(cell, gc);
		}
		this.drawCellTextAndImage(event, cell, gc);
		if (this.canDrawFocus(event)) {
			this.drawCellFocus(cell, gc);
		}
		if (applyColors) {
			gc.setForeground(oldForeground);
			gc.setBackground(oldBackground);
		}
	}

	public CellLayoutInfo getCellLayoutInfo() {
		return new DefaultCellLayoutInfo();
	}

	protected int getSpace() {
		CellLayoutInfo layoutInfo = this.getCellLayoutInfo();
		return (layoutInfo != null) ? layoutInfo.getSpace() : 0;
	}

	protected int getLeftMargin() {
		CellLayoutInfo layoutInfo = this.getCellLayoutInfo();
		return (layoutInfo != null) ? layoutInfo.getLeftMargin() : DF_MARGIN;
	}

	protected int getRightMargin() {
		CellLayoutInfo layoutInfo = this.getCellLayoutInfo();
		return (layoutInfo != null) ? layoutInfo.getRightMargin() : DF_MARGIN;
	}

	protected boolean canNotDrawSafely(Object element) {
		return !this.customPaint || !this.isElementInstanceOf(element)
				|| !(this.getViewer() instanceof CustomColumnViewer);
	}

	private void drawCellColor(ViewerCell cell, GC gc) {
		Color foreground = cell.getForeground();
		if (foreground != null) {
			gc.setForeground(foreground);
		}
		Color background = cell.getBackground();
		if (background != null) {
			gc.setBackground(background);
		}
	}

	private void drawCellFocus(ViewerCell cell, GC gc) {
		Rectangle focusBounds = this.getTextBounds(cell.getViewerRow().getBounds());
		gc.drawFocus(focusBounds.x, focusBounds.y, focusBounds.width + this.deltaOfLastMeasure + this.getRightMargin(),
				focusBounds.height);
	}

	private boolean canDrawFocus(Event event) {
		return (event.detail & 0x4) != 0x0;
	}

	protected void drawCellTextAndImage(Event event, ViewerCell cell, GC gc) {
		int startX = this.drawImage(event, cell, gc, cell.getImage());
		Rectangle textBounds = this.getTextBounds(cell.getTextBounds());
		if (textBounds != null) {
			TextLayout textLayout = this.getSharedTextLayout(event.display);
			Rectangle layoutBounds = textLayout.getBounds();
			int y = textBounds.y + Math.max(0, (textBounds.height - layoutBounds.height) / 2);
			textLayout.draw(gc, startX, y);
		}
	}

	protected int drawImage(Event event, ViewerCell cell, GC gc, Image image) {
		Rectangle eventBounds = cell.getImageBounds();
		int startX = this.getLeftMargin();
		if (image != null) {
			int y = eventBounds.y + Math.max(0, (eventBounds.height - image.getBounds().height) / 2);
			gc.drawImage(image, eventBounds.x + startX, y);
			startX += this.getSpace() + image.getBounds().width;
		}
		return startX + eventBounds.x;
	}

	protected Rectangle getTextBounds(Rectangle originalBounds) {
		return originalBounds;
	}

	protected ViewerCell getOwnedViewerCell(Event event) {
		ColumnViewer columnViewer = this.getViewer();
		if (columnViewer instanceof CustomColumnViewer) {
			return ((CustomColumnViewer) columnViewer).getViewerRowFromWidgetItem(event.item).getCell(this.columnIndex);
		}
		return columnViewer.getCell(new Point(event.x, event.y));
	}

	protected void measure(Event event, Object element) {
		if (this.canNotDrawSafely(element)) {
			super.measure(event, element);
			return;
		}
		ViewerCell cell = this.getOwnedViewerCell(event);
		if (this.isCellNotExisted(cell)) {
			return;
		}
		boolean applyColors = this.useColors(event);
		TextLayout layout = this.getSharedTextLayout(event.display);
		int deltaOfLastMeasure = this.updateTextLayout(layout, cell, applyColors) + this.updateImageLayout(event, cell);
		this.deltaOfLastMeasure = deltaOfLastMeasure;
		int textWidthDelta = deltaOfLastMeasure;
		event.width += textWidthDelta + this.getRightMargin() + this.getSpace() + this.getLeftMargin();
	}

	protected int updateImageLayout(Event layout, ViewerCell cell) {
		return 0;
	}

	protected boolean isCellNotExisted(ViewerCell cell) {
		return cell == null || cell.getViewerRow() == null;
	}

	protected boolean useColors(Event event) {
		return (event.detail & 0x2) == 0x0;
	}

	private int updateTextLayout(TextLayout layout, ViewerCell cell, boolean applyColors) {
		layout.setStyle((TextStyle) null, 0, Integer.MAX_VALUE);
		layout.setText(cell.getText());
		layout.setFont(cell.getFont());
		int originalTextWidth = this.getTextBounds(layout.getBounds()).width;
		boolean containsOtherFont = false;
		StyleRange[] styleRanges = cell.getStyleRanges();
		if (styleRanges != null) {
			for (int i = 0; i < styleRanges.length; ++i) {
				StyleRange curr = this.prepareStyleRange(styleRanges[i], applyColors);
				layout.setStyle((TextStyle) curr, curr.start, curr.start + curr.length - 1);
				if (curr.font != null) {
					containsOtherFont = true;
				}
			}
		}
		int textWidthDelta = 0;
		if (containsOtherFont) {
			textWidthDelta = this.getTextBounds(layout.getBounds()).width - originalTextWidth;
		}
		return textWidthDelta;
	}

	protected TextLayout getSharedTextLayout(Display display) {
		if (this.cachedTextLayout == null) {
			int orientation = this.getViewer().getControl().getStyle() & 0x6000000;
			(this.cachedTextLayout = new TextLayout((Device) display)).setOrientation(orientation);
		}
		return this.cachedTextLayout;
	}

	@SuppressWarnings("unchecked")
	public void update(ViewerCell cell) {
		T element = (T) cell.getElement();
		cell.setText(this.getText(element));
		cell.setImage(this.getImage(element));
		cell.setBackground(this.getBackground(cell.getBackground(), element));
		cell.setForeground(this.getForeground(cell.getForeground(), element));
		cell.setStyleRanges(this.getStyleRanges(cell, element));
		super.update(cell);
	}

	@SuppressWarnings("unchecked")
	public String getToolTipText(Object element) {
		if (this.isElementInstanceOf(element)) {
			return this.getElementToolTipText((T) element);
		}
		return super.getToolTipText(element);
	}

	protected String getElementToolTipText(T element) {
		return null;
	}

	protected Color getBackground(Color background, T element) {
		return null;
	}

	protected Color getForeground(Color foreground, T element) {
		return null;
	}

	protected abstract Image getImage(T p0);

	protected abstract String getText(T p0);

	protected StyleRange[] getStyleRanges(ViewerCell cell, T element) {
		return null;
	}
}