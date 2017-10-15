
public class Grid {
  private int rows = 10;
  private int columns = 6;
  private Node head;
  
  //Constructor: Makes a 10x6 grid of nodes, 
  //circularly linked with right and down pointers,
  //using arrays to assist in initial linking of nodes 
  public Grid(){
    //Create 2d-array and fill with Nodes
    Node[][] gridArray = new Node[rows][columns];
    for(int i = 0; i < rows; i++)
      for(int j = 0; j < columns; j++)
      gridArray[i][j] = new Node();
    head = gridArray[0][0];
    //Traverse 2d-array and set each Node's right and down pointers
    for(int i = 0; i < rows; i++)
      for(int j = 0; j < columns; j++){
      gridArray[i][j].setRight(gridArray[i][(j+1) % columns]);
      gridArray[i][j].setDown(gridArray[(i+1) % rows][j]);
    }
  }
  
  //Traverses to a given node from the head pointer.
  public Node traverse(int row, int col){
    Node iterator = head;
    for(int i = 0; i < col; i++)
      iterator = iterator.getRight();
    for(int i = 0; i < row; i++)
      iterator = iterator.getDown();
    return iterator;
  }
  
  //Creates a Value from given string, 
  //uses Traverse method to get to given cell, then setValue
  public void assignCell(int row, int col, String s){
    Value newValue = new Value(s);
    Node iterator = traverse(row,col);
    iterator.setValue(newValue);
  }
  
  //Creates a subgrid from beginning and ending coordinates, and fills cells in
  //a rectangular subgrid with a given value 
  public void fill(int fromRow, int fromCol, int toRow, int toCol, Value val){
    Node iterator = traverse(fromRow, fromCol);
    for(int j = fromRow; j <= toRow; j++){
      for(int i = fromCol; i <= toCol; i++){
        iterator.setValue(val);
        iterator = iterator.getRight();
      }
      //After reaching right edge of subgrid, traverse back to left side
      //and then step down a row
      for(int i = 0; i < (columns - (toCol - fromCol + 1)); i++)
        iterator = iterator.getRight();
      iterator = iterator.getDown();
    }
  }
  
  //Similar to fill: creates rectuangular subgrid and fills cells with
  //incrementing numbers, multipliedByting with zero.
  public void number(int fromRow, int fromCol, int toRow, int toCol){
    Node iterator = traverse(fromRow, fromCol);
    int val = 0;
    for(int j = fromRow; j <= toRow; j++){
      for(int i = fromCol; i <= toCol; i++){
        iterator.setValue(new Value(String.valueOf(val)));
        iterator = iterator.getRight();
        val++;
      }
      for(int i = 0; i < (columns - (toCol - fromCol + 1)); i++)
        iterator = iterator.getRight();
      iterator = iterator.getDown();
    }
  }
  
  //Takes three cells as parameters. Adds values in first two cells
  //and sets the result in the third (target) cell. Checks to make
  //sure each operand is a DBL.
  public void addCells(int firstRow, int firstCol, int secondRow, 
                       int secondCol, int targetRow, int targetCol){
    Node first = traverse(firstRow, firstCol);
    Node second = traverse(secondRow, secondCol);
    Node target = traverse(targetRow, targetCol);
    Node result = new Node();
    result.setValue(first.getValue().plus(second.getValue()));
    if(result.getValue().getTag() != "INVALID")
      target.setValue(result.getValue());
  }
  
  //Takes three cells as parameters. Subtracts values in first two cells
  //and sets the result in the third (target) cell. Checks to make
  //sure each operand is a DBL.
  public void subtractCells(int firstRow, int firstCol, int secondRow, 
                            int secondCol, int targetRow, int targetCol){
    Node first = traverse(firstRow, firstCol);
    Node second = traverse(secondRow, secondCol);
    Node target = traverse(targetRow, targetCol);
    Node result = new Node();
    result.setValue(first.getValue().minus(second.getValue()));
    if(result.getValue().getTag() != "INVALID")
      target.setValue(result.getValue());
  }
  
