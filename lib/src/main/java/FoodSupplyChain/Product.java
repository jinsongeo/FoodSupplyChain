package FoodSupplyChain;

import com.owlike.genson.annotation.JsonProperty;

import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;

import java.text.SimpleDateFormat;
import java.util.Date;
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
	private String distributerName = null;

	// Address of the distributer
	@Property()
	private String distributerAddress = null;

	// Date of transfer from producer to distributer
	@Property()
	private String prodToDistDate = null;

	// Name of the retailer
	@Property()
	private String retailerName = null;

	// Address of the retailer 
	@Property()
	private String retailerAddress = null;
	
	// Date of transfer from distributer to retailer
	@Property()
	private String distToRetaDate = null;	
	
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
	
	public void SetDistributerName(String name) {
		this.distributerName = name;
	}
	
	public String DistributerAddress() {
		return distributerAddress;
	}
	
	public void SetDistributerAddress(String address) {
		this.distributerAddress = address;
	}
	
	public String ProdToDistDate() {
		return prodToDistDate;
	}
	
	public void SetProdToDistDate(String date) {
		this.prodToDistDate = date;
	}
	
	public String RetailerName() {
		return retailerName;
	}
	
	public void SetRetailerName(String name) {
		this.retailerName = name;
	}
	
	public String RetailerAddress() {
		return retailerAddress;
	}
	
	public void SetRetailerAddress(String address) {
		this.retailerAddress = address;
	}
	
	public String DistToRetaDate() {
		return distToRetaDate;
	}
	
	public void SetDistToRetaDate(String date) {
		this.distToRetaDate = date;
	}

	public Product(
	@JsonProperty("productId") final String productId, 
	@JsonProperty("productDescription") final String productDescription, 
	@JsonProperty("producerName") final String producerName, 
	@JsonProperty("producerAddress") final String producerAddress, 
	@JsonProperty("harvestDate") final String harvestDate) 
 	{
		this.productId = productId;
		this.productDescription = productDescription;
		this.producerName = producerName;
		this.producerAddress = producerAddress;
		this.harvestDate = harvestDate;
	}
	
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
				+ ", distributerName =" + distributerName 
				+ ", retailerName =" + retailerName 
				+ "]";
	}	
}
