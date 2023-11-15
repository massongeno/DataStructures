// BinarySearchTree class
//
// CONSTRUCTION: with no initializer
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
// boolean contains( x )  --> Return true if x is present
// Comparable findMin( )  --> Return smallest item
// Comparable findMax( )  --> Return largest item
// boolean isEmpty( )     --> Return true if empty; else false
// void makeEmpty( )      --> Remove all items
// void printTree( )      --> Print tree in sorted order
// ******************ERRORS********************************
// Throws UnderflowException as appropriate

/**
 * Implements an unbalanced binary search tree.
 * Note that all "matching" is based on the compareTo method.
 * @author Mark Allen Weiss
 */
public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }
    
    public void mirrorInsert( AnyType x )
    {
        root = mirrorInsert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
    }

    /**
     * Test if the tree is logically empty.
     * @return true if empty, false otherwise.
     */
    public boolean isEmpty( )
    {
        return root == null;
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }
    
    public int nodeCount(){
        if ( isEmpty( ) ){
            return 0;
        } else {
            return nodeCount(root);
        }
    }
    
    public boolean isFull(){
            return isFull(root);
    }
    
    public boolean compareStructure(BinarySearchTree<AnyType> a,BinarySearchTree<AnyType> b){
        return compareStructure(a.root,b.root);
    }
    
    public boolean equals(BinarySearchTree<AnyType> a,BinarySearchTree<AnyType> b){
        return equals (a.root,b.root);
    }
    
    public BinarySearchTree<AnyType> copy(BinarySearchTree<AnyType> a){
        BinarySearchTree<AnyType> c = new BinarySearchTree<>( );
        if (a.root != null){
            copy(a.root,c);
        }
        return c;
    }
    
    public BinarySearchTree<AnyType> mirror(BinarySearchTree<AnyType> a){
        BinarySearchTree<AnyType> c = new BinarySearchTree<>( );
        if (a.root != null){
            mirror(a.root,c);
        }
        return c;
    }
    
    public void printLevels(BinarySearchTree<AnyType> a){
      if (isEmpty() ){
        return;
      }
      for (int i = 1; i <= height(a.root) + 1; i++){
            printLevels(a.root, i);
            System.out.println();
      }
    }
    
    public boolean isMirror(BinarySearchTree<AnyType> a,BinarySearchTree<AnyType> b){
        return isMirror(a.root,b.root);
    }
    
    public void rotateLeft(AnyType x) {
        root = rotateLeft(root, x);
    }

    public void rotateRight(AnyType x) {
        root = rotateRight(root, x);
    }

    

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 )
            t.right = insert( x, t.right );
        else
            ;  // Duplicate; do nothing
        return t;
    }
    private BinaryNode<AnyType> mirrorInsert( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return new BinaryNode<>( x, null, null );
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.right = mirrorInsert( x, t.right );
        else if( compareResult > 0 )
            t.left = mirrorInsert( x, t.left );
        else
            ;  // Duplicate; do nothing
        return t;
    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else if( t.left != null && t.right != null ) // Two children
        {
            t.element = findMin( t.right ).element;
            t.right = remove( t.element, t.right );
        }
        else
            t = ( t.left != null ) ? t.left : t.right;
        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        else if( t.left == null )
            return t;
        return findMin( t.left );
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
        if( t != null )
            while( t.right != null )
                t = t.right;

        return t;
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );
        else
            return true;    // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );
            System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    private int nodeCount(BinaryNode<AnyType> t){
        if (t == null){
            return 0;
        } else {
            return 1 + nodeCount(t.left) + nodeCount(t.right);
        }
    }
    
    private boolean isFull(BinaryNode<AnyType> t) {
        if (t == null){
            return true;
        }
        if (t.left == null && t.right == null) {
            return true;
        }
        if (t.left != null && t.right != null) {
            return isFull(t.left) && isFull(t.right);
        }
        return false;
    }
    
    private boolean compareStructure(BinaryNode<AnyType> a, BinaryNode<AnyType> b) {
        
        if ( a == null && b == null) {
            return true;
        }
        if (a != null && b!= null) {
            return compareStructure(a.left , b.left) && compareStructure(a.right , b.right);
        }
        return false;
        
    }
    
    private boolean equals(BinaryNode<AnyType> a, BinaryNode<AnyType> b) {
        if (a == null && b == null){
            return true;
        }
        if (a != null && b != null) {
            if (a.element.compareTo(b.element) != 0){
                return false;
            }
            return equals(a.left,b.left) && equals(a.right,b.right);
        }
        return false;
    }
    
    private void copy(BinaryNode<AnyType> a, BinarySearchTree<AnyType> c) {
       if (a != null){
           c.insert(a.element);
           copy(a.left, c);
           copy(a.right, c);
       }
    }
    
    private void mirror(BinaryNode<AnyType> a, BinarySearchTree<AnyType> c) {
       if (a != null){
           c.mirrorInsert(a.element);
           mirror(a.left, c);
           mirror(a.right, c);
       }
    }
    
    private boolean isMirror(BinaryNode<AnyType> a, BinaryNode<AnyType> b) {
        
        if ( a == null && b == null) {
            return true;
        }
        if (a != null && b!= null) {
            return compareStructure(a.left , b.right) && compareStructure(a.right , b.left);
        }
        return false;
        
    }
    
    private BinaryNode<AnyType> rotateRight(BinaryNode<AnyType> root, AnyType x) {
        if (root == null) {
            return null;
        }
        if (root.element.compareTo(x) > 0) {
            root.left = rotateRight(root.left, x);
            return root;
        } else if (root.element.compareTo(x) < 0) {
            root.right = rotateRight(root.right, x);
            return root;
        } else {
            if (root.left == null){
                return root;
            }
            BinaryNode<AnyType> y = root.left;
            root.left = y.right;
            y.right = root;
            return y;
        }
    }
    
    private BinaryNode<AnyType> rotateLeft(BinaryNode<AnyType> root, AnyType x) {
        if (root == null) {
            return null;
        }
        if (root.element.compareTo(x) > 0) {
            root.left = rotateLeft(root.left, x);
            return root;
        } else if (root.element.compareTo(x) < 0) {
            root.right = rotateLeft(root.right, x);
            return root;
        } else {
            if (root.right == null){
                return root;
            }
            BinaryNode<AnyType> y = root.right;
            root.right = y.left;
            y.left = root;
            return y;
        }
    }

    private void printLevels(BinaryNode<AnyType> a, int level) {
        if (a == null){
            return;
        }
        if (level == 1){
            System.out.print(a.element + " ");
        }
        else if (level > 1) {
            printLevels(a.left, level - 1);
            printLevels(a.right, level - 1);
        }
        
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;


        // Test program
    public static void main( String [ ] args )
    {
        BinarySearchTree<Integer> a = new BinarySearchTree<>( );
        BinarySearchTree<Integer> b = new BinarySearchTree<>( );
        BinarySearchTree<Integer> c = new BinarySearchTree<>( );
        
        a.insert(10);
        a.insert(8);
        a.insert(3);
        a.insert(9);
        a.insert(11);

        
        b.insert(10);
        b.insert(5);
        b.insert(2);
        b.insert(7);
        b.insert(15);
        
        BinarySearchTree<Integer> copy = a.copy(a);
        
        BinarySearchTree<Integer> mirror = a.mirror(a);

        
        System.out.println("Tree A: ");
        a.printLevels(a);
        System.out.println("Is Full: " + a.isFull());
        System.out.println("Number of nodes: " + a.nodeCount());
        
        System.out.println();
        
        System.out.println("Tree B:");
        b.printLevels(b);
        System.out.println("Is Full: " + b.isFull());
        System.out.println("Number of nodes: " + b.nodeCount());
        System.out.println("Same Structure? " + a.compareStructure(a,b) );
        System.out.println("Equal? " + a.equals(a,b) );
        
        System.out.println();
        
        copy.printLevels(copy);
        System.out.println("Equal? " + a.equals(a,copy) );
        mirror.printLevels(mirror);
        
        System.out.println();
        
        System.out.println("Same Structure? " + a.compareStructure(a,mirror) );
        System.out.println("Mirror? " + a.isMirror(a,mirror) );
        a.printLevels(a);
        
        System.out.println();
        a.makeEmpty();
        a.insert(100);
        a.insert(50);
        a.insert(40);
        a.insert(45);
        a.insert(150);
        
        System.out.println("Tree A: ");
        a.printLevels(a);
        System.out.println("Rotate Tree A, node 100 right: ");
        a.rotateRight(100);
        a.printLevels(a);
        
        System.out.println();
        
        System.out.println("Rotate Tree A, node 50 left: ");
        a.rotateRight(50);
        a.printLevels(a);
        
        System.out.println("Rotate Tree A, node 4 (null) left: ");
        a.rotateRight(4);
        a.printLevels(a);

    }
}