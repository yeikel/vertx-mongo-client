package io.vertx.ext.mongo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.Objects;

/**
 * Options for configuring updates.
 *
 * @author <a href="mailto:nscavell@redhat.com">Nick Scavelli</a>
 */
@DataObject
public class UpdateOptions {

  /**
   * The default value of upsert = false
   */
  public static final boolean DEFAULT_UPSERT = false;

  /**
   * The default value of multi = false
   */
  public static final boolean DEFAULT_MULTI = false;

  /**
   * The default value of returning new document = false
   */
  public static final boolean DEFAULT_RETURN_NEW_DOCUMENT = false;

  private WriteOption writeOption;
  private boolean upsert;
  private boolean multi;
  private boolean returnNewDocument; // uniquely valid on findOneAnd* methods
  private JsonArray arrayFilters;
  private CollationOptions collation;

  /**
   * Default constructor
   */
  public UpdateOptions() {
    this.upsert = DEFAULT_UPSERT;
    this.multi = DEFAULT_MULTI;
    this.returnNewDocument = DEFAULT_RETURN_NEW_DOCUMENT;
  }

  /**
   * Constructor specify upsert
   *
   * @param upsert the value of upsert
   */
  public UpdateOptions(boolean upsert) {
    this.upsert = upsert;
    this.multi = DEFAULT_MULTI;
    this.returnNewDocument = DEFAULT_RETURN_NEW_DOCUMENT;
  }

  /**
   * Constructor specify upsert and multi
   *
   * @param upsert the value of upsert
   * @param multi  the value of multi
   */
  public UpdateOptions(boolean upsert, boolean multi) {
    this.upsert = upsert;
    this.multi = multi;
    this.returnNewDocument = DEFAULT_RETURN_NEW_DOCUMENT;
  }

  /**
   * Copy constructor
   *
   * @param other the one to copy
   */
  public UpdateOptions(UpdateOptions other) {
    this.writeOption = other.writeOption;
    this.upsert = other.upsert;
    this.multi = other.multi;
    this.returnNewDocument = other.returnNewDocument;
    this.arrayFilters = other.arrayFilters;
    this.collation = other.collation;
  }

  /**
   * Constructor from JSON
   *
   * @param json the json
   */
  public UpdateOptions(JsonObject json) {
    String wo = json.getString("writeOption");
    if (wo != null) {
      writeOption = WriteOption.valueOf(wo.toUpperCase());
    }
    upsert = json.getBoolean("upsert", DEFAULT_UPSERT);
    multi = json.getBoolean("multi", DEFAULT_MULTI);
    returnNewDocument = json.getBoolean("return_new_document", DEFAULT_RETURN_NEW_DOCUMENT);
    arrayFilters = json.getJsonArray("arrayFilters", null);
    if (json.getJsonObject("collation") != null) {
      collation = new CollationOptions(json.getJsonObject("collation"));
    }
  }

  public CollationOptions getCollation() {
    return collation;
  }

  /**
   * Collation options
   *
   * @param collation
   */
  public UpdateOptions setCollation(CollationOptions collation) {
    this.collation = collation;
    return this;
  }

  /**
   * Get the write option.
   *
   * @return the write option
   */
  public WriteOption getWriteOption() {
    return writeOption;
  }

  /**
   * Set the write option
   *
   * @param writeOption the write option
   * @return reference to this, for fluency
   */
  public UpdateOptions setWriteOption(WriteOption writeOption) {
    this.writeOption = writeOption;
    return this;
  }

  /**
   * Get whether upsert is enabled
   *
   * @return upsert is enabled?
   */
  public boolean isUpsert() {
    return upsert;
  }

  /**
   * Set whether upsert is enabled
   *
   * @param upsert true if enabled
   * @return reference to this, for fluency
   */
  public UpdateOptions setUpsert(boolean upsert) {
    this.upsert = upsert;
    return this;
  }

  /**
   * Get whether returning new document property is enabled. Valid only on findOneAnd* methods.
   *
   * @return new document property is enabled?
   */
  public boolean isReturningNewDocument() {
    return returnNewDocument;
  }

  /**
   * Set whether new document property is enabled. Valid only on findOneAnd* methods.
   *
   * @param returnNewDocument true if enabled
   * @return reference to this, for fluency
   */
  public UpdateOptions setReturningNewDocument(boolean returnNewDocument) {
    this.returnNewDocument = returnNewDocument;
    return this;
  }

  /**
   * Get whether multi is enabled. Multi means more than one document can be updated.
   *
   * @return multi is enabled?
   */
  public boolean isMulti() {
    return multi;
  }

  /**
   * Set whether multi is enabled
   *
   * @param multi true if enabled
   * @return reference to this, for fluency
   */
  public UpdateOptions setMulti(boolean multi) {
    this.multi = multi;
    return this;
  }

  /**
   * Get the arrayFilters option.
   *
   * @return the arrayFilters option
   */
  public JsonArray getArrayFilters() {
    return arrayFilters;
  }

  /**
   * Set the arrayFilters option
   *
   * @param arrayFilters the arrayFilters option
   * @return reference to this, for fluency
   */
  public UpdateOptions setArrayFilters(JsonArray arrayFilters) {
    this.arrayFilters = arrayFilters;
    return this;
  }

  public JsonObject toJson() {
    JsonObject json = new JsonObject();
    if (writeOption != null) {
      json.put("writeOption", writeOption.name());
    }
    if (upsert) {
      json.put("upsert", true);
    }
    if (multi) {
      json.put("multi", true);
    }
    if (returnNewDocument) {
      json.put("return_new_document", true);
    }
    if (arrayFilters != null && !arrayFilters.isEmpty()) {
      json.put("arrayFilters", arrayFilters);
    }
    if (collation != null) {
      json.put("collation", collation.toJson());
    }

    return json;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UpdateOptions that = (UpdateOptions) o;
    return upsert == that.upsert && multi == that.multi && returnNewDocument == that.returnNewDocument && writeOption == that.writeOption && Objects.equals(arrayFilters, that.arrayFilters) && Objects.equals(collation, that.collation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(writeOption, upsert, multi, returnNewDocument, arrayFilters, collation);
  }

  @Override
  public String toString() {
    return "UpdateOptions{" +
      "writeOption=" + writeOption +
      ", upsert=" + upsert +
      ", multi=" + multi +
      ", returnNewDocument=" + returnNewDocument +
      ", arrayFilters=" + arrayFilters +
      ", collation=" + collation +
      '}';
  }
}
