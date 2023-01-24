namespace FitnessWebApi.Database.Entities
{
	public class UserPlan
	{
		[Key]
		public int UserPlanID { get; set; }

		public double StartWeight { get; set; }

		public double CurrentWeight { get; set; }

		public double WeightGoal { get; set; }

		public int ActivityLevelID { get; set; }
		public ActivityLevel ActivityLevel { get; set; }
	}
}
