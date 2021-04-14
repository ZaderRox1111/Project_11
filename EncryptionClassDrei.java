package p11_package;

/**
 * Class uses linked list to encrypt text
 * 
 * @author MichaelL
 *
 */

public class EncryptionClassDrei
   {
    /**
     * Constant for one digit offset in calculations
     */
    private final int ONE_DIGIT_OFFSET = 10;
   
    /**
     * Constant for two digit offset in calculations
     */
    private final int TWO_DIGIT_OFFSET = 100;
   
    /**
     * Constant for one digit offset in calculations
     */
    private final int THREE_DIGIT_OFFSET = 1000;
   
    /**
     * Constant for minimum random iterations in encryption
     */
    private final int MIN_ITERATIONS = 3;
   
    /**
     * Constant for maximum random iterations in encryption
     */
    private final int MAX_ITERATIONS = 5;
   
    /**
     * Constant for minimum three digit random number
     */
    private final int THREE_DIGIT_LOW = 100;
   
    /**
     * Constant for maximum three digit random number
     */
    private final int THREE_DIGIT_HIGH = 999;
   
    /**
     * Constant for minimum two digit random number
     */
    private final int TWO_DIGIT_LOW = 10;
   
    /**
     * Constant for maximum two digit random number
     */
    private final int TWO_DIGIT_HIGH = 99;
   
    /**
     * Constant for caret character
     */
    private final char CARET_CHAR = '^';
   
    /**
     * Head reference for linked list
     */
    private NodeClass headRef;
    
    /**
     * Default constructor
     * <p>
     * Sets head reference to null
     */
    public EncryptionClassDrei()
       {
        //        
          //set head reference to null
          this.headRef = null;
       }
    
    /**
     * Initialization constructor
     * <p>
     * Accepts and encrypts String parameter
     * <p>
     * Dependencies: encryptString
     * 
     * @param toEncrypt String value to be encrypted
     */
    public EncryptionClassDrei( String toEncrypt )
       {
        //       
          //encrypt the string
          encryptString(toEncrypt);
       }
    
    /**
     * Copy constructor
     * <p>
     * Copies other linked list into this linked list, 
     * making duplicate with new data
     * <p>
     * Dependencies: NodeClass copy constructor
     * 
     * @param copied EncryptionClassDrei object with linked list to be copied
     */
    public EncryptionClassDrei( EncryptionClassDrei copied )
       {
        //
          //create variables
          NodeClass cpdRef, thisRef;
          
          //check if the list is empty. If so, set this list to empty as well
          if (copied.headRef == null) 
             {
                this.headRef = null;
             }
          
          //otherwise go through the list copying each node
          else 
             {
                //copy the head reference to avoid aliasing
                 //Method: copy constructor for NodeClass
                this.headRef = new NodeClass(copied.headRef);
                cpdRef = copied.headRef;
                thisRef = this.headRef;
                
                cpdRef = cpdRef.nextRef;
                
                //loop through the copied list, and make a copy to put into this list
                //to avoid aliasing
                while(cpdRef != null) 
                   {
                       //Method: copy constructor for NodeClass
                      thisRef.nextRef = new NodeClass(cpdRef);
                      cpdRef = cpdRef.nextRef;
                      thisRef = thisRef.nextRef;
                   }
             }
       }
   
    /**
     * appendItem
     * <p>
     * Adds item to end of list, uses helper method
     * <p>
     * Dependencies: appendItemHelper
     * 
     * @param newVal long value to be appended to list
     */
    public void appendItem( long newVal )
       {
        // TODO
          //receive the head reference in the linked list
          //and recursively add the newVal to the end of the list
           //Method: appendItemHelper
          headRef = appendItemHelper(headRef, newVal);
       }
    
    /**
     * appendItemHelper
     * <p>
     * Recursively iterates to end of list, then adds node
     * <p>
     * Dependencies: NodeClass initialization constructor
     * 
     * @param wkgRef NodeClass reference pointing to current node in recursion
     * 
     * @param newVal long value to be appended to list
     * 
     * @return NodeClass reference to most recent node in recursion
     */
    private NodeClass appendItemHelper( NodeClass wkgRef, long newVal )
       {
        // 
          
          //check if the wkgRef is null
          //if not, recursively call itself
          if (wkgRef != null) 
             {
                //It will set the nextRef as the returned wkgRef from the next iteration
                 //Method: recursive appendItemHelper
                wkgRef.nextRef = appendItemHelper(wkgRef.nextRef, newVal);
                
                //after it comes back from the end, it will return the wkgRef
                return wkgRef;
             }
          else 
             {
                //when it reaches null, it will return the last node
                //then it will set the previous node's nextRef to this final value
                 //Method: NodeClass initialization constructor
                return new NodeClass(newVal);
             }
       }
    
    /**
     * decodeCharacter
     * <p>
     * Removes values encoded in long, reassembles and returns as character
     * <p>
     * Incoming character first digit is in the 7th position (000000x00, 
     * the second digit is in the fourth position (000x00000),
     * and the third digit is in the  first position (x00000000)
     * <p>
     * Dependencies: none
     * 
     * @param encodedLong long value holding encoded character value
     * 
     * @return value holding decoded character
     */
    private char decodeCharacter( long encodedLong )
       {
        // 
          //create variables
          int firstDigit, secondDigit, thirdDigit, charValue;
          char decodedChar;
          
          //remove the first two numbers by division and receive the first value with modulo
          //remove the next three, then get the second val with modulo
          //remove the next three, what you're left with is the final number
          encodedLong /= TWO_DIGIT_OFFSET;
          firstDigit = (int) encodedLong % ONE_DIGIT_OFFSET;
          
          encodedLong /= THREE_DIGIT_OFFSET;
          secondDigit = (int) encodedLong % ONE_DIGIT_OFFSET;
          
          encodedLong /= THREE_DIGIT_OFFSET;
          thirdDigit = (int) encodedLong;
          
          //multiply the first digit by 2 digit offset,
          //add second digit multiplied by 1 offset, 
          //then add third digit, to get the decoded character value
          charValue = firstDigit * TWO_DIGIT_OFFSET;
          charValue += secondDigit * ONE_DIGIT_OFFSET;
          charValue += thirdDigit;
          
          //convert the char value to a char
          decodedChar = (char) charValue;
          
          //return the character decoded
          return decodedChar;
       }
        
    /**
     * decryptList
     * <p>
     * Converts linked list of encoded integers into string
     * <p>
     * Dependencies: decodeCharacter
     * 
     * @param wkgRef NodeClass reference to uncloaked linked list
     * 
     * @return String result of processing
     */
    public String decryptList( NodeClass wkgRef )
       {
        //
          //run through the linked list, converting each node to a character
          //add each character to a string
          
          //create variables
          String decryptedString = "";
          char currentChar;
          long encryptedValue;
          
          //run until you hit a null
          while (wkgRef != null) 
             {
                //get encrypted value
                encryptedValue = wkgRef.encryptedVal;
                
                //convert the value to a char
                 //Method: decodeCharacter
                currentChar = decodeCharacter(encryptedValue);
                
                //add the char to the string
                decryptedString += currentChar;
                
                //update the copied wkgRef
                wkgRef = wkgRef.nextRef;
             }
          
          //return the string
          return decryptedString;
       }
   
    /**
     * displayEncryptedList (overloaded)
     * <p>
     * Displays THIS linked list as series of numbers with a specified width
     * 
     * @param width integer value specifying how many numbers on a line
     */
    public void displayEncryptedList( int width )
       {
        // 
          //call with headRef and given width
           //Method: displayEncryptedList
          displayEncryptedList(headRef, width);
       }
    
       /**
     * displayEncryptedList (overloaded)
     * <p>
     * Displays USER PROVIDED linked list as series of numbers 
     * with a specified width
     * 
     * @param wkgRef NodeClass reference starting at head of list
     * 
     * @param width integer value specifying how many numbers on a line
     */
    public void displayEncryptedList( NodeClass wkgRef, int width )
       {
        //
          //create variables
          char decodedChar;
          long encryptedValue;
          int counter = 0;
          
          //access the encrypted value at node
          //get char there and put on end
          //print out the value and character
          while (wkgRef != null) 
             {
                //get encrypted value
                encryptedValue = wkgRef.encryptedVal;
                
                //convert the value to a char
                 //method: decodeCharacter
                decodedChar = decodeCharacter(encryptedValue);
                
                //print out the value and character
                //check if the counter modulo width is 0
                //if so, print a new line as well
                if (counter % width == 0) 
                   {
                      System.out.println();
                   }
                
                System.out.format(" %9d('%c')", encryptedValue, decodedChar);
                
                //update the wkgRef and counter
                wkgRef = wkgRef.nextRef;
                counter += 1;
             }
          
          //print a newline at the end
          System.out.println();
       }
    
    /**
     * encodeCharacter
     * <p>
     * Sets three-digit character value into nine-digit long such that
     * the first digit is in the 7th position (000000x00), the second digit 
     * is in the fourth position (000x00000)and the third digit is in the 
     * first position (x00000000)
     * <p>
     * Dependencies: getRandBetween
     * 
     * @param encodeChar character value to be encoded
     * 
     * @return long value containing encoded character
     */
    public long encodeCharacter( char encodeChar )
       {
        // initialize method, variables
       
           // set the character to an integer
           int charInt = (int)encodeChar;
           
           // get the first/LSD digit
           int firstDigit = charInt % ONE_DIGIT_OFFSET;
           
           // declare other variables
           int thirdDigit, secondDigit;
           long resultLong;
        
        // divide off first digit
        charInt /= ONE_DIGIT_OFFSET;
        
        // capture second digit
        secondDigit = charInt % ONE_DIGIT_OFFSET;
        
        // divide off second digit
        charInt /= ONE_DIGIT_OFFSET;
        
        // capture third digit
        thirdDigit = charInt;
        
        // set first digit in long value
           // method: getRandBetween
        resultLong = firstDigit * TWO_DIGIT_OFFSET 
                              + getRandBetween( TWO_DIGIT_LOW, TWO_DIGIT_HIGH );
        
        // set second digit in long value
           // method: getRandBetween
        resultLong = secondDigit * TWO_DIGIT_OFFSET 
                        + getRandBetween( TWO_DIGIT_LOW, TWO_DIGIT_HIGH ) 
                                               + resultLong * THREE_DIGIT_OFFSET;
        
        // set third digit in long value
           // method: getRandBetween
        resultLong = thirdDigit * TWO_DIGIT_OFFSET
                        + getRandBetween( TWO_DIGIT_LOW, TWO_DIGIT_HIGH )
                                               + resultLong * THREE_DIGIT_OFFSET;
        // return long value
        return resultLong;
       }
    
    /**
     * encryptString
     * <p>
     * Generates random groups of values in linked list,
     * then places caret and next character,
     * repeats this process until end of string,
     * then adds one more random group
     * <p>
     * Each random group has a random number between 3 and 5 values
     * which are themselves random
     * <p>
     * Dependencies: .length, charAt, getRandBetween, generateNineDigitRandom,
     * encodeCharacer, appendItem
     * 
     * @param toEncrypt String value to be encrypted to linked list
     */
    public void encryptString( String toEncrypt )
       {
        // initialize method, variables
        int index = 0;
        int randIndex, numIterations;
        long nineDigitRand, charLongValue;
        char encodeChar;
        
        // loop to end of string
           // method: .length
        while( index < toEncrypt.length() )
           {
            // create number of random iterations
               // method: getRandBetween
            numIterations = getRandBetween( MIN_ITERATIONS, MAX_ITERATIONS );
            
            // loop across random iterations
            for( randIndex = 0; randIndex < numIterations; randIndex++ )
               {
                // create dummy number
                   // method: generateNineDigitRandom
                nineDigitRand = generateNineDigitRandom();
                
                // add item to linked list
                   // method: appendItem
                appendItem( nineDigitRand );
               }           
            // end loop
           
            // create value for caret
               // method: encodeCharacter
            charLongValue = encodeCharacter( CARET_CHAR );
            
            // add caret to linked list
               // method: appendItem
            appendItem( charLongValue );
            
            // get next character to encrypt
               // method: .charAt
            encodeChar = toEncrypt.charAt( index );
            
            // create value for new character
               // method: encodeCharacter
            charLongValue = encodeCharacter( encodeChar );
            
            // add item to linked list
               // method: appendItem
            appendItem( charLongValue );
            
            // increment loop index
            index++;
           }
        // end loop
        
        // add one more series of random values
        
           // create number of random iterations
              // method: getRandBetween
           numIterations = getRandBetween( MIN_ITERATIONS, MAX_ITERATIONS );
                
           // loop across random iterations
           for( randIndex = 0; randIndex < numIterations; randIndex++ )
              {
               // create new random value
                  // method: generateNineDigitRandom
               nineDigitRand = generateNineDigitRandom();
               
               // add value to linked list
                  // method: appendItem
               appendItem( nineDigitRand );
              }           
           // end loop
       }    
   
    /**
     * generateNineDigitRandom
     * <p>
     * Generates a nine digit random value for cloaking encrypted data
     * <p>
     * Dependencies: getRandBetween
     * 
     * @return nine digit random long
     */
    private long generateNineDigitRandom()
       {
        // 
          //create variables
          int randomNineNum;
          
          //get random three digit number
          //offset it, then get new randomthree digit number and add it to the original
          //do one more time to get nine digits
           //Method: getRandBetween
          randomNineNum = getRandBetween(THREE_DIGIT_LOW, THREE_DIGIT_HIGH);
          randomNineNum *= THREE_DIGIT_OFFSET;
          randomNineNum += getRandBetween(THREE_DIGIT_LOW, THREE_DIGIT_HIGH);
          randomNineNum *= THREE_DIGIT_OFFSET;
          randomNineNum += getRandBetween(THREE_DIGIT_LOW, THREE_DIGIT_HIGH);
          
          //return the random number
          return randomNineNum;
       }
    
    /**
     * getRandBetween
     * <p>
     * Returns random value between low and high parameters, inclusive
     * <p>
     * Dependencies: Math.random
     * 
     * @param low integer value indicating low end of random range to generate
     * 
     * @param high integer value indicating high end of random range to generate
     * 
     * @return random value between low and high parameters, inclusive
     */
    private int getRandBetween( int low, int high )
       {
        // initialize method, variables
       
           // create range value
           int range = high - low + 1;
   
        // find random within range, inclusive, return
           // method: Math.random
        return (int)( Math.random() * range + low );
       }
    
    /**
     * isCharacter
     * <p>
     * Returns Boolean true if node holds specified character,
     * false otherwise
     * <p>
     * Dependencies: decodeCharacter
     * 
     * @param testRef NodeClass reference to given node
     * 
     * @param testChar character value to be tested in node
     * 
     * @return Boolean result of specified test
     */
    private boolean isCharacter( NodeClass testRef, char testChar )
       {
        // 
          //create variables
          char decodedChar;
          long encryptedValue;
          
          //get the encrypted value from a test reference
          //decode the value to get the character at the node
           //Method: decodeCharacter
          //compare testChar and the decoded character
          encryptedValue = testRef.encryptedVal;
          
          decodedChar = decodeCharacter(encryptedValue);
          
          //return true if the char is in the node
          if (decodedChar == testChar) 
             {
                return true;
             }
       
          //return false if the char is not in the node
          return false;
       }
    
    /**
     * uncloakList
     * <p>
     * Removes all random and caret nodes from linked list, 
     * linked list with encoded integers/characters remains
     * <p>
     * Dependencies: isCharacter
     * 
     * @return NodeClass reference to newly created linked list
     * containing only the encoded text letters
     */
    public NodeClass uncloakList()
       {
        // initialize method, variables
       
           // set working reference to head reference
           NodeClass wkgRef = headRef;
           
           // set other references to null
           NodeClass wkgResultRef = null, resultRef = null;
        
        // loop to end of linked list   
        while( wkgRef != null )
           {
            // search for caret
               // method: isCharacter
            while( wkgRef != null && !isCharacter( wkgRef, CARET_CHAR ) )
               {
                // skip ahead in linked list
                wkgRef = wkgRef.nextRef;               
               }
            
            // check for caret found (not null)
            if( wkgRef != null )
               {
                // skip over caret
                wkgRef = wkgRef.nextRef;
                
                // check for result reference null (empty)
                if( resultRef == null )
                   {
                    // set result reference to working reference
                    resultRef = wkgRef;
                    
                    // set a working result reference to the result reference
                    wkgResultRef = resultRef;
                   }
                
                // otherwise, assume result reference already has data
                else
                   {
                    // link the newest found character node
                    //   to the working result reference
                    wkgResultRef.nextRef = wkgRef;
                    
                    // advance the working result reference
                    wkgResultRef = wkgResultRef.nextRef;
                   }
               }
           }
        // end loop across linked list
        
        // set end for working result reference
        wkgResultRef.nextRef = null;
        
        // return result reference
        return resultRef;
       }
   }