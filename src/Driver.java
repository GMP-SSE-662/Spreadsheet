import java.io.*;
import java.util.Scanner;

public class Driver {
  
  //Constructs an empty 10x6 grid, and runs in a loop displaying
  //the menu of choices to the user, accepts arguments from the user,
  //and runs the correct methods.
  public static void main(String[] args){
    Tag.loadTags();
    Scanner scanner = new Scanner(System.in);
    Grid g = new Grid();
    int row,col,fromRow,fromCol,toRow,toCol,firstRow,
      firstCol,secondRow,secondCol,targetRow,targetCol;
    String val;
    Value value;
    String menu = "Operations\n" +
      "  display           dis         assign cell       as\n"+
      "  fill              f           number            n\n"+
      "  add cells         a           subtract cells    s\n"+
      "  multiply cells    m           divide cells      d\n"+
      "  add rows          ar          subtract rows     sr\n"+
      "  multiply rows     mr          divide rows       dr\n"+
      "  add columns       ac          subtract columns  sc\n"+
      "  multiply columns  mc          divide columns    dc\n"+
      "  insert row        ir          insert column     ic\n"+
      "  delete row        delr        delete column     delc\n"+
      "  quit              q\n";
    
    while(true){
      System.out.print("\n" + menu);
      //Take input from user, and use input to determine which 
      //switch case to run.
      String input = scanner.nextLine();
      //Wrap in try+catch for the possibility that the user enters
      //a non-digit for a row or column number. Other validation for
      //ensuring digits are actually in the grid is done using if-statements.
      try{
        switch(input){
          case "dis":
            g.display();
            break;
            
          case "as":
            System.out.println("row: ");
            row = Integer.parseInt(scanner.nextLine());
            System.out.println("column: ");
            col = Integer.parseInt(scanner.nextLine());
            if(row >= g.getRows() || col >= g.getColumns()){
              System.out.println("Error: Cell out of bounds");
              break;
            }
            System.out.println("with value: ");
            val = scanner.nextLine(); 
            g.assignCell(row,col,val);
            break;
            
          case "f":
            System.out.println("from row: ");
            fromRow = Integer.parseInt(scanner.nextLine());
            System.out.println("from column: ");
            fromCol = Integer.parseInt(scanner.nextLine());
            System.out.println("to row: ");
            toRow = Integer.parseInt(scanner.nextLine());
            System.out.println("to column: ");
            toCol = Integer.parseInt(scanner.nextLine());
            if(fromRow >= g.getRows() || fromCol >= g.getColumns() ||
               toRow >= g.getRows() || toCol >= g.getColumns()){
              System.out.println("Error: cell out of bounds");
              break;
            }
            //For fill and number methods, must check that first cell is before
            //second cell in grid.
            if(toRow < fromRow || toCol < fromCol){
              System.out.println("Error: Second cell is before first cell in grid");
              break;
            }
            System.out.println("with value: ");
            value = new Value(scanner.nextLine());
            g.fill(fromRow,fromCol,toRow,toCol,value);
            break;
            
          case "n":
            System.out.println("from row: ");
            fromRow = Integer.parseInt(scanner.nextLine());
            System.out.println("from column: ");
            fromCol = Integer.parseInt(scanner.nextLine());
            System.out.println("to row: ");
            toRow = Integer.parseInt(scanner.nextLine());
            System.out.println("to column: ");
            toCol = Integer.parseInt(scanner.nextLine());
            if(fromRow >= g.getRows() || fromCol >= g.getColumns() ||
               toRow >= g.getRows() || toCol >= g.getColumns()){
              System.out.println("Error: cell out of bounds");
              break;
            }
            if(toRow < fromRow || toCol < fromCol){
              System.out.println("Error: Second cell is before first cell in grid");
              break;
            }
            g.number(fromRow,fromCol,toRow,toCol);
            break;
            
          case "a":
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstRow >= g.getRows() || firstCol >= g.getColumns() ||
               secondRow >= g.getRows() || secondCol >= g.getColumns() ||
               targetRow >= g.getRows() || targetCol >= g.getColumns()){
              System.out.println("Error: cell out of bounds");
              break;
            }
            g.addCells(firstRow,firstCol,secondRow,secondCol,targetRow,targetCol);
            break;
            
          case "s":
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstRow >= g.getRows() || firstCol >= g.getColumns() ||
               secondRow >= g.getRows() || secondCol >= g.getColumns() ||
               targetRow >= g.getRows() || targetCol >= g.getColumns()){
              System.out.println("Error: cell out of bounds");
              break;
            }
            g.subtractCells(firstRow,firstCol,secondRow,secondCol,targetRow,targetCol);
            break;
            
          case "m":
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstRow >= g.getRows() || firstCol >= g.getColumns() ||
               secondRow >= g.getRows() || secondCol >= g.getColumns() ||
               targetRow >= g.getRows() || targetCol >= g.getColumns()){
              System.out.println("Error: cell out of bounds");
              break;
            }
            g.multiplyCells(firstRow,firstCol,secondRow,secondCol,targetRow,targetCol);
            break;
            
          case "d":
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstRow >= g.getRows() || firstCol >= g.getColumns() ||
               secondRow >= g.getRows() || secondCol >= g.getColumns() ||
               targetRow >= g.getRows() || targetCol >= g.getColumns()){
              System.out.println("Error: cell out of bounds");
              break;
            }
            g.divideCells(firstRow,firstCol,secondRow,secondCol,targetRow,targetCol);
            break;
            
