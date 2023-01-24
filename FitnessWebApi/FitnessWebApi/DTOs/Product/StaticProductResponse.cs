namespace FitnessWebApi.DTOs.Product
{
	public class StaticProductResponse
	{
		public int ProductID { get; set; }

		public string ProductCode { get; set; }

		public string ProductName { get; set; }

		public double EnergyAmount { get; set; }

		public double FatAmount { get; set; }

		public double SaturatedFatAmount { get; set; }

		public double CarbohydrateAmount { get; set; }

		public double SugarAmount { get; set; }

		public double FiberAmount { get; set; }

		public double ProteinAmount { get; set; }

		public double SaltAmount { get; set; }
	}
}
