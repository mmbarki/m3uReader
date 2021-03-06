package com.mbarki.m3uReader.ui.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;

public class M3uEditor extends Panel {

	private static final long serialVersionUID = -4145175088751301733L;

	TextArea editorTextArea;
	HorizontalLayout content;

	@Autowired
	UiUtils uiUtils;

	public M3uEditor(String editorCaption) {
		this.setCaption("Edit here");

		editorTextArea = new TextArea();
		editorTextArea.setPlaceholder(editorCaption);
		editorTextArea.setSizeFull();
		editorTextArea.focus();

		content = (HorizontalLayout) this.createHBloc(editorTextArea);

		content.setSizeFull();
		this.setContent(content);
	}

	public String getValue() {
		return editorTextArea.getValue();
	}

	public void setValue(String value) {
		editorTextArea.setValue(value);
	}

	public AbstractOrderedLayout createHBloc(Component... components) {
		HorizontalLayout content = new HorizontalLayout();
		content.setMargin(true);
		content.setSizeFull();

		for (Component component : components) {
			if (component != null) {
				content.addComponent(component);
			}
		}
		return content;
	}
}
