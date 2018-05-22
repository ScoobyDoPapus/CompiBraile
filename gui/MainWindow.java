package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import lexer.Lexer;
import lexer.Token;

public class MainWindow extends JFrame {
    
    private final int _width = 800;
    private final int _height = 600;
    private JPanel _mainPanel;
    private JButton _fileUploadButton;
    private JButton _fileTraductorButton;
    private JLabel _filePathText;
    
    private JTextArea _fileText;
    private JScrollPane _fileTextScrollPane;
    
    private JTextArea _analyzerText;
    private JScrollPane _analyzerTextScrollPane;
    
    private JLabel _ipInformationText;
    private JLabel _portInformationText;
    
    private Lexer _lexer;
    
    public MainWindow(){
        this._lexer = new Lexer();
        this.initComponents();
    }
    
    /**
     * 
     */
    private void initComponents(){
        this.setTitle("Tranductor a Braille");
        this.setSize(this._width, this._height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        
        //Main Panel
        this._mainPanel = new JPanel();
        this._mainPanel.setLayout(null);
        this._mainPanel.setBounds(0, 0, this._width, this._height);
        this._mainPanel.setOpaque(true);
        this._mainPanel.setBackground(Color.lightGray);
        
        //Button to upload a file
        this._fileUploadButton = new JButton("Cargar Archivo");
        this._fileUploadButton.setBounds(60, 82, 110, 40);
        this._mainPanel.add(this._fileUploadButton);
        
        // Button to traduct the file
        this._fileTraductorButton = new JButton("Traducir");
        this._fileTraductorButton.setBounds(60, 122, 110, 40);
        this._mainPanel.add(this._fileTraductorButton);
        
        // File Path
        this._filePathText = new JLabel();
        this._filePathText.setBounds(200, 105, 470, 35);
        this._filePathText.setOpaque(true);
        this._filePathText.setBackground(Color.WHITE);
        this._mainPanel.add(this._filePathText);
        
        //File text
        this._fileText = new JTextArea();
        this._fileText.setEditable(false);
        //this._mainPanel.add(this._fileText); tqm <3
        this._fileTextScrollPane = new JScrollPane(this._fileText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this._fileTextScrollPane.setBounds(30, 180, 355, 330);
        this._fileTextScrollPane.setOpaque(true);
        this._fileTextScrollPane.setBackground(Color.WHITE);
        this._mainPanel.add(this._fileTextScrollPane);
        
        //Analyzer text
        this._analyzerText = new JTextArea();
        this._analyzerText.setEditable(false);
        this._analyzerTextScrollPane = new JScrollPane(this._analyzerText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this._analyzerTextScrollPane.setBounds(415, 180, 355, 330);
        this._analyzerTextScrollPane.setOpaque(true);
        this._analyzerTextScrollPane.setBackground(Color.WHITE);
        this._mainPanel.add(this._analyzerTextScrollPane);
        
        // Labels information
        this._ipInformationText = new JLabel();
        this._ipInformationText.setText("IP: ");
        this._ipInformationText.setBounds(30, 750, 20, 20);
        this._mainPanel.add(this._ipInformationText);
        this._portInformationText = new JLabel();
        this._portInformationText.setText("Port: ");
        this._portInformationText.setBounds(30, 780, 20, 20);
        this._mainPanel.add(this._portInformationText);
        
        //Button action
        this._fileUploadButton.addActionListener((ActionEvent e) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            chooser.setAcceptAllFileFilterUsed(false);  
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.txt)", "txt"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents (*.pdf)", "pdf"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word Documents (*.docx)", "docx"));
            int option = chooser.showOpenDialog(null);
            if(option == JFileChooser.APPROVE_OPTION){
                File f = chooser.getSelectedFile();
                String filename = f.getAbsolutePath();
                _filePathText.setText(filename);
                
                try {
                    this._lexer.readText(filename);
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                this._fileText.setText(this._lexer._docText);
            }
        });
        
        //Button action
        this._fileTraductorButton.addActionListener((ActionEvent e) -> {
            
            Token tk = this._lexer.getToken();
            String tokenString = "(" + tk.getType() + ": " + tk.getValue() + ")\n";
            this._analyzerText.setText(this._analyzerText.getText() + tokenString);    
            
        });
 
        // Add the main panel to the main frame
        this.add(this._mainPanel);
        this.setVisible(true);
    }
   
}
