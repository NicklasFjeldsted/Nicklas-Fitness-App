namespace FitnessWebApi.DTOs.PlanProgress
{
	public class DirectPlanProgressResponse
	{
		public int PlanProgressID { get; set; }

		public List<StaticProgressMealResponse> ProgressMeals { get; set; } = new List<StaticProgressMealResponse>();

		public DateTime ProgressDate { get; set; }

		public double CurrentWeight { get; set; }
	}
}
