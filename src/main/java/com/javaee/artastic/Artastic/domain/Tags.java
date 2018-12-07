package com.javaee.artastic.Artastic.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(TagsPK.class)
public class Tags {
    private int artworkId;
    private String tagName;

    @Id
    @Column(name = "Artwork_ID")
    public int getArtworkId() {
        return artworkId;
    }

    public void setArtworkId(int artworkId) {
        this.artworkId = artworkId;
    }

    @Id
    @Column(name = "Tag_name")
    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tags tags = (Tags) o;

        if (artworkId != tags.artworkId) return false;
        if (tagName != null ? !tagName.equals(tags.tagName) : tags.tagName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = artworkId;
        result = 31 * result + (tagName != null ? tagName.hashCode() : 0);
        return result;
    }
}
