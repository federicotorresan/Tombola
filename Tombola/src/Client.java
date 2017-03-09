import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Label;

public class Client {

	protected Shell shlCartella;
	private Socket s;
	private PrintWriter out;
	private BufferedReader in;
	private ArrayList<Label> Caselle = new ArrayList<Label>();

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
		shlCartella.setSize(384, 217);

		Button btnConnessione = new Button(shlCartella, SWT.NONE);
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
		btnConnessione.setBounds(283, 144, 75, 25);
		btnConnessione.setText("Connessione");

		Label lblNewLabel_1 = new Label(shlCartella, SWT.NONE);
		lblNewLabel_1.setBounds(10, 10, 55, 15);
		Caselle.add(lblNewLabel_1);

		Label lblNewLabel_2 = new Label(shlCartella, SWT.NONE);
		lblNewLabel_2.setBounds(83, 10, 55, 15);
		Caselle.add(lblNewLabel_2);

		Label lblNewLabel_3 = new Label(shlCartella, SWT.NONE);
		lblNewLabel_3.setBounds(150, 10, 55, 15);
		Caselle.add(lblNewLabel_3);

		Label lblNewLabel_4 = new Label(shlCartella, SWT.NONE);
		lblNewLabel_4.setBounds(224, 10, 55, 15);
		Caselle.add(lblNewLabel_4);

		Label lblNewLabel_5 = new Label(shlCartella, SWT.NONE);
		lblNewLabel_5.setBounds(297, 10, 55, 15);
		Caselle.add(lblNewLabel_5);

		Label label = new Label(shlCartella, SWT.NONE);
		label.setBounds(10, 44, 55, 15);
		Caselle.add(label);

		Label label_1 = new Label(shlCartella, SWT.NONE);
		label_1.setBounds(83, 44, 55, 15);
		Caselle.add(label_1);

		Label label_2 = new Label(shlCartella, SWT.NONE);
		label_2.setBounds(150, 44, 55, 15);
		Caselle.add(label_2);

		Label label_3 = new Label(shlCartella, SWT.NONE);
		label_3.setBounds(224, 44, 55, 15);
		Caselle.add(label_3);

		Label label_4 = new Label(shlCartella, SWT.NONE);
		label_4.setBounds(297, 44, 55, 15);
		Caselle.add(label_4);

		Label label_5 = new Label(shlCartella, SWT.NONE);
		label_5.setBounds(10, 80, 55, 15);
		Caselle.add(label_5);

		Label label_6 = new Label(shlCartella, SWT.NONE);
		label_6.setBounds(83, 80, 55, 15);
		Caselle.add(label_6);

		Label label_7 = new Label(shlCartella, SWT.NONE);
		label_7.setBounds(150, 80, 55, 15);
		Caselle.add(label_7);

		Label label_8 = new Label(shlCartella, SWT.NONE);
		label_8.setBounds(224, 80, 55, 15);
		Caselle.add(label_8);

		Label label_9 = new Label(shlCartella, SWT.NONE);
		label_9.setBounds(297, 80, 55, 15);
		Caselle.add(label_9);

	}

	public void addMessage(String message) {
		Display.getDefault().asyncExec(new Runnable() {
			// TODO Auto-generated method stub
			public void run() {
				if(message.contains(";")){
					// recupero numeri
					String[] numeriStr = message.split(";");
					int[] numeri = new int[numeriStr.length];
					for (int i = 0; i < numeriStr.length; i++) {
						// converto in interi
						numeri[i] = Integer.parseInt(numeriStr[i]);
					}
					for (int i = 0; i < numeri.length; i++) {
						Caselle.get(i).setText(numeri[i] + "");
					}
				}else{
					//controlla numeri sulla cartella
					for (int i = 0; i < Caselle.size(); i++) {
						if(Caselle.get(i).getText().equals(message)){
							Caselle.get(i).setBackground(SWTResourceManager.getColor(SWT.COLOR_RED));
						}
					}
				}
			}
		});
	}
}
