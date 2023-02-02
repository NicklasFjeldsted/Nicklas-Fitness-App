namespace FitnessWebApi.Database.Entities
{
	public class SizedProduct
	{
		[Key]
		public int SizedProductID { get; set; }

		public double ServingSize { get; set; }

		public int ProductID { get; set; }
		public Product Product { get; set; }

		public ProgressMeal ProgressMeal { get; set; }

		public UserRecipe UserRecipe { get; set; }

		public UserMeal UserMeal { get; set; }
	}
}
