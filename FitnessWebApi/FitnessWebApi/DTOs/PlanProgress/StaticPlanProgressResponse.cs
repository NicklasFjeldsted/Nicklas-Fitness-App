namespace FitnessWebApi.DTOs.PlanProgress
{
	public class StaticPlanProgressResponse
	{
		public int PlanProgressID { get; set; }

		public DateTime ProgressDate { get; set; }

		public double CurrentWeight { get; set; }

		public int UserPlanID { get; set; }
	}
}
