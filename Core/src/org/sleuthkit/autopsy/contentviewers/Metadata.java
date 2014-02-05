/*
 * Autopsy Forensic Browser
 *
 * Copyright 2013-2014 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.sleuthkit.autopsy.contentviewers;

import java.awt.Component;
import org.openide.nodes.Node;
import org.openide.util.lookup.ServiceProvider;
import org.sleuthkit.autopsy.corecomponentinterfaces.DataContentViewer;
import org.sleuthkit.datamodel.AbstractFile;
import org.sleuthkit.datamodel.TskCoreException;
import org.sleuthkit.datamodel.TskData.TSK_DB_FILES_TYPE_ENUM;

/**
 * Shows file metadata as a list to make it easy to copy and paste.
 * Typically shows the same data that can also be found in the ResultViewer table,
 * just a different order and allows the full path to be visible in the bottom area.
 */
@ServiceProvider(service = DataContentViewer.class, position = 3)
public class Metadata extends javax.swing.JPanel implements DataContentViewer 
{
    /**
     * Creates new form Metadata
     */
    public Metadata() {
        initComponents();
        customizeComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jTextPane1.setEditable(false);
        jScrollPane2.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    private void customizeComponents(){
        /*
        jTextPane1.setComponentPopupMenu(rightClickMenu);
        ActionListener actList = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                JMenuItem jmi = (JMenuItem) e.getSource();
                if(jmi.equals(copyMenuItem))
                    outputViewPane.copy();
                else if(jmi.equals(selectAllMenuItem))
                    outputViewPane.selectAll();
            }
        };
        copyMenuItem.addActionListener(actList);
        selectAllMenuItem.addActionListener(actList);
        */
            
        Utilities.configureTextPaneAsHtml(jTextPane1);
    }
    
    private void setText(String str) {
        jTextPane1.setText("<html><body>" + str + "</body></html>");
    }
    
    private void startTable(StringBuilder sb) {
        sb.append("<table>");
    }
    
    private void endTable(StringBuilder sb) {
        sb.append("</table>");
    }
    
    private void addRow(StringBuilder sb, String key, String value) {
        sb.append("<tr><td>");
        sb.append(key);
        sb.append("</td><td>");
        sb.append(value);
        sb.append("</td></tr>");
    }
    
    @Override
    public void setNode(Node node) {
        AbstractFile file = node.getLookup().lookup(AbstractFile.class);
        if (file == null) {
            setText("Non-file passed in");
            return;
        }
        
        StringBuilder sb = new StringBuilder();
        startTable(sb);
        
        try {
            addRow(sb, "Name", file.getUniquePath());
        } catch (TskCoreException ex) {
            addRow(sb, "Name", file.getParentPath() + "/" + file.getName());
        }
        
        addRow(sb, "Size", new Long(file.getSize()).toString() );
        addRow(sb, "File Name Allocation", file.getDirFlagAsString());
        addRow(sb, "Metadata Allocation", file.getMetaFlagsAsString());
        
        addRow(sb, "Modified", file.getMtimeAsDate());
        addRow(sb, "Accessed", file.getAtimeAsDate());
        addRow(sb, "Created",  file.getCrtimeAsDate());
        addRow(sb, "Changed",  file.getCtimeAsDate());
       
        String md5 = file.getMd5Hash();
        if (md5 == null) {
            md5 = "Not calculated";
        }
        addRow(sb, "MD5", md5);
        addRow(sb, "Hash Lookup Results", file.getKnown().toString());
        
        addRow(sb, "Internal ID", new Long(file.getId()).toString());
        if (file.getType().compareTo(TSK_DB_FILES_TYPE_ENUM.LOCAL) == 0) {
            addRow(sb, "Local Path", file.getLocalAbsPath());
        }
        
        endTable(sb);
        setText(sb.toString());
    }

    @Override
    public String getTitle() {
        return "Metadata";
    }

    @Override
    public String getToolTip() {
        return "Displays metadata about the file.";
    }

    @Override
    public DataContentViewer createInstance() {
        return new Metadata();
    }

    @Override
    public Component getComponent() {
        return this;
    }

    @Override
    public void resetComponent() {
        return;
    }

    @Override
    public boolean isSupported(Node node) {
        AbstractFile file = node.getLookup().lookup(AbstractFile.class);
        if (file == null) {
            return false;
        }
        return true;
    }

    @Override
    public int isPreferred(Node node) {
        return 1;
    }
}
