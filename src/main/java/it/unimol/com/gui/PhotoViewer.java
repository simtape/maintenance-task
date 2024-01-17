package it.unimol.com.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class PhotoViewer extends JFrame {

    private DefaultListModel<ImageEntry> imageListModel;
    private JList<ImageEntry> imageList;

    public PhotoViewer() {
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Photo Viewer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel mainPanel = new JPanel(new BorderLayout());

        imageListModel = new DefaultListModel<>();
        imageList = new JList<>(imageListModel);
        JScrollPane scrollPane = new JScrollPane(imageList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        JButton browseButton = new JButton("Browse Zip Folder");
        browseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showFilePickerDialog();
            }
        });
        mainPanel.add(browseButton, BorderLayout.SOUTH);

        add(mainPanel);

        setLocationRelativeTo(null);
    }

    private void showFilePickerDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File file) {
                return file.isDirectory() || file.getName().toLowerCase().endsWith(".zip");
            }

            public String getDescription() {
                return "ZIP Files (*.zip)";
            }
        });

        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            displayPhotosFromZip(selectedFile);
        }
    }

    private void displayPhotosFromZip(File zipFile) {
        imageListModel.clear();

        try (ZipFile zip = new ZipFile(zipFile)) {
            Enumeration<? extends ZipEntry> entries = zip.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                if (!entry.isDirectory() && isImageFile(entry.getName())) {
                    ImageEntry imageEntry = new ImageEntry(entry.getName(), zip.getInputStream(entry));
                    imageListModel.addElement(imageEntry);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading zip file: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean isImageFile(String fileName) {
        return fileName.toLowerCase().endsWith(".jpg") ||
                fileName.toLowerCase().endsWith(".jpeg") ||
                fileName.toLowerCase().endsWith(".png") ||
                fileName.toLowerCase().endsWith(".gif");
    }

    private class ImageEntry {
        private String name;
        private InputStream inputStream;

        public ImageEntry(String name, InputStream inputStream) {
            this.name = name;
            this.inputStream = inputStream;
        }

        public String getName() {
            return name;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}