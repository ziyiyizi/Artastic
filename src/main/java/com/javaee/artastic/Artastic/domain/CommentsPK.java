package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Timestamp;

public class CommentsPK implements Serializable {
    private int artworkId;
    private String userName;
    private String commentorName;
    private Timestamp commentTime;

    @Column(name = "Artwork_ID")
    @Id
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Column(name = "User_Name")
    @Id
    public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "Commentor_Name")
    @Id
	public String getCommentorName() {
		return commentorName;
	}

	public void setCommentorName(String commentorName) {
		this.commentorName = commentorName;
	}

	@Column(name = "Comment_time")
    @Id
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

        CommentsPK that = (CommentsPK) o;

        if (artworkId != that.artworkId) return false;
        if (userName != null ? !userName.equals(that.userName) : that.userName != null) return false;
        if (commentorName != null ? !commentorName.equals(that.commentorName) : that.commentorName != null) return false;
        if (commentTime != null ? !commentTime.equals(that.commentTime) : that.commentTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (commentorName != null ? commentorName.hashCode() : 0);
        result = 31 * result + (commentTime != null ? commentTime.hashCode() : 0);
        return result;
    }
}
