import javax.swing.*;

public class FormFour extends JFrame
{
    private JPanel MainPanel;
    public FormFour()
    {
        setContentPane(MainPanel);
        setTitle ( "Hello");
        setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
        setSize ( 300, 600);
        setLocation (1000, 500);
        setVisible ( true );
    }
}