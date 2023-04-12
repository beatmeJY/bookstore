package com.develop.bookstore.domain.user.domain.book;

import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AttributeOverride(name = "id", column = @Column(name = "author_no"))
@Entity
@Table(name = "book_author")
@Getter @Setter
@NoArgsConstructor
public class Author extends DefaultEntity {

	// 저자 이름
	@Column(nullable = false)
	private String authorName;

	// 저자 생일
	@Column(nullable = false)
	private Integer authorBirth;

	// 저자 설명
	@Column(nullable = false, length = 4000)
	private String authorDescription;

	@OneToOne(mappedBy = "author")
	private Book book;
}
