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

import dao.controller.EmpleadoController;
import modelos.Empleado;
import utils.Utils;

import java.util.List;

public class ModificarDatosCliente extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private DefaultTableModel model;
	private JButton okButton;

	private EmpleadoController empleadoController;

	public ModificarDatosCliente() {
		empleadoController = new EmpleadoController();

		setResizable(false);
		setTitle("Modificar datos de un cliente");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		model = new DefaultTableModel();
		table = new JTable(model);
		model.setColumnIdentifiers(new String[] { "ID", "Nombre", "Apellido", "Email" });

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 564, 319);
		contentPanel.add(scrollPane);

		cargarEmpleadosEnTabla();

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		okButton = new JButton("Modificar cliente");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int filaSeleccionada = table.getSelectedRow();

				if (filaSeleccionada == -1) {
					JOptionPane.showMessageDialog(null, "Por favor selecciona una fila.", "Advertencia",
							JOptionPane.WARNING_MESSAGE);
					okButton.setEnabled(false);
				} else {
					try {
						empleadoController.eliminarEmpleado(filaSeleccionada);
						model.removeRow(filaSeleccionada);
					} catch (Exception e1) {
						e1.getMessage();
					}
				}
			}
		});
		okButton.setActionCommand("OK");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

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

	private void cargarEmpleadosEnTabla() {
		try {
			List<Empleado> empleados = empleadoController.obtenerTodosEmpleados();
			for (Empleado emp : empleados) {
				model.addRow(new Object[] { emp.getEmployeeID(), emp.getName(), emp.getLastName(), emp.getEmail() });
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
