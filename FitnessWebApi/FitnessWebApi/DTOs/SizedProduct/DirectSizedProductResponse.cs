namespace FitnessWebApi.DTOs.SizedProduct
{
	public class DirectSizedProductResponse
	{
		public int SizedProductID { get; set; }

		public double ServingSize { get; set; }

		public StaticUserResponse User { get; set; }

		public StaticProductResponse Product { get; set; }
	}
}
