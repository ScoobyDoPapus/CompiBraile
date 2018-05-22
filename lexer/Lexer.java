package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class Lexer {
    
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------    
    private final SymbolTable _symbolTable;
    private Queue<Token> _tokens;
    public String _docText;
    
    //--------------------------------------------------------------------------
    // Constructor
    //--------------------------------------------------------------------------      
    public Lexer(){
        this._symbolTable = new SymbolTable();
        this._tokens = new LinkedList<>();
        this._docText = "";
    }
    
    /**
     * 
     * @return 
     */
    public Token getToken(){
        return this._tokens.poll();
    }
    
    /**
     * 
     * @param pFilePath
     * @throws IOException 
     */
    /*public void readPDF(String pFilePath) throws IOException{
        
        this._tokens.clear();
        this._docText = "";
        
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        
        File file = new File(pFilePath);
        PDDocument doc = PDDocument.load(file);
        
        PDFTextStripper pdfStripper = new PDFTextStripper();
        
        String text = pdfStripper.getText(doc);
        
        System.out.println(text);
        
        for(int i = 0; i < text.length(); i++){
            
            if(this._symbolTable.consult(text.charAt(i))){
                Token tk = new Token(text.charAt(i), this._symbolTable.getValue(text.charAt(i)));
                this._tokens.add(tk);
            }
            else{
                Token tk = new Token(text.charAt(i), "Desconocido");
                this._tokens.add(tk);
            }
        }
        doc.close();
    }*/
    
    /**
     * 
     * @param pFilePath
     * @throws FileNotFoundException
     * @throws IOException 
     */
    /*public void readDocx(String pFilePath) throws FileNotFoundException, IOException{
    
        this._tokens.clear();
        this._docText = "";
    
        XWPFDocument docx = new XWPFDocument(new FileInputStream(pFilePath));
        
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        System.out.println(we.getText());
        
        String text = we.getText();
        
        for(int i = 0; i < text.length(); i++){
                
            if(this._symbolTable.consult(text.charAt(i))){
                Token tk = new Token(text.charAt(i), this._symbolTable.getValue(text.charAt(i)));
                this._tokens.add(tk);
            }
            else{
                Token tk = new Token(text.charAt(i), "Desconocido");
                this._tokens.add(tk);
            }
        }
    }*/
    
    /**
     * 
     * @param pFilePath
     * @return
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readText(String pFilePath) throws FileNotFoundException, IOException{
        
        this._tokens.clear();
        this._docText = "";
        BufferedReader in = new BufferedReader(new FileReader(pFilePath));
        String text;
        while((text = in.readLine()) != null){
            for(int i = 0; i < text.length(); i++){
                if(this._symbolTable.consult(text.charAt(i))){
                    Token tk = new Token(text.charAt(i), this._symbolTable.getValue(text.charAt(i)));
                    this._tokens.add(tk);
                }
                else{
                    Token tk = new Token(text.charAt(i), "Desconocido");
                    this._tokens.add(tk);
                }
            }
            this._docText += (text + "\n");
        }
        in.close();
    }
   
}
