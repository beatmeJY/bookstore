package com.develop.bookstore.domain.user.domain.book;

import com.develop.bookstore.domain.user.dto.book.AuthorRegisterDTO;
import com.develop.bookstore.domain.user.dto.book.AuthorSearchDTO;
import com.develop.bookstore.global.entity.DefaultEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AttributeOverride(name = "id", column = @Column(name = "author_no"))
@Entity
@Table(name = "book_author")
@Getter @Setter
@NoArgsConstructor
public class Author extends DefaultEntity {

	@Column(nullable = false)
	private String authorName; // 저자 이름
	@Column(nullable = false)
	private Integer authorBirth; // 저자 생일
	@Column(nullable = false, length = 4000)
	private String authorInfo; // 저자 설명
	@Column(nullable = false)
	private Long regMemberNo; // 등록자 NO
	private Long modMemberNo; // 수정자 No

	@OneToMany(mappedBy = "author")
	private List<Book> book;


	public Author(String authorName, Integer authorBirth, String authorInfo, Long regMemberNo) {
		this.authorName = authorName;
		this.authorBirth = authorBirth;
		this.authorInfo = authorInfo;
		this.regMemberNo = regMemberNo;
	}

	/**
	 *  AuthorSearchDTO 로 변환.
	 */
	public AuthorSearchDTO toAuthorSearchDto() {
		return new AuthorSearchDTO(this.getId(), this.getAuthorName(), this.getAuthorBirth());
	}

}
