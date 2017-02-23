import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

public class Server {

	protected Shell shlBanco;

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
		shlBanco.open();
		shlBanco.layout();
		while (!shlBanco.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shlBanco = new Shell();
		shlBanco.setSize(366, 301);
		shlBanco.setText("Banco");
		
		List list = new List(shlBanco, SWT.BORDER);
		list.setBounds(10, 31, 222, 208);
		
		Button btnEstrai = new Button(shlBanco, SWT.NONE);
		btnEstrai.setBounds(270, 214, 75, 25);
		btnEstrai.setText("Estrai");
		
		Label lblNumeriEstratti = new Label(shlBanco, SWT.NONE);
		lblNumeriEstratti.setBounds(83, 10, 86, 15);
		lblNumeriEstratti.setText("Numeri estratti");

	}
}
