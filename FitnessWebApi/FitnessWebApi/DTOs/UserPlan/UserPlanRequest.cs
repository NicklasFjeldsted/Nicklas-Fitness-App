namespace FitnessWebApi.DTOs.UserPlan
{
	public class UserPlanRequest
	{
		public double StartWeight { get; set; }

		public DateTime StartDate { get; set; }

		public double WeightGoal { get; set; }

		public double WeeklyGoal { get; set; }

		public int ActivityLevelID { get; set; }
	}
}
