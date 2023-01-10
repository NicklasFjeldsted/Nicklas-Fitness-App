using System.ComponentModel.DataAnnotations.Schema;

namespace FitnessWebApi.Database.Entities
{
	public class User
	{
		[Key]
		public int UserID { get; set; }

		[Column(TypeName = "nvarchar(64)")]
		public string Email { get; set; } = string.Empty;

		[Column(TypeName = "nvarchar(255)")]
		public string Password { get; set; } = string.Empty;

		public string FirstName { get; set; } = string.Empty;

		public string LastName { get; set; } = string.Empty;

		public short Height { get; set; }

		public int GenderID { get; set; }
		public Gender Gender { get; set; }

		[Column(TypeName = "datetime2")]
		public DateTime BirthdayDate { get; set; }

		[Column(TypeName = "datetime2")]
		public DateTime Created_At { get; set; }

		[Column(TypeName = "datetime2")]
		public DateTime Modified_At { get; set; }

		[Column(TypeName = "datetime2")]
		public DateTime Last_Login { get; set; }
	}
}
