import javax.swing.*;

public class FormTwo extends JFrame
{
    private JPanel MainPanel;
    public FormTwo()
    {
        setContentPane(MainPanel);
        setTitle ( "Hello");
        setDefaultCloseOperation ( DISPOSE_ON_CLOSE );
        setSize ( 300, 600);
        setLocation (400, 100);
        setVisible ( true );
    }
}
