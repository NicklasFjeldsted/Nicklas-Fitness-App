namespace FitnessWebApi.Database.Entities
{
	public class UserPlan
	{
		[Key]
		public int UserPlanID { get; set; }

		public double StartWeight { get; set; }

		public DateTime StartDate { get; set; }

		public double WeightGoal { get; set; }

		public double WeeklyGoal { get; set; }

		public int ActivityLevelID { get; set; }
		public ActivityLevel ActivityLevel { get; set; }

		public ICollection<PlanProgress> PlanProgress { get; set; }

		public User User { get; set; }
	}
}
