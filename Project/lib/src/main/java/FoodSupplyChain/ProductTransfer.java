package FoodSupplyChain;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import com.owlike.genson.Genson;

/**
 * @author jinsongeogmail
 *
 */
@Contract(
    name = "FoodSupplyChain",
    info = @Info(
            title = "Food Supply Chain Contract",
            description = "Food Supply Chain Code",
            version = "0.0.1-SNAPSHOT")
)

@Default
public final class ProductTransfer implements ContractInterface {
	
	private final Genson genson = new Genson();
	private enum ChainCodeErrors {
	        NOT_FOUND,
	        ALREADY_EXISTS
	}
	
	/**
     * Add some initial properties to the ledger
     *
     * @param ctx the transaction context
     */
    @Transaction()
    public void initLedger(final Context ctx) {
    	
        ChaincodeStub stub= ctx.getStub();
        
        String key = "PRD0000";
        Product product = new Product(key, "Black Grapes","Jinson","BLR 001", "", "", "", "", "", "", "");
        
        String state = genson.serialize(product);
        
        stub.putStringState(key, state);
    }
    
    /**
     * Add new Product on the ledger.
     *
	 * @param ctx         		the transaction context
	 * @param id          		the product id of the farm product
	 * @param description 		the description of the farm product
	 * @param producerName 		producer or farmer name
	 * @param producerAddress 	producer or farmer address
	 * @param harvestdate 		harvest date of the farm product
	 * @return 					the Product details
	 */	
    @Transaction()
    public Product addNewProduct(final Context ctx, final String id, 
    		final String description, final String producerName, 
    		final String producerAddress, final String harvestdate) {
        
    	ChaincodeStub stub = ctx.getStub();
 
        String state = stub.getStringState(id);
        
        if (!state.isEmpty()) {
            String errorMessage = String.format("Product %s already exists", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, ChainCodeErrors.ALREADY_EXISTS.toString());
        }
        
        Product product = new Product(id, description, producerName, producerAddress, harvestdate,"","","","","","");
        
        state = genson.serialize(product);
        
        stub.putStringState(id, state); 
        
        return product;
    }
    
    /**
     * Retrieves a Product based upon Product Id from the ledger.
     *
	 * @param ctx      	the transaction context
	 * @param id 		product Id of the farm Product
	 * @return 			Farm Product supply chain details
     */
	@Transaction()
    public Product getProductById(final Context ctx, final String id) {
        ChaincodeStub stub = ctx.getStub();
        String state = stub.getStringState(id);

        if (state.isEmpty()) {
            String errorMessage = String.format("Product %s does not exist", id);
            System.out.println(errorMessage);
            throw new ChaincodeException(errorMessage, ChainCodeErrors.NOT_FOUND.toString());
        }
        
        Product product = genson.deserialize(state, Product.class);
        return product;
    }
    
	/**
	 * Transfers the Product Ownership from the Producer to the Distributor
	 * 
	 * @param ctx      				the transaction context
	 * @param id     				product Id of the Farm Product
	 * @param distributerName 		distributer name
	 * @param distributerAddress 	distributer address
	 * @param transferDate 			transaction date between producer and distributer 
	 * @return 						the product id
	 */
	@Transaction()
	public String transferProductToDistributor(final Context ctx, final String id, 
			final String distributerName,final String distributerAddress, final String transferDate ) {
		
	    ChaincodeStub stub = ctx.getStub();	
	    String state = stub.getStringState(id);
	
	    if (state.isEmpty()) {
	        String errorMessage = String.format("Product %s does not exist", id);
	        System.out.println(errorMessage);
	        throw new ChaincodeException(errorMessage, ChainCodeErrors.NOT_FOUND.toString());
	    }
	
	    Product product = genson.deserialize(state, Product.class);
	    System.out.println("=========== Retrieved Product from State ===============");
	    System.out.println(product.toString());
	    System.out.println("=========== Retrieved Product from State ===============");
	    
	    Product newProduct = new Product(
	    		product.ProductId(), product.ProductDescription(), 
	    		product.ProducerName(), product.ProducerAddress(), product.HarvestDate(),
	    		distributerName, distributerAddress, transferDate, "", "", "");
	    
	    String newState = genson.serialize(newProduct);
	    
	    stub.putStringState(id, newState);
	    
	    System.out.println("=========== Set Product to State ===============");
	    System.out.println(newProduct.toString());
	    System.out.println("=========== Set Product to State ===============");
	
	    return newProduct.ProductId();
	} 
	
	/**
	 * Transfers the Product Ownership from the Distributor to the Retailer
	 * 
	 * @param ctx      				the transaction context
	 * @param id     				product Id of the Farm Product
	 * @param retailerName  		The Retailer name
	 * @param retailerAddress  		The Retailer address
	 * @param transferDate 			transaction date between Distributor and Retailer 
	 * @return 						the product id
	 */
	@Transaction()
	public String transferProductToRetailer(final Context ctx, final String id, 
			final String retailerName,final String retailerAddress, final String transferDate ) {
		
	    ChaincodeStub stub = ctx.getStub();	
	    String state = stub.getStringState(id);
	
	    if (state.isEmpty()) {
	        String errorMessage = String.format("Product %s does not exist", id);
	        System.out.println(errorMessage);
	        throw new ChaincodeException(errorMessage, ChainCodeErrors.NOT_FOUND.toString());
	    }
	
	    Product product = genson.deserialize(state, Product.class);
	    System.out.println("=========== Retrieved Product from State ===============");
	    System.out.println(product.toString());
	    System.out.println("=========== Retrieved Product from State ===============");
	    
	    Product newProduct = new Product(
	    		product.ProductId(), product.ProductDescription(), 
	    		product.ProducerName(), product.ProducerAddress(), product.HarvestDate(),
	    		product.DistributerName(), product.DistributerAddress(), product.ProdToDistDate(), retailerName, retailerAddress, transferDate);
	    
	    String newState = genson.serialize(newProduct);
	    
	    stub.putStringState(id, newState);
	    
	    System.out.println("=========== Set Product to State ===============");
	    System.out.println(newProduct.toString());
	    System.out.println("=========== Set Product to State ===============");
	
	    return newProduct.ProductId();
	} 
}
