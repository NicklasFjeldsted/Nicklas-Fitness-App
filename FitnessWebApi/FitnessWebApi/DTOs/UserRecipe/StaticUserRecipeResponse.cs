namespace FitnessWebApi.DTOs.UserRecipe
{
	public class StaticUserRecipeResponse
	{
		public int RecipeID { get; set; }

		public int UserID { get; set; }

		public string RecipeName { get; set; }

		public double PortionAmount { get; set; }

		public DateTime TotalTime { get; set; }

		public string Instructions { get; set; }
	}
}
