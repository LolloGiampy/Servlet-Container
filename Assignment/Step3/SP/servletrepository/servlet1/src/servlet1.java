import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class servlet1 extends HttpServlet {
 
   private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
   private String message;
   public void doGet(HttpServletRequest request, HttpServletResponse response)   //method that returns this string in HTML
      throws ServletException, IOException {
     	   message = "HTTP/1.1 200 OK\r\n"+
				"Content-Type: text/html\r\n"+
				"Content-Length: 580\r\n" +
				"\r\n"+
	   			"<h1>Hi!! This is a dynamic content generated by a servlet ...</h1>";
      PrintWriter out = response.getWriter();
      out.println(message);
      for (char letter : ALPHABET.toCharArray()) {
         out.print("<p>" + letter + "</p>");
         out.flush();  // Assicura che il carattere venga inviato subito al client
         try {
             Thread.sleep(400); // Attendiamo 400 millisecondi tra le lettere
         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }
      }
   }
}