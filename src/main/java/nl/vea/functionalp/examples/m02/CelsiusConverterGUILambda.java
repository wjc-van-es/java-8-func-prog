package nl.vea.functionalp.examples.m02;


/*
 * CelsiusConverterGUI.java
 *
 */

import java.awt.event.ActionEvent;

public class CelsiusConverterGUILambda extends javax.swing.JFrame {

    /**
     * Creates new form CelsiusConverterGUI
     */
    public CelsiusConverterGUILambda() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {
        tempTextField = new javax.swing.JTextField();
        celsiusLabel = new javax.swing.JLabel();
        convertButton = new javax.swing.JButton();
        fahrenheitLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Celsius Converter");

        celsiusLabel.setText("Celsius");

        convertButton.setText("Convert");

        /**
         * Beware an {@link java.awt.event.ActionListener} is an interface with only one declared abstract method actionPerformed
         * 	Therefore ActionListener counts as functional interface and as an argument to
         * 	the addActionListener method a lambda expression implementing
         *    {@link java.awt.event.ActionListener#actionPerformed(ActionEvent) could be used instead of an anonymous inner class.
         */
        //convertButton.addActionListener(event -> convertButtonActionPerformed(event));

        //we can even use a method reference here
        convertButton.addActionListener(this::convertButtonActionPerformed);

        fahrenheitLabel.setText("Fahrenheit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(tempTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(celsiusLabel))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(convertButton)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(fahrenheitLabel)))
                                .addContainerGap(27, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[]{convertButton, tempTextField});

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(tempTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(celsiusLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(convertButton)
                                        .addComponent(fahrenheitLabel))
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        pack();
    }

    private void convertButtonActionPerformed(java.awt.event.ActionEvent evt) {
        //Parse degrees Celsius as a double and convert to Fahrenheit
        System.out.println(String.format("An event occurred with actionCommand = %s and paramString = %s",
                evt.getActionCommand(), evt.paramString()));
        int tempFahr = (int) ((Double.parseDouble(tempTextField.getText()))
                * 1.8 + 32);
        fahrenheitLabel.setText(tempFahr + " Fahrenheit");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /**
         * A {@link java.lang.Runnable} also has just one abstract method and can therefore also be
         * treated as a functional interface whereby {@link Runnable#run()} can be implemented with a
         * lambda expression instead of an anonymous inner class.
         */
        java.awt.EventQueue.invokeLater(() -> new CelsiusConverterGUILambda().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JLabel celsiusLabel;
    private javax.swing.JButton convertButton;
    private javax.swing.JLabel fahrenheitLabel;
    private javax.swing.JTextField tempTextField;

}

