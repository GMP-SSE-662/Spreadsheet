
public class Value {

  public static final String STRING_TAG = "STRING";
  public static final String DOUBLE_TAG = "DBL";
  public static final String INVALID_TAG = "INVALID";

  private double _dVal;
  private String _sVal;
  private Tag _tag;

  /**
   * Constructs default value tagged string.
   */
  public Value() {
    setDVal(0.0);
    setSVal(null);
  }

  /**
   * Creates value of given input.
   * @param valueInput the type of value.
   */
  public Value(String valueInput){
    if(valueIsString(valueInput)){
      setSVal(getValueAfterStringDelimiter(valueInput));
    }
    else{
      setDVal(Double.parseDouble(valueInput));
    }
  }

  String getValueAfterStringDelimiter(String value)
  {
      return value.substring(1);
  }

  public boolean valueIsString(String s){
    return s.substring(0,1).equals("\"");
  }

  /**
   * Adds the given value to this.
   * @param v the value to be added.
   * @return the result of the addition.
   */
  public Value plus(Value v){
    Value newValue = new Value();

    if(bothValuesAreTaggedDouble(this, v)){
      newValue.setDVal(add(this.getDVal(), v.getDVal()));
    }
    else
      newValue.setInvalid();
    return newValue;
  }

  Double add(Double firstNum, Double secondNum)
  {
      return firstNum + secondNum;
  }

  /**
   * Subtracts the given value from this.
   * @param v the value to be subtracted.
   * @return the result of the subtraction.
   */
  public Value minus(Value v){
    Value newValue = new Value();

    if(bothValuesAreTaggedDouble(this, v)){
      newValue.setDVal(subtractFirstBySecond(this.getDVal(), v.getDVal()));
    }
    else
      newValue.setInvalid();
    return newValue;
  }

  Double subtractFirstBySecond(Double firstNum, Double secondNum)
  {
      return firstNum - secondNum;
  }

  /**
   * Multiplies the given value by this.
   * @param v the value to be multiplied.
   * @return the result of the multiplication.
   */
  public Value multipliedBy(Value v){
    Value newValue = new Value();

    if(bothValuesAreTaggedDouble(this, v)){
      newValue.setDVal(multiply(this.getDVal(), v.getDVal()));
    }
    else
      newValue.setInvalid();
    return newValue;
  }

  Double multiply(Double firstNum, Double secondNum)
  {
      return firstNum * secondNum;
  }

  /**
   * Divides this by the given value.
   * @param v the value to divide by.
   * @return the result of the division.
   */
  public Value dividedBy(Value v){
    Value newValue = new Value();

    if(bothValuesAreTaggedDouble(this, v) && !isDivideByZero(v.getDVal())){
      newValue.setDVal(divideFirstBySecond(this.getDVal(), v.getDVal()));
    }
    else
      newValue.setInvalid();
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

  boolean bothValuesAreTaggedDouble(Value valOne, Value valTwo)
  {
      return valOne.getTag().equals(DOUBLE_TAG) && valTwo.getTag().equals(DOUBLE_TAG);
  }

  void setInvalid()
  {
      setTag(INVALID_TAG);
  }

  /**
   * Overrides the toString method.
   * @return appropriate string representation of this value.
   */
  @Override
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

  private void setSVal(String sVal)
  {
      _sVal = sVal;
      setTag(STRING_TAG);
  }

  public Double getDVal(){
    return _dVal;
  }

  private void setDVal(Double dVal)
  {
      _dVal = dVal;
      setTag(DOUBLE_TAG);
  }

  public String getTag(){
    return _tag.getTagName();
  }

  public void setTag(String tagName)
  {
      _tag = Tag.createTag(tagName);
  }
}