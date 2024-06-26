import entities.User;
import entities.emplivre;
import services.ServiceDocumentImpl;
import services.ServiceUtilisateurImpl;
import services.demandeimpl;
import services.emplivreImpl;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

public class Adminpage extends JDialog {
    private  int pg;
    private JTable table1;
    private JTextField UserID;
    private JTextField DocID;
    private JTextField NbDoc;
    private JButton addBookButton;
    private JButton deleteEmprunt;
    private JButton searchUserButton;
    private JPanel Adminpanel;
    private JButton Switchusers;
    private JButton logoutButton;
    private JTextField Emailtf;
    private JButton empruntsButton;
    private JButton livresButton;
    private JButton RequestList;
    private ServiceUtilisateurImpl users;
    private emplivreImpl emplivre;
    private ServiceDocumentImpl liv;
    private demandeimpl dems;
    public Adminpage(JFrame parent) {
        super(parent);
        this.users = new ServiceUtilisateurImpl();
        this.emplivre=new emplivreImpl();
        this.liv=new ServiceDocumentImpl();
        this.dems=new demandeimpl();
        this.table_load();
        setTitle("Admin page");
        setContentPane(Adminpanel);
        setMinimumSize(new Dimension(1000, 600));
        setModal(true);
        setLocationRelativeTo(parent);

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addbook();
                table_load();
            }
        });

        deleteEmprunt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletebook();
                table_load();
            }
        });
        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                table_load();
            }
        });
        Switchusers.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switchtable(0);
                table_load();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Index(null);
            }
        });
        searchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchuser();
            }
        });

        livresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchtable(1);
                table_load();
            }
        });
        empruntsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchtable(2);
                table_load();
            }
        });

        RequestList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchtable(3);
                table_load();
            }
        });





        setVisible(true);
    }

    private void searchuser() {
        if (this.Emailtf.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Register failed");
            return;
        }else{
            String email=this.Emailtf.getText();
            User user=this.users.getByEmail(email);
            System.out.println(user.toString());
            this.UserID.setText(""+user.getId());
            this.Emailtf.setText(email);
            this.NbDoc.setText(""+user.getNblivre());
        }
    }

    private void switchtable(int i) {
        this.pg=i;
    }

    private void deletebook() {
        if (this.DocID.getText().isEmpty() || this.UserID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Register failed");
            return;
        }else{
            int livreid=Integer.parseInt(this.DocID.getText());
            int userId=Integer.parseInt(this.UserID.getText());
            this.emplivre.deleteEmprunt(livreid,userId);
        }
    }

    private void addbook() {

        if (this.DocID.getText().isEmpty() || this.UserID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Please enter all fields",
                    "Try Again",
                    JOptionPane.ERROR_MESSAGE);
            System.out.println("Register failed");
            return;
        }else{
            int livreid=Integer.parseInt(this.DocID.getText());
            int userId=Integer.parseInt(this.UserID.getText());
            emplivre emlivre= new emplivre(livreid,userId);
            this.emplivre.addEmprunt(emlivre);}
    }

    void table_load() {
        if(this.pg==0){
        ResultSet rs = this.users.getUsers2();
        table1.setModel(this.resultSetToTableModel(rs));
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
        else if(this.pg==1){
            ResultSet rs =  this.liv.getDocuments();
            table1.setModel(this.resultSetToTableModel(rs));
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        } else if(this.pg==2){
            ResultSet rs =  this.emplivre.getAllEmprunts2();
            table1.setModel(this.resultSetToTableModel(rs));
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }else{
            ResultSet rs =  this.dems.getDemande();
            table1.setModel(this.resultSetToTableModel(rs));
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }
    }
    public  TableModel resultSetToTableModel(ResultSet rs) {
        try {
            ResultSetMetaData metaData = rs.getMetaData();
            int numberOfColumns = metaData.getColumnCount();
            Vector columnNames = new Vector();

            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }

            Vector rows = new Vector();

            while (rs.next()) {
                Vector newRow = new Vector();

                for (int i = 1; i <= numberOfColumns; i++) {
                    newRow.addElement(rs.getObject(i));
                }

                rows.addElement(newRow);
            }

            return new DefaultTableModel(rows, columnNames);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }
//    public static void main(String[] args) {
//        Adminpage adminform = new Adminpage(null);
//    }
}
