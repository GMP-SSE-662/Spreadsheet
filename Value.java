public class Value {

    public static final String STRING_TAG = "STRING";
    public static final String DOUBLE_TAG = "DBL";
    public static final String INVALID_TAG = "INVALID";

  private double _dVal;
  private String _sVal;
  private String _tag;

  public Value() {
    setDVal(0.0);
    setSVal(null);
    setTag(STRING_TAG);
  }

  //Constructor
  public Value(String valueInput){
    if(valueIsString(valueInput)){
      setSVal(getValueAfterStringDelimiter(valueInput));
      setTag(STRING_TAG);
    }
    else{
      setDVal(Double.parseDouble(valueInput));
      setTag(DOUBLE_TAG);
    }
  }

  String getValueAfterStringDelimiter(String value)
  {
      return value.substring(1);
  }

  //Checks if the first character of input is a quote,
  //returns true or false. Helper method for Value constructor.
  public boolean valueIsString(String s){
    return s.substring(0,1).equals("\"");
  }

  //Method to add two Values together
  public Value plus(Value v){
    Value newValue = new Value();
    //Check to ensure both Values have _tag == DBL
    if(this.getTag().equals(DOUBLE_TAG) && v.getTag().equals(DOUBLE_TAG)){
      newValue.setDVal(add(this.getDVal(), v.getDVal()));
      newValue.setTag(DOUBLE_TAG);
    }
    //If both Values don't have DBL _tags, don't do the addition
    //and set the new Value's _tag to INVALID
    else
      newValue.setTag(INVALID_TAG);
    return newValue;
  }

  Double add(Double firstNum, Double secondNum)
  {
      return firstNum + secondNum;
  }

  //Method to subtract one Value from another. Same logic as plus()
  public Value minus(Value v){
    Value newValue = new Value();
    if(this.getTag().equals(DOUBLE_TAG) && v.getTag().equals(DOUBLE_TAG)){
      newValue.setDVal(subtractFirstBySecond(this.getDVal(), v.getDVal()));
      newValue.setTag(DOUBLE_TAG);
    }
    else
      newValue.setTag(INVALID_TAG);
    return newValue;
  }

  Double subtractFirstBySecond(Double firstNum, Double secondNum)
  {
      return firstNum - secondNum;
  }

  //Method to multiply two Values together. Same logic as plus()
  public Value multipliedBy(Value v){
    Value newValue = new Value();
    if(this.getTag().equals(DOUBLE_TAG) && v.getTag().equals(DOUBLE_TAG)){
      newValue.setDVal(multiply(this.getDVal(), v.getDVal()));
      newValue.setTag(DOUBLE_TAG);
    }
    else
      newValue.setTag(INVALID_TAG);
    return newValue;
  }

  Double multiply(Double firstNum, Double secondNum)
  {
      return firstNum * secondNum;
  }

  //Method to divide one Value from another.
  public Value dividedBy(Value v){
    Value newValue = new Value();
    //Check both _tags are DBL and divisor is not zero
    if(this.getTag().equals(DOUBLE_TAG) && v.getTag().equals(DOUBLE_TAG) && !isDivideByZero(v.getDVal())){
      newValue.setDVal(divideFirstBySecond(this.getDVal(), v.getDVal()));
      newValue.setTag(DOUBLE_TAG);
    }
    else
      newValue.setTag(INVALID_TAG);
    return newValue;
  }

  boolean isDivideByZero(Double value)
  {
      boolean isZero = value == 0;
      if (isZero)
          System.out.println("Attempted division by zero");
      return isZero;
  }

  Double divideFirstBySecond(Double firstNum, Double secondNum)
  {
      return firstNum / secondNum;
  }

  // toString that checks _tag, then truncates strings to 10 characters,
  // and doubles to 10 digits with 4 decimal places.
  public String toString(){
    if(this.getTag().equals(STRING_TAG))
      return String.format("%10s", this.getSVal());
    else
      return String.format("%10.4f", this.getDVal());
  }

  public String getSVal(){
    if(_sVal == null)
      return "";
    return _sVal;
  }

  void setSVal(String sVal)
  {
      _sVal = sVal;
  }

  public Double getDVal(){
    return _dVal;
  }

  void setDVal(Double dVal)
  {
      _dVal = dVal;
  }

  public String getTag(){
    return _tag;
  }

  void setTag(String tag)
  {
      _tag = tag;
  }
}