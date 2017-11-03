package com.mbarki.m3uReader.ui.component;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Header extends Panel {

	private static final long serialVersionUID = 7707193514956020967L;

	VerticalLayout headerLayout = new VerticalLayout();

	public Header(String headerText) {
		TitleBar titleBar = new TitleBar(headerText, VaadinIcons.AIRPLANE);

		headerLayout.addComponent(titleBar);
		headerLayout.setComponentAlignment(titleBar, Alignment.MIDDLE_CENTER);

		this.setHeight("70");
		this.setContent(headerLayout);
	}

}
