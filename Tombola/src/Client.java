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
import org.eclipse.swt.widgets.Label;

public class Client {

	protected Shell shlCartella;
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
		shlCartella.open();
		shlCartella.layout();
		while (!shlCartella.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlCartella = new Shell();
		shlCartella.setText("Cartella");
		shlCartella.setSize(378, 275);
		
		
		int n =1;
		for(int i=0; i<9; i++){
			for(int j=0; j<10; j++){
				Label lblNewLabel = new Label(shlCartella, SWT.NONE);
				lblNewLabel.setBounds(10+35*i, 25+15*j, 35, 15);
				lblNewLabel.setText(String.valueOf(n));
				n++;
			}
		}
		
		Button btnRicevi = new Button(shlCartella, SWT.NONE);
		btnRicevi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					Socket s = new Socket("localhost", 9999);
					for (int i = 0; i < 15; i++) {
						System.out.print(s.getInputStream().read() + " ");
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnRicevi.setBounds(277, 202, 75, 25);
		btnRicevi.setText("Ricevi");

	}

	public void addMessage(String message) {
		Display.getDefault().asyncExec(new Runnable() {
			// TODO Auto-generated method stub
			public void run() {
				//lbl.setText(message+"\n");
			}
		});
	}
}
