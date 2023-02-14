namespace FitnessWebApi.Database.Entities
{
	public class ProgressMealProduct
	{
		public int ProgressMealID { get; set; }
		public ProgressMeal ProgressMeal { get; set; }

		public int SizedProductID { get; set; }
		public SizedProduct SizedProduct { get; set; }
	}
}
