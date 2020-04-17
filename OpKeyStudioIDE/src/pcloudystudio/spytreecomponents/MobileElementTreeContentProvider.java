package pcloudystudio.spytreecomponents;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import java.util.List;

import org.eclipse.jface.viewers.Viewer;

import pcloudystudio.spytreecomponents.providers.TypeCheckedTreeContentProvider;

public class MobileElementTreeContentProvider extends TypeCheckedTreeContentProvider<TreeMobileElement> {
	public Object[] getElements( Object inputElement) {
		if (inputElement == null) {
			return null;
		}
		Class<?> clazz = inputElement.getClass();
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

	public void inputChanged( Viewer viewer,  Object oldInput,  Object newInput) {
	}

	protected Class<TreeMobileElement> getElementType() {
		return TreeMobileElement.class;
	}

	protected Object[] getChildElements( TreeMobileElement parentElement) {
		return parentElement.getChildrenElement().toArray(new TreeMobileElement[0]);
	}

	protected Object getParentElement( TreeMobileElement element) {
		return element.getParentElement();
	}

	protected boolean hasChildElements( TreeMobileElement element) {
		return !element.getChildrenElement().isEmpty();
	}
}
