
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class RequestProcessor implements Runnable {
    private Socket socket;

    public RequestProcessor(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        InputStream input = null;
        OutputStream output = null;

        try {
            input = socket.getInputStream();
            output = socket.getOutputStream();
            Request request = new Request(input);
            request.parse();
            Response response = new Response(output);
            response.setRequest(request);

            if (request.getUri() != null) {
                if (request.getUri().startsWith("/servlet")) {
                    MyServletProcessor processor = new MyServletProcessor();
                    processor.process(request, response);
                } else {
                    MyStaticResourceProcessor processor = new MyStaticResourceProcessor();
                    processor.process(request, response);
                }
            }

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

