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
import dao.controller.RegionController;
import excepciones.DataAccessException;
import modelos.Countries;
import modelos.Locations;
import modelos.Region;
import utils.Utils;

public class AltaAlmacen extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private AlmacenController almacenController; // controller para el almacen
	private RegionController regionController; // controller para la region

	// Componentes de la interfaz
	private JTextField textName;
	private JComboBox<String> comboBox_Regiones;
	private JComboBox<String> comboBox_Paises;
	private JComboBox<String> comboBox_Ubicaciones;

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

	// Lista de regiones y countries
	private List<Region> listaRegiones;
	private List<Countries> listaPaises;
	private List<Locations> listaLocations;

	public AltaAlmacen() {
		setTitle("Insertar Almacen");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		// Inicializamos el controller
		almacenController = new AlmacenController();
		regionController = new RegionController();

		JLabel lblNombre = new JLabel("Nombre del almacen");
		lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombre.setBounds(21, 34, 150, 47);
		contentPanel.add(lblNombre);

		textName = new JTextField();
		textName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		textName.setBounds(280, 36, 215, 47);
		contentPanel.add(textName);
		textName.setColumns(10);

		JLabel lblRegiones = new JLabel("Regiones");
		lblRegiones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRegiones.setBounds(21, 93, 150, 47);
		contentPanel.add(lblRegiones);

		comboBox_Regiones = new JComboBox<>();
		comboBox_Regiones.setBounds(280, 107, 216, 22);
		contentPanel.add(comboBox_Regiones);
		comboBox_Regiones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarPaisesPorRegion();
			}
		});

		JLabel lblUbicaciones = new JLabel("Ubicaciones");
		lblUbicaciones.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUbicaciones.setBounds(21, 209, 150, 47);
		contentPanel.add(lblUbicaciones);

		comboBox_Ubicaciones = new JComboBox<>();
		comboBox_Ubicaciones.setBounds(280, 223, 216, 22);
		contentPanel.add(comboBox_Ubicaciones);

		JLabel lblPaises = new JLabel("Paises");
		lblPaises.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPaises.setBounds(21, 151, 150, 47);
		contentPanel.add(lblPaises);

		comboBox_Paises = new JComboBox<>();
		comboBox_Paises.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarUbicacionesPorPais();
			}
		});
		comboBox_Paises.setBounds(279, 165, 216, 22);
		contentPanel.add(comboBox_Paises);

		cargarRegiones(); // Cargamos las regiones en el combox

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);

		JButton okButton = new JButton("Crear nuevo almacén");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String nombreAlmacen = textName.getText();

				if (nombreAlmacen.isEmpty()) {
					okButton.setEnabled(false); // Deshabilitamos el boton en caso de que no haya texto
				} else {
					try {
						// Obtenemos la region seleccionada
						int opcionRegion = comboBox_Regiones.getSelectedIndex();
						if (opcionRegion == -1)
							return;

						// Obtenemos la ID de la region seleccionada
						long regionSeleccionada = listaRegiones.get(opcionRegion).getRegionId();

						// Llamamos al controller para insertar el almacen en la base de datos
						almacenController.crearAlmacen(regionSeleccionada, nombreAlmacen);

						JOptionPane.showMessageDialog(null, "Almacen insertado correctamente", "Exito",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (DataAccessException ex) {
						JOptionPane.showMessageDialog(null, "Error al insertar un almacen: " + ex.getMessage(), "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);

		JButton cancelButton = new JButton("Cancelar");
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		buttonPane.add(cancelButton);
	}

	private void cargarRegiones() {
		try {
			// limpiamos items para evitar que se mezclen las regiones
			comboBox_Regiones.removeAllItems();

			listaRegiones = regionController.obtenerTodasRegiones();

			for (Region region : listaRegiones) {
				comboBox_Regiones.addItem(region.getRegionName());
			}
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar regiones: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarPaisesPorRegion() {
		try {
			// limpiamos items para evitar que se mezclen las ubicaciones y los paises
			comboBox_Paises.removeAllItems();
			comboBox_Ubicaciones.removeAllItems();

			// guardamos la opcion seleccionada en una variable
			int numOpcion = comboBox_Regiones.getSelectedIndex();
			if (numOpcion == -1)
				return;

			// guardamos la id de la region
			int regionId = (int) listaRegiones.get(numOpcion).getRegionId();

			// Obtenemos el pais de la region y la guardamos en la lista
			listaPaises = regionController.obtenerPaisesPorRegion(regionId);
			for (Countries pais : listaPaises) {
				comboBox_Paises.addItem(pais.getCountryName());
			}
		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar paises: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void cargarUbicacionesPorPais() {
		try {
			// removemos items para evitar que se mezclen las ubicaciones
			comboBox_Ubicaciones.removeAllItems();

			// guardamos la opcion seleccionada en una variable
			int numOpcion = comboBox_Paises.getSelectedIndex();
			if (numOpcion == -1)
				return;

			// guardamos la id del pais
			String countryId = listaPaises.get(numOpcion).getCountryID();

			// Obtenemos la ubicacion del pais y la gardamos en la lista de ubicaciones
			listaLocations = regionController.obtenerUbicacionesPorPais(countryId);

			if (listaLocations.isEmpty()) {
				comboBox_Ubicaciones.addItem("No existen ubicaciones");
			} else {
				for (Locations ubicacion : listaLocations) {
					comboBox_Ubicaciones.addItem(ubicacion.getCity());
				}
			}

		} catch (DataAccessException e) {
			JOptionPane.showMessageDialog(this, "Error al cargar ubicaciones: " + e.getMessage(), "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
