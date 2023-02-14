namespace FitnessWebApi.DTOs.PlanProgress
{
	public class PlanProgressRequest
	{
		public DateTime ProgressDate { get; set; }

		public List<ProgressMealRequest> ProgressMeals { get; set; } 

		public double CurrentWeight { get; set; }

		public int UserPlanID { get; set; }
	}
}
