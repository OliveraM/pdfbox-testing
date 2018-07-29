import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDVariableText;

public class PdfBoxUtil {

	public void createPdf() throws IOException {
		PDDocument document = PDDocument.load(new File("C:/Users/Olivera/Desktop/proba.pdf"));

		PDDocumentCatalog docCatalog = document.getDocumentCatalog();
		PDAcroForm acroForm = docCatalog.getAcroForm();

		PDFont formFont = PDType0Font.load(document, new FileInputStream("C:/Users/Olivera/Downloads/fonts/arialuni.ttf"), false); // check that the font has what you need; ARIALUNI.TTF is good but huge
		PDResources res = acroForm.getDefaultResources(); // could be null, if so, then create it with the setter
//		PDResources res = new PDResources();
		String fontName = res.add(formFont).getName();
		acroForm.setDefaultResources(res);
		String defaultAppearanceString = "/" + fontName + " 0 Tf 0 g"; // adjust to replace existing font name
		
		PDVariableText text1 = (PDVariableText) acroForm.getField("Text1");
		text1.setDefaultAppearance(defaultAppearanceString);
		text1.setValue(" č š ć ž rade");
		
		PDVariableText text2 = (PDVariableText) acroForm.getField("Text2");
		text2.setDefaultAppearance(defaultAppearanceString);
		text2.setValue(" Ȁ , ȁ, ȃ, Ȅ, ȅ, Ȇ, ȇ rade");
		
		PDVariableText text3 = (PDVariableText) acroForm.getField("Text3");
		text3.setDefaultAppearance(defaultAppearanceString);
		text3.setValue("  Ȉ, ȉ, Ȋ, ȋ, Ȍ, ȍ, Ȏ, ȏ, Ȑ rade");

		PDDocumentInformation docInfo = new PDDocumentInformation();
		docInfo.setAuthor("Olivera Mitrovic");
		docInfo.setTitle("pomozi boze");
		docInfo.setCreator("PDF demo");
		docInfo.setSubject("ocajnicki pokusaj");
		Calendar creationDate = new GregorianCalendar();
		creationDate.set(2018, 7, 29);
		docInfo.setCreationDate(creationDate);

		document.save("C:/Users/Olivera/Desktop/proba-posle.pdf");

		document.close();
	}
}
