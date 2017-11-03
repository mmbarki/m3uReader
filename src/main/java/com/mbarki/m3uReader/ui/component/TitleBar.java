package com.mbarki.m3uReader.ui.component;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;

public class TitleBar extends HorizontalLayout {

	private static final long serialVersionUID = -2092034175346566853L;

	public TitleBar(String titleText, Resource icon) {

		Label title = new Label(((VaadinIcons) icon).getHtml() + " " + titleText, ContentMode.HTML);
		title.addStyleName(ValoTheme.LABEL_HUGE);
		title.addStyleName(ValoTheme.LABEL_BOLD);
		title.addStyleName(ValoTheme.LABEL_LARGE);
		title.addStyleName(ValoTheme.LABEL_NO_MARGIN);
		addComponent(title);

		setComponentAlignment(title, Alignment.MIDDLE_CENTER);
	}
}
