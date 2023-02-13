namespace FitnessWebApi.Database.Entities
{
	public class Product
	{
		[Key]
		public int ProductID { get; set; }

		[Column(TypeName = "nvarchar(96)")]
		public string ProductName { get; set; }

		[Column(TypeName = "nvarchar(96)")]
		public string ProductManufacturer { get; set; }

		[Column(TypeName = "nvarchar(32)")]
		public string ProductCode { get; set; }

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
