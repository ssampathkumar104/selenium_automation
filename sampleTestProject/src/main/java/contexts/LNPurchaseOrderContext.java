package contexts;

import lombok.Data;

@Data
public class LNPurchaseOrderContext {
	public String businessPartner;
	public String businessPartnerAddress;
	public String purchaseOffice;
	public String purchaseOrderType;
	public String purchaseSeries;
	public String position;
	public String item;
	public String quantity;
	public String price;
	public String warehouse;
	public String returnPurchaseOrder;
}
