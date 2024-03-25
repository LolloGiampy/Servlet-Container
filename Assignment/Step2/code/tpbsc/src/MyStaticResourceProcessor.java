import java.io.IOException;
public class MyStaticResourceProcessor {
	//accetta due argomenti di tipo Request e Response, che rappresentano rispettivamente l'oggetto di richiesta e l'oggetto di risposta associati a una richiesta HTTP
	public void process(Request request, Response response) {
		try {
			//Questo metodo è responsabile dell'invio di risorse statiche come pagine HTML o file all'interno della risposta HTTP
			response.sendStaticResource();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
