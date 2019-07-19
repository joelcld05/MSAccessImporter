/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frames;
import datasynch.CoreFunctions;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ItemEvent;
import java.io.*;
import javax.swing.filechooser.*;
import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;

 
public class MainFrame extends javax.swing.JFrame {
    private int selectecOption = 1;
    private boolean processrunning = false;
    private CoreFunctions cf;
    private CoreFunctions dbTodb;
    protected static final int TIMER_DELAY = 600;
    
    
    DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
    
    public MainFrame() throws ClassNotFoundException {
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        cf = new CoreFunctions();
        initComponents();
    }
    
    private void setPanelEnabled(JPanel panel, Boolean isEnabled) {
        processrunning = !isEnabled;
        panel.setEnabled(isEnabled);
        Component[] components = panel.getComponents();
        settxt.setEnabled(isEnabled);
        setDB.setEnabled(isEnabled);
        runButton.setEnabled(isEnabled);
        cancelButton.setEnabled(!isEnabled);
        whereCondition.setEnabled(isEnabled);  
        columnsList.setEnabled(isEnabled);  
        for(int i = 0; i < components.length; i++) {
            if("javax.swing.JPanel".equals(components[i].getClass().getName())) {
                setPanelEnabled((JPanel) components[i], isEnabled);
            }
            components[i].setEnabled(isEnabled);
        }
    }
    
        private void runTextToAccess(){
        Object tableSelected;
        String separator;
        String condition;
        Thread background;
        
        tableSelected =  listaTablas.getSelectedItem();
        separator = (String) specialChart.getText();
        condition = (String) whereCondition.getText();

        if (tableSelected != null && dataSource != null && !"---".equals((String)tableSelected) && (!"".equals(separator) || separator != null)) {   
            background = new Thread(() -> {
                int[] selectColumns;
                setPanelEnabled(textToAccessPane, false);
                selectColumns =  new int[columnsList.getSelectedIndices().length];
                selectColumns = columnsList.getSelectedIndices();
                cf.setSelectedColumns(selectColumns);
                cf.RunProcess(textlog, separator, selectecOption, condition);
                setPanelEnabled(textToAccessPane, true);
            });
            background.start();
        }else{
            appendToLog(10, null);
        }
    }
    
