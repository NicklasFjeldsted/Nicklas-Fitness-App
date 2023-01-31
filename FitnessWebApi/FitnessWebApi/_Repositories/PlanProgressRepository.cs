namespace FitnessWebApi._Repositories
{
	public interface IPlanProgressRepository
	{
		public Task<List<PlanProgress>> GetAll();
		public Task<PlanProgress> GetById(int id);
		public Task<PlanProgress> Create(PlanProgress request);
		public Task<PlanProgress> Update(int id, PlanProgress request);
		public Task<PlanProgress> Delete(PlanProgress product);
	}

	public class PlanProgressRepository : IPlanProgressRepository
	{
		private readonly DatabaseContext _context;

		public PlanProgressRepository(DatabaseContext context)
		{
			_context = context;
		}

		public async Task<List<PlanProgress>> GetAll()
		{
			return await _context.PlanProgress
				.ToListAsync();
		}

		public async Task<PlanProgress> GetById(int id)
		{
			return await _context.PlanProgress
				.Include(x => x.UserPlan)
				.Include(x => x.ProgressMeals)
				.ThenInclude(x => x.SizedProducts)
				.Include(x => x.ProgressMeals)
				.ThenInclude(x => x.MealTime)
				.Where(x => x.PlanProgressID == id)
				.FirstOrDefaultAsync();
		}

		public async Task<PlanProgress> Create(PlanProgress request)
		{
			_context.PlanProgress.Add(request);
			await _context.SaveChangesAsync();
			return await GetById(request.PlanProgressID);
		}

		public async Task<PlanProgress> Update(int id, PlanProgress request)
		{
			PlanProgress planProgress = await GetById(id);
			if(planProgress != null)
			{
				planProgress.ProgressMeals = request.ProgressMeals;
				planProgress.ProgressDate = request.ProgressDate;
				planProgress.CurrentWeight = request.CurrentWeight;

				await _context.SaveChangesAsync();
			}

			return planProgress;
		}

		public async Task<PlanProgress> Delete(PlanProgress planProgress)
		{
			_context.PlanProgress.Remove(planProgress);
			await _context.SaveChangesAsync();
			return planProgress;
		}
	}
}
