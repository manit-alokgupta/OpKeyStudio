package pcloudystudio.objectspy.element.tree;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.List;

import org.eclipse.jface.viewers.Viewer;

import pcloudystudio.objectspy.element.TreeMobileElement;
import pcloudystudio.objectspy.element.impl.providers.TypeCheckedTreeContentProvider;

public class MobileElementTreeContentProvider extends TypeCheckedTreeContentProvider<TreeMobileElement> {
	public Object[] getElements(final Object inputElement) {
		if (inputElement == null) {
			return null;
		}
		final Class<?> clazz = inputElement.getClass();
		if (clazz.isArray()) {
			return (Object[]) inputElement;
		}
		if (inputElement instanceof List) {
			return ((List<?>) inputElement).toArray();
		}
		return null;
	}

	public void dispose() {
	}

	public void inputChanged(final Viewer viewer, final Object oldInput, final Object newInput) {
	}

	protected Class<TreeMobileElement> getElementType() {
		return TreeMobileElement.class;
	}

	protected Object[] getChildElements(final TreeMobileElement parentElement) {
		return parentElement.getChildrenElement().toArray(new TreeMobileElement[0]);
	}

	protected Object getParentElement(final TreeMobileElement element) {
		return element.getParentElement();
	}

	protected boolean hasChildElements(final TreeMobileElement element) {
		return !element.getChildrenElement().isEmpty();
	}
}
