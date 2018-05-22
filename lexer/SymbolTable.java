package lexer;

import java.util.Hashtable;

public class SymbolTable {
   
    private Hashtable _symbolTable;
    
    public SymbolTable(){
        this._symbolTable = new Hashtable();
        this.fillSymbolTable();
    }
   
    /**
     * 
     */
    private void fillSymbolTable(){
        //----------------------------------------------------------------------
        // Numbers
        //----------------------------------------------------------------------
        this._symbolTable.put('0', "0,1/0,1/1,1-0,1/1,1/0,0"); // 0
        this._symbolTable.put('1', "0,1/0,1/1,1-1,0/0,0/0,0"); // 1
        this._symbolTable.put('2', "0,1/0,1/1,1-1,0/1,0/0,0"); // 2
        this._symbolTable.put('3', "0,1/0,1/1,1-1,1/0,0/0,0"); // 3
        this._symbolTable.put('4', "0,1/0,1/1,1-1,1/0,1/0,0"); // 4
        this._symbolTable.put('5', "0,1/0,1/1,1-1,0/0,1/0,0"); // 5
        this._symbolTable.put('6', "0,1/0,1/1,1-1,1/1,0/0,0"); // 6
        this._symbolTable.put('7', "0,1/0,1/1,1-1,1/1,1/0,0"); // 7
        this._symbolTable.put('8', "0,1/0,1/1,1-1,0/1,1/0,0"); // 8
        this._symbolTable.put('9', "0,1/0,1/1,1-0,1/1,0/0,0"); // 9
        //----------------------------------------------------------------------
        // Punctuation
        //----------------------------------------------------------------------
        this._symbolTable.put('.', "0,0/0,0/0,0-0,0/1,1/0,1"); //.
        this._symbolTable.put(',', "0,0/0,0/0,0-0,0/1,0/0,0"); //,
        this._symbolTable.put(':', "0,0/0,0/0,0-0,0/1,1/0,0"); //:
        this._symbolTable.put(';', "0,0/0,0/0,0-0,0/1,0/1,0"); //;
        //----------------------------------------------------------------------
        // Signs
        //----------------------------------------------------------------------
        this._symbolTable.put('+', "0,0/0,0/0,0-0,0/1,1/1,0"); // +
        this._symbolTable.put('-', "0,0/0,0/0,0-0,0/1,1/0,0"); // -
        this._symbolTable.put('*', "0,0/0,0/0,0-0,0/0,1/1,0"); // *
        this._symbolTable.put('@', "0,0/0,0/0,0-0,1/0,1/1,0"); // @
        this._symbolTable.put('<', "0,0/0,0/0,0-1,0/1,0/0,1"); // <
        this._symbolTable.put('>', "0,0/0,0/0,0-0,1/0,1/1,0"); // >
        this._symbolTable.put('/', "0,0/0,0/0,0-0,1/0,0/1,0"); // /
        this._symbolTable.put('=', "0,0/0,0/0,0-0,0/1,1/1,1"); // =
        this._symbolTable.put('#', "0,0/0,0/0,0-0,1/0,1/1,1"); // #
        this._symbolTable.put('_', "0,0/0,0/0,0-0,0/0,1/1,1"); // _
        //----------------------------------------------------------------------
        // Uppercase Letters
        //----------------------------------------------------------------------
        this._symbolTable.put('A', "0,0/0,0/0,1-1,0/0,0/0,0"); // A
        this._symbolTable.put('B', "0,0/0,0/0,1-1,0/1,0/0,0"); // B
        this._symbolTable.put('C', "0,0/0,0/0,1-1,1/0,0/0,0"); // C
        this._symbolTable.put('D', "0,0/0,0/0,1-1,1/0,1/0,0"); // D
        this._symbolTable.put('E', "0,0/0,0/0,1-1,0/0,1/0,0"); // E
        this._symbolTable.put('F', "0,0/0,0/0,1-1,1/1,0/0,0"); // F
        this._symbolTable.put('G', "0,0/0,0/0,1-1,1/1,1/0,0"); // G
        this._symbolTable.put('H', "0,0/0,0/0,1-1,0/1,1/0,0"); // H
        this._symbolTable.put('I', "0,0/0,0/0,1-0,1/1,0/0,0"); // I
        this._symbolTable.put('J', "0,0/0,0/0,1-0,1/1,1/0,0"); // J
        this._symbolTable.put('K', "0,0/0,0/0,1-1,0/0,0/1,0"); // K
        this._symbolTable.put('L', "0,0/0,0/0,1-1,0/1,0/1,0"); // L
        this._symbolTable.put('M', "0,0/0,0/0,1-1,1/0,0/1,0"); // M
        this._symbolTable.put('N', "0,0/0,0/0,1-1,1/0,1/1,0"); // N
        this._symbolTable.put('O', "0,0/0,0/0,1-1,0/0,1/1,0"); // O
        this._symbolTable.put('P', "0,0/0,0/0,1-1,1/1,0/1,0"); // P
        this._symbolTable.put('Q', "0,0/0,0/0,1-1,1/1,1/1,0"); // Q
        this._symbolTable.put('R', "0,0/0,0/0,1-1,0/1,1/1,0"); // R
        this._symbolTable.put('S', "0,0/0,0/0,1-0,1/1,0/1,0"); // S
        this._symbolTable.put('T', "0,0/0,0/0,1-0,1/1,1/1,0"); // T
        this._symbolTable.put('U', "0,0/0,0/0,1-1,0/0,0/1,1"); // U
        this._symbolTable.put('V', "0,0/0,0/0,1-1,0/1,0/1,1"); // V
        this._symbolTable.put('W', "0,0/0,0/0,1-0,1/1,1/0,1"); // W
        this._symbolTable.put('X', "0,0/0,0/0,1-1,1/0,0/1,1"); // X
        this._symbolTable.put('Y', "0,0/0,0/0,1-1,1/0,1/1,1"); // Y
        this._symbolTable.put('Z', "0,0/0,0/0,1-1,0/0,1/1,1"); // Z 
        //----------------------------------------------------------------------
        // Lowercase Letters
        //----------------------------------------------------------------------
        this._symbolTable.put('a', "0,0/0,0/0,0-1,0/0,0/0,0"); // a
        this._symbolTable.put('b', "0,0/0,0/0,0-1,0/1,0/0,0"); // b
        this._symbolTable.put('c', "0,0/0,0/0,0-1,1/0,0/0,0"); // c
        this._symbolTable.put('d', "0,0/0,0/0,0-1,1/0,1/0,0"); // d
        this._symbolTable.put('e', "0,0/0,0/0,0-1,0/0,1/0,0"); // e
        this._symbolTable.put('f', "0,0/0,0/0,0-1,1/1,0/0,0"); // f
        this._symbolTable.put('g', "0,0/0,0/0,0-1,1/1,1/0,0"); // g
        this._symbolTable.put('h', "0,0/0,0/0,0-1,0/1,1/0,0"); // h
        this._symbolTable.put('i', "0,0/0,0/0,0-0,1/1,0/0,0"); // i
        this._symbolTable.put('j', "0,0/0,0/0,0-0,1/1,1/0,0"); // j
        this._symbolTable.put('k', "0,0/0,0/0,0-1,0/0,0/1,0"); // k
        this._symbolTable.put('l', "0,0/0,0/0,0-1,0/1,0/1,0"); // l
        this._symbolTable.put('m', "0,0/0,0/0,0-1,1/0,0/1,0"); // m
        this._symbolTable.put('n', "0,0/0,0/0,0-1,1/0,1/1,0"); // n
        this._symbolTable.put('o', "0,0/0,0/0,0-1,0/0,1/1,0"); // o
        this._symbolTable.put('p', "0,0/0,0/0,0-1,1/1,0/1,0"); // p
        this._symbolTable.put('q', "0,0/0,0/0,0-1,1/1,1/1,0"); // q
        this._symbolTable.put('r', "0,0/0,0/0,0-1,0/1,1/1,0"); // r
        this._symbolTable.put('s', "0,0/0,0/0,0-0,1/1,0/1,0"); // s
        this._symbolTable.put('t', "0,0/0,0/0,0-0,1/1,1/1,0"); // t
        this._symbolTable.put('u', "0,0/0,0/0,0-1,0/0,0/1,1"); // u
        this._symbolTable.put('v', "0,0/0,0/0,0-1,0/1,0/1,1"); // v
        this._symbolTable.put('w', "0,0/0,0/0,0-0,1/1,1/0,1"); // w
        this._symbolTable.put('x', "0,0/0,0/0,0-1,1/0,0/1,1"); // x
        this._symbolTable.put('y', "0,0/0,0/0,0-1,1/0,1/1,1"); // y
        this._symbolTable.put('z', "0,0/0,0/0,0-1,0/0,1/1,1"); // z
    }
    
    /**
     * 
     * @param pKey
     * @return 
     */
    public boolean consult(char pKey){
        return this._symbolTable.containsKey(pKey);
    }
    
    /**
     * 
     * @param pKey
     * @return 
     */
    public String getValue(char pKey){
        return this._symbolTable.get(pKey).toString();
    }
    
}
