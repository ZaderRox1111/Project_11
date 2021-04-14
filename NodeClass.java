package p11_package;

public class NodeClass
{
 /**
  *  encryption value
  *  <p>
  *  Integer value holding encoded character
  */
 public long encryptedVal;
 
 /**
  * next reference
  * <p>
  * NodeClass reference used to point at the next NodeClass object
  */
 public NodeClass nextRef;
 
 /**
  * Initialization constructor
  * <p>
  * Sets data value to parameter value, next reference to null
  * 
  * @param newVal integer value to be stored in node
  */
 public NodeClass( long newVal )
    {
     encryptedVal = newVal;
     
     nextRef = null;
    }
 
 /**
  * Copy constructor
  * <p>
  * Sets data value to copied data value, sets next reference to null
  * 
  * @param copied NodeClass object to be copied
  */
 public NodeClass( NodeClass copied )
    {
     encryptedVal = copied.encryptedVal;
     
     nextRef = null;
    }

}