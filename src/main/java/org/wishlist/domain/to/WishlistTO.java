package org.wishlist.domain.to;

import java.io.Serializable;

import org.bson.types.ObjectId;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class WishlistTO implements Serializable {
	
	@NotEmpty
	private String nome;
	@NotNull
	private ObjectId clientId;
	
}
