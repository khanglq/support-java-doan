package com.fpt.hava.configuration;

import java.util.Objects;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Component
@ConfigurationProperties("swagger2")
public class Swagger2Properties {
  private String title;
  private String description;
  private String version;
  private String[] urlView;

  public String getTitle() {
    return Objects.isNull(title) ? "" : title;
  }

  public String getDescription() {
    return Objects.isNull(description) ? "" : description;
  }

  public String getVersion() {
    return Objects.isNull(version) ? "" : version;
  }

  public String[] getUrlView() {
    return Objects.isNull(urlView) ? new String[]{} : urlView;
  }
}
