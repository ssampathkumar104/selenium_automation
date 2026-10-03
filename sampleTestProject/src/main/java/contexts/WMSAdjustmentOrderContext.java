package contexts;

public class WMSAdjustmentOrderContext {
	private String businessPartner;
	private String businessPartnerAddress;
	private String purchaseOffice;
	private String purchaseOrderType;
	private String purchaseSeries;
	private String position;
	private String item;
	private String quantity;
	private String price;
	private String warehouse;
	private String returnPurchaseOrder;

	public String getBusinessPartner() {
		return this.businessPartner;
	}
	public void setBusinessPartner(final String businessPartner) {
		this.businessPartner = businessPartner;
	}

	public String getBusinessPartnerAddress() {
		return this.businessPartnerAddress;
	}
	public void setBusinessPartnerAddress(final String businessPartnerAddress) {
		this.businessPartnerAddress = businessPartnerAddress;
	}

	public String getPurchaseOffice() {
		return this.purchaseOffice;
	}
	public void setPurchaseOffice(final String purchaseOffice) {
		this.purchaseOffice = purchaseOffice;
	}
	
	public String getPurchaseOrderType() {
		return this.purchaseOrderType;
	}
	public void setPurchaseOrderType(final String purchaseOrderType) {
		this.purchaseOrderType = purchaseOrderType;
	}
	
	public String getPurchaseSeries() {
		return this.purchaseSeries;
	}
	public void setPurchaseSeries(final String purchaseSeries) {
		this.purchaseSeries = purchaseSeries;
	}
	
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getItem() {
		return this.item;
	}
	public void setItem(final String item) {
		this.item = item;
	}
	
	public String getQuantity() {
		return this.quantity;
	}
	public void setQuantity(final String quantity) {
		this.quantity = quantity;
	}
	
	public String getPrice() {
		return this.price;
	}
	public void setPrice(final String price) {
		this.price = price;
	}
	
	public String getWarehouse() {
		return this.warehouse;
	}
	public void setWarehouse(final String warehouse) {
		this.warehouse = warehouse;
	}
	
	public String getReturnPurchaseOrder() {
		return this.returnPurchaseOrder;
	}
	public void setReturnPurchaseOrder(final String returnPurchaseOrder) {
		this.returnPurchaseOrder = returnPurchaseOrder;
	}
	
}
