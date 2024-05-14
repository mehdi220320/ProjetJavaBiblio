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

public class Userpage extends JDialog {
    private int pg;
    private JTextField docID;
    private JTextField NBPage;
    private JTable table1;
    private JButton logoutButton;
    private JButton updateNbDocumentButton;
    private JPanel UserPanel;
    private JButton EmprenteButton;
    private JButton DocumentButton;
    private JButton RequestButon;
    private JButton SendRequest;
    private ServiceDocumentImpl liv;
    private ServiceUtilisateurImpl users;
    private emplivreImpl emplivre;
    private demandeimpl dem;
    public Userpage(JFrame parent){
        super(parent);
        this.users = new ServiceUtilisateurImpl();
        this.emplivre=new emplivreImpl();
        this.liv=new ServiceDocumentImpl();
        this.table_load();
        this.dem=new demandeimpl();
        setTitle("Create a new account");
        setContentPane(UserPanel);
        setMinimumSize(new Dimension(890, 600));
        setModal(true);
        setLocationRelativeTo(parent);
        updateNbDocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatenb();

            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Index(null);
            }
        });
        EmprenteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchview(0);
                table_load();
            }
        });
        DocumentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchview(1);
                table_load();
            }
        });
        RequestButon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchview(2);
                table_load();
            }
        });
        SendRequest.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRequest();
               table_load();
            }
        });
        setVisible(true);

    }

    private void addRequest() {
//        int dID =Integer.parseInt(this.docID.getText());
//        Livre livre= (Livre) this.liv.getlivre(dID);
//        this.dem.addDemande(new demande(livre.getTitre(),this.util));
    }

    private void switchview(int i) {
        this.pg=i;
    }

    private void updatenb() {
        int idoc=Integer.parseInt(this.docID.getText());
        int nbp=Integer.parseInt(this.NBPage.getText());
        System.out.println(this.users.getUser_connected());
        emplivre.updatenombrepages(idoc, nbp, this.users.getUser_connected());
    }


    public TableModel resultSetToTableModel(ResultSet rs) {
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

    private void table_load() {
        if(this.pg==0){
            ResultSet rs = this.liv.getDocuments();
            table1.setModel(this.resultSetToTableModel(rs));
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        }
        else if(this.pg==1){
            ResultSet rs = this.emplivre.getEmpruntsByUser2(this.users.getUser_connected());
            table1.setModel(this.resultSetToTableModel(rs));
            table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }else{

        }
    }

//    public static void main(String[] args) {
//        new Userpage(null);
//    }
}