  //Takes three cells as parameters. Multiplies values in first two cells
  //and sets the result in the third (target) cell. Checks to make
  //sure each operand is a DBL.
  public void multiplyCells(int firstRow, int firstCol, int secondRow, 
                            int secondCol, int targetRow, int targetCol){
    Node first = traverse(firstRow, firstCol);
    Node second = traverse(secondRow, secondCol);
    Node target = traverse(targetRow, targetCol);
    Node result = new Node();
    result.setValue(first.getValue().multipliedBy(second.getValue()));
    if(result.getValue().getTag() != "INVALID")
      target.setValue(result.getValue());
  }
  
  //Takes three cells as parameters. Divides values in first two cells
  //and sets the result in the third (target) cell. Checks to make
  //sure each operand is a DBL.
  public void divideCells(int firstRow, int firstCol, int secondRow, 
                          int secondCol, int targetRow, int targetCol){
    Node first = traverse(firstRow, firstCol);
    Node second = traverse(secondRow, secondCol);
    Node target = traverse(targetRow, targetCol);
    Node result = new Node();
    result.setValue(first.getValue().dividedBy(second.getValue()));
    if(result.getValue().getTag() != "INVALID")
      target.setValue(result.getValue());
  }
  
  //Takes three rows as parameters. Adds values in first two rows
  //and sets the results as values in the third (target) row.
  public void addRows(int firstRow, int secondRow, int targetRow){
    for(int i = 0; i < columns; i++){
      addCells(firstRow, i, secondRow, i, targetRow, i);
    }
  }
  
  //Takes three rows as parameters. Subtracts values in first two rows
  //and sets the results as values in the third (target) row.
  public void subtractRows(int firstRow, int secondRow, int targetRow){
    for(int i = 0; i < columns; i++){
      subtractCells(firstRow, i, secondRow, i, targetRow, i);
    }
  }
  
  //Takes three rows as parameters. Multiplies values in first two rows
  //and sets the results as values in the third (target) row.
  public void multiplyRows(int firstRow, int secondRow, int targetRow){
    for(int i = 0; i < columns; i++){
      multiplyCells(firstRow, i, secondRow, i, targetRow, i);
    }
  }
  
  //Takes three rows as parameters. Divides values in first two rows
  //and sets the results as values in the third (target) row.
  public void divideRows(int firstRow, int secondRow, int targetRow){
    for(int i = 0; i < columns; i++){
      divideCells(firstRow, i, secondRow, i, targetRow, i);
    }
  }
  
  //Takes three columns as parameters. Adds values in first two columns
  //and sets the results as values in the third (target) column.
  public void addColumns(int firstColumn, int secondColumn , int targetColumn){
    for(int i = 0; i < rows; i++){
      addCells(i, firstColumn, i, secondColumn, i, targetColumn);
    }
  }
  
  //Takes three columns as parameters. Subtracts values in first two columns
  //and sets the results as values in the third (target) column.
  public void subtractColumns(int firstColumn, int secondColumn , int targetColumn){
    for(int i = 0; i < rows; i++){
      subtractCells(i, firstColumn, i, secondColumn, i, targetColumn);
    }
  }
  
  //Takes three columns as parameters. Subtracts values in first two columns
  //and sets the results as values in the third (target) column.
  public void multiplyColumns(int firstColumn, int secondColumn , int targetColumn){
    for(int i = 0; i < rows; i++){
      multiplyCells(i, firstColumn, i, secondColumn, i, targetColumn);
    }
  }
  
  //Takes three columns as parameters. Divides values in first two columns
  //and sets the results as values in the third (target) column.
  public void divideColumns(int firstColumn, int secondColumn , int targetColumn){
    for(int i = 0; i < rows; i++){
      divideCells(i, firstColumn, i, secondColumn, i, targetColumn);
    }
  }
  
