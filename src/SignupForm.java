import javax.swing.*;
import java.awt.*;

public class SignupForm extends JDialog {
    private JTextField tfUsername;
    private JTextField tfPassword;
    private JTextField tfEmail;
    private JButton btnCancel;
    private JButton btnRegister;
    private JPanel SignupPanel;
    public SignupForm(JFrame parent){
        super(parent);
        setTitle("Create a new account");
        setContentPane(SignupPanel);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public static void main(String[] args) {
        SignupForm form =new SignupForm(null);
    }


}
