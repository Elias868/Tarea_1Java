import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCliente extends JFrame {
    private JTextField nitField, nombreField, apellidoField, direccionField, telefonoField;
    private JSpinner fechaNacimientoSpinner;
    private JTable table;
    private DefaultTableModel tableModel;

    public FormularioCliente() {
        setTitle("Formulario de Cliente");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        // Crear los componentes del formulario
        JLabel nitLabel = new JLabel("NIT:");
        nitField = new JTextField(20);

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField(20);

        JLabel apellidoLabel = new JLabel("Apellido:");
        apellidoField = new JTextField(20);

        JLabel direccionLabel = new JLabel("Dirección:");
        direccionField = new JTextField(20);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField(20);

        JLabel fechaNacimientoLabel = new JLabel("Fecha de Nacimiento:");
        fechaNacimientoSpinner = new JSpinner(new SpinnerDateModel());
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(fechaNacimientoSpinner, "dd/MM/yyyy");
        fechaNacimientoSpinner.setEditor(dateEditor);

        JButton guardarButton = new JButton("Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCliente();
            }
        });

        JButton borrarButton = new JButton("Borrar");
        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarCliente();
            }
        });

        // Agregar componentes al panel
        panel.add(nitLabel);
        panel.add(nitField);
        panel.add(nombreLabel);
        panel.add(nombreField);
        panel.add(apellidoLabel);
        panel.add(apellidoField);
        panel.add(direccionLabel);
        panel.add(direccionField);
        panel.add(telefonoLabel);
        panel.add(telefonoField);
        panel.add(fechaNacimientoLabel);
        panel.add(fechaNacimientoSpinner);
        panel.add(guardarButton);
        panel.add(borrarButton);

        // Crear tabla para mostrar los datos
        String[] columnNames = {"NIT", "Nombre", "Apellido", "Dirección", "Teléfono", "Fecha de Nacimiento"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Agregar paneles al frame
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void guardarCliente() {
        String nit = nitField.getText();
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String direccion = direccionField.getText();
        String telefono = telefonoField.getText();
        String fechaNacimiento = ((JSpinner.DateEditor) fechaNacimientoSpinner.getEditor()).getFormat().format(fechaNacimientoSpinner.getValue());

        Object[] row = {nit, nombre, apellido, direccion, telefono, fechaNacimiento};
        tableModel.addRow(row);

        // Limpiar campos
        nitField.setText("");
        nombreField.setText("");
        apellidoField.setText("");
        direccionField.setText("");
        telefonoField.setText("");
        fechaNacimientoSpinner.setValue(new java.util.Date());
    }

    private void borrarCliente() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un cliente para borrar.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormularioCliente().setVisible(true);
            }
        });
    }
}
