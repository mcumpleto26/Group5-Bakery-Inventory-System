public class StockTransaction {
private int sequenceNo;
private String type;
private String productId;
private int quantity;
public StockTransaction(int sequenceNo, String type, String productId, 
int quantity) {
this.sequenceNo = sequenceNo;
this.type = type;
this.productId = productId;
this.quantity = quantity;
}

public int getSequenceNo() { return sequenceNo; }
public String getType() { return type; }
public String getProductId() { return productId; }
public int getQuantity() { return quantity; }
}