    private void runAccessToAccess(){
        Object tableSelected;
        String separator;
        String condition;
        Thread background;

        int[] selectColumns;
        tableSelected =  destinyTable.getSelectedItem();           
        separator = (String) specialChart.getText();
        condition = (String) whereConditionOrigin.getText();
        selectColumns =  new int[columnsList.getSelectedIndices().length];
        selectColumns = columnsList.getSelectedIndices();

        if (tableSelected != null && 
            dataBaseOrigin != null && 
            !"---".equals((String)tableSelected) && 
            (!"".equals(separator) || separator != null)) {
            cf.setSelectedColumns(selectColumns);

            background = new Thread(() -> {
                setPanelEnabled(textToAccessPane, false);
                cf.RunProcess(textlog, separator, selectecOption, condition);
                setPanelEnabled(textToAccessPane, true);
            });
            background.start();
        }else{
            appendToLog(10, "hello");
        }
    }
    
    
    private void appendToLog(int id, String aditionalTxt) { 
       String temp;
       if (aditionalTxt == null){
           temp = df.format(new Date()) + " - " + cf.getMessageValue(id) + "\n";
       }else{
           temp = df.format(new Date()) + " - " + cf.getMessageValue(id) + aditionalTxt + "\n";
       }
       textlog.append(temp);
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        eventGroup = new javax.swing.ButtonGroup();
        filepiker = new javax.swing.JFileChooser();
        jRadioButtonMenuItem1 = new javax.swing.JRadioButtonMenuItem();
        configList = new javax.swing.JFrame();
        jScrollPane4 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jButton1 = new javax.swing.JButton();
        cancelconfig = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        runButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        textlog = new javax.swing.JTextArea();
        clearLogButton = new javax.swing.JButton();
        tabpanel = new javax.swing.JTabbedPane();
        textToAccessPane = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        columnsList = new javax.swing.JList<>();
        listaTablas = new javax.swing.JComboBox<>();
        columsID = new javax.swing.JComboBox<>();
        specialChart = new javax.swing.JTextField();
        tableLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        insertandup = new javax.swing.JRadioButton();
        deleteandin = new javax.swing.JRadioButton();
        deleandInsertAll = new javax.swing.JRadioButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        whereCondition = new javax.swing.JTextArea();
        onlyInsert = new javax.swing.JRadioButton();
        settxt = new javax.swing.JButton();
        dataSource = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        accessToAccess = new javax.swing.JPanel();
        originTable = new javax.swing.JComboBox<>();
        destinyTable = new javax.swing.JComboBox<>();
        tableLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        insertandup1 = new javax.swing.JRadioButton();
        deleteandin1 = new javax.swing.JRadioButton();
        deleandInsertAll1 = new javax.swing.JRadioButton();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        whereConditionOrigin = new javax.swing.JTextArea();
        onlyInsert1 = new javax.swing.JRadioButton();
        setDBRoot = new javax.swing.JButton();
        dataBaseOrigin = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        columnsListOrigin = new javax.swing.JList<>();
        jScrollPane8 = new javax.swing.JScrollPane();
        columnsListDesteny = new javax.swing.JList<>();
        accessToAccess2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        sqlcommand = new javax.swing.JTextArea();
        DefaultSql = new javax.swing.JComboBox<>();
        dataBaseroot = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        setDB = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        configOptions = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();

        eventGroup.add(deleteandin);
        eventGroup.add(insertandup);
        eventGroup.add(deleandInsertAll);
        eventGroup.add(onlyInsert);

        jRadioButtonMenuItem1.setSelected(true);
        jRadioButtonMenuItem1.setText("jRadioButtonMenuItem1");

        configList.setAlwaysOnTop(true);
        configList.setName("Configurations"); // NOI18N
        configList.setResizable(false);
        configList.setSize(new java.awt.Dimension(324, 290));
        configList.setType(java.awt.Window.Type.POPUP);

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane4.setViewportView(jList1);

        jButton1.setText("Load");

        cancelconfig.setText("Cancel");
        cancelconfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelconfigMouseClicked(evt);
            }
        });

        jLabel8.setText("Select configuration:");

        javax.swing.GroupLayout configListLayout = new javax.swing.GroupLayout(configList.getContentPane());
        configList.getContentPane().setLayout(configListLayout);
        configListLayout.setHorizontalGroup(
            configListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(configListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(configListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configListLayout.createSequentialGroup()
                        .addComponent(cancelconfig)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configListLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        configListLayout.setVerticalGroup(
            configListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, configListLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(configListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelconfig)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Data");
        setBackground(new java.awt.Color(255, 255, 255));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        runButton.setText("Run");
        runButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                runButtonMouseClicked(evt);
            }
        });

        cancelButton.setText("Cancel");
        cancelButton.setEnabled(false);
        cancelButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelButtonMouseClicked(evt);
            }
        });

        textlog.setColumns(20);
        textlog.setRows(5);
        jScrollPane2.setViewportView(textlog);
        DefaultCaret caret;
        caret = (DefaultCaret)textlog.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        clearLogButton.setText("Clear Log");
        clearLogButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clearLogButtonMouseClicked(evt);
            }
        });

        tabpanel.setBackground(new java.awt.Color(255, 255, 255));
        tabpanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        textToAccessPane.setBackground(new java.awt.Color(255, 255, 255));

        jScrollPane1.setViewportView(columnsList);

        listaTablas.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                listaTablasItemStateChanged(evt);
            }
        });

        columsID.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                columsIDItemStateChanged(evt);
            }
        });

        tableLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tableLabel.setText("Select Table Name:");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel2.setText("Primary Key:");
        jLabel2.setToolTipText("");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel3.setText("Select Columns:");

        jLabel5.setText("Character Separator:");

        insertandup.setBackground(new java.awt.Color(255, 255, 255));
        insertandup.setText("Insert and Update individual records");
        insertandup.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                insertandupItemStateChanged(evt);
            }
        });

        deleteandin.setBackground(new java.awt.Color(255, 255, 255));
        deleteandin.setText("Delete and insert individual records");
        deleteandin.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                deleteandinItemStateChanged(evt);
            }
        });

        deleandInsertAll.setBackground(new java.awt.Color(255, 255, 255));
        deleandInsertAll.setSelected(true);
        deleandInsertAll.setText("Delete and insert all records");
        deleandInsertAll.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                deleandInsertAllItemStateChanged(evt);
            }
        });

        jLabel1.setText("Optional where condition for delete:");

        whereCondition.setColumns(1);
        whereCondition.setLineWrap(true);
        whereCondition.setRows(5);
        whereCondition.setTabSize(1);
        whereCondition.setWrapStyleWord(true);
        jScrollPane3.setViewportView(whereCondition);

        onlyInsert.setBackground(new java.awt.Color(255, 255, 255));
        onlyInsert.setText("Only insert");
        onlyInsert.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                onlyInsertItemStateChanged(evt);
            }
        });

        settxt.setText("Set Data Source");
        settxt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                settxtMouseClicked(evt);
            }
        });

        dataSource.setEditable(false);
        dataSource.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("Data Text file:");

        javax.swing.GroupLayout textToAccessPaneLayout = new javax.swing.GroupLayout(textToAccessPane);
        textToAccessPane.setLayout(textToAccessPaneLayout);
        textToAccessPaneLayout.setHorizontalGroup(
            textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textToAccessPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textToAccessPaneLayout.createSequentialGroup()
                        .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(columsID, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(listaTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(textToAccessPaneLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(specialChart, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(insertandup, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(onlyInsert, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteandin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleandInsertAll, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(textToAccessPaneLayout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(textToAccessPaneLayout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 20, Short.MAX_VALUE))))
                    .addGroup(textToAccessPaneLayout.createSequentialGroup()
                        .addComponent(dataSource, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(settxt, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        textToAccessPaneLayout.setVerticalGroup(
            textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, textToAccessPaneLayout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataSource, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(settxt)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(textToAccessPaneLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(textToAccessPaneLayout.createSequentialGroup()
                        .addComponent(deleandInsertAll)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteandin, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(insertandup)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(onlyInsert)
                        .addGap(3, 3, 3)
                        .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(specialChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(textToAccessPaneLayout.createSequentialGroup()
                        .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(listaTablas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(columsID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(8, 8, 8)
                        .addGroup(textToAccessPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE))))
                .addContainerGap())
        );

        tabpanel.addTab("TXT - ACCESS", textToAccessPane);

        accessToAccess.setBackground(new java.awt.Color(255, 255, 255));

        originTable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                originTableItemStateChanged(evt);
            }
        });

        destinyTable.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                destinyTableItemStateChanged(evt);
            }
        });

        tableLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        tableLabel1.setText("Select * from:");

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel4.setText("Insert into:");
        jLabel4.setToolTipText("");

        insertandup1.setText("Insert and Update individual records");
        insertandup1.setMaximumSize(new java.awt.Dimension(199, 20));
        insertandup1.setMinimumSize(new java.awt.Dimension(199, 20));
        insertandup1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                insertandup1ItemStateChanged(evt);
            }
        });

        deleteandin1.setText("Delete and insert individual records");
        deleteandin1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                deleteandin1ItemStateChanged(evt);
            }
        });

        deleandInsertAll1.setSelected(true);
        deleandInsertAll1.setText("Delete and insert all records");
        deleandInsertAll1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                deleandInsertAll1ItemStateChanged(evt);
            }
        });

        jLabel10.setText("Optional where condition for delete:");

        whereConditionOrigin.setColumns(1);
        whereConditionOrigin.setLineWrap(true);
        whereConditionOrigin.setRows(5);
        whereConditionOrigin.setTabSize(1);
        whereConditionOrigin.setWrapStyleWord(true);
        jScrollPane5.setViewportView(whereConditionOrigin);

        onlyInsert1.setText("Only insert");
        onlyInsert1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                onlyInsert1ItemStateChanged(evt);
            }
        });

        setDBRoot.setText("Set Data Base");
        setDBRoot.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setDBRootMouseClicked(evt);
            }
        });

        dataBaseOrigin.setEditable(false);
        dataBaseOrigin.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("Database origin:");

        jScrollPane6.setViewportView(columnsListOrigin);

        jScrollPane8.setViewportView(columnsListDesteny);

        javax.swing.GroupLayout accessToAccessLayout = new javax.swing.GroupLayout(accessToAccess);
        accessToAccess.setLayout(accessToAccessLayout);
        accessToAccessLayout.setHorizontalGroup(
            accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accessToAccessLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dataBaseOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(setDBRoot, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                .addGap(10, 10, 10))
            .addGroup(accessToAccessLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accessToAccessLayout.createSequentialGroup()
                        .addComponent(tableLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(originTable, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(14, 14, 14)
                .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(accessToAccessLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinyTable, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addGap(36, 36, 36))
            .addGroup(accessToAccessLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(deleandInsertAll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(insertandup1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteandin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(onlyInsert1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        accessToAccessLayout.setVerticalGroup(
            accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accessToAccessLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(dataBaseOrigin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setDBRoot))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(originTable)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(destinyTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tableLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(accessToAccessLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(deleandInsertAll1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(insertandup1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(deleteandin1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(onlyInsert1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        tabpanel.addTab("SELECT - ACCESS", accessToAccess);

        accessToAccess2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel14.setText("SQL Statement:");

        sqlcommand.setColumns(1);
        sqlcommand.setLineWrap(true);
        sqlcommand.setRows(5);
        sqlcommand.setTabSize(1);
        sqlcommand.setWrapStyleWord(true);
        jScrollPane7.setViewportView(sqlcommand);

        DefaultSql.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "UPDATE tbl_colaborador SET col_estado = false WHERE col_id not in (2122,4784,3522,2110,360,286);", "UPDATE tbl_infocolab SET colab_tipo = 0;" }));
        DefaultSql.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                DefaultSqlItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout accessToAccess2Layout = new javax.swing.GroupLayout(accessToAccess2);
        accessToAccess2.setLayout(accessToAccess2Layout);
        accessToAccess2Layout.setHorizontalGroup(
            accessToAccess2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accessToAccess2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accessToAccess2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accessToAccess2Layout.createSequentialGroup()
                        .addComponent(jScrollPane7)
                        .addContainerGap())
                    .addGroup(accessToAccess2Layout.createSequentialGroup()
                        .addGroup(accessToAccess2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(DefaultSql, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 525, Short.MAX_VALUE))))
        );
        accessToAccess2Layout.setVerticalGroup(
            accessToAccess2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accessToAccess2Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(DefaultSql, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabpanel.addTab("SQL", accessToAccess2);

        dataBaseroot.setEditable(false);
        dataBaseroot.setBackground(new java.awt.Color(255, 255, 255));

        jLabel7.setText("Access Data Base Root:");

        setDB.setText("Set Data Base");
        setDB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                setDBMouseClicked(evt);
            }
        });

        jMenu1.setText("Tools");

        configOptions.setText("Configurations");
        configOptions.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                configOptionsMouseClicked(evt);
            }
        });
        configOptions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configOptionsActionPerformed(evt);
            }
        });
        jMenu1.add(configOptions);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tabpanel)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(dataBaseroot, javax.swing.GroupLayout.PREFERRED_SIZE, 663, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(setDB, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clearLogButton, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(runButton)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dataBaseroot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(setDB))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabpanel, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clearLogButton)
                    .addComponent(cancelButton)
                    .addComponent(runButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
       cf.closeConnection();
    }//GEN-LAST:event_formWindowClosing

    
    private void runButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_runButtonMouseClicked
        
        switch(tabpanel.getSelectedIndex()){
            case 0:
                this.runTextToAccess();
                break;
            case 1:
                this.runAccessToAccess();
                break;
            case 2:
                cf.runSqlCommand(textlog, sqlcommand.getText());                
                break;
        }
    }//GEN-LAST:event_runButtonMouseClicked

    private void clearLogButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clearLogButtonMouseClicked
        textlog.setText("");
    }//GEN-LAST:event_clearLogButtonMouseClicked

    private void cancelButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelButtonMouseClicked
        cf.cancelProcess = true;
    }//GEN-LAST:event_cancelButtonMouseClicked

    private void settxtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_settxtMouseClicked
        if (processrunning == false){
            filepiker.setAcceptAllFileFilterUsed(false);
            filepiker.setFileFilter(new FileNameExtensionFilter("Text File", "txt"));
            int i=filepiker.showOpenDialog(this);
            if(i==JFileChooser.APPROVE_OPTION){
                File f=filepiker.getSelectedFile();
                String filepath=f.getPath();
                cf.setTxtPath(filepath);
                this.dataSource.setText(filepath);
                appendToLog(3,null);
            }
        }
    }//GEN-LAST:event_settxtMouseClicked

    private void setDBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setDBMouseClicked
        if (processrunning == false){
            filepiker.setAcceptAllFileFilterUsed(false);
            filepiker.setFileFilter(new FileNameExtensionFilter("MS Office Access DSN", "accdb"));
            int i=filepiker.showOpenDialog(this);
            if(i==JFileChooser.APPROVE_OPTION){
                File f=filepiker.getSelectedFile();
                String filepath= f.getPath();
                dataBaseroot.setText(filepath);
                appendToLog(1,null);
                cf.setPath(filepath);
                if(true == cf.getConncetion()) {
                    cf.closeConnection();
                    appendToLog(5,null);
                    cf.openConection();
                    appendToLog(0,null);
                }else{
                    cf.openConection();
                    appendToLog(0,null);
                }
                String[] tableLisArray = cf.getTablenames();
                listaTablas.setModel(new javax.swing.DefaultComboBoxModel<>(tableLisArray));
                destinyTable.setModel(new javax.swing.DefaultComboBoxModel<>(tableLisArray));
            }
        }
    }//GEN-LAST:event_setDBMouseClicked

    private void onlyInsertItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_onlyInsertItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            selectecOption = 4;
        }
    }//GEN-LAST:event_onlyInsertItemStateChanged

    private void deleandInsertAllItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_deleandInsertAllItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            whereCondition.setEnabled(true);
            selectecOption = 1;
        }else{
            whereCondition.setEnabled(false);
        }
    }//GEN-LAST:event_deleandInsertAllItemStateChanged

    private void deleteandinItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_deleteandinItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            selectecOption = 2;
        }
    }//GEN-LAST:event_deleteandinItemStateChanged

    private void insertandupItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_insertandupItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            selectecOption = 3;
        }
    }//GEN-LAST:event_insertandupItemStateChanged

    private void columsIDItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_columsIDItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String tablepk = (String) columsID.getSelectedItem();
            if (!"---".equals(tablepk)) {
                cf.setTablePk(tablepk);
            }else{
                cf.setTablePk(null);
            }
        }
    }//GEN-LAST:event_columsIDItemStateChanged

    private void listaTablasItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_listaTablasItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String tableSelected = (String) listaTablas.getSelectedItem();
            if (!"---".equals(tableSelected)) {
                int i;
                columsID.removeAllItems();
                cf.setTableName(tableSelected);
                String[] tableLisArray = cf.getColumnsNames(tableSelected);
                columsID.addItem("---");
                for (i=0; i<tableLisArray.length; i++){
                    columsID.addItem(tableLisArray[i]);
                }
                columnsList.setListData(tableLisArray);
                appendToLog(4,": "+tableSelected);
            }else{
                String[] emptyarr= {};
                cf.setTableName(null);
                columsID.removeAllItems();
                columnsList.setListData(emptyarr);
            }
        }
    }//GEN-LAST:event_listaTablasItemStateChanged

    private void configOptionsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_configOptionsMouseClicked
        
    }//GEN-LAST:event_configOptionsMouseClicked

    private void configOptionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configOptionsActionPerformed
        configList.pack();
        configList.setLocationRelativeTo(null);
        configList.setVisible(true);
    }//GEN-LAST:event_configOptionsActionPerformed

    private void cancelconfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelconfigMouseClicked
        configList.setVisible(false);
    }//GEN-LAST:event_cancelconfigMouseClicked

    private void setDBRootMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_setDBRootMouseClicked
        if (processrunning == false){
            filepiker.setAcceptAllFileFilterUsed(false);
            filepiker.setFileFilter(new FileNameExtensionFilter("MS Office Access DSN", "accdb"));
            int i=filepiker.showOpenDialog(this);
            if(i==JFileChooser.APPROVE_OPTION){
                dbTodb = new CoreFunctions();
                File f=filepiker.getSelectedFile();
                String filepath= f.getPath();
                dataBaseOrigin.setText(filepath);
                appendToLog(1,null);
                dbTodb.setPath(filepath);
                if(true == dbTodb.getConncetion()) {
                    dbTodb.closeConnection();
                    appendToLog(5,null);
                    dbTodb.openConection();
                    appendToLog(0,null);
                }else{
                    dbTodb.openConection();
                    appendToLog(0,null);
                }
                String[] tableLisArray = dbTodb.getTablenames();
                originTable.setModel(new javax.swing.DefaultComboBoxModel<>(tableLisArray));
                
            }
        }
    }//GEN-LAST:event_setDBRootMouseClicked

    private void onlyInsert1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_onlyInsert1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_onlyInsert1ItemStateChanged

    private void deleandInsertAll1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_deleandInsertAll1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_deleandInsertAll1ItemStateChanged

    private void deleteandin1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_deleteandin1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_deleteandin1ItemStateChanged

    private void insertandup1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_insertandup1ItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_insertandup1ItemStateChanged

    private void destinyTableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_destinyTableItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String tableSelected = (String) destinyTable.getSelectedItem();
            if (!"---".equals(tableSelected)) {
                int i;
                cf.setTableName(tableSelected);
                String[] tableLisArray = dbTodb.getColumnsNames(tableSelected);
                columnsListDesteny.setListData(tableLisArray);
                appendToLog(4,": "+tableSelected);
            }else{
                String[] emptyarr= {};
                cf.setTableName(null);
                columnsListDesteny.setListData(emptyarr);
            }
        }
    }//GEN-LAST:event_destinyTableItemStateChanged

    private void originTableItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_originTableItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String tableSelected = (String) originTable.getSelectedItem();
            if (!"---".equals(tableSelected)) {
                int i;
                dbTodb.setTableName(tableSelected);
                String[] tableLisArray = dbTodb.getColumnsNames(tableSelected);
                columnsListOrigin.setListData(tableLisArray);
                appendToLog(4,": "+tableSelected);
            }else{
                String[] emptyarr= {};
                dbTodb.setTableName(null);
                columnsListOrigin.setListData(emptyarr);
            }
        }
    }//GEN-LAST:event_originTableItemStateChanged

    private void DefaultSqlItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_DefaultSqlItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            sqlcommand.setText((String) DefaultSql.getSelectedItem());
        }        
    }//GEN-LAST:event_DefaultSqlItemStateChanged

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
                this.getContentPane().setBackground( Color.white );
    }//GEN-LAST:event_formWindowActivated
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> DefaultSql;
    private javax.swing.JPanel accessToAccess;
    private javax.swing.JPanel accessToAccess2;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton cancelconfig;
    private javax.swing.JButton clearLogButton;
    private javax.swing.JList<String> columnsList;
    private javax.swing.JList<String> columnsListDesteny;
    private javax.swing.JList<String> columnsListOrigin;
    private javax.swing.JComboBox<String> columsID;
    private javax.swing.JFrame configList;
    private javax.swing.JMenuItem configOptions;
    private javax.swing.JTextField dataBaseOrigin;
    private javax.swing.JTextField dataBaseroot;
    private javax.swing.JTextField dataSource;
    private javax.swing.JRadioButton deleandInsertAll;
    private javax.swing.JRadioButton deleandInsertAll1;
    private javax.swing.JRadioButton deleteandin;
    private javax.swing.JRadioButton deleteandin1;
    private javax.swing.JComboBox<String> destinyTable;
    private javax.swing.ButtonGroup eventGroup;
    private javax.swing.JFileChooser filepiker;
    private javax.swing.JRadioButton insertandup;
    private javax.swing.JRadioButton insertandup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JList<String> jList1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButtonMenuItem jRadioButtonMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JComboBox<String> listaTablas;
    private javax.swing.JRadioButton onlyInsert;
    private javax.swing.JRadioButton onlyInsert1;
    private javax.swing.JComboBox<String> originTable;
    private javax.swing.JButton runButton;
    private javax.swing.JButton setDB;
    private javax.swing.JButton setDBRoot;
    private javax.swing.JButton settxt;
    private javax.swing.JTextField specialChart;
    private javax.swing.JTextArea sqlcommand;
    private javax.swing.JLabel tableLabel;
    private javax.swing.JLabel tableLabel1;
    private javax.swing.JTabbedPane tabpanel;
    private javax.swing.JPanel textToAccessPane;
    private javax.swing.JTextArea textlog;
    private javax.swing.JTextArea whereCondition;
    private javax.swing.JTextArea whereConditionOrigin;
    // End of variables declaration//GEN-END:variables
}
