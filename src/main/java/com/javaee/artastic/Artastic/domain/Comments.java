package com.javaee.artastic.Artastic.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@IdClass(CommentsPK.class)
public class Comments {
    private int artworkId;
    private String userName;
    private String commentorName;
    private String comment;
    private Timestamp commentTime;

    @Id
    @Column(name = "Artwork_ID")
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Id
    @Column(name = "User_Name")
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Id
    @Column(name = "Commentor_Name")
	public String getCommentorName() {
		return commentorName;
	}

	public void setCommentorName(String commentorName) {
		this.commentorName = commentorName;
	}

	@Basic
    @Column(name = "Comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Id
    @Column(name = "Comment_time")
    public Timestamp getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Timestamp commentTime) {
        this.commentTime = commentTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (artworkId != comments.artworkId) return false;
        if (userName != null ? !userName.equals(comments.userName) : comments.userName != null) return false;
        if (commentorName != null ? !commentorName.equals(comments.commentorName) : comments.commentorName != null) return false;
        if (comment != null ? !comment.equals(comments.comment) : comments.comment != null) return false;
        if (commentTime != null ? !commentTime.equals(comments.commentTime) : comments.commentTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (commentorName != null ? commentorName.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        return result;
    }
}
