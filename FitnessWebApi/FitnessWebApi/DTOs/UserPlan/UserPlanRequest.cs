namespace FitnessWebApi.DTOs.UserPlan
{
	public class UserPlanRequest
	{
		public double StartWeight { get; set; }

		public double CurrentWeight { get; set; }

		public double WeightGoal { get; set; }

		public int ActivityLevelID { get; set; }
	}
}
