namespace FitnessWebApi.DTOs.User
{
	public class UserRequest
	{
		public string Email { get; set; } = string.Empty;

		public string Password { get; set; } = string.Empty;

		public string FirstName { get; set; } = string.Empty;

		public string LastName { get; set; } = string.Empty;

		public double Height { get; set; }

		public int UserPlanID { get; set; }

		public int GenderID { get; set; }

		public DateTime BirthdayDate { get; set; }
	}
}
