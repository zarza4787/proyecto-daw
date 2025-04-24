package interfaz.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Utils;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTable;

public class PedidosClienteVistaDetalle extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textIdCliente;
	private JTextField textNombreCliente;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PedidosClienteVistaDetalle dialog = new PedidosClienteVistaDetalle();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PedidosClienteVistaDetalle() {
		setResizable(false);
		setTitle("Pedidos de un cliente y vista detalle");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblIdCliente = new JLabel("ID del Cliente");
		lblIdCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblIdCliente.setBounds(80, 30, 137, 30);
		contentPanel.add(lblIdCliente);
		{
			textIdCliente = new JTextField();
			textIdCliente.setBounds(370, 30, 137, 36);
			contentPanel.add(textIdCliente);
			textIdCliente.setColumns(10);
		}
		{
			JLabel lblNombreCliente = new JLabel("Nombre del cliente");
			lblNombreCliente.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblNombreCliente.setBounds(80, 100, 137, 30);
			contentPanel.add(lblNombreCliente);
		}
		{
			textNombreCliente = new JTextField();
			textNombreCliente.setColumns(10);
			textNombreCliente.setBounds(370, 100, 137, 36);
			contentPanel.add(textNombreCliente);
		}

		table = new JTable();
		table.setBounds(10, 172, 564, 290);
		contentPanel.add(table);

		JButton btnDetalles = new JButton("Detalles");
		btnDetalles.setEnabled(false);
		btnDetalles.setBounds(80, 494, 89, 23);
		contentPanel.add(btnDetalles);
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
