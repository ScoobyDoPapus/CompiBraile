package lexer;

public class Token {
    
    //--------------------------------------------------------------------------
    // Attributes
    //--------------------------------------------------------------------------
    private char _type;
    private String _value;
    
    //--------------------------------------------------------------------------
    // Constructors
    //--------------------------------------------------------------------------    
    public Token(){
        this._type = ' ';
        this._value = null;
    }
    
    public Token(char pType, String pValue){
        this._type = pType;
        this._value = pValue;
    }
    
    //--------------------------------------------------------------------------
    // Getters
    //--------------------------------------------------------------------------
    public char getType(){
        return this._type;
    }
    
    public String getValue(){
        return this._value;
    }
    
    //--------------------------------------------------------------------------
    // Setters
    //--------------------------------------------------------------------------
    public void setType(char pType){
        this._type = pType;
    }
    
    public void setValue(String pValue){
        this._value = pValue;
    }
    
    //--------------------------------------------------------------------------
    // Debugging
    //--------------------------------------------------------------------------
    public void print(){
        System.out.println("(" + this._type + " : " + this._value + ")");
    }
    
}
