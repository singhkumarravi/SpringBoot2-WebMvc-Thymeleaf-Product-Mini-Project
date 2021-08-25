package com.olive.model;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@Table(name="Prod_tab")
public class Product {
	@GeneratedValue
	@Id
	@Column(name="pId")
	private Integer prodId;
	@Column(name="pcode")
	private String prodCode;
	@Column(name="pcost")
	private Double prodCost;
	@Column(name="pgst")
	private Double prodGst;
	@Column(name="pdiscount")
	private Double ProdDiscount;
	@Column(name="pvendor")
	@ElementCollection
	@CollectionTable(name="pvendor_tab")
	private List<String> prodVendor;
	private String note;

}
 