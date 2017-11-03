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
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Layout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload.SucceededEvent;
import com.vaadin.ui.Upload.SucceededListener;
import com.vaadin.ui.VerticalLayout;

@Theme("valo")
@SpringUI
public class M3uReaderUi extends UI implements ClickListener, SucceededListener {

	private static final long serialVersionUID = 4134672115269419785L;

	private static final Logger LOGGER = LoggerFactory.getLogger(M3uReaderUi.class);

	Header header;
	Panel body;

	M3uEditor m3uEditor;
	M3uFileUplaoder m3uFileUplaoder;

	VerticalLayout rootContent;
	Layout bodyContent;
	GridLayout documentsContent;

	@Override
	public void init(VaadinRequest request) {

		rootContent = new VerticalLayout();

		header = new Header("... Reader");

		createBody();

		header.setWidth("100%");
		body.setWidth("100%");

		rootContent.addComponent(header);
		rootContent.addComponent(body);
		rootContent.setExpandRatio(body, 1);
		rootContent.setComponentAlignment(header, Alignment.TOP_CENTER);
		rootContent.setComponentAlignment(body, Alignment.TOP_CENTER);
		this.setContent(rootContent);

		LOGGER.info("M3u Reader - ui successfully initialized");
	}

	private void createBody() {
		body = new Panel();
		body.setSizeFull();

		bodyContent = new VerticalLayout();

		// les derniers comptes Créés:
		// List<Component> lastAccounts = createLastAccounts();
		// if (!lastAccounts.isEmpty()) {
		// AbstractOrderedLayout blocLastAccount =
		// createHBloc(lastAccounts.toArray(new
		// Component[lastAccounts.size()]));
		// blocLastAccount.setDefaultComponentAlignment(Alignment.BOTTOM_RIGHT);
		// bodyContent.addComponent(blocLastAccount);
		// }

		// m3u editor
		m3uEditor = new M3uEditor("edit here ...");
		m3uEditor.setActionLister(this);
		bodyContent.addComponent(m3uEditor);

		// file uploader
		m3uFileUplaoder = new M3uFileUplaoder("Upload file here");
		m3uFileUplaoder.setSucceededListener(this);
		bodyContent.addComponent(m3uFileUplaoder);

		// account infos:
		// accountInfosPanel = new AcountInfoPanel();
		// accountValidationPanel = new AcountValidationPanel();

		// AbstractOrderedLayout blocAccount = createHBloc(accountInfosPanel,
		// accountValidationPanel);

		// bodyContent.addComponent(blocAccount);
		// accountValidationPanel.setActionLister(this, validationRejection);

		// prepare docs
		documentsContent = new GridLayout();
		documentsContent.setColumns(4);
		documentsContent.setSizeFull();
		documentsContent.setSpacing(true);
		// documentsContent.setHideEmptyRowsAndColumns(true);
		bodyContent.addComponent(documentsContent);

		body.setContent(bodyContent);
	}

	private AbstractOrderedLayout createHBloc(Component... components) {
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
	// --------------------------------------- actions ----------------------------------------------

	@Override
	public void buttonClick(ClickEvent event) {

		// if (event.getButton().getId().equals("FIND")) {
		// this.currentCriteria = acountSearchBox.getValue();
		// findAction();
		// return;
		// }
		// if (event.getButton().getId().startsWith("OK_")) {
		// validateDocument(getDocumentType(event.getButton().getId()));
		// findAction();
		// return;
		// }
		// if (event.getButton().getId().startsWith("KO_")) {
		// invalidateDocument(getDocumentType(event.getButton().getId()));
		// findAction();
		// return;
		// }
		// if (event.getButton().getId().equals("ACC_OK")) {
		// validateAccount();
		// findAction();
		// return;
		// }
		// if (event.getButton().getId().equals("ACC_KO")) {
		// invalidateAccount();
		// findAction();
		// return;
		// }
		// if (event.getButton().getId().startsWith("LAST_ACCOUNT_")) {
		// this.account = ((Account) event.getButton().getData());
		// findAction(false);
		// return;
		// }
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

	@Override
	public void uploadSucceeded(SucceededEvent event) {
		byte[] buffer = this.m3uFileUplaoder.getOutputStream().toByteArray();
		//				boolean isPlainText = MimeTypes.PLAIN_TEXT.equals(new MimeTypes().getMimeType(buffer).getName());
		//		if (isPlainText) {

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

}