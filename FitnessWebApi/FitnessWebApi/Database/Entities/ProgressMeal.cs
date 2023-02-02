namespace FitnessWebApi.Database.Entities
{
	public class ProgressMeal
	{
		[Key]
		public int ProgressMealID { get; set; }

		public int MealTimeID { get; set; }
		public MealTime MealTime { get; set; }

		public ICollection<SizedProduct> SizedProducts { get; set; }

		public int PlanProgressID { get; set; }
		public PlanProgress PlanProgress { get; set; }
	}
}
