/*
 * Farm Water Services
 * Farms services to be utilized by the OC to access various systems that don't conform to JSON RESTful services
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package com.farms.water.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;
import javax.validation.Valid;

/**
 * FarmsRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-02-23T10:09:49.905+05:30")
public class FarmsRequest {
  private int farmId ;

  @JsonProperty("status")
  private String status = null;

  @JsonProperty("startDateTime")
  private String startDateTime = null;

  @JsonProperty("duration")
  private int duration;
  public FarmsRequest farmId(int farmId) {
    this.farmId = farmId;
    return this;
  }

   /**
   * Get farmId
   * @return farmId
  **/
  @ApiModelProperty(value = "")
  public int getFarmId() {
    return farmId;
  }

  public void setFarmId(int farmId) {
    this.farmId = farmId;
  }

  public FarmsRequest status(String status) {
    this.status = status;
    return this;
  }

   /**
   * Get status
   * @return status
  **/
  @ApiModelProperty(value = "")
  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public FarmsRequest startDateTime(String startDateTime) {
    this.startDateTime = startDateTime;
    return this;
  }

   /**
   * Get startDateTime
   * @return startDateTime
  **/
  @ApiModelProperty(value = "")
  public String getStartDateTime() {
    return startDateTime;
  }

  public void setStartDateTime(String startDateTime) {
    this.startDateTime = startDateTime;
  }

  public FarmsRequest duration(int duration) {
    this.duration = duration;
    return this;
  }

   /**
   * Get duration
   * @return duration
  **/
  @ApiModelProperty(value = "")
  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    FarmsRequest farmsRequest = (FarmsRequest) o;
    return Objects.equals(this.farmId, farmsRequest.farmId) &&
        Objects.equals(this.status, farmsRequest.status) &&
        Objects.equals(this.startDateTime, farmsRequest.startDateTime) &&
        Objects.equals(this.duration, farmsRequest.duration);
  }

  @Override
  public int hashCode() {
    return Objects.hash(farmId, status, startDateTime, duration);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class FarmsRequest {\n");
    
    sb.append("    farmId: ").append(toIndentedString(farmId)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    startDateTime: ").append(toIndentedString(startDateTime)).append("\n");
    sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

