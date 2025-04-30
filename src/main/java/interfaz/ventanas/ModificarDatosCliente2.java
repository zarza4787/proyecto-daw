package interfaz.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.controller.CustomerController;
import excepciones.DataAccessException;
import modelos.Customers;
import utils.Utils;

public class ModificarDatosCliente2 extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultTableModel model;
	private JButton grabarCambios;

	private CustomerController customerController;
	private JTextField textName;
	private JTextField textAddress;
	private JTextField textWebsite;
	private JTextField textCreditLimit;

	public ModificarDatosCliente2(long customerId) throws DataAccessException {
		customerController = new CustomerController();

		/*
		 * String sentencia = "SELECT * FROM CUSTOMERS WHERE customer_id = " +
		 * customerId;
		 * 
		 * 
		 * try (Connection conn = DBUtils.getConnection()) { // PreparedStatement ps =
		 * conn.prepareStatement(sentencia); Statement ps = null; ps =
		 * conn.createStatement();
		 * 
		 * ResultSet rs = ps.executeQuery(sentencia);
		 * 
		 * while (rs.next()) { firstName = rs.getString("FIRST_NAME"); lastName =
		 * rs.getString("LAST_NAME"); email = rs.getString("EMAIL"); phone =
		 * rs.getString("PHONE"); jobTitle = rs.getString("JOB_TITLE"); fechaAlta =
		 * rs.getString("HIRE_DATE");
		 * 
		 * } } catch (SQLException ex) { throw new
		 * DataAccessException("Error al obtener al empleado", ex); }
		 */

		setResizable(false);
		setTitle("Modificar datos de un cliente");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(32, 38, 94, 29);
		contentPanel.add(lblNewLabel);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAddress.setBounds(32, 91, 94, 29);
		contentPanel.add(lblAddress);

		JLabel lblWebsite = new JLabel("Website");
		lblWebsite.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblWebsite.setBounds(32, 147, 94, 29);
		contentPanel.add(lblWebsite);

		JLabel lblCreditLimit = new JLabel("Limite de credito");
		lblCreditLimit.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblCreditLimit.setBounds(32, 210, 132, 29);
		contentPanel.add(lblCreditLimit);

		textName = new JTextField();
		textName.setBounds(364, 40, 162, 29);
		contentPanel.add(textName);
		textName.setColumns(10);

		textAddress = new JTextField();
		textAddress.setColumns(10);
		textAddress.setBounds(364, 91, 162, 29);
		contentPanel.add(textAddress);

		textWebsite = new JTextField();
		textWebsite.setColumns(10);
		textWebsite.setBounds(364, 147, 162, 29);
		contentPanel.add(textWebsite);

		textCreditLimit = new JTextField();
		textCreditLimit.setColumns(10);
		textCreditLimit.setBounds(364, 210, 162, 29);
		contentPanel.add(textCreditLimit);

		// Creamos un cliente de tipo customer para poder obtener el el ID
		Customers cliente = customerController.obtenerPorId(customerId);

		textName.setText(cliente.getName());
		textAddress.setText(cliente.getAddress());
		textWebsite.setText(cliente.getWebsite());
		textCreditLimit.setText(String.valueOf(cliente.getCreditLimit()));

		model = new DefaultTableModel();
		model.setColumnIdentifiers(new String[] { "ID", "Nombre", "Apellido", "Email" });

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		grabarCambios = new JButton("Grabar cambios");
		grabarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				long customerId = cliente.getCustomerId();
				String nombre = textName.getText();
				String address = textAddress.getText();
				String website = textWebsite.getText();
				double credit = Double.parseDouble(textCreditLimit.getText());
				try {
					customerController.actualizarCustomer(customerId, nombre, address, website, credit);
					JOptionPane.showMessageDialog(null, "El cliente ha sido actualizado con exito", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					dispose();
				} catch (DataAccessException ex) {
					JOptionPane.showMessageDialog(null, "Error al insertar empleado: " + ex.getMessage(), "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
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
