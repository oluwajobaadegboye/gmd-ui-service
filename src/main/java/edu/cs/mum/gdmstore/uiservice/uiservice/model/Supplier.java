package edu.cs.mum.gdmstore.uiservice.uiservice.model;


import java.util.List;


public class Supplier {

    private int supplierId;
	private int supplierNumber;
	private String name;
	private String contactPhoneNumber;

	private List<Product> productsSupplied;
	
	public Supplier() {
		super();
	}

	public Supplier(int supplierNumber, String name, String contactPhoneNumber, List<Product> productsSupplied) {
		this.supplierNumber = supplierNumber;
		this.name = name;
		this.contactPhoneNumber = contactPhoneNumber;
		this.productsSupplied = productsSupplied;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getSupplierNumber() {
		return supplierNumber;
	}

	public void setSupplierNumber(int supplierNumber) {
		this.supplierNumber = supplierNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public List<Product> getProductsSupplied() {
		return productsSupplied;
	}

	public void setProductsSupplied(List<Product> productsSupplied) {
		this.productsSupplied = productsSupplied;
	}

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierNumber=" + supplierNumber +
                ", name='" + name + '\'' +
                ", contactPhoneNumber='" + contactPhoneNumber + '\'' +
                ", productsSupplied=" + productsSupplied +
                '}';
    }
}
