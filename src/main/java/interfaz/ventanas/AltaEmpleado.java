package interfaz.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;

import dao.controller.EmpleadoController;
import excepciones.DataAccessException;
import modelos.Empleado;
import utils.Utils;

import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AltaEmpleado extends JDialog {

	// Declaracion del panel, el empleadoController y de los textField para que el
	// usuario introduzca los datos

	private final JPanel contentPanel = new JPanel();
	private EmpleadoController empleadoController;
	private JTextField textName;
	private JTextField textLastName;
	private JTextField textEmail;
	private JTextField textPhone;
	private JTextField textJobTitle;
	private JComboBox<String> comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaEmpleado dialog = new AltaEmpleado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaEmpleado() {
		setResizable(false);
		setTitle("Insertar Empleado");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		empleadoController = new EmpleadoController();
		{
			JLabel lblNewLabel = new JLabel("Nombre");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(21, 34, 150, 47);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblApellidos = new JLabel("Apellidos");
			lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblApellidos.setBounds(21, 92, 150, 47);
			contentPanel.add(lblApellidos);
		}
		{
			JLabel lblEmail = new JLabel("Email");
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblEmail.setBounds(21, 150, 150, 47);
			contentPanel.add(lblEmail);
		}
		{
			JLabel lblTelefono = new JLabel("Telefono");
			lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTelefono.setBounds(21, 208, 150, 47);
			contentPanel.add(lblTelefono);
		}
		{
			JLabel lblFechaDeAlta = new JLabel("Fecha de Alta");
			lblFechaDeAlta.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblFechaDeAlta.setBounds(21, 266, 150, 47);
			contentPanel.add(lblFechaDeAlta);
		}

		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textName.setBounds(280, 36, 215, 47);
		contentPanel.add(textName);
		textName.setColumns(10);

		textLastName = new JTextField();
		textLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textLastName.setColumns(10);
		textLastName.setBounds(280, 92, 215, 47);
		contentPanel.add(textLastName);

		textEmail = new JTextField();
		textEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textEmail.setColumns(10);
		textEmail.setBounds(280, 150, 215, 47);
		contentPanel.add(textEmail);

		textPhone = new JTextField();
		textPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textPhone.setColumns(10);
		textPhone.setBounds(280, 208, 215, 47);
		contentPanel.add(textPhone);
		{
			JLabel lblTitulo = new JLabel("Titulo");
			lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblTitulo.setBounds(21, 324, 150, 47);
			contentPanel.add(lblTitulo);
		}
		{
			textJobTitle = new JTextField();
			textJobTitle.setFont(new Font("Tahoma", Font.PLAIN, 15));
			textJobTitle.setColumns(10);
			textJobTitle.setBounds(280, 324, 215, 47);
			contentPanel.add(textJobTitle);
		}

		JSpinner dateSpinner = new JSpinner(new SpinnerDateModel());
		dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd"));
		dateSpinner.setBounds(280, 275, 209, 32);
		contentPanel.add(dateSpinner);
		{
			JLabel lblManager = new JLabel("Manager");
			lblManager.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblManager.setBounds(21, 382, 150, 47);
			contentPanel.add(lblManager);
		}

		comboBox.setBounds(280, 396, 216, 22);
		contentPanel.add(comboBox);
		cargarJefes();
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						try {
							String nombreString = textName.getText();
							String apellidosString = textLastName.getText();
							String emailString = textEmail.getText();
							String phoneString = textPhone.getText();
							String jobTitleString = textJobTitle.getText();
							String hireDate = new SimpleDateFormat("yyyy-MM-dd").format(dateSpinner.getValue());
							long managerSeleccionado = (long) comboBox.getSelectedIndex();

							empleadoController.crearEmpleado(nombreString, apellidosString, emailString, phoneString,
									hireDate, managerSeleccionado, jobTitleString);

							JOptionPane.showMessageDialog(null, "Empleado insertado correctamente", "Exito",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (DataAccessException e1) {
							JOptionPane.showMessageDialog(null, "Error al insertar empleado: " + e1.getMessage(),
									"Error", JOptionPane.ERROR_MESSAGE);
						}
					}
				});

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

	private void cargarJefes() {
		try {
			List<Empleado> empleados = empleadoController.obtenerTodosEmpleados();
			for (Empleado empleado : empleados) {
				comboBox.addItem(empleado.getName() + " " + empleado.getLastName());
			}
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar los empleados: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
