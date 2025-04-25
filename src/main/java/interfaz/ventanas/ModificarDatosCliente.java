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
	private JButton modificarCliente;

	private EmpleadoController empleadoController;

	public ModificarDatosCliente() {
		empleadoController = new EmpleadoController();

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
		model.setColumnIdentifiers(
				new String[] { "ID", "Nombre", "Apellido", "Email", "Telefono", "Fecha de alta", "Manager", "Puesto" });

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 11, 914, 319);
		contentPanel.add(scrollPane);

		cargarEmpleadosEnTabla();

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
				} else {
					try {
						ModificarDatosCliente2 d1 = new ModificarDatosCliente2();
						d1.setVisible(true);
					} catch (Exception e1) {
						e1.getMessage();
					}
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

	private void cargarEmpleadosEnTabla() {
		try {
			List<Empleado> empleados = empleadoController.obtenerTodosEmpleados();
			for (Empleado e : empleados) {
				model.addRow(new Object[] { e.getEmployeeID(), e.getName(), e.getLastName(), e.getEmail(), e.getPhone(),
						e.getHireDate(), e.getManagerId(), e.getJobTitle() });
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
