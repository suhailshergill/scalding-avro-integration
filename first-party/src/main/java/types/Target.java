/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package types;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Target extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Target\",\"namespace\":\"types\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"origin\",\"type\":{\"type\":\"record\",\"name\":\"Coordinates\",\"fields\":[{\"name\":\"x\",\"type\":\"float\"},{\"name\":\"y\",\"type\":\"float\"}]}},{\"name\":\"radius\",\"type\":\"int\"},{\"name\":\"clicks\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"ClickHistory\",\"fields\":[{\"name\":\"session_id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"pixels\",\"type\":{\"type\":\"array\",\"items\":\"Coordinates\"}}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String id;
  @Deprecated public types.Coordinates origin;
  @Deprecated public int radius;
  @Deprecated public java.util.List<types.ClickHistory> clicks;

  /**
   * Default constructor.
   */
  public Target() {}

  /**
   * All-args constructor.
   */
  public Target(java.lang.String id, types.Coordinates origin, java.lang.Integer radius, java.util.List<types.ClickHistory> clicks) {
    this.id = id;
    this.origin = origin;
    this.radius = radius;
    this.clicks = clicks;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return origin;
    case 2: return radius;
    case 3: return clicks;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.String)value$; break;
    case 1: origin = (types.Coordinates)value$; break;
    case 2: radius = (java.lang.Integer)value$; break;
    case 3: clicks = (java.util.List<types.ClickHistory>)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'id' field.
   */
  public java.lang.String getId() {
    return id;
  }

  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(java.lang.String value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'origin' field.
   */
  public types.Coordinates getOrigin() {
    return origin;
  }

  /**
   * Sets the value of the 'origin' field.
   * @param value the value to set.
   */
  public void setOrigin(types.Coordinates value) {
    this.origin = value;
  }

  /**
   * Gets the value of the 'radius' field.
   */
  public java.lang.Integer getRadius() {
    return radius;
  }

  /**
   * Sets the value of the 'radius' field.
   * @param value the value to set.
   */
  public void setRadius(java.lang.Integer value) {
    this.radius = value;
  }

  /**
   * Gets the value of the 'clicks' field.
   */
  public java.util.List<types.ClickHistory> getClicks() {
    return clicks;
  }

  /**
   * Sets the value of the 'clicks' field.
   * @param value the value to set.
   */
  public void setClicks(java.util.List<types.ClickHistory> value) {
    this.clicks = value;
  }

  /** Creates a new Target RecordBuilder */
  public static types.Target.Builder newBuilder() {
    return new types.Target.Builder();
  }
  
  /** Creates a new Target RecordBuilder by copying an existing Builder */
  public static types.Target.Builder newBuilder(types.Target.Builder other) {
    return new types.Target.Builder(other);
  }
  
  /** Creates a new Target RecordBuilder by copying an existing Target instance */
  public static types.Target.Builder newBuilder(types.Target other) {
    return new types.Target.Builder(other);
  }
  
  /**
   * RecordBuilder for Target instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Target>
    implements org.apache.avro.data.RecordBuilder<Target> {

    private java.lang.String id;
    private types.Coordinates origin;
    private int radius;
    private java.util.List<types.ClickHistory> clicks;

    /** Creates a new Builder */
    private Builder() {
      super(types.Target.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(types.Target.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Target instance */
    private Builder(types.Target other) {
            super(types.Target.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.origin)) {
        this.origin = data().deepCopy(fields()[1].schema(), other.origin);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.radius)) {
        this.radius = data().deepCopy(fields()[2].schema(), other.radius);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.clicks)) {
        this.clicks = data().deepCopy(fields()[3].schema(), other.clicks);
        fieldSetFlags()[3] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.String getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public types.Target.Builder setId(java.lang.String value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this; 
    }
    
    /** Checks whether the 'id' field has been set */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }
    
    /** Clears the value of the 'id' field */
    public types.Target.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'origin' field */
    public types.Coordinates getOrigin() {
      return origin;
    }
    
    /** Sets the value of the 'origin' field */
    public types.Target.Builder setOrigin(types.Coordinates value) {
      validate(fields()[1], value);
      this.origin = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'origin' field has been set */
    public boolean hasOrigin() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'origin' field */
    public types.Target.Builder clearOrigin() {
      origin = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'radius' field */
    public java.lang.Integer getRadius() {
      return radius;
    }
    
    /** Sets the value of the 'radius' field */
    public types.Target.Builder setRadius(int value) {
      validate(fields()[2], value);
      this.radius = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'radius' field has been set */
    public boolean hasRadius() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'radius' field */
    public types.Target.Builder clearRadius() {
      fieldSetFlags()[2] = false;
      return this;
    }

    /** Gets the value of the 'clicks' field */
    public java.util.List<types.ClickHistory> getClicks() {
      return clicks;
    }
    
    /** Sets the value of the 'clicks' field */
    public types.Target.Builder setClicks(java.util.List<types.ClickHistory> value) {
      validate(fields()[3], value);
      this.clicks = value;
      fieldSetFlags()[3] = true;
      return this; 
    }
    
    /** Checks whether the 'clicks' field has been set */
    public boolean hasClicks() {
      return fieldSetFlags()[3];
    }
    
    /** Clears the value of the 'clicks' field */
    public types.Target.Builder clearClicks() {
      clicks = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    @Override
    public Target build() {
      try {
        Target record = new Target();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.origin = fieldSetFlags()[1] ? this.origin : (types.Coordinates) defaultValue(fields()[1]);
        record.radius = fieldSetFlags()[2] ? this.radius : (java.lang.Integer) defaultValue(fields()[2]);
        record.clicks = fieldSetFlags()[3] ? this.clicks : (java.util.List<types.ClickHistory>) defaultValue(fields()[3]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}