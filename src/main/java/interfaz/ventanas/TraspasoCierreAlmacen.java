package interfaz.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utils.Utils;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

public class TraspasoCierreAlmacen extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textIdAlmacen1;
	private JTextField textIdAlmacen2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TraspasoCierreAlmacen dialog = new TraspasoCierreAlmacen();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TraspasoCierreAlmacen() {
		setResizable(false);
		setTitle("Traspaso y cierre de almacen");
		setSize(600, 600);
		setLocation((Utils.AnchoPantalla() - this.getWidth()) / 2, (Utils.AltoPantalla() - this.getHeight()) / 2);
		setModal(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		{
			JLabel lblWareHouseId = new JLabel("ID del Almacen");
			lblWareHouseId.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblWareHouseId.setBounds(80, 30, 110, 30);
			contentPanel.add(lblWareHouseId);
		}
		{
			JLabel lblWareHouseId2 = new JLabel("ID del Almacen a Traspasar");
			lblWareHouseId2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblWareHouseId2.setBounds(80, 100, 195, 30);
			contentPanel.add(lblWareHouseId2);
		}
		{
			textIdAlmacen1 = new JTextField();
			textIdAlmacen1.setBounds(330, 30, 137, 36);
			contentPanel.add(textIdAlmacen1);
			textIdAlmacen1.setColumns(10);
		}
		{
			textIdAlmacen2 = new JTextField();
			textIdAlmacen2.setColumns(10);
			textIdAlmacen2.setBounds(330, 100, 137, 36);
			contentPanel.add(textIdAlmacen2);
		}
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
