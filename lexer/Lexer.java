package lexer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

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
     * Get the text that was currently analyzed.
     * @return The text.
     */
    public String getText(){
        return this._docText;
    }
    
    /**
     * Get a token from the list of tokens that the "lexical analyzer" created
     * from the analyzed text.
     * @return The first token in that list.
     */
    public Token getToken(){
        return this._tokens.poll();
    }
    
    /**
     * Translate a document in ".pdf" format to string.
     * @param pFilePath The file location path.
     * @throws IOException 
     */
    public void readPDF(String pFilePath) throws IOException{
        
        System.setProperty("sun.java2d.cmm", "sun.java2d.cmm.kcms.KcmsServiceProvider");
        this._docText = "";  
        File file = new File(pFilePath);
        PDDocument doc = PDDocument.load(file);
        PDFTextStripper pdfStripper = new PDFTextStripper();     
        this._docText = pdfStripper.getText(doc);
        doc.close();
        
    }
    
    /**
     * Translate a document in ".docx" format to string.
     * @param pFilePath The file location path.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readDocx(String pFilePath) throws FileNotFoundException, IOException{
    
        this._docText = "";
        XWPFDocument docx = new XWPFDocument(new FileInputStream(pFilePath));
        XWPFWordExtractor we = new XWPFWordExtractor(docx);
        this._docText = we.getText();
        we.close();
        
    }
    
    /**
     * Translate a document in ".txt" format to string.
     * @param pFilePath The file location path.
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void readText(String pFilePath) throws FileNotFoundException, IOException{
       
        this._docText = ""; 
        BufferedReader in = new BufferedReader(new FileReader(pFilePath));
        String text;
        while((text = in.readLine()) != null){
            this._docText += (text + "\n");
        }
        in.close();
        
    }
    
    /**
     * 
     * @param pFile 
     */
    public void readString(String pFile){ 
        
        this._docText = pFile;
        
    }
    
    /**
     * Apply the lexical analysis to previously read text.
     */
    public void analyzeText(){
        
        //Reset the list of tokens, in case there were any token from
        //the previous analysis.
        this._tokens.clear();
        
        //Go through the text.
        for(int i = 0; i < this._docText.length(); i++){
            //If the character is in the alphabet.
            if(this._symbolTable.consult(this._docText.charAt(i))){
                Token tk = new Token(this._docText.charAt(i), this._symbolTable.getValue(this._docText.charAt(i)));
                this._tokens.add(tk);
            }
            else{
                Token tk = new Token(this._docText.charAt(i), "Desconocido");
                this._tokens.add(tk);
            }
        }
        
    }
   
}
