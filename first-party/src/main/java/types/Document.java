/**
 * Autogenerated by Avro
 * 
 * DO NOT EDIT DIRECTLY
 */
package types;  
@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class Document extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"Document\",\"namespace\":\"types\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"targets\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Target\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"origin\",\"type\":{\"type\":\"record\",\"name\":\"Coordinates\",\"fields\":[{\"name\":\"x\",\"type\":\"float\"},{\"name\":\"y\",\"type\":\"float\"}]}},{\"name\":\"radius\",\"type\":\"int\"}]}}},{\"name\":\"sessions\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Session\",\"fields\":[{\"name\":\"id\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"clicks\",\"type\":{\"type\":\"array\",\"items\":\"Coordinates\"}}]}}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String id;
  @Deprecated public java.util.List<types.Target> targets;
  @Deprecated public java.util.List<types.Session> sessions;

  /**
   * Default constructor.
   */
  public Document() {}

  /**
   * All-args constructor.
   */
  public Document(java.lang.String id, java.util.List<types.Target> targets, java.util.List<types.Session> sessions) {
    this.id = id;
    this.targets = targets;
    this.sessions = sessions;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call. 
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return targets;
    case 2: return sessions;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }
  // Used by DatumReader.  Applications should not call. 
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.String)value$; break;
    case 1: targets = (java.util.List<types.Target>)value$; break;
    case 2: sessions = (java.util.List<types.Session>)value$; break;
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
   * Gets the value of the 'targets' field.
   */
  public java.util.List<types.Target> getTargets() {
    return targets;
  }

  /**
   * Sets the value of the 'targets' field.
   * @param value the value to set.
   */
  public void setTargets(java.util.List<types.Target> value) {
    this.targets = value;
  }

  /**
   * Gets the value of the 'sessions' field.
   */
  public java.util.List<types.Session> getSessions() {
    return sessions;
  }

  /**
   * Sets the value of the 'sessions' field.
   * @param value the value to set.
   */
  public void setSessions(java.util.List<types.Session> value) {
    this.sessions = value;
  }

  /** Creates a new Document RecordBuilder */
  public static types.Document.Builder newBuilder() {
    return new types.Document.Builder();
  }
  
  /** Creates a new Document RecordBuilder by copying an existing Builder */
  public static types.Document.Builder newBuilder(types.Document.Builder other) {
    return new types.Document.Builder(other);
  }
  
  /** Creates a new Document RecordBuilder by copying an existing Document instance */
  public static types.Document.Builder newBuilder(types.Document other) {
    return new types.Document.Builder(other);
  }
  
  /**
   * RecordBuilder for Document instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<Document>
    implements org.apache.avro.data.RecordBuilder<Document> {

    private java.lang.String id;
    private java.util.List<types.Target> targets;
    private java.util.List<types.Session> sessions;

    /** Creates a new Builder */
    private Builder() {
      super(types.Document.SCHEMA$);
    }
    
    /** Creates a Builder by copying an existing Builder */
    private Builder(types.Document.Builder other) {
      super(other);
    }
    
    /** Creates a Builder by copying an existing Document instance */
    private Builder(types.Document other) {
            super(types.Document.SCHEMA$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.targets)) {
        this.targets = data().deepCopy(fields()[1].schema(), other.targets);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.sessions)) {
        this.sessions = data().deepCopy(fields()[2].schema(), other.sessions);
        fieldSetFlags()[2] = true;
      }
    }

    /** Gets the value of the 'id' field */
    public java.lang.String getId() {
      return id;
    }
    
    /** Sets the value of the 'id' field */
    public types.Document.Builder setId(java.lang.String value) {
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
    public types.Document.Builder clearId() {
      id = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /** Gets the value of the 'targets' field */
    public java.util.List<types.Target> getTargets() {
      return targets;
    }
    
    /** Sets the value of the 'targets' field */
    public types.Document.Builder setTargets(java.util.List<types.Target> value) {
      validate(fields()[1], value);
      this.targets = value;
      fieldSetFlags()[1] = true;
      return this; 
    }
    
    /** Checks whether the 'targets' field has been set */
    public boolean hasTargets() {
      return fieldSetFlags()[1];
    }
    
    /** Clears the value of the 'targets' field */
    public types.Document.Builder clearTargets() {
      targets = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /** Gets the value of the 'sessions' field */
    public java.util.List<types.Session> getSessions() {
      return sessions;
    }
    
    /** Sets the value of the 'sessions' field */
    public types.Document.Builder setSessions(java.util.List<types.Session> value) {
      validate(fields()[2], value);
      this.sessions = value;
      fieldSetFlags()[2] = true;
      return this; 
    }
    
    /** Checks whether the 'sessions' field has been set */
    public boolean hasSessions() {
      return fieldSetFlags()[2];
    }
    
    /** Clears the value of the 'sessions' field */
    public types.Document.Builder clearSessions() {
      sessions = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    @Override
    public Document build() {
      try {
        Document record = new Document();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.String) defaultValue(fields()[0]);
        record.targets = fieldSetFlags()[1] ? this.targets : (java.util.List<types.Target>) defaultValue(fields()[1]);
        record.sessions = fieldSetFlags()[2] ? this.sessions : (java.util.List<types.Session>) defaultValue(fields()[2]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }
}
