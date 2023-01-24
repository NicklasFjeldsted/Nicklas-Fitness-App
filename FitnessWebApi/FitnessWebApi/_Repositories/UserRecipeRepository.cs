namespace FitnessWebApi._Repositories
{
	public interface IUserRecipeRepository
	{
		public Task<List<UserRecipe>> GetAllById(int id);
		public Task<UserRecipe> GetById(int id);
		public Task<UserRecipe> Create(UserRecipe request);
		public Task<UserRecipe> Update(int id, UserRecipe request);
		public Task<UserRecipe> Delete(UserRecipe sizedProduct);
	}

	public class UserRecipeRepository : IUserRecipeRepository
	{
		private readonly DatabaseContext _context;

		public UserRecipeRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<UserRecipe>> GetAllById(int id)
		{
			return await _context.UserRecipe
				.Include(x => x.User)
				.Where(x => x.UserID == id)
				.ToListAsync();
		}

		public async Task<UserRecipe> GetById(int id)
		{
			return await _context.UserRecipe
				.Include(x => x.User)
				.Include(x => x.SizedProducts)
				.Where(x => x.RecipeID == id)
				.FirstOrDefaultAsync();
		}

		public async Task<UserRecipe> Create(UserRecipe request)
		{
			_context.UserRecipe.Add(request);
			await _context.SaveChangesAsync();
			return await GetById(request.UserID);
		}

		public async Task<UserRecipe> Update(int id, UserRecipe request)
		{
			UserRecipe meal = await GetById(id);
			if (meal != null)
			{
				meal.RecipeName = request.RecipeName;
				meal.PortionAmount = request.PortionAmount;
				meal.TotalTime = request.TotalTime;
				meal.Instructions = request.Instructions;
				meal.SizedProducts = request.SizedProducts;
				await _context.SaveChangesAsync();
			}

			return meal;
		}

		public async Task<UserRecipe> Delete(UserRecipe sizedProduct)
		{
			_context.UserRecipe.Remove(sizedProduct);
			await _context.SaveChangesAsync();
			return sizedProduct;
		}
	}
}
