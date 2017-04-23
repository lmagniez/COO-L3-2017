package com.model;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

/**
 * Classe gérant les erreurs XML
 * @author loick
 *
 */
public class SimpleErrorHandler implements ErrorHandler{

	
	@Override
	public void error(SAXParseException exception) throws SAXException {
		System.out.println("error SimpleErrorHandler");
		
	}

	@Override
	public void fatalError(SAXParseException exception) throws SAXException {
		System.out.println("fatalError!! SimpleErrorHandler");
		
	}

	@Override
	public void warning(SAXParseException exception) throws SAXException {
		System.out.println("warning SimpleErrorHandler");
	}

}