          case "ar":
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());         
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());  
            if(firstRow >= g.getRows() || secondRow >= g.getRows() || targetRow >= g.getRows()){
              System.out.println("Error: Row out of bounds");
              break;
            }
            g.addRows(firstRow,secondRow,targetRow);
            break;
            
          case "sr":
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());         
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());  
            if(firstRow >= g.getRows() || secondRow >= g.getRows() || targetRow >= g.getRows()){
              System.out.println("Error: Row out of bounds");
              break;
            }
            g.subtractRows(firstRow,secondRow,targetRow);
            break;
            
          case "mr":
            
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());         
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());  
            if(firstRow >= g.getRows() || secondRow >= g.getRows() || targetRow >= g.getRows()){
              System.out.println("Error: Row out of bounds");
              break;
            }
            g.multiplyRows(firstRow,secondRow,targetRow);
            break;
            
          case "dr":
            System.out.println("first row: ");
            firstRow = Integer.parseInt(scanner.nextLine());
            System.out.println("second row: ");
            secondRow = Integer.parseInt(scanner.nextLine());         
            System.out.println("target row: ");
            targetRow = Integer.parseInt(scanner.nextLine());  
            if(firstRow >= g.getRows() || secondRow >= g.getRows() || targetRow >= g.getRows()){
              System.out.println("Error: Row out of bounds");
              break;
            }
            g.divideRows(firstRow,secondRow,targetRow);
            break;
            
          case "ac":
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstCol >= g.getColumns() || secondCol >= g.getColumns() || targetCol >= g.getColumns()){
              System.out.println("Error: Column out of bounds");
              break;
            }
            g.addColumns(firstCol,secondCol,targetCol);
            break;
            
          case "sc":
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstCol >= g.getColumns() || secondCol >= g.getColumns() || targetCol >= g.getColumns()){
              System.out.println("Error: Column out of bounds");
              break;
            }
            g.subtractColumns(firstCol,secondCol,targetCol);
            break;
            
          case "mc":
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstCol >= g.getColumns() || secondCol >= g.getColumns() || targetCol >= g.getColumns()){
              System.out.println("Error: Column out of bounds");
              break;
            }
            g.multiplyColumns(firstCol,secondCol,targetCol);
            break;
            
          case "dc":
            System.out.println("first column: ");
            firstCol = Integer.parseInt(scanner.nextLine());
            System.out.println("second column: ");
            secondCol = Integer.parseInt(scanner.nextLine());
            System.out.println("target column: ");
            targetCol = Integer.parseInt(scanner.nextLine());
            if(firstCol >= g.getColumns() || secondCol >= g.getColumns() || targetCol >= g.getColumns()){
              System.out.println("Error: Column out of bounds");
              break;
            }
            g.divideColumns(firstCol,secondCol,targetCol);
            break;
            
          case "ir":
            System.out.println("row: ");
            row = Integer.parseInt(scanner.nextLine());
            if(row > g.getRows()){
              System.out.println("Error: Row out of bounds");
              break;
            }
            g.insertRow(row);
            break;
            
          case "delr":
            System.out.println("row: ");
            row = Integer.parseInt(scanner.nextLine());
            if(row > g.getRows()){
              System.out.println("Error: Row out of bounds");
              break;
            }
            g.deleteRow(row);
            break;
            
          case "ic":
            System.out.println("column: ");
            col = Integer.parseInt(scanner.nextLine());
            if(col > g.getColumns()){
              System.out.println("Error: Column out of bounds");
              break;
            }
            g.insertColumn(col);
            break;
            
          case "delc":
            System.out.println("column: ");
            col = Integer.parseInt(scanner.nextLine());
            if(col > g.getColumns()){
              System.out.println("Error: Column out of bounds");
              break;
            }
            g.deleteColumn(col);
            break;
            
          case "q":
            break;
            
          default:
            System.out.println("Error: Please enter one of the commands in the menu");
        }
      }
      catch(NumberFormatException e){
        System.out.println("Error: Not a number");
      }
      if(input.equals("q"))
        break;
    }
  }
}


