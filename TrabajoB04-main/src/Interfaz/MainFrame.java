//Marcos López Gómez

package B04.Interfaz;

import B04.PaqB04.Contenedores;
import B04.PaqB04.Puerto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame{
    private JTextField textNId;
    private JTextField textPeso;
    private JTextArea textAreaDescripcion;
    private JComboBox comboBoxPais;
    private JCheckBox Prioridad3CheckBox;
    private JCheckBox Prioridad2CheckBox;
    private JCheckBox Prioridad1CheckBox;
    private JTextField textFieldRemitente;
    private JTextField textFieldReceptora;
    private JCheckBox inspeccionadoCheckBox;
    private JTextArea textAreaEstado;
    private JButton apilarButton;
    private JButton desapilarButton;
    private JButton mostrarButton;
    private JButton cuantosButton;
    private JTextField textFieldMostrarDatos;
    private JTextField textField6;
    private JTextField textFieldDesapilar;
    private JComboBox comboBoxCuantos;
    private JPanel operaciones;
    private JPanel mainPanel;
    private JTextField textFieldCuantos;
    private JPanel Logo;
    private JButton pesoTotalHubButton;
    private JTextField textFieldpesoTotal;

    public MainFrame() {
        setTitle("GESTOR DE CONTENEDORES");
        setSize(1500,920);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        setContentPane(mainPanel);

        Contenedores c1=new Contenedores();
        Puerto p1=new Puerto();
        p1.inicializar();

        apilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                boolean abortar=false;//Si es true no se realizará ningún cambio en el hub
                try{
                    if(p1.existeId(Integer.parseInt(textNId.getText()))){//No puede haber dos identificadores iguales
                        JOptionPane.showMessageDialog(mainPanel,"Ese identificador ya está en uso");
                        abortar=true;
                    }else{c1.setIdentificador(Integer.parseInt(textNId.getText()));}
                }catch(Exception e){//en caso de que el campo introducido no sea un entero o resulte en alguna otra excepción:
                    JOptionPane.showMessageDialog(mainPanel,"Necesita introducir un identificador válido");
                    abortar=true;
                }

                try{
                    if(c1.setPeso(Integer.parseInt(textPeso.getText()))==0){//Si devuelve 0 es porque se ha asignado correctamente
                    c1.setPeso(Integer.parseInt(textPeso.getText()));
                    }else{
                        JOptionPane.showMessageDialog(mainPanel,"El peso no es válido");
                        abortar=true;}
                }catch(Exception e) {
                    JOptionPane.showMessageDialog(mainPanel,"Necesita introducir un peso válido");
                    abortar=true;
                }

                if(!textAreaDescripcion.getText().isBlank()) {
                    c1.setDescripcion(textAreaDescripcion.getText());
                }else {//Si no se ha introducido una descripcion se comunica con un mensaje
                    JOptionPane.showMessageDialog(mainPanel,"Necesita introducir una descripción");
                    abortar=true;
                }

                if(!textFieldRemitente.getText().isBlank()) {
                    c1.setEnvia(textFieldRemitente.getText());
                }else {
                    JOptionPane.showMessageDialog(mainPanel,"Necesita introducir un remitente");
                    abortar=true;
                }

                if(!textFieldReceptora.getText().isBlank()) {
                    c1.setRecibe(textFieldReceptora.getText());
                }else {
                    JOptionPane.showMessageDialog(mainPanel,"Necesita introducir un receptor");
                    abortar=true;
                }

                c1.setPais((String)comboBoxPais.getSelectedItem());
                if(Prioridad1CheckBox.isSelected()){
                    c1.setPrioridad(1);
                }else if(Prioridad2CheckBox.isSelected()){
                    c1.setPrioridad(2);
                }else if(Prioridad3CheckBox.isSelected()){
                    c1.setPrioridad(3);
                }else{
                    //error porque no hay nada seleccionado
                    JOptionPane.showMessageDialog(mainPanel,"Necesita seleccionar una prioridad");
                    abortar=true;
                }

                if(inspeccionadoCheckBox.isSelected()){
                    c1.setInspeccion(true);
                } else{
                    c1.setInspeccion(false);
                }
                if(!abortar){//En caso de no haber sucedido ningún error "abortar" será false y se ejecutará el siguiente switch:
                    switch (p1.agregarContenedor(c1)){
                        case 0:
                            textAreaEstado.setText(p1.toString());//Simplemente se actualiza el estado del HUB
                            break;
                        case 1://Si agregarContenedor devuelve algo distinto de 0 es porque la prioridad devuelta esta llena.
                            JOptionPane.showMessageDialog(mainPanel,"No hay espacios disponibles para contenedores de prioridad 1");
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(mainPanel,"No hay espacios disponibles para contenedores de prioridad 2");
                            break;
                        case 3:
                            JOptionPane.showMessageDialog(mainPanel,"No hay espacios disponibles para contenedores de prioridad 3");
                    }
                }
            }
        });
        desapilarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    boolean vacia=false;//En caso de true la columna estará vacía y se lanzará un aviso mas tarde
                    if(p1.cVacia(Integer.parseInt(textFieldDesapilar.getText()))){
                        vacia=true;
                    }

                    switch (p1.desapilar(Integer.parseInt(textFieldDesapilar.getText()))){//Dependiendo del valor devuelto se sabe si el valor era una columna valida
                        case 0:
                            textAreaEstado.setText(p1.toString());//Se actualiza el estado del HUB
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(mainPanel,"Columna errónea, se elegirá la 1ª columna");
                            textAreaEstado.setText(p1.toString());
                            break;
                        case 2:
                            JOptionPane.showMessageDialog(mainPanel,"Columna errónea, se eligirá la "+p1.getnColumnas()+"º columna");
                            textAreaEstado.setText(p1.toString());
                    }

                    if(vacia){//Aviso de columna vacia, despues del switch en caso de que se introduzca un numero menor o mayor al de columnas disponibles
                        JOptionPane.showMessageDialog(mainPanel,"La columna no tiene contenedores");
                    }
                }catch (Exception e){//en caso de que el campo introducido no sea un entero o resulte en alguna otra excepción:
                    JOptionPane.showMessageDialog(mainPanel,"Introduzca una columna del HUB válida");
                }
            }
        });
        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try{
                    JOptionPane.showMessageDialog(mainPanel, p1.mostrarDatos(Integer.parseInt(textFieldMostrarDatos.getText())));
                }catch (Exception e){
                    JOptionPane.showMessageDialog(mainPanel,"Introduzca un numero de identificacion válido");
                }
            }
        });
        cuantosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textFieldCuantos.setText(String.valueOf(p1.numPais((String)comboBoxCuantos.getSelectedItem())));
            }
        });
        pesoTotalHubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               textFieldpesoTotal.setText(p1.toStringPesoT(p1.pesoTotal()));
            }
        });
    }
}
