/**
 * Value object for each cell (node).
 */
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

  /**
   * Gets the value after the first character of the input.
   * @param value with dummy first character.
   * @return the value after the first character of the input.
   */
  String getValueAfterStringDelimiter(String value)
  {
      return value.substring(1);
  }

  /**
   * Checks if the input value is supposed to be read as a string.
   * @param s the value to check.
   * @return true if the value begins with a quotation mark, false otherwise.
   */
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

  /**
   * Adds the two parameters together, returning the result.
   * @param firstNum to be added.
   * @param secondNum to be added.
   * @return the sum of firstNum and secondNum.
   */
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

  /**
   * Subtracts the first parameter by the second, returning the result.
   * @param firstNum to be subtracted.
   * @param secondNum to subtract by.
   * @return the result of the subtraction.
   */
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

  /**
   * Multiplies the two parameters together, returning the result.
   * @param firstNum to be multiplied.
   * @param secondNum to be multiplied.
   * @return the result of the multiplication.
   */
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

  /**
   * Checks if the input parameter is zero to avoid a divide by zero error.
   * @param value to check.
   * @return true if value == 0, false if value != 0.
   */
  boolean isDivideByZero(Double value)
  {
      boolean isZero = value == 0;
      if (isZero)
          System.out.println("Attempted division by zero");
      return isZero;
  }

  /**
   * Divides the first parameter by the second, returning the result.
   * @param firstNum to be divided.
   * @param secondNum to divide by.
   * @return the result of the division.
   */
  Double divideFirstBySecond(Double firstNum, Double secondNum)
  {
      return firstNum / secondNum;
  }

  /**
   * Checks if each of the input Values are tagged double.
   * @param valOne to check if double.
   * @param valTwo to check if double.
   * @return true if both @param's are double, false otherwise.
   */
  boolean bothValuesAreTaggedDouble(Value valOne, Value valTwo)
  {
      return valOne.getTag().equals(DOUBLE_TAG) && valTwo.getTag().equals(DOUBLE_TAG);
  }

  /**
   * Sets the tag of this value to invalid.
   */
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

  /**
   * Gets the string value of the this Value.
   * @return the string value of this Value.
   */
  public String getSVal(){
    if(_sVal == null)
      return "";
    return _sVal;
  }

  /**
   * Sets the string value of this Value.
   * @param sVal to be the new string value of this Value.
   */
  private void setSVal(String sVal)
  {
      _sVal = sVal;
      setTag(STRING_TAG);
  }

  /**
   * Gets the double value of this Value.
   * @return the double value of this Value.
   */
  public Double getDVal(){
    return _dVal;
  }

  /**
   * Sets the double value of this Value.
   * @param dVal to be the new double value of this Value.
   */
  private void setDVal(Double dVal)
  {
      _dVal = dVal;
      setTag(DOUBLE_TAG);
  }

  /**
   * Gets the Tag this Value belongs to.
   * @return the Tag this Value belongs to.
   */
  public String getTag(){
    return _tag.getTagName();
  }

  /**
   * Sets the Tag this Value belongs to.
   * @param tagName of Tag this Value will belong to.
   */
  public void setTag(String tagName)
  {
      _tag = Tag.createTag(tagName);
  }
}