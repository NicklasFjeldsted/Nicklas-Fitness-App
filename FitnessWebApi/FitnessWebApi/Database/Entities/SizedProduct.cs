namespace FitnessWebApi.Database.Entities
{
	public class SizedProduct
	{
		[Key]
		public int SizedProductID { get; set; }

		public double ServingSize { get; set; }

		public int UserID { get; set; }
		public User User { get; set; }

		public int ProductID { get; set; }
		public Product Product { get; set; }

		public ICollection<ProgressMealProduct> ProgressMealProducts { get; set; }
	}
}
