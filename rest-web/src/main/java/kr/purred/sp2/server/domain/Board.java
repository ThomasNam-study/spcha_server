package kr.purred.sp2.server.domain;

import kr.purred.sp2.server.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table
@AllArgsConstructor
@Builder
public class Board implements Serializable
{
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idx;

	@Column
	private String title;

	@Column
	private String subTitle;

	@Column
	private String content;

	@Column
	@Enumerated(EnumType.STRING)
	private BoardType boardType;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;

	@OneToOne(fetch = FetchType.LAZY)
	private User user;

	public void setCreateDateNow ()
	{
		createdDate = LocalDateTime.now ();
	}

	public void setUpdatedDateNow ()
	{
		updatedDate = LocalDateTime.now ();
	}

	public void update (Board board)
	{
		this.title = board.getTitle ();
		this.subTitle = board.getSubTitle ();
		this.content = board.getContent ();
		this.boardType = board.getBoardType ();
		this.updatedDate = LocalDateTime.now ();
	}
}
