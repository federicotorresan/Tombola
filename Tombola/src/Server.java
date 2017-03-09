import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Label;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Server {
		
	PrintWriter out;
	private ArrayList<Label>Numeri = new ArrayList<Label>();
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
	
	public void inizia(){
		Runnable r=new Runnable(){

			@Override
			public void run() {
				try {
					// Crei un server di connessione
					ServerSocket ss = new ServerSocket(9999);
					while (true) {
						// riceva una connessione
						Socket s = ss.accept();
						// riceva del testo
						InputStreamReader isr = new InputStreamReader(s.getInputStream());
						BufferedReader in = new BufferedReader(isr);
						out= new PrintWriter(s.getOutputStream(), true);
						System.out.println("ciao");
						
						// Invio i numeri
						String mandati="";
						for (int i=0; i<15; i++) {
							//prendo e aggiungo un numero
							mandati+=((int)(Math.random()*90)+1)+";";
						}
						out.println(mandati);
						
					}
				} catch (Exception exception) {
					exception.printStackTrace();
				}
				
			}
			
		};
		Thread t=new Thread(r);
		t.start();
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
				Numeri.add(lblNewLabel);
			}
		}
		
		
		Button btnNewButton = new Button(shlServer, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				inizia();
			}
		});
		btnNewButton.setBounds(10, 214, 75, 25);
		btnNewButton.setText("Inizia");
		
		Button btnEstrai = new Button(shlServer, SWT.NONE);
		btnEstrai.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int ncas=((int)(Math.random()*90)+1);
				for (int i = 0; i < Numeri.size(); i++) {
					if(Numeri.get(i).getText().equals(ncas+"")){
						Numeri.get(i).setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
					}
				}
				out.println(ncas+"");
			}
		});
		btnEstrai.setBounds(349, 214, 75, 25);
		btnEstrai.setText("Estrai");

	}
}
