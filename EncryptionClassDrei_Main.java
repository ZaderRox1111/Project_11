package p11_package;

public class EncryptionClassDrei_Main
{

public static void main(String[] args)
   {
    String resultStr, testStr = "Every smart person keeps learning";
    EncryptionClassDrei testClass = new EncryptionClassDrei( testStr );
    NodeClass uncloaked;
    int displayWidth = 6;
    
    System.out.println( "\nDisplay of encrypted list" );
    testClass.displayEncryptedList( displayWidth );
    
    uncloaked = testClass.uncloakList();
    
    System.out.println( "\nDisplay of uncloaked list" );
    testClass.displayEncryptedList( uncloaked, displayWidth );

    System.out.print( "\nDisplay of original string: " );
    resultStr = testClass.decryptList( uncloaked );
    System.out.println( resultStr );
   }

}