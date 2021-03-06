// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: pricing.proto

package com.bbva.ticker.model;

/**
 * Protobuf type {@code DataHistoryRequest}
 */
public  final class DataHistoryRequest extends
    com.google.protobuf.GeneratedMessage
    implements DataHistoryRequestOrBuilder {
  // Use DataHistoryRequest.newBuilder() to construct.
  private DataHistoryRequest(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
    super(builder);
    this.unknownFields = builder.getUnknownFields();
  }
  private DataHistoryRequest(boolean noInit) { this.unknownFields = com.google.protobuf.UnknownFieldSet.getDefaultInstance(); }

  private static final DataHistoryRequest defaultInstance;
  public static DataHistoryRequest getDefaultInstance() {
    return defaultInstance;
  }

  public DataHistoryRequest getDefaultInstanceForType() {
    return defaultInstance;
  }

  private final com.google.protobuf.UnknownFieldSet unknownFields;
  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
      getUnknownFields() {
    return this.unknownFields;
  }
  private DataHistoryRequest(
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
            id_ = input.readInt32();
            break;
          }
          case 18: {
            com.bbva.ticker.model.Instrument.Builder subBuilder = null;
            if (((bitField0_ & 0x00000002) == 0x00000002)) {
              subBuilder = instrument_.toBuilder();
            }
            instrument_ = input.readMessage(com.bbva.ticker.model.Instrument.PARSER, extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(instrument_);
              instrument_ = subBuilder.buildPartial();
            }
            bitField0_ |= 0x00000002;
            break;
          }
          case 24: {
            int rawValue = input.readEnum();
            com.bbva.ticker.model.PriceDataSourceType value = com.bbva.ticker.model.PriceDataSourceType.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(3, rawValue);
            } else {
              bitField0_ |= 0x00000004;
              priceDataSourceType_ = value;
            }
            break;
          }
          case 32: {
            int rawValue = input.readEnum();
            com.bbva.ticker.model.PricingType value = com.bbva.ticker.model.PricingType.valueOf(rawValue);
            if (value == null) {
              unknownFields.mergeVarintField(4, rawValue);
            } else {
              bitField0_ |= 0x00000008;
              pricingType_ = value;
            }
            break;
          }
          case 40: {
            bitField0_ |= 0x00000010;
            count_ = input.readInt32();
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
    return com.bbva.ticker.model.TickDataProtos.internal_static_DataHistoryRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.bbva.ticker.model.TickDataProtos.internal_static_DataHistoryRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.bbva.ticker.model.DataHistoryRequest.class, com.bbva.ticker.model.DataHistoryRequest.Builder.class);
  }

  public static com.google.protobuf.Parser<DataHistoryRequest> PARSER =
      new com.google.protobuf.AbstractParser<DataHistoryRequest>() {
    public DataHistoryRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new DataHistoryRequest(input, extensionRegistry);
    }
  };

  @java.lang.Override
  public com.google.protobuf.Parser<DataHistoryRequest> getParserForType() {
    return PARSER;
  }

  private int bitField0_;
  // required int32 id = 1;
  public static final int ID_FIELD_NUMBER = 1;
  private int id_;
  /**
   * <code>required int32 id = 1;</code>
   */
  public boolean hasId() {
    return ((bitField0_ & 0x00000001) == 0x00000001);
  }
  /**
   * <code>required int32 id = 1;</code>
   */
  public int getId() {
    return id_;
  }

  // required .Instrument instrument = 2;
  public static final int INSTRUMENT_FIELD_NUMBER = 2;
  private com.bbva.ticker.model.Instrument instrument_;
  /**
   * <code>required .Instrument instrument = 2;</code>
   */
  public boolean hasInstrument() {
    return ((bitField0_ & 0x00000002) == 0x00000002);
  }
  /**
   * <code>required .Instrument instrument = 2;</code>
   */
  public com.bbva.ticker.model.Instrument getInstrument() {
    return instrument_;
  }
  /**
   * <code>required .Instrument instrument = 2;</code>
   */
  public com.bbva.ticker.model.InstrumentOrBuilder getInstrumentOrBuilder() {
    return instrument_;
  }

  // required .PriceDataSourceType priceDataSourceType = 3;
  public static final int PRICEDATASOURCETYPE_FIELD_NUMBER = 3;
  private com.bbva.ticker.model.PriceDataSourceType priceDataSourceType_;
  /**
   * <code>required .PriceDataSourceType priceDataSourceType = 3;</code>
   */
  public boolean hasPriceDataSourceType() {
    return ((bitField0_ & 0x00000004) == 0x00000004);
  }
  /**
   * <code>required .PriceDataSourceType priceDataSourceType = 3;</code>
   */
  public com.bbva.ticker.model.PriceDataSourceType getPriceDataSourceType() {
    return priceDataSourceType_;
  }

  // required .PricingType pricingType = 4;
  public static final int PRICINGTYPE_FIELD_NUMBER = 4;
  private com.bbva.ticker.model.PricingType pricingType_;
  /**
   * <code>required .PricingType pricingType = 4;</code>
   */
  public boolean hasPricingType() {
    return ((bitField0_ & 0x00000008) == 0x00000008);
  }
  /**
   * <code>required .PricingType pricingType = 4;</code>
   */
  public com.bbva.ticker.model.PricingType getPricingType() {
    return pricingType_;
  }

  // required int32 count = 5;
  public static final int COUNT_FIELD_NUMBER = 5;
  private int count_;
  /**
   * <code>required int32 count = 5;</code>
   */
  public boolean hasCount() {
    return ((bitField0_ & 0x00000010) == 0x00000010);
  }
  /**
   * <code>required int32 count = 5;</code>
   */
  public int getCount() {
    return count_;
  }

  private void initFields() {
    id_ = 0;
    instrument_ = com.bbva.ticker.model.Instrument.getDefaultInstance();
    priceDataSourceType_ = com.bbva.ticker.model.PriceDataSourceType.SOURCE1;
    pricingType_ = com.bbva.ticker.model.PricingType.TICK;
    count_ = 0;
  }
  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized != -1) return isInitialized == 1;

    if (!hasId()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasInstrument()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPriceDataSourceType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasPricingType()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!hasCount()) {
      memoizedIsInitialized = 0;
      return false;
    }
    if (!getInstrument().isInitialized()) {
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
      output.writeInt32(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      output.writeMessage(2, instrument_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      output.writeEnum(3, priceDataSourceType_.getNumber());
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      output.writeEnum(4, pricingType_.getNumber());
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      output.writeInt32(5, count_);
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
        .computeInt32Size(1, id_);
    }
    if (((bitField0_ & 0x00000002) == 0x00000002)) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, instrument_);
    }
    if (((bitField0_ & 0x00000004) == 0x00000004)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(3, priceDataSourceType_.getNumber());
    }
    if (((bitField0_ & 0x00000008) == 0x00000008)) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, pricingType_.getNumber());
    }
    if (((bitField0_ & 0x00000010) == 0x00000010)) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, count_);
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
    if (!(obj instanceof com.bbva.ticker.model.DataHistoryRequest)) {
      return super.equals(obj);
    }
    com.bbva.ticker.model.DataHistoryRequest other = (com.bbva.ticker.model.DataHistoryRequest) obj;

    boolean result = true;
    result = result && (hasId() == other.hasId());
    if (hasId()) {
      result = result && (getId()
          == other.getId());
    }
    result = result && (hasInstrument() == other.hasInstrument());
    if (hasInstrument()) {
      result = result && getInstrument()
          .equals(other.getInstrument());
    }
    result = result && (hasPriceDataSourceType() == other.hasPriceDataSourceType());
    if (hasPriceDataSourceType()) {
      result = result &&
          (getPriceDataSourceType() == other.getPriceDataSourceType());
    }
    result = result && (hasPricingType() == other.hasPricingType());
    if (hasPricingType()) {
      result = result &&
          (getPricingType() == other.getPricingType());
    }
    result = result && (hasCount() == other.hasCount());
    if (hasCount()) {
      result = result && (getCount()
          == other.getCount());
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
    if (hasId()) {
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId();
    }
    if (hasInstrument()) {
      hash = (37 * hash) + INSTRUMENT_FIELD_NUMBER;
      hash = (53 * hash) + getInstrument().hashCode();
    }
    if (hasPriceDataSourceType()) {
      hash = (37 * hash) + PRICEDATASOURCETYPE_FIELD_NUMBER;
      hash = (53 * hash) + hashEnum(getPriceDataSourceType());
    }
    if (hasPricingType()) {
      hash = (37 * hash) + PRICINGTYPE_FIELD_NUMBER;
      hash = (53 * hash) + hashEnum(getPricingType());
    }
    if (hasCount()) {
      hash = (37 * hash) + COUNT_FIELD_NUMBER;
      hash = (53 * hash) + getCount();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseDelimitedFrom(input, extensionRegistry);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return PARSER.parseFrom(input);
  }
  public static com.bbva.ticker.model.DataHistoryRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return PARSER.parseFrom(input, extensionRegistry);
  }

  public static Builder newBuilder() { return Builder.create(); }
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder(com.bbva.ticker.model.DataHistoryRequest prototype) {
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
   * Protobuf type {@code DataHistoryRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessage.Builder<Builder>
     implements com.bbva.ticker.model.DataHistoryRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.bbva.ticker.model.TickDataProtos.internal_static_DataHistoryRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.bbva.ticker.model.TickDataProtos.internal_static_DataHistoryRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.bbva.ticker.model.DataHistoryRequest.class, com.bbva.ticker.model.DataHistoryRequest.Builder.class);
    }

    // Construct using com.bbva.ticker.model.DataHistoryRequest.newBuilder()
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
        getInstrumentFieldBuilder();
      }
    }
    private static Builder create() {
      return new Builder();
    }

    public Builder clear() {
      super.clear();
      id_ = 0;
      bitField0_ = (bitField0_ & ~0x00000001);
      if (instrumentBuilder_ == null) {
        instrument_ = com.bbva.ticker.model.Instrument.getDefaultInstance();
      } else {
        instrumentBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      priceDataSourceType_ = com.bbva.ticker.model.PriceDataSourceType.SOURCE1;
      bitField0_ = (bitField0_ & ~0x00000004);
      pricingType_ = com.bbva.ticker.model.PricingType.TICK;
      bitField0_ = (bitField0_ & ~0x00000008);
      count_ = 0;
      bitField0_ = (bitField0_ & ~0x00000010);
      return this;
    }

    public Builder clone() {
      return create().mergeFrom(buildPartial());
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.bbva.ticker.model.TickDataProtos.internal_static_DataHistoryRequest_descriptor;
    }

    public com.bbva.ticker.model.DataHistoryRequest getDefaultInstanceForType() {
      return com.bbva.ticker.model.DataHistoryRequest.getDefaultInstance();
    }

    public com.bbva.ticker.model.DataHistoryRequest build() {
      com.bbva.ticker.model.DataHistoryRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public com.bbva.ticker.model.DataHistoryRequest buildPartial() {
      com.bbva.ticker.model.DataHistoryRequest result = new com.bbva.ticker.model.DataHistoryRequest(this);
      int from_bitField0_ = bitField0_;
      int to_bitField0_ = 0;
      if (((from_bitField0_ & 0x00000001) == 0x00000001)) {
        to_bitField0_ |= 0x00000001;
      }
      result.id_ = id_;
      if (((from_bitField0_ & 0x00000002) == 0x00000002)) {
        to_bitField0_ |= 0x00000002;
      }
      if (instrumentBuilder_ == null) {
        result.instrument_ = instrument_;
      } else {
        result.instrument_ = instrumentBuilder_.build();
      }
      if (((from_bitField0_ & 0x00000004) == 0x00000004)) {
        to_bitField0_ |= 0x00000004;
      }
      result.priceDataSourceType_ = priceDataSourceType_;
      if (((from_bitField0_ & 0x00000008) == 0x00000008)) {
        to_bitField0_ |= 0x00000008;
      }
      result.pricingType_ = pricingType_;
      if (((from_bitField0_ & 0x00000010) == 0x00000010)) {
        to_bitField0_ |= 0x00000010;
      }
      result.count_ = count_;
      result.bitField0_ = to_bitField0_;
      onBuilt();
      return result;
    }

    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.bbva.ticker.model.DataHistoryRequest) {
        return mergeFrom((com.bbva.ticker.model.DataHistoryRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.bbva.ticker.model.DataHistoryRequest other) {
      if (other == com.bbva.ticker.model.DataHistoryRequest.getDefaultInstance()) return this;
      if (other.hasId()) {
        setId(other.getId());
      }
      if (other.hasInstrument()) {
        mergeInstrument(other.getInstrument());
      }
      if (other.hasPriceDataSourceType()) {
        setPriceDataSourceType(other.getPriceDataSourceType());
      }
      if (other.hasPricingType()) {
        setPricingType(other.getPricingType());
      }
      if (other.hasCount()) {
        setCount(other.getCount());
      }
      this.mergeUnknownFields(other.getUnknownFields());
      return this;
    }

    public final boolean isInitialized() {
      if (!hasId()) {
        
        return false;
      }
      if (!hasInstrument()) {
        
        return false;
      }
      if (!hasPriceDataSourceType()) {
        
        return false;
      }
      if (!hasPricingType()) {
        
        return false;
      }
      if (!hasCount()) {
        
        return false;
      }
      if (!getInstrument().isInitialized()) {
        
        return false;
      }
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.bbva.ticker.model.DataHistoryRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.bbva.ticker.model.DataHistoryRequest) e.getUnfinishedMessage();
        throw e;
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    // required int32 id = 1;
    private int id_ ;
    /**
     * <code>required int32 id = 1;</code>
     */
    public boolean hasId() {
      return ((bitField0_ & 0x00000001) == 0x00000001);
    }
    /**
     * <code>required int32 id = 1;</code>
     */
    public int getId() {
      return id_;
    }
    /**
     * <code>required int32 id = 1;</code>
     */
    public Builder setId(int value) {
      bitField0_ |= 0x00000001;
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 id = 1;</code>
     */
    public Builder clearId() {
      bitField0_ = (bitField0_ & ~0x00000001);
      id_ = 0;
      onChanged();
      return this;
    }

    // required .Instrument instrument = 2;
    private com.bbva.ticker.model.Instrument instrument_ = com.bbva.ticker.model.Instrument.getDefaultInstance();
    private com.google.protobuf.SingleFieldBuilder<
        com.bbva.ticker.model.Instrument, com.bbva.ticker.model.Instrument.Builder, com.bbva.ticker.model.InstrumentOrBuilder> instrumentBuilder_;
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public boolean hasInstrument() {
      return ((bitField0_ & 0x00000002) == 0x00000002);
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public com.bbva.ticker.model.Instrument getInstrument() {
      if (instrumentBuilder_ == null) {
        return instrument_;
      } else {
        return instrumentBuilder_.getMessage();
      }
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public Builder setInstrument(com.bbva.ticker.model.Instrument value) {
      if (instrumentBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        instrument_ = value;
        onChanged();
      } else {
        instrumentBuilder_.setMessage(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public Builder setInstrument(
        com.bbva.ticker.model.Instrument.Builder builderForValue) {
      if (instrumentBuilder_ == null) {
        instrument_ = builderForValue.build();
        onChanged();
      } else {
        instrumentBuilder_.setMessage(builderForValue.build());
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public Builder mergeInstrument(com.bbva.ticker.model.Instrument value) {
      if (instrumentBuilder_ == null) {
        if (((bitField0_ & 0x00000002) == 0x00000002) &&
            instrument_ != com.bbva.ticker.model.Instrument.getDefaultInstance()) {
          instrument_ =
            com.bbva.ticker.model.Instrument.newBuilder(instrument_).mergeFrom(value).buildPartial();
        } else {
          instrument_ = value;
        }
        onChanged();
      } else {
        instrumentBuilder_.mergeFrom(value);
      }
      bitField0_ |= 0x00000002;
      return this;
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public Builder clearInstrument() {
      if (instrumentBuilder_ == null) {
        instrument_ = com.bbva.ticker.model.Instrument.getDefaultInstance();
        onChanged();
      } else {
        instrumentBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public com.bbva.ticker.model.Instrument.Builder getInstrumentBuilder() {
      bitField0_ |= 0x00000002;
      onChanged();
      return getInstrumentFieldBuilder().getBuilder();
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    public com.bbva.ticker.model.InstrumentOrBuilder getInstrumentOrBuilder() {
      if (instrumentBuilder_ != null) {
        return instrumentBuilder_.getMessageOrBuilder();
      } else {
        return instrument_;
      }
    }
    /**
     * <code>required .Instrument instrument = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilder<
        com.bbva.ticker.model.Instrument, com.bbva.ticker.model.Instrument.Builder, com.bbva.ticker.model.InstrumentOrBuilder> 
        getInstrumentFieldBuilder() {
      if (instrumentBuilder_ == null) {
        instrumentBuilder_ = new com.google.protobuf.SingleFieldBuilder<
            com.bbva.ticker.model.Instrument, com.bbva.ticker.model.Instrument.Builder, com.bbva.ticker.model.InstrumentOrBuilder>(
                instrument_,
                getParentForChildren(),
                isClean());
        instrument_ = null;
      }
      return instrumentBuilder_;
    }

    // required .PriceDataSourceType priceDataSourceType = 3;
    private com.bbva.ticker.model.PriceDataSourceType priceDataSourceType_ = com.bbva.ticker.model.PriceDataSourceType.SOURCE1;
    /**
     * <code>required .PriceDataSourceType priceDataSourceType = 3;</code>
     */
    public boolean hasPriceDataSourceType() {
      return ((bitField0_ & 0x00000004) == 0x00000004);
    }
    /**
     * <code>required .PriceDataSourceType priceDataSourceType = 3;</code>
     */
    public com.bbva.ticker.model.PriceDataSourceType getPriceDataSourceType() {
      return priceDataSourceType_;
    }
    /**
     * <code>required .PriceDataSourceType priceDataSourceType = 3;</code>
     */
    public Builder setPriceDataSourceType(com.bbva.ticker.model.PriceDataSourceType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000004;
      priceDataSourceType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required .PriceDataSourceType priceDataSourceType = 3;</code>
     */
    public Builder clearPriceDataSourceType() {
      bitField0_ = (bitField0_ & ~0x00000004);
      priceDataSourceType_ = com.bbva.ticker.model.PriceDataSourceType.SOURCE1;
      onChanged();
      return this;
    }

    // required .PricingType pricingType = 4;
    private com.bbva.ticker.model.PricingType pricingType_ = com.bbva.ticker.model.PricingType.TICK;
    /**
     * <code>required .PricingType pricingType = 4;</code>
     */
    public boolean hasPricingType() {
      return ((bitField0_ & 0x00000008) == 0x00000008);
    }
    /**
     * <code>required .PricingType pricingType = 4;</code>
     */
    public com.bbva.ticker.model.PricingType getPricingType() {
      return pricingType_;
    }
    /**
     * <code>required .PricingType pricingType = 4;</code>
     */
    public Builder setPricingType(com.bbva.ticker.model.PricingType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000008;
      pricingType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required .PricingType pricingType = 4;</code>
     */
    public Builder clearPricingType() {
      bitField0_ = (bitField0_ & ~0x00000008);
      pricingType_ = com.bbva.ticker.model.PricingType.TICK;
      onChanged();
      return this;
    }

    // required int32 count = 5;
    private int count_ ;
    /**
     * <code>required int32 count = 5;</code>
     */
    public boolean hasCount() {
      return ((bitField0_ & 0x00000010) == 0x00000010);
    }
    /**
     * <code>required int32 count = 5;</code>
     */
    public int getCount() {
      return count_;
    }
    /**
     * <code>required int32 count = 5;</code>
     */
    public Builder setCount(int value) {
      bitField0_ |= 0x00000010;
      count_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>required int32 count = 5;</code>
     */
    public Builder clearCount() {
      bitField0_ = (bitField0_ & ~0x00000010);
      count_ = 0;
      onChanged();
      return this;
    }

    // @@protoc_insertion_point(builder_scope:DataHistoryRequest)
  }

  static {
    defaultInstance = new DataHistoryRequest(true);
    defaultInstance.initFields();
  }

  // @@protoc_insertion_point(class_scope:DataHistoryRequest)
}

