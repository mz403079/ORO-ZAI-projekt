package com.orozai.projekt.model.dto.specialized;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class RegisterFormDTO {
  @NotBlank
  @Length(max = 15)
  private String username;

  @NotBlank
  @Length(max = 50)
  @Email
  private String email;

  @JsonProperty("plainPassword")
  private String password;
}
