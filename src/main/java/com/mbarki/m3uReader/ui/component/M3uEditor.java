package com.mbarki.m3uReader.ui.component;

import org.springframework.beans.factory.annotation.Autowired;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;

public class M3uEditor extends Panel {

	private static final long serialVersionUID = -4145175088751301733L;

	HorizontalLayout content;

	TextArea editorTextArea;
	Button processButton;

	@Autowired
	UiUtils uiUtils;

	public M3uEditor(String editorCaption) {

		editorTextArea = new TextArea();
		editorTextArea.setPlaceholder(editorCaption);
		editorTextArea.setSizeFull();
		editorTextArea.focus();

		processButton = new Button("Process");
		processButton.setId("PROCESS");

		content = (HorizontalLayout) this.createHBloc(editorTextArea, processButton);

		content.setComponentAlignment(processButton, Alignment.BOTTOM_RIGHT);
		content.setExpandRatio(editorTextArea, 2);
		content.setSizeFull();

	}

	public String getValue() {
		return editorTextArea.getValue();
	}

	public void setValue(String value) {
		editorTextArea.setValue(value);
	}

	public void setActionLister(ClickListener clickListener) {
		processButton.addClickListener(clickListener);
	}
	
	public AbstractOrderedLayout createHBloc(Component... components) {
		HorizontalLayout content = new HorizontalLayout();
		content.setMargin(false);
		content.setSizeFull();

		for (Component component : components) {
			if (component != null) {
				content.addComponent(component);
			}
		}
		return content;
	}
}
