namespace FitnessWebApi.Database.Entities
{
	public class PlanProgress
	{
		[Key]
		public int PlanProgressID { get; set; }

		public ICollection<ProgressMeal> ProgressMeals { get; set; }

		public DateTime ProgressDate { get; set; }

		public double? CurrentWeight { get; set; }

		public int UserPlanID { get; set; }
		public UserPlan UserPlan { get; set; }
	}
}