  //Inserts a row of empty cells at index from parameter.
  public void insertRow(int row){
    Node iterator;
    Node newNode;
    //Special case required for inserting at 0th index
    if(row == 0){
      //Traverse to bottom of grid, which circularly links to top of grid.
      iterator = traverse(rows-1, 0);
      newNode = new Node();
      //Because creating new 0th index row, set head to first
      //element in new row.
      head = newNode;
    }
    //If inserting a row anywhere other than 0th index,
    //traverse to row above new row's spot
    else{
      iterator = traverse(row-1, 0);
      newNode = new Node();
    }
    Node current = iterator;
    for(int i = 0; i < columns - 1; i++){
      //For each column, set the new Node's down pointer
      //to row above its down pointer
      newNode.setDown(current.getDown());
      //Set row above new row's down pointer to the new row
      current.setDown(newNode);
      newNode.setRight(new Node());
      newNode = newNode.getRight();
      current = current.getRight();
    }
    newNode.setDown(current.getDown());
    current.setDown(newNode);
    newNode.setRight(iterator.getDown());
    //Increment instance variable to reflect newly added row
    rows++;
  }
  
  //Inserts an empty column at index from parameter
  public void insertColumn(int col){
    Node iterator;
    Node newNode;
    //Special case required for inserting at 0th index
    if(col == 0){
      //Traverse to rightmost column of grid, 
      //which circularly links to leftmost column of grid.
      iterator = traverse(0, columns - 1);
      newNode = new Node();
      //Because creating new 0th index columns, set head to first
      //element in new column.
      head = newNode;
    }
    //If inserting a columns anywhere other than 0th index,
    //traverse to column left of new column's spot
    else{
      iterator = traverse(0, col-1);
      newNode = new Node();
    }
    Node current = iterator;
    for(int i = 0; i < rows - 1; i++){
      //For each row, set the new Node's right pointer
      //to column left of its right pointer
      newNode.setRight(current.getRight());
      //Set column left of new column's right pointer to the new column
      current.setRight(newNode);
      newNode.setDown(new Node());
      newNode = newNode.getDown();
      current = current.getDown();
    }
    newNode.setRight(current.getRight());
    current.setRight(newNode);
    newNode.setDown(iterator.getRight());
    //Increment instance variable to reflect newly added column
    columns++;
  }
  
  //Deletes row at index from parameter, and links surrounding rows
  public void deleteRow(int row){
    Node iterator;
    //Special case for deleting 0th index row
    if(row == 0)
      //traverse to bottom of grid, which links to top of grid
      iterator = traverse(rows - 1, 0);
    else
      //traverse to row above row-to-be-deleted
      iterator = traverse(row - 1, 0);
    for(int i = 0; i < columns; i++){
      //for each node in row above row-to-be-deleted
      //set down pointer to row-to-be-deleted's down pointer
      iterator.setDown(iterator.getDown().getDown());
      iterator = iterator.getRight();
    }
    //If 0th index row has been deleted, set head to first node
    //in new 0th index row
    if(row == 0)
      head = iterator.getDown();
    rows--;
  }
  
  //Deletes column at index from parameter, and links surrounding columns
  public void deleteColumn(int col){
    Node iterator;
    //Special case for deleting 0th index row
    if(col == 0)
      //traverse to rightmost column of grid, which links to leftmost column of grid
      iterator = traverse(0, columns - 1);
    else
      //traverse to column left of column-to-be-deleted
      iterator = traverse(0, col - 1);
    for(int i = 0; i < rows; i++){
      //for each node in column left of column-to-be-deleted
      //set right pointer to column-to-be-deleted's right pointer
      iterator.setRight(iterator.getRight().getRight());
      iterator = iterator.getDown();
    }
    //If 0th index column has been deleted, set head to first node
    //in new 0th index column
    if(col == 0)
      head = iterator.getRight();
    columns--;
  }
  
  //Displays grid with row and column labels.
  public void display(){
    System.out.print("     ");
    //Print column labels
    for(int i = 0; i < columns; i++){
      System.out.printf("     col " + i);
    }
    System.out.println();
    Node temp = head;
    for(int i = 0; i < rows; i++){
      //Print row labels
      System.out.print("row " + i);
      for(int j = 0; j < columns; j++){
        //Print value of each Node in row
        System.out.print(temp.getValue());
        temp = temp.getRight();
      }
      temp = temp.getDown();
      System.out.println();
    }
  }
  
  public Node getHead(){
    return this.head;
  }
  
  public int getRows(){
    return this.rows;
  }
  
  public int getColumns(){
    return this.columns;
  }
}

