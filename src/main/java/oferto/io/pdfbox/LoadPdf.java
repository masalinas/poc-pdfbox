package oferto.io.pdfbox;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSString;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

public class LoadPdf {
	 public static void main(String args[]) throws IOException {
		//Loading an existing document 
	    File file = new File("./my_form.pdf");
	      
		try (PDDocument document = Loader.loadPDF(file)) {
			PDDocumentCatalog docCatalog = document.getDocumentCatalog();
	        PDAcroForm acroForm = docCatalog.getAcroForm();
	        List<PDField> fields = acroForm.getFields();
	        
	        for (PDField field : fields)
	        {
	        	COSString taxCode = (COSString)field.getCOSObject().getItem("TAX_CODE");      
	        	COSString viewCode = (COSString)field.getCOSObject().getItem("VIEW_CODE");
	        					
				System.out.println("TAX_CODE: " + taxCode.getString());
				System.out.println("VIEW_CODE: " + viewCode.getString());
	        }
	      
			//Closing the document  
			document.close();  
		}	      
	 }
}
