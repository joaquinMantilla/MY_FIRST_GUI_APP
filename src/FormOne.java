import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class FormOne extends JFrame
{
    private JPanel MainPanel;
    private JTextField txt_Username;
    private JPasswordField txt_password1;
    private JPasswordField txt_password2;
    private JComboBox combo_role;
    private JButton button_insert_data;
    private JButton button_reset_form;

    public FormOne()
    {
        setContentPane(MainPanel);
        setTitle ( "Hello");
        setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
        setSize ( 700, 400);
        setLocation (100, 2400);
        setVisible ( true );

        combo_role.addItem( "Student");
        combo_role.addItem( "Admin");

        combo_role.setSelectedItem( "Student");

        button_insert_data.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {   Properties properties = new Properties();
                try ( InputStream input= new FileInputStream("src/config.Properties"))
                {
                    properties.load( input );

                    String db_name = properties.getProperty( "db.url");
                    String db_user = properties.getProperty( "db.user");
                    String db_password = properties.getProperty( "db.password");

                    String message_string = "DB name is " + db_name + "\nDB user is " + db_user + "\nDB password is "+ db_password;
                    JOptionPane.showMessageDialog( null, message_string);
                    try (Connection conn = DriverManager.getConnection( db_name, db_user, db_password))
                    {
                      System.out.println("Connection succesful!");

                      PreparedStatement insert_statement;
                      insert_statement = conn.prepareStatement("insert into Student_Database.users (username, password, role, created_by) values ( ?, ?, ?, ?)");

                      insert_statement.setString(1, txt_Username.getText());
                      insert_statement.setString(2, txt_password1.getText());
                      insert_statement.setString(3, combo_role.getSelectedItem().toString());
                      insert_statement.setString(4,"MY_FIRST_GUI");

                      int n= insert_statement.executeUpdate();
                      if ( n > 0)
                      {
                          JOptionPane.showMessageDialog( null, "Insert succesfull");
                      }
                    }
                } catch (IOException | SQLException ex)
                {
                    ex.printStackTrace();
                }
            }
        });

        button_reset_form.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                txt_Username.setText (" ");
                txt_password1.setText (" ");
                txt_password2.setText (" ");

                combo_role.setSelectedItem( "Student");
            }
        });
    }


}

