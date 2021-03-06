package com.naturalprogrammer.spring.lemondemo.domain;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonView;
import com.naturalprogrammer.spring.lemon.commons.util.UserUtils;
import com.naturalprogrammer.spring.lemonreactive.domain.AbstractMongoUser;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
@TypeAlias("User")
@Document(collection = "usr")
public class User extends AbstractMongoUser<ObjectId> {

    public static final int NAME_MIN = 1;
    public static final int NAME_MAX = 50;

	public static class Tag implements Serializable {
		
		private static final long serialVersionUID = -2129078111926834670L;

		private String name;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}

	public User(String email, String password, String name) {
		this.email = email;
		this.password = password;
		this.name = name;
	}

	@JsonView(UserUtils.SignupInput.class)
	@NotBlank(message = "{blank.name}", groups = {UserUtils.SignUpValidation.class, UserUtils.UpdateValidation.class})
    @Size(min=NAME_MIN, max=NAME_MAX, groups = {UserUtils.SignUpValidation.class, UserUtils.UpdateValidation.class})
    private String name;
	
	@Override
	public Tag toTag() {
		
		Tag tag = new Tag();
		tag.setName(name);
		return tag;
	}
	
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
