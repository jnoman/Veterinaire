package beans;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity @Table(name = "messages")
public class Message {
	@Id 
	@GeneratedValue(strategy=GenerationType.AUTO) 
	private Long idMessage;
	@ManyToOne(cascade=CascadeType.ALL)
	private User user;
	@Column(name = "text")
	private String text;
	@Column(name = "sender")
	private boolean sender;
	@Column(name = "dateEnvoyer")
	private Date dateEnvoyer;
	
	public Long getIdMessage() {
		return idMessage;
	}
	public void setIdMessage(Long idMessage) {
		this.idMessage = idMessage;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isSender() {
		return sender;
	}
	public void setSender(boolean sender) {
		this.sender = sender;
	}
	public Date getDateEnvoyer() {
		return dateEnvoyer;
	}
	public void setDateEnvoyer(Date dateEnvoyer) {
		this.dateEnvoyer = dateEnvoyer;
	}
	public Message(User user, String text, boolean sender, Date dateEnvoyer) {
		super();
		this.user = user;
		this.text = text;
		this.sender = sender;
		this.dateEnvoyer = dateEnvoyer;
	}
	public Message() {
		super();
	}
}