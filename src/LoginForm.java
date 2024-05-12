import services.ServiceUtilisateurImpl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm  extends  JDialog{
    private JTextField ftEmail;
    private JPasswordField ftPassword;
    private JButton registerButton;
    private JButton confirmButton;
    private JButton cancelButton;
    private JPanel LoginPanel;
    ServiceUtilisateurImpl serviceUtilisateur ;
    public LoginForm(JFrame parent){
        super(parent);
        setTitle("Login Account");
        setContentPane(LoginPanel);
        setMinimumSize(new Dimension(600,600));
        setModal(true);
        setLocationRelativeTo(parent);
        this.serviceUtilisateur=new ServiceUtilisateurImpl() ;
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SignupForm signupForm = new SignupForm(null);
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Index index=new Index(null);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login();
            }
        });
        setVisible(true);
    }

    private void Login() {
        String email=this.ftEmail.getText();
        String password=this.ftPassword.getText();
        if(email.isEmpty()|| password.isEmpty()){
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Login failed");
        }
        else if(this.serviceUtilisateur.getByEmail(email)==null){
            JOptionPane.showMessageDialog(this,
                    "Account doesn't exist",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Login failed");
        }else{
            if(this.serviceUtilisateur.login(email,password)){
                JOptionPane.showMessageDialog(this,
                        "You are welcome",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                System.out.println("Login Successed");
                dispose();
                Index index=new Index(null);
            }else{
                System.out.println(this.serviceUtilisateur.login(email,password));
                JOptionPane.showMessageDialog(this,
                        "Wrong password",
                        "Try Again",
                        JOptionPane.ERROR_MESSAGE);
                System.out.println("Login failed");
            }
        }
    }


}
