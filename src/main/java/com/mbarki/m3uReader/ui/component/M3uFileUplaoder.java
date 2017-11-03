package com.mbarki.m3uReader.ui.component;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.Upload.SucceededListener;

public class M3uFileUplaoder extends HorizontalLayout {

	private static final long serialVersionUID = -4145175088751301733L;

	private ByteArrayOutputStream outputStream;
	Upload upload;

	public M3uFileUplaoder(String editorCaption) {
		this.outputStream = outputStream;
		upload = new Upload("Upload it here", new Receiver() {

			@Override
			public OutputStream receiveUpload(String filename, String mimeType) {
				outputStream = new ByteArrayOutputStream();
				return outputStream;
			}
		});

		upload.setButtonCaption("Upload Now");
		upload.setImmediateMode(false);
		upload.setSizeFull();

		addComponent(upload);
		setSizeFull();
	}

	public void setSucceededListener(SucceededListener succeededListener) {
		upload.addSucceededListener(succeededListener);
	}

	public ByteArrayOutputStream getOutputStream() {
		return outputStream;
	}
}
