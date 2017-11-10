package com.mbarki.m3uReader.ui.component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededListener;

public class M3uFileUplaoder extends Panel {

	private static final long serialVersionUID = -7621639793157329480L;

	HorizontalLayout content;

	Upload upload;
	ByteArrayOutputStream outputStream;

	public M3uFileUplaoder(String editorCaption) {
		this.setCaption("Upload file here");

		//		this.outputStream = outputStream;
		upload = new Upload("", new Receiver() {

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				outputStream = new ByteArrayOutputStream();
				return outputStream;
			}
		});

		upload.setButtonCaption("Upload");
		upload.setImmediateMode(false);
		upload.setSizeFull();

		content = (HorizontalLayout) this.createHBloc(upload);

		//.setComponentAlignment(processButton, Alignment.BOTTOM_RIGHT);
		//		content.setExpandRatio(editorTextArea, 2);
		content.setSizeFull();

		this.setContent(content);
	}

	public void setSucceededListener(SucceededListener succeededListener) {
		upload.addSucceededListener(succeededListener);
	}

	public ByteArrayOutputStream getOutputStream() {
		return outputStream;
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
