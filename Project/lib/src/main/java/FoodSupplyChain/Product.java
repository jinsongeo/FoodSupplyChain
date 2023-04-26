package FoodSupplyChain;

import com.owlike.genson.annotation.JsonProperty;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.util.Objects;


/**
 * @author jinsongeogmail
 *
 */
@DataType()
public class Product {

	//Product Id
	@Property()
	private final String productId;

	//Description of the Product
	@Property()
	private final String productDescription;

	// Name of the producer or farmer
	@Property()
	private final String producerName;

	// Address of the producer or farmer
	@Property()
	private final String producerAddress;

	// Date of the harvest
	@Property()
	private final String harvestDate;

	// Name of the distributer
	@Property()
	private final String distributerName;

	// Address of the distributer
	@Property()
	private final String distributerAddress;

	// Date of transfer from producer to distributer
	@Property()
	private final String prodToDistDate;

	// Name of the retailer
	@Property()
	private final String retailerName;

	// Address of the retailer 
	@Property()
	private final String retailerAddress;
	
	// Date of transfer from distributer to retailer
	@Property()
	private final String distToRetaDate;	
	
	public String ProductId() {
		return productId;
	}
	
	public String ProductDescription() {
		return productDescription;
	}
	
	public String ProducerName() {
		return producerName;
	}
	
	public String ProducerAddress() {
		return producerAddress;
	}
	
	public String HarvestDate() {
		return harvestDate;
	}
	
	public String DistributerName() {
		return distributerName;
	}

	public String DistributerAddress() {
		return distributerAddress;
	}
	
	public String ProdToDistDate() {
		return prodToDistDate;
	}
	
	public String RetailerName() {
		return retailerName;
	}
	
	public String RetailerAddress() {
		return retailerAddress;
	}
	
	public String DistToRetaDate() {
		return distToRetaDate;
	}

	public Product(
	@JsonProperty("productId") final String productId, 
	@JsonProperty("productDescription") final String productDescription, 
	@JsonProperty("producerName") final String producerName, 
	@JsonProperty("producerAddress") final String producerAddress, 
	@JsonProperty("harvestDate") final String harvestDate,
	@JsonProperty("distributerName") final String distributerName,
	@JsonProperty("distributerAddress") final String distributerAddress,
	@JsonProperty("prodToDistDate") final String prodToDistDate,
	@JsonProperty("retailerName") final String retailerName,
	@JsonProperty("retailerAddress") final String retailerAddress,
	@JsonProperty("distToRetaDate") final String distToRetaDate
	) 
 	{
		this.productId = productId;
		this.productDescription = productDescription;
		this.producerName = producerName;
		this.producerAddress = producerAddress;
		this.harvestDate = harvestDate;
		this.distributerName = distributerName;
		this.distributerAddress = distributerAddress;
		this.prodToDistDate = prodToDistDate;
		this.retailerName = retailerName;
		this.retailerAddress = retailerAddress;
		this.distToRetaDate = distToRetaDate;		
	}
	
	/*
	public Product(
	@JsonProperty("productId") final String productId, 
	@JsonProperty("productDescription") final String productDescription, 
	@JsonProperty("producerName") final String producerName, 
	@JsonProperty("producerAddress") final String producerAddress)
 	{
		this.productId = productId;
		this.productDescription = productDescription;
		this.producerName = producerName;
		this.producerAddress = producerAddress;
		this.harvestDate = new SimpleDateFormat("dd MMMM yyyy").format(new Date());
	}
	*/
	
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
 
		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}
 
		Product other = (Product) obj;
 
		return Objects.deepEquals(new String[] { this.ProductId(), this.ProductDescription(), this.ProducerName(), this.ProducerAddress(), this.HarvestDate() },
				new String[] { other.ProductId(), other.ProductDescription(), other.ProducerName(), other.ProducerAddress(), other.HarvestDate() });
	}
 
	@Override
	public int hashCode() {
		return Objects.hash(this.ProductId(), this.ProductDescription(), this.ProducerName(), this.ProducerAddress(), this.HarvestDate());
	}
 
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + 
				" ["
				+ "id=" + productId 
				+ ", description=" + productDescription
				+ ", producer=" + producerName 
				+ ", harvestDate=" + harvestDate 
				+ ", distributerName=" + distributerName
				+ ", prodToDistDate=" + prodToDistDate
				+ ", retailerName=" + retailerName 
				+ ", distToRetaDate=" + distToRetaDate 
				+ "]";
	}	
}
