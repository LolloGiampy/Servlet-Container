
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;
import javax.servlet.ServletOutputStream;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class Response implements HttpServletResponse {
	private static final int BUFFER_SIZE = 1024;
	Request request;
	OutputStream output;
	PrintWriter writer;
	//costruttore con il flusso di output in cui verrà scritta la risposta http
	public Response(OutputStream output) {
		this.output = output;
	}	
	//consente di impostare l'oggetto Request associato a questa risposta. L'oggetto Request viene utilizzato per estrarre informazioni sulla richiesta in arrivo, ad esempio l'URI richiesto
	public void setRequest(Request request) {
		this.request = request;
	}
	//Questo metodo è utilizzato per servire risorse statiche, come pagine HTML o file, all'interno di una risposta HTTP. Legge il contenuto di un file richiesto, ne calcola la lunghezza e invia una risposta HTTP con il contenuto del file. In caso di errore, come quando il file richiesto non esiste, viene inviato un messaggio di errore "File Not Found" come risposta.
	public void sendStaticResource() throws IOException {
		String headerHttpBeforeLength = "HTTP/1.1 200 OK"+"\r\n"+"Content-Type: text/html"+"\r\n"+"Content-Length: ";
		String headerHttpAfterLength = "\r\n" + "\r\n";
		try {
			String fileName = MyHttpServer.STATIC_WEB_ROOT+request.getUri();
			FileReader fileReader = new FileReader(fileName.trim());
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			StringBuilder stringBuilder = new StringBuilder();
			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line);
			}
			bufferedReader.close();
			int len=stringBuilder.toString().length();
			String outMsg=headerHttpBeforeLength+Integer.toString(len)+headerHttpAfterLength+stringBuilder.toString();
			
			output.write(outMsg.getBytes());
		}
		catch (FileNotFoundException e) {
			String errorMessage = "HTTP/1.1 404 File Not Found\r\n" +
			"Content-Type: text/html\r\n" +
			"Content-Length: 23\r\n" +
			"\r\n" +
			"<h1>File Not Found</h1>";
			output.write(errorMessage.getBytes());
		}
	}
	
	/** implementation of ServletResponse */
	public void flushBuffer() throws IOException { }
	public int getBufferSize() {
		return 0;
	}
	public String getCharacterEncoding() {
		return null;
	}
	public Locale getLocale() {
		return null;
	}
	public ServletOutputStream getOutputStream() throws IOException {
		return null;
	}
	public PrintWriter getWriter() throws IOException {
		writer = new PrintWriter(output, true);
		return writer;
	}
	public boolean isCommitted() {
		return false;
	}
	public void reset() { }
	public void resetBuffer() { }
	public void setBufferSize(int size) { }
	public void setContentLength(int length) { }
	public void setContentType(String type) { }
	public void setLocale(Locale locale) { }
	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setCharacterEncoding(String arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setContentLengthLong(long arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addCookie(Cookie cookie) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean containsHeader(String name) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public String encodeURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String encodeRedirectURL(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String encodeUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String encodeRedirectUrl(String url) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void sendError(int sc, String msg) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendError(int sc) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sendRedirect(String location) throws IOException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addDateHeader(String name, long date) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setHeader(String name, String value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addHeader(String name, String value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setIntHeader(String name, int value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addIntHeader(String name, int value) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setStatus(int sc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setStatus(int sc, String sm) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int getStatus() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public String getHeader(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<String> getHeaders(String name) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<String> getHeaderNames() {
		// TODO Auto-generated method stub
		return null;
	}
}

