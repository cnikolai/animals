package edu.cmn.deepdive.animals.model;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Animal {

  private String id;

  @Expose
  private String title;

  @Expose
  private String description;

  // serialize = taking json object and translating into Java byte code.
  @Expose //need to get the information through to Java Object
  @SerializedName("href") //the name that comes from json
  private String imageUrl;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }

  @NonNull
  @Override
  public String toString() {
    return title;
  }
}
