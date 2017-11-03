package com.mbarki.m3uReader.ui.component;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;

public class M3uEditor extends HorizontalLayout {

	private static final long serialVersionUID = -4145175088751301733L;

	TextArea editorTextArea;
	Button processButton;

	public M3uEditor(String editorCaption) {

		editorTextArea = new TextArea();
		editorTextArea.setPlaceholder(editorCaption);
		processButton = new Button("Process");
		processButton.setId("PROCESS");

		addComponent(editorTextArea);
		addComponent(processButton);
		this.setComponentAlignment(processButton, Alignment.BOTTOM_RIGHT);

		editorTextArea.setSizeFull();
		setExpandRatio(editorTextArea, 2);
		setSizeFull();
		editorTextArea.focus();
	}

	public String getValue() {
		return editorTextArea.getValue();
	}

	public void setActionLister(ClickListener clickListener) {
		processButton.addClickListener(clickListener);
	}
}
