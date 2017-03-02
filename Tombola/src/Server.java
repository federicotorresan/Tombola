import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Server {
		
	protected Shell shlServer;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Server window = new Server();
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
		shlServer.open();
		shlServer.layout();
		while (!shlServer.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlServer = new Shell();
		shlServer.setSize(450, 300);
		shlServer.setText("Server");
		
		int n =1;
		for(int i=0; i<9; i++){
			for(int j=0; j<10; j++){
				Label lblNewLabel = new Label(shlServer, SWT.NONE);
				lblNewLabel.setBounds(10+35*i, 25+15*j, 35, 15);
				lblNewLabel.setText(String.valueOf(n));
				n++;
			}
		}
		
		
		Button btnNewButton = new Button(shlServer, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					// Crei un server di connessione
					ServerSocket ss = new ServerSocket(9999);
					while (true) {
						// riceva una connessione
						Socket s = ss.accept();
						// riceva del testo
						InputStreamReader isr = new InputStreamReader(s.getInputStream());
						BufferedReader in = new BufferedReader(isr);
						
						// Invio i numeri
						// TODO Auto-generated method stub
						//	Cartella c = new Cartella();
						// L'elenco dei numeri da dare al client
						/*int numeri[] = c.getNumeri();
						for (int i : numeri) {
							System.out.print(i + " ");
							s.getOutputStream().write(i);
						}*/
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(349, 214, 75, 25);
		btnNewButton.setText("New Button");

	}
}
