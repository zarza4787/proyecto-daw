package com.daw.dao.interfaz;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.daw.interfaz.ventanas.AltaAlmacen;
import com.daw.interfaz.ventanas.AltaEmpleado;
import com.daw.interfaz.ventanas.AplicarDescuentoPorCategoria;
import com.daw.interfaz.ventanas.EliminarEmpleado;
import com.daw.interfaz.ventanas.InformeVentasManager;
import com.daw.interfaz.ventanas.ModificarDatosCliente;
import com.daw.interfaz.ventanas.PedidosClienteVistaDetalle;
import com.daw.interfaz.ventanas.RankingEmpleadosPorVolumenVentas;
import com.daw.interfaz.ventanas.TraspasoCierreAlmacen;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnAltaEmpleado = new JButton("Alta Nuevo Empleado");
		btnAltaEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaEmpleado d1 = new AltaEmpleado();
				d1.setVisible(true);

			}
		});
		btnAltaEmpleado.setBounds(150, 27, 300, 23);
		contentPane.add(btnAltaEmpleado);

		JButton btnAltaAlmacen = new JButton("Alta Nuevo Almacen");
		btnAltaAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaAlmacen d1 = new AltaAlmacen();
				d1.setVisible(true);

			}
		});
		btnAltaAlmacen.setBounds(150, 77, 300, 23);
		contentPane.add(btnAltaAlmacen);

		JButton btnModificarCliente = new JButton("Modificar Cliente");
		btnModificarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarDatosCliente d1 = new ModificarDatosCliente();
				d1.setVisible(true);

			}
		});

		btnModificarCliente.setBounds(150, 127, 300, 23);
		contentPane.add(btnModificarCliente);

		JButton btnPedidosVistaCliente = new JButton("Pedidos de un Cliente y Vista Detalle");
		btnPedidosVistaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidosClienteVistaDetalle d1 = new PedidosClienteVistaDetalle();
				d1.setVisible(true);
			}
		});
		btnPedidosVistaCliente.setBounds(150, 177, 300, 23);
		contentPane.add(btnPedidosVistaCliente);

		JButton btnEliminarEmpleado = new JButton("Eliminar Empleado");
		btnEliminarEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EliminarEmpleado d1 = new EliminarEmpleado();
				d1.setVisible(true);
			}
		});
		btnEliminarEmpleado.setBounds(150, 227, 300, 23);
		contentPane.add(btnEliminarEmpleado);

		JButton btnTraspasoCierreAlmacen = new JButton("Traspaso y Cierre de un almacen");
		btnTraspasoCierreAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TraspasoCierreAlmacen d1 = new TraspasoCierreAlmacen();
				d1.setVisible(true);
			}
		});
		btnTraspasoCierreAlmacen.setBounds(150, 277, 300, 23);
		contentPane.add(btnTraspasoCierreAlmacen);

		JButton btnAplicarDescuentoPorCategoria = new JButton("Aplicar Descuento Por Categoria");
		btnAplicarDescuentoPorCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AplicarDescuentoPorCategoria d1 = new AplicarDescuentoPorCategoria();
				d1.setVisible(true);
			}
		});
		btnAplicarDescuentoPorCategoria.setBounds(150, 327, 300, 23);
		contentPane.add(btnAplicarDescuentoPorCategoria);

		JButton btnRankingEmpleadosPorVolumenVentas = new JButton("Ranking de Empleados Por Volumen de Ventas");
		btnRankingEmpleadosPorVolumenVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankingEmpleadosPorVolumenVentas d1 = new RankingEmpleadosPorVolumenVentas();
				d1.setVisible(true);
			}
		});
		btnRankingEmpleadosPorVolumenVentas.setBounds(150, 377, 300, 23);
		contentPane.add(btnRankingEmpleadosPorVolumenVentas);

		JButton btnInformeVentasManager = new JButton("Informe de Ventas de un Manager");
		btnInformeVentasManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InformeVentasManager d1 = new InformeVentasManager();
				d1.setVisible(true);
			}
		});

		btnInformeVentasManager.setBounds(150, 427, 300, 23);
		contentPane.add(btnInformeVentasManager);

		JButton btnCancelarPedidoDevolverAlmacen = new JButton("Cancelar Pedido y Devolver a Almacen");
		btnCancelarPedidoDevolverAlmacen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TraspasoCierreAlmacen d1 = new TraspasoCierreAlmacen();
				d1.setVisible(true);
			}
		});
		btnCancelarPedidoDevolverAlmacen.setBounds(150, 477, 300, 23);
		contentPane.add(btnCancelarPedidoDevolverAlmacen);

	}
}
