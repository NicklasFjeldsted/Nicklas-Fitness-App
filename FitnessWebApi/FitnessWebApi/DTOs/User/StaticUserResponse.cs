namespace FitnessWebApi.DTOs.User
{
	public class StaticUserResponse
	{
		public int UserID { get; set; }

		public string Email { get; set; } = string.Empty;

		public string Password { get; set; } = string.Empty;

		public string FirstName { get; set; } = string.Empty;

		public string LastName { get; set; } = string.Empty;

		public double Height { get; set; }

		public int UserPlanID { get; set; }

		public int GenderID { get; set; }

		public DateTime BirthdayDate { get; set; }

		public DateTime Created_At { get; set; }

		public DateTime Modified_At { get; set; }

		public DateTime Last_Login { get; set; }
	}
}
