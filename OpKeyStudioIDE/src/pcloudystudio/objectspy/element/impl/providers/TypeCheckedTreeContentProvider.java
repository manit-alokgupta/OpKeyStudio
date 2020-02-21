package pcloudystudio.objectspy.element.impl.providers;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.ITreeContentProvider;

public abstract class TypeCheckedTreeContentProvider<T> implements ITreeContentProvider {
	protected abstract Class<T> getElementType();

	private boolean isElementInstanceOf(final Object element) {
		final Class<?> clazz = this.getElementType();
		return clazz != null && clazz.isInstance(element);
	}

	@SuppressWarnings("unchecked")
	public Object[] getChildren(final Object parentElement) {
		if (this.isElementInstanceOf(parentElement)) {
			return this.getChildElements((T) parentElement);
		}
		return null;
	}

	protected abstract Object[] getChildElements(final T p0);

	@SuppressWarnings("unchecked")
	public Object getParent(final Object element) {
		if (this.isElementInstanceOf(element)) {
			return this.getParentElement((T) element);
		}
		return null;
	}

	protected abstract Object getParentElement(final T p0);

	@SuppressWarnings("unchecked")
	public boolean hasChildren(final Object element) {
		return this.isElementInstanceOf(element) && this.hasChildElements((T) element);
	}

	protected abstract boolean hasChildElements(final T p0);
}
