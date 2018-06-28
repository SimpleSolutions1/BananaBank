package com.bank.BananaBank.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@Table(name="transaction")
@JsonTypeName(value = "transactions")  
@JsonIgnoreProperties({"id", "createdDate", "user"})
public class Transaction{
	
		@Id
		@GeneratedValue
		private Integer id;
		
	 	@Column(name = "type", nullable = false)
	    @Enumerated(EnumType.STRING)
	    private TransactionType type;

	    @Column(name = "value", nullable = false)
	    private Integer value;

	    @ManyToOne
	    @JoinColumn(name="user_id", nullable=false)
	    private User user;

	    @Column(name = "created_date", nullable = false)
	    @CreationTimestamp
	    private LocalDateTime createdDate;

	    public Transaction(){
	    	
	    }
	    
	    
	    
		public Transaction(TransactionType type, Integer value, User user) {
			this.type = type;
			this.value = value;
			this.user = user;
		}

		public Transaction(TransactionType type, Integer value) {
			this.type = type;
			this.value = value;
		}
		public TransactionType getType() {
			return type;
		}

		public void setType(TransactionType type) {
			this.type = type;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		public User getUser() {
			return user;
		}

		public void setUser(User user) {
			this.user = user;
		}

		public LocalDateTime getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(LocalDateTime createdDate) {
			this.createdDate = createdDate;
		}

	    

}
