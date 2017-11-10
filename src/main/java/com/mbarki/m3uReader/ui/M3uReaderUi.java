package com.mbarki.m3uReader.ui;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mbarki.m3uReader.ui.component.Header;
import com.mbarki.m3uReader.ui.component.M3uEditor;
import com.mbarki.m3uReader.ui.component.M3uFileUplaoder;
import com.vaadin.annotations.Theme;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

@Theme("valo")
@SpringUI
public class M3uReaderUi extends UI implements ClickListener, SucceededListener {

	private static final long serialVersionUID = 4134672115269419785L;

	private static final Logger LOGGER = LoggerFactory.getLogger(M3uReaderUi.class);

	Header header;
	Panel bodyInput;
	Panel bodyOutput;

	M3uEditor m3uEditor;
	M3uFileUplaoder m3uFileUplaoder;
	TextArea resultTextArea;
	Button processButton;

	VerticalLayout rootContent;
	Layout bodyInputContent;
	Layout bodyOutputContent;

	@Override
	public void init(VaadinRequest request) {

		rootContent = new VerticalLayout();

		header = new Header("... Reader");

		createBody();

		header.setWidth("100%");
		bodyInput.setWidth("100%");
		bodyOutput.setWidth("100%");

		rootContent.addComponent(header);
		rootContent.addComponent(bodyInput);
		rootContent.addComponent(processButton);
		rootContent.addComponent(bodyOutput);

		rootContent.setExpandRatio(bodyInput, 1);
		rootContent.setComponentAlignment(header, Alignment.TOP_CENTER);
		rootContent.setComponentAlignment(bodyInput, Alignment.TOP_CENTER);
		rootContent.setComponentAlignment(bodyOutput, Alignment.TOP_CENTER);
		this.setContent(rootContent);

		LOGGER.info("M3u Reader - ui successfully initialized");
	}

	private void createBody() {
		bodyInput = new Panel();
		bodyOutput = new Panel("Result ...");
		bodyInput.setSizeFull();
		bodyOutput.setSizeFull();

		bodyInputContent = new VerticalLayout();
		bodyOutputContent = new HorizontalLayout();
		bodyOutputContent.setSizeFull();

		// file uploader
		m3uFileUplaoder = new M3uFileUplaoder("Upload file here");
		m3uFileUplaoder.setSucceededListener(this);
		bodyInputContent.addComponent(m3uFileUplaoder);

		// m3u editor
		m3uEditor = new M3uEditor("edit here ...");
		bodyInputContent.addComponent(m3uEditor);

		// Process button
		processButton = new Button("Click to process", VaadinIcons.COGS);
		processButton.setId("PROCESS");
		processButton.setSizeFull();
		processButton.addStyleName(ValoTheme.BUTTON_FRIENDLY);
		processButton.addStyleName(ValoTheme.BUTTON_TINY);

		processButton.addClickListener(this);

		// Result
		resultTextArea = new TextArea();
		resultTextArea.addStyleName(ValoTheme.TEXTAREA_BORDERLESS);
		resultTextArea.setSizeFull();
		bodyOutputContent.addComponent(resultTextArea);

		bodyInput.setContent(bodyInputContent);
		bodyOutput.setContent(bodyOutputContent);
	}

	private AbstractOrderedLayout createHBloc(Component... components) {
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

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		byte[] buffer = this.m3uFileUplaoder.getOutputStream().toByteArray();
		String value = "";

		try {
			value = IOUtils.toString(new ByteArrayInputStream(buffer), StandardCharsets.UTF_8);
		} catch (IOException e) {
			Notification.show("Failed", Type.TRAY_NOTIFICATION);
			return;
		}
		this.m3uEditor.setValue(value);
		Notification.show("Success", Type.TRAY_NOTIFICATION);
	}

	// --------------------------------------- actions ----------------------------------------------

	@Override
	public void buttonClick(ClickEvent event) {

		if (event.getButton().getId().equals("PROCESS")) {
			process();
			return;
		}

		//		if (event.getButton().getId().startsWith("OK_")) {
		//			validateDocument(getDocumentType(event.getButton().getId()));
		//			findAction();
		//			return;
		//		}
		//		if (event.getButton().getId().startsWith("KO_")) {
		//			invalidateDocument(getDocumentType(event.getButton().getId()));
		//			findAction();
		//			return;
		//		}
		//		if (event.getButton().getId().equals("ACC_OK")) {
		//			validateAccount();
		//			findAction();
		//			return;
		//		}
		//		if (event.getButton().getId().equals("ACC_KO")) {
		//			invalidateAccount();
		//			findAction();
		//			return;
		//		}
		//		if (event.getButton().getId().startsWith("LAST_ACCOUNT_")) {
		//			this.account = ((Account) event.getButton().getData());
		//			findAction(false);
		//			return;
		//		}
	}

	private void process() {
		resultTextArea.setValue(m3uEditor.getValue());

	}

	// private void validateDocument(DocumentType documentType) {
	// try {
	// backofficeService.validateDocument(account.getExternalId(),
	// documentType);
	// Notification.show(documentType.name(), "Document is accepted",
	// Type.TRAY_NOTIFICATION);
	// } catch (BusinessException e) {
	// LOGGER.error("[Backoffice UI Validation] error when Validating Document.
	// Account :'{}', Document: '{}' ", this.account.getExternalId(),
	// documentType.name());
	// }
	// }
	//
	// private void invalidateDocument(DocumentType documentType) {
	// try {
	// backofficeService.invalidateDocument(account.getExternalId(),
	// documentType, (DocumentValidationError) validationRejection.getReason(),
	// validationRejection.getComment());
	// Notification.show(documentType.name(), "Document is rejected",
	// Type.TRAY_NOTIFICATION);
	// } catch (BusinessException e) {
	// LOGGER.error("[Backoffice UI Validation] error when Invalidating
	// Document. Account :'{}', Document: '{}' ", this.account.getExternalId(),
	// documentType.name());
	// }
	// }
	//
	// private void findAction() {
	// this.findAction(true);
	// }

	// private void findAction(Boolean findAccount) {
	// documentsContent.removeAllComponents();
	// accountInfosPanel.raz();
	// accountValidationPanel.raz();
	// // find account
	// if (findAccount && StringUtils.isNotEmpty(this.currentCriteria)) {
	// account = accountRepository.findByExternalId(this.currentCriteria);
	// }
	// if (account != null) {
	// // Infos & validation status
	// validation = accountService.getAccountValidation(account);
	// accountInfosPanel.updateUi(account,
	// accountService.getAccountInfos(account));
	// if (validation != null) {
	// accountValidationPanel.updateUi(validation);
	// }
	//
	// // Documents
	// documents = accountDocumentOperation.getDocuments(account, true);
	// for (Document document : documents) {
	// DocumentPanel documentPanel = new DocumentPanel(document);
	// documentPanel.setActionLister(this, validationRejection);
	// documentsContent.addComponent(documentPanel);
	// }
	// }
	// }

	// private DocumentType getDocumentType(String buttonId) {
	// return DocumentType.getEnum(buttonId.substring(buttonId.indexOf("_") +
	// 1));
	// }

}