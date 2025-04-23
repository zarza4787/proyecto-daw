package interfaz.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Utils;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class EliminarEmpleado extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			EliminarEmpleado dialog = new EliminarEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public EliminarEmpleado() {
		setResizable(false);
		setTitle("Eliminar empleado");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			table = new JTable();
			table.setColumnSelectionAllowed(true);
			table.setModel(new DefaultTableModel(
					new Object[][] { { null, null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null, null },
							{ null, null, null, null, null, null, null, null }, },
					new String[] { "ID", "Nombre", "Apellidos", "Email", "Telefono", "Fecha de alta", "Manager ",
							"Puesto" }) {
				Class[] columnTypes = new Class[] { Long.class, Object.class, Object.class, String.class, String.class,
						String.class, Object.class, Object.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
			table.setBounds(10, 32, 564, 426);
			contentPanel.add(table);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
