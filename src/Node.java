public class Node {
  
  private Value value;
  private Node right;
  private Node down;
  
  public Node(){
    this.value = new Value();
    this.right = null;
    this.down = null;
  }
  
  public Node(Value v){
    this.value = v;
  }
  
  public Value getValue(){
    return this.value;
  }
  
  public Node getRight(){
    return this.right;
  }
  
  public Node getDown(){
    return this.down;
  }
  
  public void setValue(Value v){
    this.value = v;
  }
  
  public void setRight(Node n){
    this.right = n;
  }
  
  public void setDown(Node n){
    this.down = n;
  }
}
