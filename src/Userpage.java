import entities.User;
import services.ServiceDocumentImpl;
import services.ServiceUtilisateurImpl;
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
    private JTextField docID;
    private JTextField NBPage;
    private JTable table1;
    private JButton logoutButton;
    private JButton updateNbDocumentButton;
    private JPanel UserPanel;
    private ServiceDocumentImpl liv;
    private ServiceUtilisateurImpl users;
    private emplivreImpl emplivre;
    public Userpage(JFrame parent){
        super(parent);
        this.users = new ServiceUtilisateurImpl();
        this.emplivre=new emplivreImpl();
        this.liv=new ServiceDocumentImpl();
        this.table_load();
        setTitle("Create a new account");
        setContentPane(UserPanel);
        setMinimumSize(new Dimension(780, 400));
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
        setVisible(true);

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

            // Get the column names
            for (int column = 0; column < numberOfColumns; column++) {
                columnNames.addElement(metaData.getColumnLabel(column + 1));
            }

            // Get all rows.
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
        ResultSet rs = this.liv.getDocuments();
        table1.setModel(this.resultSetToTableModel(rs));
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    public static void main(String[] args) {
        new Userpage(null);
    }
}
