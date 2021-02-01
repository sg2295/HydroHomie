
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Creates add record window
 * @author sgavr
 */
public class AddRecordGUI extends javax.swing.JFrame {

    /**
     * MainPage from where the add record window was called
     */
    private MainPageGUI mainPage;

    /**
     * Holds the existing error that is being edited
     */
    private Record oldRecord;
    
    /**
     * Creates an add record window.
     * @param mainPage Main window from where it was called
     */
    public AddRecordGUI(MainPageGUI mainPage) {
        this.oldRecord = null;
        this.mainPage = mainPage;
        initComponents();
        this.errorLabel.setForeground(Color.red);
        this.dateTextBox.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date())); // Set the current time and date
        this.volumeTextField.setText("240.00"); // Average mililitres in a glass
    }

    /**
     * Creates an add record window to edit and existing record.
     * @param mainPage Main window from where it was called
     * @param date Date of the existing record
     * @param type Liquid type of the existing record
     * @param volume Volume of the existing record
     */
    public AddRecordGUI(MainPageGUI mainPage, Record oldRecord) {
        this(mainPage); // Calls above constructor
        this.oldRecord = oldRecord;
        // Fill in the eixisting record data:
        this.dateTextBox.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(this.oldRecord.getDate()));
        this.typeComboBox.setSelectedItem(this.oldRecord.getType().toString());
        this.volumeTextField.setText(String.valueOf(this.oldRecord.getVolume()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        newRecordPanel = new javax.swing.JPanel();
        volumeLabel = new javax.swing.JLabel();
        typeLabel = new javax.swing.JLabel();
        dateLabel = new javax.swing.JLabel();
        typeComboBox = new javax.swing.JComboBox<>();
        volumeTextField = new javax.swing.JTextField();
        dateTextBox = new javax.swing.JTextField();
        addButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        errorLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HydroHomie - Add Record");

        newRecordPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("New Record"));

        volumeLabel.setText("Volume (ml)");

        typeLabel.setText("Type");

        dateLabel.setText("Date:");

        typeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Water", "Milk", "Soup", "Soda" }));

        volumeTextField.setText("Volume");
        volumeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volumeTextFieldActionPerformed(evt);
            }
        });

        dateTextBox.setText("date");
        dateTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dateTextBoxActionPerformed(evt);
            }
        });

        addButton.setText("ADD");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        clearButton.setText("CLEAR");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        exitButton.setText("EXIT");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout newRecordPanelLayout = new javax.swing.GroupLayout(newRecordPanel);
        newRecordPanel.setLayout(newRecordPanelLayout);
        newRecordPanelLayout.setHorizontalGroup(
            newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newRecordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(newRecordPanelLayout.createSequentialGroup()
                            .addComponent(volumeLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(volumeTextField))
                        .addGroup(newRecordPanelLayout.createSequentialGroup()
                            .addComponent(typeLabel)
                            .addGap(18, 18, 18)
                            .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(newRecordPanelLayout.createSequentialGroup()
                            .addComponent(dateLabel)
                            .addGap(18, 18, 18)
                            .addComponent(dateTextBox)))
                    .addComponent(errorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clearButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        newRecordPanelLayout.setVerticalGroup(
            newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newRecordPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(newRecordPanelLayout.createSequentialGroup()
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(clearButton)
                        .addGap(18, 18, 18)
                        .addComponent(exitButton))
                    .addGroup(newRecordPanelLayout.createSequentialGroup()
                        .addGroup(newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(volumeLabel)
                            .addComponent(volumeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(typeLabel)
                            .addComponent(typeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(newRecordPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dateLabel)
                            .addComponent(dateTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errorLabel)))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newRecordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newRecordPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        newRecordPanel.getAccessibleContext().setAccessibleName("New Goal");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void dateTextBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dateTextBoxActionPerformed
    }//GEN-LAST:event_dateTextBoxActionPerformed

    /**
     * Extracts the record data input, sends it to the main window to be
     * handled, and triggers the transition from this window to the main one.
     *
     * @param evt Event triggering the action
     */
    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // Strings holding user input
        String volumeStr = this.volumeTextField.getText();
        String dateStr = this.dateTextBox.getText();
        String typeStr = (String) this.typeComboBox.getSelectedItem();

        float volume = 0;
        Date date = null;

        if (!this.checkValues()) {
            return; // Checks that the given inputs are valid
        }
        this.errorLabel.setText(""); // Removes error message

        // Converts String values to their corresponding types
        try {
            volume = Float.parseFloat(volumeStr);
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateStr);
        } catch (NumberFormatException | ParseException e) {
            System.err.println("Invalid data types entered.");
        }

        this.mainPage.addRecordToTable(volume, typeStr, date); // Adds the new record to the table
        this.moveToMainWindow(); // Closes this window and opens the main one
    }//GEN-LAST:event_addButtonActionPerformed

    /**
     * Resets initial values to the text fields and combo box.
     *
     * @param evt Event triggering the action
     */
    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        this.volumeTextField.setText("240.00");
        this.dateTextBox.setText(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
        this.typeComboBox.setSelectedIndex(0);
        this.errorLabel.setText(""); // Remove error message
    }//GEN-LAST:event_clearButtonActionPerformed

    private void volumeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volumeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_volumeTextFieldActionPerformed

    /**
     * Move back to main window
     * @param evt Event triggering the action
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        // Move back to main window
        if (this.oldRecord != null) {
            this.mainPage.addRecordToTable(this.oldRecord);
        }
        this.moveToMainWindow(); 
    }//GEN-LAST:event_exitButtonActionPerformed

    /**
     * Checks that input volume is a positive number. Prints error message if
     * volume is not acceptable.
     *
     * @return True if it is a positive number, false otherwise
     */
    private boolean checkVolume() {
        boolean validFlag = true;
        String volumeStr = this.volumeTextField.getText(); // Gets given volume as a String
        float volume = 0;

        // Attempt to convert String value to float
        try {
            volume = Float.parseFloat(volumeStr);
        } catch (NumberFormatException e) {
            validFlag = false;
            System.err.println("Invalid volume entered.");
        }

        // Check that the value of the volume is positive
        if (volume <= 0) {
            validFlag = false;
            System.err.println("Non-positive volume entered.");
        }

        return validFlag;
    }

    /**
     * Checks that the given date is a valid date. Prints error message if date
     * is not acceptable.
     *
     * @return True if it is a valid date, false otherwise
     */
    private boolean checkDate() {
        boolean validFlag = true;
        String dateStr = this.dateTextBox.getText(); // Gets the given date as a String
        Date date = null;
        // Attempt to create a date using the given String
        try {
            date = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(dateStr);
            // Check that the date is not an invalid (future) date
            if (new Date().before(date)) {
                validFlag = false;
                System.err.println("Future date entered.");
            }
        } catch (ParseException e) {
            validFlag = false;
            System.err.println("Invalid date entered.");
        }

        return validFlag;
    }

    /**
     * Check that values given by the user are valid. If the values are invalid
     * a specialised error message is printed.
     *
     * @return True if given values are valid, false otherwise.
     */
    private boolean checkValues() {
        boolean validFlag, dateFlag, volumeFlag; // Flags used to check for incompatible inputs
        // Check date and volume inputs:
        dateFlag = this.checkDate();
        volumeFlag = this.checkVolume();
        
        if (dateFlag && volumeFlag) { // set flag to true and remove existing error message:
            validFlag = true;
            this.errorLabel.setText("");
        } else { // Otherwise set flag to false, and print corresponding error message
            validFlag = false;
            if (!dateFlag && !volumeFlag) {
                this.errorLabel.setText("Given inputs are invalid, try again.");
            } else if (!volumeFlag && dateFlag) {
                this.errorLabel.setText("Given volume value is invalid, try again.");
            } else {
                this.errorLabel.setText("Given date is invalid, try again.");
            }
        }

        return validFlag;
    }

    /**
     * Closes the add record window and opens the main window.
     */
    private void moveToMainWindow() {
        this.setVisible(false);
        this.mainPage.setVisible(true);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddRecordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddRecordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddRecordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddRecordGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddRecordGUI(new MainPageGUI()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton clearButton;
    private javax.swing.JLabel dateLabel;
    private javax.swing.JTextField dateTextBox;
    private javax.swing.JLabel errorLabel;
    private javax.swing.JButton exitButton;
    private javax.swing.JPanel newRecordPanel;
    private javax.swing.JComboBox<String> typeComboBox;
    private javax.swing.JLabel typeLabel;
    private javax.swing.JLabel volumeLabel;
    private javax.swing.JTextField volumeTextField;
    // End of variables declaration//GEN-END:variables
}
