import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ThreadClient extends Thread {
	private Socket s;
	private Client c;

	// deve essere inizializzato con il socket e il riferimento nella grafica
	public ThreadClient(Socket s, Client c) {
		this.s = s;
		this.c = c;
	}

	public void run() {
		super.run();
		// all'infinito resta in ascolto di nuovi messaggi nel socket
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
			while (true) {
				// quando arriva un nuovo messaggio
				String message = in.readLine();
				// legge il messaggio

				// comunica alla grafica il nuovo messaggio
				c.addMessage(message);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
