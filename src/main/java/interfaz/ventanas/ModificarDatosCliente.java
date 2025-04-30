package interfaz.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import dao.controller.CustomerController;
import dao.controller.EmpleadoController;
import modelos.Customers;
import modelos.Empleado;
import utils.Utils;

import java.util.List;

public class ModificarDatosCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private JButton modificarCliente;

	private CustomerController customerController;

	public ModificarDatosCliente() {
		customerController = new CustomerController();

		setResizable(false);
		setTitle("Modificar datos de un cliente");
		setSize(950, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		model = new DefaultTableModel();
		table = new JTable(model);
		model.setColumnIdentifiers(new String[] { "ID", "Nombre", "Address", "Website", "Credit Limit" });

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 914, 319);
		contentPanel.add(scrollPane);

		cargarCustomersEnTabla();

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		modificarCliente = new JButton("Modificar cliente");
		modificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = table.getSelectedRow();

				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "Por favor selecciona una fila.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
<<<<<<< HEAD
					return;
				}

				// Guardamos la id en un objeto de la fila seleccionada y de la columna 0 (ID)
				Object id = table.getValueAt(filaSeleccionada, 0);

				try {
					long customerId = Long.parseLong(id.toString());
					ModificarDatosCliente2 d1 = new ModificarDatosCliente2(customerId);
					d1.setVisible(true);
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Error al abrir ventana: " + e1.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
=======
				} else {
					try {
						ModificarDatosCliente2 d1 = new ModificarDatosCliente2(
								(long) table.getValueAt(filaSeleccionada, 0));
						d1.setVisible(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
>>>>>>> 2eafbcf111da3eb8603ec36ab729339b5f433f27
				}
			}

		});
		modificarCliente.setActionCommand("OK");
		buttonPane.add(modificarCliente);
		getRootPane().setDefaultButton(modificarCliente);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setActionCommand("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	private void cargarCustomersEnTabla() {
		try {
			List<Customers> customers = customerController.obtenerTodosCustomers();
			for (Customers c : customers) {
				model.addRow(new Object[] { c.getCustomerId(), c.getName(), c.getAddress(), c.getWebsite(),
						c.getCreditLimit() });
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this, "Error al cargar empleados: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void main(String[] args) {
		try {
			ModificarDatosCliente dialog = new ModificarDatosCliente();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
