package org.wishlist.domain.commons;

import java.util.Collection;
import java.util.Objects;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PageTO<T> {

	private final Collection<T> content;
	private final int pages;
	private final long total;
	private final boolean first;
	private final boolean last;
	private final boolean hasNext;
	private final boolean hasPrevious;
	private final int offset;
	private final int size;
	private final int page;
	private final int totalElements;

	private PageTO(Collection<T> values, PaginationTO paginationTO, long total) {
		Objects.requireNonNull(values);
		Objects.requireNonNull(paginationTO);

		this.size = paginationTO.getSize();
		this.page = paginationTO.getPage();
		this.content = values;
		this.total = total;
		this.totalElements = values.size();

		this.pages = (int) Math.ceil((double) total / paginationTO.getSize());
		this.first = getPages() == 0 || getPage() == 0;
		this.last = getPages() == 0 || getPage() == pages - 1;
		this.hasPrevious = getPage() > 0;
		this.hasNext = getPage() < (getPages() - 1);
		this.offset = getSize() * getPage();
	}

	public static <T> PageTO<T> of(@NotNull Collection<T> values, @NotNull PaginationTO pagination, long total) {
		return new PageTO<>(values, pagination, total);
	}

}
