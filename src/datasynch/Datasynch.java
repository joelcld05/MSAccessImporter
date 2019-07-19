
package datasynch;
import frames.MainFrame;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Datasynch {
    public static void main(String[] args) {
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainFrame mf = new MainFrame();
                    mf.setResizable(false);
                    mf.setDefaultCloseOperation(mf.EXIT_ON_CLOSE);
                    mf.pack();
                    mf.setLocationRelativeTo(null);
                    mf.setVisible(true);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Datasynch.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
    }
}
