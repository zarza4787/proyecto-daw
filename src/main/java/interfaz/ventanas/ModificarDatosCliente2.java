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

public class ModificarDatosCliente2 extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private JButton grabarCambios;

	private EmpleadoController empleadoController;

	public ModificarDatosCliente2() {
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
		model.setColumnIdentifiers(new String[] { "ID", "Nombre", "Apellido", "Email" });

		cargarEmpleadosEnTabla();

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		grabarCambios = new JButton("Grabar cambios");
		grabarCambios.setActionCommand("OK");
		buttonPane.add(grabarCambios);
		getRootPane().setDefaultButton(grabarCambios);

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
