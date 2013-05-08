/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package types;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Coordinates extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Coordinates\",\"namespace\":\"types\",\"fields\":[{\"name\":\"x\",\"type\":\"float\"},{\"name\":\"y\",\"type\":\"float\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public float x;
  @Deprecated public float y;

  /**
   * Default constructor.
   */
  public Coordinates() {}

  /**
   * All-args constructor.
   */
  public Coordinates(java.lang.Float x, java.lang.Float y) {
    this.x = x;
    this.y = y;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return x;
    case 1: return y;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: x = (java.lang.Float)value$; break;
    case 1: y = (java.lang.Float)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'x' field.
   */
  public java.lang.Float getX() {
    return x;
  }

  /**
   * Sets the value of the 'x' field.
   * @param value the value to set.
   */
  public void setX(java.lang.Float value) {
    this.x = value;
  }

  /**
   * Gets the value of the 'y' field.
   */
  public java.lang.Float getY() {
    return y;
  }

  /**
   * Sets the value of the 'y' field.
   * @param value the value to set.
   */
  public void setY(java.lang.Float value) {
    this.y = value;
  }

  /** Creates a new Coordinates RecordBuilder */
  public static types.Coordinates.Builder newBuilder() {
    return new types.Coordinates.Builder();
  }
  
  /** Creates a new Coordinates RecordBuilder by copying an existing Builder */
  public static types.Coordinates.Builder newBuilder(types.Coordinates.Builder other) {
    return new types.Coordinates.Builder(other);
  }
  
  /** Creates a new Coordinates RecordBuilder by copying an existing Coordinates instance */
  public static types.Coordinates.Builder newBuilder(types.Coordinates other) {
    return new types.Coordinates.Builder(other);
  }
  
  /**
   * RecordBuilder for Coordinates instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Coordinates>
    implements org.apache.avro.data.RecordBuilder<Coordinates> {

    private float x;
    private float y;

    /** Creates a new Builder */
    private Builder() {
      super(types.Coordinates.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(types.Coordinates.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Coordinates instance */
    private Builder(types.Coordinates other) {
            super(types.Coordinates.SCHEMA$);
      if (isValidValue(fields()[0], other.x)) {
        this.x = data().deepCopy(fields()[0].schema(), other.x);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.y)) {
        this.y = data().deepCopy(fields()[1].schema(), other.y);
        fieldSetFlags()[1] = true;
      }
    }

    /** Gets the value of the 'x' field */
    public java.lang.Float getX() {
      return x;
    }
    
    /** Sets the value of the 'x' field */
    public types.Coordinates.Builder setX(float value) {
      validate(fields()[0], value);
      this.x = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'x' field has been set */
    public boolean hasX() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'x' field */
    public types.Coordinates.Builder clearX() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'y' field */
    public java.lang.Float getY() {
      return y;
    }
    
    /** Sets the value of the 'y' field */
    public types.Coordinates.Builder setY(float value) {
      validate(fields()[1], value);
      this.y = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'y' field has been set */
    public boolean hasY() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'y' field */
    public types.Coordinates.Builder clearY() {
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    public Coordinates build() {
      try {
        Coordinates record = new Coordinates();
        record.x = fieldSetFlags()[0] ? this.x : (java.lang.Float) defaultValue(fields()[0]);
        record.y = fieldSetFlags()[1] ? this.y : (java.lang.Float) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
