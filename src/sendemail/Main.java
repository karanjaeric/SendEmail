/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sendemail;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author ekaranja
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        
       // send("pentacrew4@gmail.com","pentacrew","muthike789@gmail.com","hello from java app","How r this morning?");  

        // Create a new empty document
//        PDDocument document = new PDDocument();
//
//// Create a new blank page and add it to the document
//        PDPage blankPage = new PDPage();
//        document.addPage(blankPage);
//
//// Save the newly created document
//        document.save("BlankPage.pdf");
//
//// finally make sure that the document is properly
//// closed.
//        document.close();





 //Create a document and add a page to it
PDDocument document = new PDDocument();
PDPage page = new PDPage();
document.addPage( page );

// Create a new font object selecting one of the PDF base fonts
PDFont font = PDType1Font.HELVETICA_BOLD;

// Start a new content stream which will "hold" the to be created content
PDPageContentStream contentStream = new PDPageContentStream(document, page);

// Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"

contentStream.beginText();
contentStream.setFont( font, 12 );
contentStream.moveTextPositionByAmount( 250, 770);
contentStream.drawString( "Karanja&Sons Company");
contentStream.endText();

contentStream.beginText();
contentStream.setFont( font, 12 );
contentStream.moveTextPositionByAmount( 0, 750);
contentStream.drawString( "No.");
contentStream.endText();

contentStream.beginText();
contentStream.setFont( font, 12 );
contentStream.moveTextPositionByAmount(500, 750);
contentStream.drawString( "Date:");
contentStream.endText();

contentStream.beginText();
contentStream.setFont( font, 12 );
contentStream.moveTextPositionByAmount( 0, 700);
contentStream.drawString( "Received from Eric Karanja Muthike");
contentStream.endText();

contentStream.beginText();
contentStream.setFont( font, 12 );
contentStream.moveTextPositionByAmount( 0, 650);
contentStream.drawString( "The sum of shillings Eight Thousand only");
contentStream.endText();

contentStream.beginText();
contentStream.setFont( font, 12 );
contentStream.moveTextPositionByAmount( 0, 600);
contentStream.drawString( "being payment of October");
contentStream.endText();

// Make sure that the content stream is closed:
contentStream.close();

// Save the results and ensure that the document is properly closed:
document.save( "BlankPage.pdf");
document.close();

//send email with attachments

    }

    public static void send(String from, String password, String to, String sub, String msg) throws UnsupportedEncodingException {
        //Get properties object    
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        //get Session   
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });
//        //compose message    
//        try {
//            MimeMessage message = new MimeMessage(session);
//            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            message.setSubject(sub);
//            message.setText(msg);
//            //send message  
//            Transport.send(message);
//            System.out.println("message sent successfully");
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }

//2) compose message     
  try{  
    MimeMessage message = new MimeMessage(session);  
    message.setFrom(new InternetAddress(from,"Anne Gichui"));  
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
    message.setSubject("Message Aleart");  
      
    //3) create MimeBodyPart object and set your message text     
    BodyPart messageBodyPart1 = new MimeBodyPart();  
    messageBodyPart1.setText("This is message body");  
      
    //4) create new MimeBodyPart object and set DataHandler object to this object      
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
  
    String filename = "BlankPage.pdf";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName(filename);  
     
     
    //5) create Multipart object and add MimeBodyPart objects to this object      
    Multipart multipart = new MimeMultipart();  
    multipart.addBodyPart(messageBodyPart1);  
    multipart.addBodyPart(messageBodyPart2);  
  
    //6) set the multiplart object to the message object  
    message.setContent(multipart );  
     
    //7) send message  
    Transport.send(message);  
   
   System.out.println("message sent....");  
   }catch (MessagingException ex) {ex.printStackTrace();}  

    }

}
