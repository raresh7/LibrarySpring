package entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(	name="libuser", uniqueConstraints = @UniqueConstraint(columnNames={"name"}))
public class User {
	@Transient
	public static int nextId;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String ssn;
	private String address;
	private Boolean isAdmin;

	@OneToMany(fetch = FetchType.LAZY, mappedBy="user", cascade = CascadeType.ALL)
	private List<Transaction> transaction = new ArrayList<Transaction>();
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSsn() {
		return ssn;
	}
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User(){}
	
	public User(String name, String ssn, String address, int id, Boolean isAdmin){
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.id = id;
		this.isAdmin = isAdmin;
		nextId = id + 1;
	}
	public User(String name, String ssn, String address, Boolean isAdmin){
		this.name = name;
		this.ssn = ssn;
		this.address = address;
		this.isAdmin = isAdmin;
	}
	public Boolean getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	public List<Transaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<Transaction> transaction) {
		this.transaction = transaction;
	}
}
