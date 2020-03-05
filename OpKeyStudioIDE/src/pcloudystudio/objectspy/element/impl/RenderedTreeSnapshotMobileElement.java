package pcloudystudio.objectspy.element.impl;

// Created by Alok Gupta on 20/02/2020.
// Copyright © 2020 SSTS Inc. All rights reserved.

import org.apache.commons.lang3.StringUtils;

import pcloudystudio.objectspy.element.SnapshotMobileElement;

import java.util.ArrayList;
import java.util.List;

public abstract class RenderedTreeSnapshotMobileElement<T> extends BasicMobileElement implements SnapshotMobileElement<T>
{
    private static final long serialVersionUID = -6452866868131771671L;
    private final RenderedTreeSnapshotMobileElement<T> parentElement;
    private List<RenderedTreeSnapshotMobileElement<T>> childrenElement;
    
    protected RenderedTreeSnapshotMobileElement() {
        this(null);
    }
    
    protected RenderedTreeSnapshotMobileElement(final RenderedTreeSnapshotMobileElement<T> parentElement) {
        this.parentElement = parentElement;
    }
    
    @Override
    public RenderedTreeSnapshotMobileElement<T> getParentElement() {
        return this.parentElement;
    }
    
    protected String makeXpath() {
        final String tagName = this.getTagName();
        final int index = this.getIndexPropertyForElement(tagName);
        String xpath = StringUtils.isEmpty(tagName) ? "//*" : ("/" + tagName);
        if (index > 0) {
            xpath = String.valueOf(xpath) + "[" + index + "]";
        }
        if (this.parentElement == null) {
            return "/" + xpath;
        }
        final String parentXpath = this.parentElement.getXpath();
        xpath = String.valueOf(StringUtils.isEmpty(parentXpath) ? "//*" : parentXpath) + xpath;
        return xpath;
    }
    
    private int getIndexPropertyForElement(final String tagName) {
        if (StringUtils.isEmpty(tagName) || this.parentElement == null) {
            return 0;
        }
        int index = 1;
        for (final RenderedTreeSnapshotMobileElement<T> sibling : this.parentElement.getChildrenElement()) {
            if (sibling == this) {
                continue;
            }
            if (!tagName.equals(sibling.getTagName())) {
                continue;
            }
            ++index;
        }
        return index;
    }
    
    @Override
    public List<RenderedTreeSnapshotMobileElement<T>> getChildrenElement() {
        if (this.childrenElement == null) {
            this.childrenElement = new ArrayList<RenderedTreeSnapshotMobileElement<T>>();
        }
        return this.childrenElement;
    }
    
    public void setChildrenElement(final List<RenderedTreeSnapshotMobileElement<T>> childrenElement) {
        this.childrenElement = childrenElement;
    }
}