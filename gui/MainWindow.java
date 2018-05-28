package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;
import lexer.Lexer;
import lexer.Token;
import org.json.simple.JSONObject;
import server.MultiServer;

public class MainWindow extends JFrame {
    
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------
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
    private ServerSocket serverSocket = null;
    private boolean listening = true;

    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------
    public MainWindow() throws IOException{
        this._lexer = new Lexer();
        this.initComponents();
        this.initButtonsActions();
        this.initServer();
        //serverSocket.close();
    }
    
    /**
     * 
     * @throws IOException 
     */
    private void initServer() throws IOException{
  
        try {
            serverSocket = new ServerSocket(8084);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8084");
            System.exit(-1);
        }
        
        while (listening){
            new MultiServer(serverSocket.accept(), this._lexer).start();
            System.out.println("Listening on port: 8084...");
        }
        
    }
    
    /**
     * Start the program components and place them in the main window.
     */
    private void initComponents(){
        this.setTitle("Traductor a Braille");
        this.setSize(this._width, this._height);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setResizable(false);
        
        //Main Panel.
        this._mainPanel = new JPanel();
        this._mainPanel.setLayout(null);
        this._mainPanel.setBounds(0, 0, this._width, this._height);
        this._mainPanel.setOpaque(true);
        this._mainPanel.setBackground(Color.lightGray);
        
        //Button to upload a file.
        this._fileUploadButton = new JButton("Cargar Archivo");
        this._fileUploadButton.setBounds(60, 82, 110, 40);
        this._mainPanel.add(this._fileUploadButton);
        
        //Button to traduct the file.
        this._fileTraductorButton = new JButton("Traducir");
        this._fileTraductorButton.setBounds(60, 122, 110, 40);
        this._mainPanel.add(this._fileTraductorButton);
        
        //Label to put the file Path.
        this._filePathText = new JLabel();
        this._filePathText.setBounds(200, 105, 470, 35);
        this._filePathText.setOpaque(true);
        this._filePathText.setBackground(Color.WHITE);
        this._mainPanel.add(this._filePathText);
        
        //Panel text to put the file text.
        this._fileText = new JTextArea();
        this._fileText.setEditable(false);
        this._fileTextScrollPane = new JScrollPane(this._fileText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this._fileTextScrollPane.setBounds(30, 185, 485, 360);
        this._fileTextScrollPane.setOpaque(true);
        this._fileTextScrollPane.setBackground(Color.WHITE);
        this._mainPanel.add(this._fileTextScrollPane);
        
        //Panel text to put the result of the analysis.
        this._analyzerText = new JTextArea();
        this._analyzerText.setEditable(false);
        this._analyzerTextScrollPane = new JScrollPane(this._analyzerText, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this._analyzerTextScrollPane.setBounds(545, 185, 215, 360);
        this._analyzerTextScrollPane.setOpaque(true);
        this._analyzerTextScrollPane.setBackground(Color.WHITE);
        this._mainPanel.add(this._analyzerTextScrollPane);
        
        //Labels to put the information of the server.
        this._ipInformationText = new JLabel();
        this._ipInformationText.setText("IP: 127.0.0.1");
        this._ipInformationText.setBounds(30, 560, 80, 10);
        this._mainPanel.add(this._ipInformationText);
        this._portInformationText = new JLabel();
        this._portInformationText.setText("| Port: 8084");
        this._portInformationText.setBounds(115, 560, 80, 10);
        this._mainPanel.add(this._portInformationText);
        
        //Add the main panel to the frame.
        this.add(this._mainPanel);
        this.setVisible(true);
    }
   
    /**
     * Add the respective actions to the buttons.
     */
    private void initButtonsActions(){
        
        //Upload button action
        this._fileUploadButton.addActionListener((ActionEvent e) -> {
            
            JFileChooser chooser = new JFileChooser();
            //Set that only files can be selected.
            chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            //Set that any type of file can't be selected.
            chooser.setAcceptAllFileFilterUsed(false);  
            //Set the types of files that can be selected.
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Text Documents (*.txt)", "txt"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("PDF Documents (*.pdf)", "pdf"));
            chooser.addChoosableFileFilter(new FileNameExtensionFilter("Microsoft Word Documents (*.docx)", "docx"));
            //If a file is selected.
            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                try {
                    File f = chooser.getSelectedFile();
                    String filePath = f.getAbsolutePath();
                    String fileType = filePath.substring(filePath.lastIndexOf("."),filePath.length());
                    
                    //".txt" files
                    if(fileType.equals(".txt"))
                        this._lexer.readText(filePath);
                    
                    //".pdf" types
                    if(fileType.equals(".pdf"))
                        this._lexer.readPDF(filePath);
                    
                    //".docs" files
                    if(fileType.equals(".docx"))
                        this._lexer.readDocx(filePath);
                    
                    this._lexer.analyzeText();
                    
                    this._filePathText.setText(filePath);
                    this._fileText.setText(this._lexer.getText());
                    this._analyzerText.setText("");
                    
                } catch (IOException ex) {
                    Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        //Traductor button action
        this._fileTraductorButton.addActionListener((ActionEvent e) -> {
            
            Token tk = this._lexer.getToken();
            String tokenString = "(" + tk.getType() + ": " + tk.getValue() + ")\n";
            this._analyzerText.setText(this._analyzerText.getText() + tokenString); 
            
        });
 
        
    }
    
}
