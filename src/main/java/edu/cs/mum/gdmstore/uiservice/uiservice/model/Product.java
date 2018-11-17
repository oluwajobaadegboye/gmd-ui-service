package edu.cs.mum.gdmstore.uiservice.uiservice.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


public class Product {


    private long productId;

	private long productNumber;

	private String name;

	private double unitPrice;

	private double quantityInStock;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dateSupplied;


	private Supplier supplier;
	
	public Product() {
		
	}

	public Product(long productNumber, String name, double unitPrice, double quantityInStock, LocalDate dateSupplied,Supplier supplier) {
		this.productNumber = productNumber;
		this.name = name;
		this.unitPrice = unitPrice;
		this.quantityInStock = quantityInStock;
		this.dateSupplied = dateSupplied;
		this.supplier = supplier;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(long productNumber) {
		this.productNumber = productNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(double quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public LocalDate getDateSupplied() {
		return dateSupplied;
	}

	public void setDateSupplied(LocalDate dateSupplied) {
		this.dateSupplied = dateSupplied;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public String toString() {
		return "Product{" +
				"productId=" + productId +
				", productNumber=" + productNumber +
				", name='" + name + '\'' +
				", unitPrice=" + unitPrice +
				", quantityInStock=" + quantityInStock +
				", dateSupplied=" + dateSupplied +
				", supplier=" + supplier +
				'}';
	}
}
