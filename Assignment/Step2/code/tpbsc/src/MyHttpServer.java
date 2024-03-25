
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


import javax.servlet.http.HttpServlet;

public class MyHttpServer {
	public static final String STATIC_WEB_ROOT = "C:/Users/loren/Desktop/Magistrale/Software Platforms/Assignment/Step2/SP/staticcontentrepository";
	public static final String DYNAMIC_WEB_ROOT = "C:/Users/loren/Desktop/Magistrale/Software Platforms/Assignment/Step2/SP/servletrepository";

	public static void  main(String[] args) {
		ServletHashTable servletHashTable = new ServletHashTable();

		ManagementConsole managementConsole = new ManagementConsole();
		managementConsole.start();

		MyHttpServer myHttpServer = new MyHttpServer();
		myHttpServer.await();
		System.out.println("Exiting...");
	}

	MyHttpServer() {
	}

	public void await() {
		ServerSocket serverSocket = null;
		int port = 7654;
		try {
			serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
		}
		catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		while (!Shutdown.flag) {
		    try {
		        try { 
		        	serverSocket.setSoTimeout(2000);
		            Socket socket = serverSocket.accept();
		            socket.setSoTimeout(2000);

		            // Utilizza la ThreadPool per eseguire la gestione della richiesta in un thread separato
		            executorService.execute(new RequestProcessor(socket));
		            
		        } catch (SocketTimeoutException se) {
		            if (Shutdown.flag)  return;
		        }
		    } catch (Exception e) {
		        e.printStackTrace();
		        continue;
		    }
		}
	}
}


