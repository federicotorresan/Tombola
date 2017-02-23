import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Client {

	protected Shell shlChat;
	private Text txtlist;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Client window = new Client();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shlChat.open();
		shlChat.layout();
		while (!shlChat.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlChat = new Shell();
		shlChat.setText("Cartella");
		shlChat.setSize(378, 275);

		Button btnConnessione = new Button(shlChat, SWT.NONE);
		btnConnessione.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// si connette al server
				try {
					// crea un thread di ascolto dei messaggi
					s = new Socket("localhost", 9999);
					ThreadClient tc = new ThreadClient(s, Client.this);
					// il socket
					out = new PrintWriter(s.getOutputStream(), true);
					// la classe grafica
					tc.start();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnConnessione.setBounds(254, 147, 75, 25);
		btnConnessione.setText("Connessione");

		Button btnInvia = new Button(shlChat, SWT.NONE);
		btnInvia.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				// uso il socket già aperto
				try {
					out=new PrintWriter(s.getOutputStream(), true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// leggo il messaggio dalla casella di testo
			/*	String message = txtIn.getText();   */
				
				
				// invio il messaggio al client con PrintWriter
			/*	out.println(message);    */
			}
		});
		btnInvia.setBounds(254, 187, 75, 25);
		btnInvia.setText("Invia");

		txtlist = new Text(shlChat, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		txtlist.setEditable(true);
		txtlist.setBounds(10, 41, 205, 182);

	}

	public void addMessage(String message) {
		Display.getDefault().asyncExec(new Runnable() {
			// TODO Auto-generated method stub
			public void run() {
				txtlist.append(message+"\n");
			}
		});
	}
}
