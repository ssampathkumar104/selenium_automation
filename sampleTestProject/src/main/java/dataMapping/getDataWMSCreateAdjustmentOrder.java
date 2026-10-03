package dataMapping;

import java.util.HashMap;
import java.util.Map;

import static commons.Cloudsuite.getCSLoginDetailsContext;
import static testBase.BaseClass.log;
import static testBase.TestData.getPropertyFile;

import contexts.LNPurchaseOrderContext;
import dataUtils.PropertiesDataFile;

public class getDataWMSCreateAdjustmentOrder {
	
	public static Map<String, Object> getData(String propertyFile) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("purchaseOrderContext", getPurchaseOrderContext(propertyFile));
		m.put("loginContext", getCSLoginDetailsContext(propertyFile));
		return m;
	}
	
	private static LNPurchaseOrderContext getPurchaseOrderContext(String propertyFile) {
		LNPurchaseOrderContext purchaseOrderContext = new LNPurchaseOrderContext();
		PropertiesDataFile data = getPropertyFile(propertyFile);

		purchaseOrderContext.businessPartner = data.get("businessPartner");
		purchaseOrderContext.businessPartnerAddress = data.get("businessPartnerAddress");
		purchaseOrderContext.purchaseOrderType = data.get("purchaseOrderType");
		purchaseOrderContext.purchaseOffice = data.get("purchaseOffice");
		purchaseOrderContext.purchaseSeries = data.get("purchaseSeries");
		purchaseOrderContext.position = data.get("position");
		purchaseOrderContext.item = data.get("item");
		purchaseOrderContext.quantity = data.get("quantity");
		purchaseOrderContext.warehouse = data.get("warehouse");
		purchaseOrderContext.price = data.get("price");
		log().info("INFO : ========>>>>> Data mapped successfully completed <<<<<========= ");
		return purchaseOrderContext;
	}
}
