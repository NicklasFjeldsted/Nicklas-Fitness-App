namespace FitnessWebApi._Repositories
{
	public interface IUserMealRepository
	{
		public Task<List<UserMeal>> GetAllById(int id);
		public Task<UserMeal> GetById(int id);
		public Task<UserMeal> Create(UserMeal request);
		public Task<UserMeal> Update(int id, UserMeal request);
		public Task<UserMeal> Delete(UserMeal sizedProduct);
	}

	public class UserMealRepository : IUserMealRepository
	{
		private readonly DatabaseContext _context;

		public UserMealRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<UserMeal>> GetAllById(int id)
		{
			return await _context.UserMeal
				.Include(x => x.User)
				.Include(x => x.MealTime)
				.Where(x => x.UserID == id)
				.ToListAsync();
		}

		public async Task<UserMeal> GetById(int id)
		{
			return await _context.UserMeal
				.Include(x => x.User)
				.Include(x => x.MealTime)
				.Include(x => x.SizedProducts)
				.Where(x => x.MealID == id)
				.FirstOrDefaultAsync();
		}

		public async Task<UserMeal> Create(UserMeal request)
		{
			_context.UserMeal.Add(request);
			await _context.SaveChangesAsync();
			return await GetById(request.UserID);
		}

		public async Task<UserMeal> Update(int id, UserMeal request)
		{
			UserMeal meal = await GetById(id);
			if(meal != null)
			{
				meal.MealName = request.MealName;
				meal.PortionAmount = request.PortionAmount;
				meal.TotalTime = request.TotalTime;
				meal.Instructions = request.Instructions;
				meal.MealTimeID = request.MealTimeID;
				meal.SizedProducts = request.SizedProducts;
				await _context.SaveChangesAsync();
			}

			return meal;
		}

		public async Task<UserMeal> Delete(UserMeal sizedProduct)
		{
			_context.UserMeal.Remove(sizedProduct);
			await _context.SaveChangesAsync();
			return sizedProduct;
		}
	}
}
