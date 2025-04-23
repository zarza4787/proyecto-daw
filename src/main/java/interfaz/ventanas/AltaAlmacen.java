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

import dao.controller.AlmacenController;
import excepciones.DataAccessException;
import modelos.Region;
import utils.Utils;

public class AltaAlmacen extends JDialog {

	// Declaracion del panel, el almacenController y de los textField para que el
	// usuario introduzca los datos

	private final JPanel contentPanel = new JPanel();
	private AlmacenController almacenController;
	private JTextField textName;
	private JComboBox<String> comboBox_Regiones = new JComboBox<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AltaAlmacen dialog = new AltaAlmacen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AltaAlmacen() {
		setTitle("Insertar Almacen");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		almacenController = new AlmacenController();
		{
			JLabel lblNewLabel = new JLabel("Nombre del almacen");
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNewLabel.setBounds(21, 34, 150, 47);
			contentPanel.add(lblNewLabel);
		}

		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textName.setBounds(280, 36, 215, 47);
		contentPanel.add(textName);
		textName.setColumns(10);

		{
			JLabel lblManager = new JLabel("Regiones");
			lblManager.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblManager.setBounds(21, 93, 150, 47);
			contentPanel.add(lblManager);
		}

		comboBox_Regiones.setBounds(280, 107, 216, 22);
		contentPanel.add(comboBox_Regiones);

		JLabel lblUbicaciones = new JLabel("Ubicaciones");
		lblUbicaciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUbicaciones.setBounds(21, 151, 150, 47);
		contentPanel.add(lblUbicaciones);

		JComboBox comboBox_Ubicaciones = new JComboBox();
		comboBox_Ubicaciones.setBounds(279, 165, 216, 22);
		contentPanel.add(comboBox_Ubicaciones);

		JLabel lblPaises = new JLabel("Paises");
		lblPaises.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPaises.setBounds(21, 209, 150, 47);
		contentPanel.add(lblPaises);

		JComboBox comboBox_Paises = new JComboBox();
		comboBox_Paises.setBounds(280, 223, 216, 22);
		contentPanel.add(comboBox_Paises);
		cargarRegiones();
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
							String nombreAlmacenString = textName.getText();
							long regionSeleccionada = (long) comboBox_Regiones.getSelectedIndex();

							almacenController.crearAlmacen(regionSeleccionada, nombreAlmacenString);

							JOptionPane.showMessageDialog(null, "Almacen insertado correctamente", "Exito",
									JOptionPane.INFORMATION_MESSAGE);
							dispose();
						} catch (DataAccessException e1) {
							JOptionPane.showMessageDialog(null, "Error al insertar un almacen: " + e1.getMessage(),
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

	private void cargarRegiones() {
		try {
			List<Region> regiones = almacenController.obtenerTodasRegiones();
			for (Region region : regiones) {
				comboBox_Regiones.addItem(region.getRegionName());
			}
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
}
