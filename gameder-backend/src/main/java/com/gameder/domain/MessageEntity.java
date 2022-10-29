package com.gameder.domain;

import com.gameder.api.Gamer;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "MESSAGE")
public class MessageEntity {

    @Id
    @Column(name = "ID")
    @GenericGenerator(name="messageentity-uuid", strategy="uuid")
    @GeneratedValue(generator = "messageentity-uuid")
    private String id;
    private String messageText;
    private Date creationDate;
    private Date lastUpdatedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private GamerEntity fromGamer;

    @ManyToOne(fetch = FetchType.LAZY)
    private GamerEntity toGamer;

    private boolean archived;

    public MessageEntity() {
    }

    public GamerEntity getFromGamer() {
        return fromGamer;
    }

    public void setFromGamer(GamerEntity fromGamer) {
        this.fromGamer = fromGamer;
    }

    public GamerEntity getToGamer() {
        return toGamer;
    }

    public void setToGamer(final GamerEntity toGamer) {
        this.toGamer = toGamer;
    }


    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }

    public boolean isArchived() {
        return archived;
    }
}
