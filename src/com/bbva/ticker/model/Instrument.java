// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pricing.proto

package com.bbva.ticker.model;

/**
 * Protobuf type {@code Instrument}
 */
public  final class Instrument extends
    com.google.protobuf.GeneratedMessage
    implements InstrumentOrBuilder {
  // Use Instrument.newBuilder() to construct.
  private Instrument(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
    this.unknownFields = builder.getUnknownFields();
  }
  private Instrument(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

  private static final Instrument defaultInstance;
  public static Instrument getDefaultInstance() {
    return defaultInstance;
  }

  public Instrument getDefaultInstanceForType() {
    return defaultInstance;
  }

  private final com.google.protobuf.UnknownFieldSet unknownFields;
  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
      getUnknownFields() {
    return this.unknownFields;
  }
  private Instrument(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    initFields();
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!parseUnknownField(input, unknownFields,
                                   extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
          case 8: {
            bitField0_ |= 0x00000001;
            instrumentId_ = input.readInt32();
            break;
          }
          case 18: {
            bitField0_ |= 0x00000002;
            name_ = input.readBytes();
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e.getMessage()).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.bbva.ticker.model.TickDataProtos.internal_static_Instrument_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bbva.ticker.model.TickDataProtos.internal_static_Instrument_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.bbva.ticker.model.Instrument.class, com.bbva.ticker.model.Instrument.Builder.class);
  }

  public static com.google.protobuf.Parser<Instrument> PARSER =
      new com.google.protobuf.AbstractParser<Instrument>() {
    public Instrument parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new Instrument(input, extensionRegistry);
    }
  };

  @java.lang.Override
  public com.google.protobuf.Parser<Instrument> getParserForType() {
    return PARSER;
  }

  private int bitField0_;
  // required int32 instrumentId = 1;
  public static final int INSTRUMENTID_FIELD_NUMBER = 1;
  private int instrumentId_;
  /**
   * <code>required int32 instrumentId = 1;</code>
   */
  public boolean hasInstrumentId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 instrumentId = 1;</code>
   */
  public int getInstrumentId() {
    return instrumentId_;
  }

  // required string name = 2;
  public static final int NAME_FIELD_NUMBER = 2;
  private java.lang.Object name_;
  /**
   * <code>required string name = 2;</code>
   */
  public boolean hasName() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required string name = 2;</code>
   */
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      if (bs.isValidUtf8()) {
        name_ = s;
      }
      return s;
    }
  }
  /**
   * <code>required string name = 2;</code>
   */
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private void initFields() {
    instrumentId_ = 0;
    name_ = "";
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;

    if (!hasInstrumentId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasName()) {
      memoizedIsInitialized = 0;
      return false;
    }
    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    getSerializedSize();
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      output.writeInt32(1, instrumentId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeBytes(2, getNameBytes());
    }
    getUnknownFields().writeTo(output);
  }

  private int memoizedSerializedSize = -1;
  public int getSerializedSize() {
    int size = memoizedSerializedSize;
    if (size != -1) return size;

    size = 0;
    if (((bitField0_ & 0x00000001) == 0x00000001)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, instrumentId_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeBytesSize(2, getNameBytes());
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSerializedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  protected java.lang.Object writeReplace()
      throws java.io.ObjectStreamException {
    return super.writeReplace();
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.bbva.ticker.model.Instrument)) {
      return super.equals(obj);
    }
    com.bbva.ticker.model.Instrument other = (com.bbva.ticker.model.Instrument) obj;

    boolean result = true;
    result = result && (hasInstrumentId() == other.hasInstrumentId());
    if (hasInstrumentId()) {
      result = result && (getInstrumentId()
          == other.getInstrumentId());
    }
    result = result && (hasName() == other.hasName());
    if (hasName()) {
      result = result && getName()
          .equals(other.getName());
    }
    result = result &&
        getUnknownFields().equals(other.getUnknownFields());
    return result;
  }

  private int memoizedHashCode = 0;
  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptorForType().hashCode();
    if (hasInstrumentId()) {
      hash = (37 * hash) + INSTRUMENTID_FIELD_NUMBER;
      hash = (53 * hash) + getInstrumentId();
    }
    if (hasName()) {
      hash = (37 * hash) + NAME_FIELD_NUMBER;
      hash = (53 * hash) + getName().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.bbva.ticker.model.Instrument parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bbva.ticker.model.Instrument parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bbva.ticker.model.Instrument parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bbva.ticker.model.Instrument parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bbva.ticker.model.Instrument parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bbva.ticker.model.Instrument parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.bbva.ticker.model.Instrument parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.bbva.ticker.model.Instrument parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.bbva.ticker.model.Instrument parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bbva.ticker.model.Instrument parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(com.bbva.ticker.model.Instrument prototype) {
    return newBuilder().mergeFrom(prototype);
  }
  public Builder toBuilder() { return newBuilder(this); }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessage.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code Instrument}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder>
     implements com.bbva.ticker.model.InstrumentOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bbva.ticker.model.TickDataProtos.internal_static_Instrument_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bbva.ticker.model.TickDataProtos.internal_static_Instrument_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.bbva.ticker.model.Instrument.class, com.bbva.ticker.model.Instrument.Builder.class);
    }

    // Construct using com.bbva.ticker.model.Instrument.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessage.alwaysUseFieldBuilders) {
      }
    }
    private static Builder create() {
      return new Builder();
    }

    public Builder clear() {
      super.clear();
      instrumentId_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      name_ = "";
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bbva.ticker.model.TickDataProtos.internal_static_Instrument_descriptor;
    }

    public com.bbva.ticker.model.Instrument getDefaultInstanceForType() {
      return com.bbva.ticker.model.Instrument.getDefaultInstance();
    }

    public com.bbva.ticker.model.Instrument build() {
      com.bbva.ticker.model.Instrument result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.bbva.ticker.model.Instrument buildPartial() {
      com.bbva.ticker.model.Instrument result = new com.bbva.ticker.model.Instrument(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.instrumentId_ = instrumentId_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      result.name_ = name_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.bbva.ticker.model.Instrument) {
        return mergeFrom((com.bbva.ticker.model.Instrument)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.bbva.ticker.model.Instrument other) {
      if (other == com.bbva.ticker.model.Instrument.getDefaultInstance()) return this;
      if (other.hasInstrumentId()) {
        setInstrumentId(other.getInstrumentId());
      }
      if (other.hasName()) {
        bitField0_ |= 0x00000002;
        name_ = other.name_;
        onChanged();
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }

    public final boolean isInitialized() {
      if (!hasInstrumentId()) {
        
        return false;
      }
      if (!hasName()) {
        
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.bbva.ticker.model.Instrument parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.bbva.ticker.model.Instrument) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    // required int32 instrumentId = 1;
    private int instrumentId_ ;
    /**
     * <code>required int32 instrumentId = 1;</code>
     */
    public boolean hasInstrumentId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 instrumentId = 1;</code>
     */
    public int getInstrumentId() {
      return instrumentId_;
    }
    /**
     * <code>required int32 instrumentId = 1;</code>
     */
    public Builder setInstrumentId(int value) {
      bitField0_ |= 0x00000001;
      instrumentId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 instrumentId = 1;</code>
     */
    public Builder clearInstrumentId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      instrumentId_ = 0;
      onChanged();
      return this;
    }

    // required string name = 2;
    private java.lang.Object name_ = "";
    /**
     * <code>required string name = 2;</code>
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required string name = 2;</code>
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        java.lang.String s = ((com.google.protobuf.ByteString) ref)
            .toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>required string name = 2;</code>
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>required string name = 2;</code>
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required string name = 2;</code>
     */
    public Builder clearName() {
      bitField0_ = (bitField0_ & ~0x00000002);
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>required string name = 2;</code>
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000002;
      name_ = value;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:Instrument)
  }

  static {
    defaultInstance = new Instrument(true);
    defaultInstance.initFields();
  }

  // @@protoc_insertion_point(class_scope:Instrument)
}

