package com.mbarki.m3uReader.ui.component;

import org.springframework.stereotype.Service;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

@Service
public class UiUtils {

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
