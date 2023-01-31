namespace FitnessWebApi._Repositories
{
	public interface IProgressMealRepository
	{
		public Task<List<ProgressMeal>> GetAll();
		public Task<ProgressMeal> GetById(int id);
		public Task<ProgressMeal> Create(ProgressMeal request);
		public Task<ProgressMeal> Update(int id, ProgressMeal request);
		public Task<ProgressMeal> Delete(ProgressMeal product);
	}
	public class ProgressMealRepository : IProgressMealRepository
	{
		private readonly DatabaseContext _context;

		public ProgressMealRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<ProgressMeal>> GetAll()
		{
			return await _context.ProgressMeal
				.Include(x => x.SizedProducts)
				.ToListAsync();
		}

		public async Task<ProgressMeal> GetById(int id)
		{
			return await _context.ProgressMeal
				.Include(x => x.SizedProducts)
				.Include(x => x.MealTime)
				.Include(x => x.PlanProgress)
				.Where(x => x.ProgressMealID == id)
				.FirstOrDefaultAsync();

		}

		public async Task<ProgressMeal> Create(ProgressMeal request)
		{
			_context.ProgressMeal.Add(request);
			await _context.SaveChangesAsync();
			return await GetById(request.ProgressMealID);
		}

		public async Task<ProgressMeal> Update(int id, ProgressMeal request)
		{
			ProgressMeal progressMeal = await GetById(id);
			if(progressMeal != null)
			{
				progressMeal.SizedProducts = request.SizedProducts;
			}

			return progressMeal;
		}

		public async Task<ProgressMeal> Delete(ProgressMeal progressMeal)
		{
			_context.ProgressMeal.Remove(progressMeal);
			await _context.SaveChangesAsync();
			return progressMeal;
		}
	}
}
