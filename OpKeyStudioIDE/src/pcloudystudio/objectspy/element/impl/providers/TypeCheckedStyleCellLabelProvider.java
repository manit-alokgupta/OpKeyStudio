package pcloudystudio.objectspy.element.impl.providers;

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

import pcloudystudio.objectspy.element.impl.control.CustomColumnViewer;

import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.jface.viewers.StyledCellLabelProvider;

public abstract class TypeCheckedStyleCellLabelProvider<T> extends StyledCellLabelProvider {
	private static final int DF_MARGIN = 0;
	protected int columnIndex;
	private TextLayout cachedTextLayout;
	private boolean customPaint;
	private int deltaOfLastMeasure;

	public TypeCheckedStyleCellLabelProvider(final int columnIndex) {
		this(columnIndex, true);
	}

	public TypeCheckedStyleCellLabelProvider(final int columnIndex, final boolean customPaint) {
		this.columnIndex = columnIndex;
		this.customPaint = customPaint;
	}

	protected abstract Class<T> getElementType();

	private boolean isElementInstanceOf(final Object element) {
		final Class<?> clazz = this.getElementType();
		return clazz != null && clazz.isInstance(element);
	}

	protected void paint(final Event event, final Object element) {
		if (this.canNotDrawSafely(element)) {
			super.paint(event, element);
			return;
		}
		final ViewerCell cell = this.getOwnedViewerCell(event);
		if (this.isCellNotExisted(cell)) {
			return;
		}
		final GC gc = event.gc;
		final boolean applyColors = this.useColors(event);
		final Color oldForeground = gc.getForeground();
		final Color oldBackground = gc.getBackground();
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

	protected final int getSpace() {
		final CellLayoutInfo layoutInfo = this.getCellLayoutInfo();
		return (layoutInfo != null) ? layoutInfo.getSpace() : 0;
	}

	protected final int getLeftMargin() {
		final CellLayoutInfo layoutInfo = this.getCellLayoutInfo();
		return (layoutInfo != null) ? layoutInfo.getLeftMargin() : 0;
	}

	protected final int getRightMargin() {
		final CellLayoutInfo layoutInfo = this.getCellLayoutInfo();
		return (layoutInfo != null) ? layoutInfo.getRightMargin() : 0;
	}

	protected boolean canNotDrawSafely(final Object element) {
		return !this.customPaint || !this.isElementInstanceOf(element)
				|| !(this.getViewer() instanceof CustomColumnViewer);
	}

	private void drawCellColor(final ViewerCell cell, final GC gc) {
		final Color foreground = cell.getForeground();
		if (foreground != null) {
			gc.setForeground(foreground);
		}
		final Color background = cell.getBackground();
		if (background != null) {
			gc.setBackground(background);
		}
	}

	private void drawCellFocus(final ViewerCell cell, final GC gc) {
		final Rectangle focusBounds = this.getTextBounds(cell.getViewerRow().getBounds());
		gc.drawFocus(focusBounds.x, focusBounds.y, focusBounds.width + this.deltaOfLastMeasure + this.getRightMargin(),
				focusBounds.height);
	}

	private boolean canDrawFocus(final Event event) {
		return (event.detail & 0x4) != 0x0;
	}

	protected void drawCellTextAndImage(final Event event, final ViewerCell cell, final GC gc) {
		final int startX = this.drawImage(event, cell, gc, cell.getImage());
		final Rectangle textBounds = this.getTextBounds(cell.getTextBounds());
		if (textBounds != null) {
			final TextLayout textLayout = this.getSharedTextLayout(event.display);
			final Rectangle layoutBounds = textLayout.getBounds();
			final int y = textBounds.y + Math.max(0, (textBounds.height - layoutBounds.height) / 2);
			textLayout.draw(gc, startX, y);
		}
	}

	protected int drawImage(final Event event, final ViewerCell cell, final GC gc, final Image image) {
		final Rectangle eventBounds = cell.getImageBounds();
		int startX = this.getLeftMargin();
		if (image != null) {
			final int y = eventBounds.y + Math.max(0, (eventBounds.height - image.getBounds().height) / 2);
			gc.drawImage(image, eventBounds.x + startX, y);
			startX += this.getSpace() + image.getBounds().width;
		}
		return startX + eventBounds.x;
	}

	protected Rectangle getTextBounds(final Rectangle originalBounds) {
		return originalBounds;
	}

	protected ViewerCell getOwnedViewerCell(final Event event) {
		final ColumnViewer columnViewer = this.getViewer();
		if (columnViewer instanceof CustomColumnViewer) {
			return ((CustomColumnViewer) columnViewer).getViewerRowFromWidgetItem(event.item).getCell(this.columnIndex);
		}
		return columnViewer.getCell(new Point(event.x, event.y));
	}

	protected void measure(final Event event, final Object element) {
		if (this.canNotDrawSafely(element)) {
			super.measure(event, element);
			return;
		}
		final ViewerCell cell = this.getOwnedViewerCell(event);
		if (this.isCellNotExisted(cell)) {
			return;
		}
		final boolean applyColors = this.useColors(event);
		final TextLayout layout = this.getSharedTextLayout(event.display);
		final int deltaOfLastMeasure = this.updateTextLayout(layout, cell, applyColors)
				+ this.updateImageLayout(event, cell);
		this.deltaOfLastMeasure = deltaOfLastMeasure;
		final int textWidthDelta = deltaOfLastMeasure;
		event.width += textWidthDelta + this.getRightMargin() + this.getSpace() + this.getLeftMargin();
	}

	protected int updateImageLayout(final Event layout, final ViewerCell cell) {
		return 0;
	}

	protected boolean isCellNotExisted(final ViewerCell cell) {
		return cell == null || cell.getViewerRow() == null;
	}

	protected boolean useColors(final Event event) {
		return (event.detail & 0x2) == 0x0;
	}

	private int updateTextLayout(final TextLayout layout, final ViewerCell cell, final boolean applyColors) {
		layout.setStyle((TextStyle) null, 0, Integer.MAX_VALUE);
		layout.setText(cell.getText());
		layout.setFont(cell.getFont());
		final int originalTextWidth = this.getTextBounds(layout.getBounds()).width;
		boolean containsOtherFont = false;
		final StyleRange[] styleRanges = cell.getStyleRanges();
		if (styleRanges != null) {
			for (int i = 0; i < styleRanges.length; ++i) {
				final StyleRange curr = this.prepareStyleRange(styleRanges[i], applyColors);
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

	protected TextLayout getSharedTextLayout(final Display display) {
		if (this.cachedTextLayout == null) {
			final int orientation = this.getViewer().getControl().getStyle() & 0x6000000;
			(this.cachedTextLayout = new TextLayout((Device) display)).setOrientation(orientation);
		}
		return this.cachedTextLayout;
	}

	@SuppressWarnings("unchecked")
	public void update(final ViewerCell cell) {
		final T element = (T) cell.getElement();
		cell.setText(this.getText(element));
		cell.setImage(this.getImage(element));
		cell.setBackground(this.getBackground(cell.getBackground(), element));
		cell.setForeground(this.getForeground(cell.getForeground(), element));
		cell.setStyleRanges(this.getStyleRanges(cell, element));
		super.update(cell);
	}

	@SuppressWarnings("unchecked")
	public String getToolTipText(final Object element) {
		if (this.isElementInstanceOf(element)) {
			return this.getElementToolTipText((T) element);
		}
		return super.getToolTipText(element);
	}

	protected String getElementToolTipText(final T element) {
		return null;
	}

	protected Color getBackground(final Color background, final T element) {
		return null;
	}

	protected Color getForeground(final Color foreground, final T element) {
		return null;
	}

	protected abstract Image getImage(final T p0);

	protected abstract String getText(final T p0);

	protected StyleRange[] getStyleRanges(final ViewerCell cell, final T element) {
		return null;
	}
}