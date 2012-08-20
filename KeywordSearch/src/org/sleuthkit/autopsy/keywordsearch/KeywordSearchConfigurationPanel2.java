/*
 * Autopsy Forensic Browser
 *
 * Copyright 2012 Basis Technology Corp.
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
package org.sleuthkit.autopsy.keywordsearch;

import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.solr.client.solrj.SolrServerException;
import org.openide.util.Exceptions;
import org.sleuthkit.autopsy.ingest.IngestManager;

/**
 *
 * General, not per list, keyword search configuration and status display widget
 */
public class KeywordSearchConfigurationPanel2 extends javax.swing.JPanel {

    private static KeywordSearchConfigurationPanel2 instance = null;
    private final Logger logger = Logger.getLogger(KeywordSearchConfigurationPanel2.class.getName());

    /**
     * Creates new form KeywordSearchConfigurationPanel2
     */
    public KeywordSearchConfigurationPanel2() {
        initComponents();
        customizeComponents();
    }

    public static KeywordSearchConfigurationPanel2 getDefault() {
        if (instance == null) {
            instance = new KeywordSearchConfigurationPanel2();
        }
        return instance;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        activateWidgets();
    }

    private void activateWidgets() {
        final KeywordSearchIngestService service = KeywordSearchIngestService.getDefault();
        skipNSRLCheckBox.setSelected(service.getSkipKnown());
        boolean enable = !IngestManager.getDefault().isIngestRunning();
        skipNSRLCheckBox.setEnabled(enable);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        langButtonGroup = new javax.swing.ButtonGroup();
        skipNSRLCheckBox = new javax.swing.JCheckBox();
        filesIndexedLabel = new javax.swing.JLabel();
        filesIndexedValue = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        chunksLabel = new javax.swing.JLabel();
        chunksValLabel = new javax.swing.JLabel();

        skipNSRLCheckBox.setText(org.openide.util.NbBundle.getMessage(KeywordSearchConfigurationPanel2.class, "KeywordSearchConfigurationPanel2.skipNSRLCheckBox.text")); // NOI18N
        skipNSRLCheckBox.setToolTipText(org.openide.util.NbBundle.getMessage(KeywordSearchConfigurationPanel2.class, "KeywordSearchConfigurationPanel2.skipNSRLCheckBox.toolTipText")); // NOI18N
        skipNSRLCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                skipNSRLCheckBoxActionPerformed(evt);
            }
        });

        filesIndexedLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchConfigurationPanel2.class, "KeywordSearchConfigurationPanel2.filesIndexedLabel.text")); // NOI18N

        filesIndexedValue.setText(org.openide.util.NbBundle.getMessage(KeywordSearchConfigurationPanel2.class, "KeywordSearchConfigurationPanel2.filesIndexedValue.text")); // NOI18N
        filesIndexedValue.setMaximumSize(null);

        chunksLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchConfigurationPanel2.class, "KeywordSearchConfigurationPanel2.chunksLabel.text")); // NOI18N

        chunksValLabel.setText(org.openide.util.NbBundle.getMessage(KeywordSearchConfigurationPanel2.class, "KeywordSearchConfigurationPanel2.chunksValLabel.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(skipNSRLCheckBox)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(chunksLabel)
                            .addComponent(filesIndexedLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(filesIndexedValue, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)
                            .addComponent(chunksValLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(203, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(skipNSRLCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(filesIndexedLabel)
                    .addComponent(filesIndexedValue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chunksLabel)
                    .addComponent(chunksValLabel))
                .addContainerGap(31, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

private void skipNSRLCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_skipNSRLCheckBoxActionPerformed
    KeywordSearchIngestService.getDefault().setSkipKnown(skipNSRLCheckBox.isSelected());
}//GEN-LAST:event_skipNSRLCheckBoxActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel chunksLabel;
    private javax.swing.JLabel chunksValLabel;
    private javax.swing.JLabel filesIndexedLabel;
    private javax.swing.JLabel filesIndexedValue;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.ButtonGroup langButtonGroup;
    private javax.swing.JCheckBox skipNSRLCheckBox;
    // End of variables declaration//GEN-END:variables

    private void customizeComponents() {
        this.skipNSRLCheckBox.setSelected(KeywordSearchIngestService.getDefault().getSkipKnown());

        try {
            filesIndexedValue.setText(Integer.toString(KeywordSearch.getServer().queryNumIndexedFiles()));
            chunksValLabel.setText(Integer.toString(KeywordSearch.getServer().queryNumIndexedChunks()));
        } catch (SolrServerException ex) {
            logger.log(Level.WARNING, "Could not get number of indexed files/chunks");

        } catch (NoOpenCoreException ex) {
            logger.log(Level.WARNING, "Could not get number of indexed files/chunks");
        }

        KeywordSearch.changeSupport.addPropertyChangeListener(KeywordSearch.NUM_FILES_CHANGE_EVT,
                new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        String changed = evt.getPropertyName();
                        Object newValue = evt.getNewValue();

                        if (changed.equals(KeywordSearch.NUM_FILES_CHANGE_EVT)) {
                            int newFilesIndexed = ((Integer) newValue).intValue();
                            filesIndexedValue.setText(Integer.toString(newFilesIndexed));
                            try {
                                chunksValLabel.setText(Integer.toString(KeywordSearch.getServer().queryNumIndexedChunks()));
                            } catch (SolrServerException ex) {
                                logger.log(Level.WARNING, "Could not get number of indexed chunks");

                            } catch (NoOpenCoreException ex) {
                                logger.log(Level.WARNING, "Could not get number of indexed chunks");
                            }

                        }
                    }
                });


    }
}
