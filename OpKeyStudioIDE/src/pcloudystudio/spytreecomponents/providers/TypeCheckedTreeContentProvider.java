package pcloudystudio.spytreecomponents.providers;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.eclipse.jface.viewers.ITreeContentProvider;

public abstract class TypeCheckedTreeContentProvider<T> implements ITreeContentProvider {
	protected abstract Class<T> getElementType();

	private boolean isElementInstanceOf(Object element) {
		Class<?> clazz = this.getElementType();
		return clazz != null && clazz.isInstance(element);
	}

	@SuppressWarnings("unchecked")
	public Object[] getChildren(Object parentElement) {
		if (this.isElementInstanceOf(parentElement)) {
			return this.getChildElements((T) parentElement);
		}
		return null;
	}

	protected abstract Object[] getChildElements(T p0);

	@SuppressWarnings("unchecked")
	public Object getParent(Object element) {
		if (this.isElementInstanceOf(element)) {
			return this.getParentElement((T) element);
		}
		return null;
	}

	protected abstract Object getParentElement(T p0);

	@SuppressWarnings("unchecked")
	public boolean hasChildren(Object element) {
		return this.isElementInstanceOf(element) && this.hasChildElements((T) element);
	}

	protected abstract boolean hasChildElements(T p0);
}
